package Hard_Mode.objects.items.guis.material_amulet;

import org.lwjgl.opengl.GL11;

import Hard_Mode.util.References;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiMaterialAmulet extends GuiContainer{
	private static final ResourceLocation guiTexture = new ResourceLocation(References.MODID + ":textures/gui/amulet_of_exchange.png");
	private final InventoryPlayer inventory;

	public GuiMaterialAmulet(InventoryPlayer inventory, InventoryMaterialAmulet itemInventory, World world, int x, int y, int z) {
		super(new ContainerMaterialAmulet(inventory, itemInventory, world, x, y, z));
    	this.inventory = inventory;
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString("Amulet of", 61 - fontRenderer.getStringWidth("Amulet of"), 6, 4210752);
        this.fontRenderer.drawString("Exchange", 115, 6, 4210752);
		this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
	}	
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
