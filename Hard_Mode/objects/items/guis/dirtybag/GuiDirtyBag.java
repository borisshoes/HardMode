package Hard_Mode.objects.items.guis.dirtybag;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;

public class GuiDirtyBag extends GuiChest{

	public GuiDirtyBag(IInventory lowerInv) {
		super(Minecraft.getMinecraft().player.inventory, lowerInv);
		
	}
	
}
