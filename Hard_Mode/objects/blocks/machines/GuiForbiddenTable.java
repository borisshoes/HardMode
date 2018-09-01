package Hard_Mode.objects.blocks.machines;

import Hard_Mode.util.References;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiForbiddenTable extends GuiContainer{
	private static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID + ":textures/gui/forbidden_enchanting_table.png");
	private final InventoryPlayer player;
	private final TileEntityForbiddenTable tileentity;
	
	public GuiForbiddenTable(InventoryPlayer player, TileEntityForbiddenTable tileentity) {
		super(new ContainerForbiddenTable(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString("Forbidden", (this.xSize - this.fontRenderer.getStringWidth("Forbidden") - 5), this.ySize - 96 + 2 - 14 - 6, 4210752);
		this.fontRenderer.drawString("Enchanting", (this.xSize - this.fontRenderer.getStringWidth("Enchanting") - 5), this.ySize - 96 + 2 - 7 - 3, 4210752);
		this.fontRenderer.drawString("Table", (this.xSize - this.fontRenderer.getStringWidth("Table") - 5), this.ySize - 96 + 2, 4210752);
		//this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 4, 4210752);		
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int totalPixels = this.getEnchProgressScaled(40); //First 10 pixels are L and R only, last 30 are L,R and C
		int rlPixels;
		int cPixels;
		if(totalPixels > 17) {
			rlPixels = 17;
		}else{
			rlPixels = totalPixels;
		}
		if(totalPixels < 10) {
			cPixels = 0;
		}else {
			cPixels = totalPixels - 10;
		}
		
		this.drawTexturedModalRect(this.guiLeft + 42, this.guiTop + 12, 0, 166, 38, 0 + rlPixels);//Left Progress
		this.drawTexturedModalRect(this.guiLeft + 96, this.guiTop + 12, 76, 166, 38, 0 + rlPixels);//Right Progress
		this.drawTexturedModalRect(this.guiLeft + 69, this.guiTop + 22, 38, 166, 38, 0 + cPixels);//Center Progress
	}
	
	private int getEnchProgressScaled(int pixels) {
		int totalEnchTime = this.tileentity.getField(1);
		int enchTime = this.tileentity.getField(0);
		
		return totalEnchTime != 0 && enchTime != 0 ? enchTime * pixels / totalEnchTime : 0;
	}
}
