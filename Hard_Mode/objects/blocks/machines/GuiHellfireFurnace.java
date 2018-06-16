package Hard_Mode.objects.blocks.machines;

import Hard_Mode.util.References;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiHellfireFurnace extends GuiContainer{

	private static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID + ":textures/gui/hellfire_furnace.png");	
	private final InventoryPlayer player;
	private final TileEntityHellfireFurnace tileentity;
	
	
	public GuiHellfireFurnace(InventoryPlayer player, TileEntityHellfireFurnace tileentity) {
		super(new ContainerHellfireFurnace(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 4, 4210752);
		//this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityHellfireFurnace.isBurning(tileentity)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 12, this.guiTop + 24 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		if(TileEntityHellfireFurnace.isBoosted(tileentity)) {
			int k = this.getBoostLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 151, this.guiTop + 24 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		int l = this.getCookProgressScaled(18);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 17, 176, 14, l+0, 24);
	}

	private int getBoostLeftScaled(int pixels) {
		int i = this.tileentity.getField(4);
		if(i == 0) i = 200;
		return this.tileentity.getField(5) * pixels / i;
	}
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.tileentity.getField(1);
		if(i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
