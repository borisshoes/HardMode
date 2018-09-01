package Hard_Mode.objects.items.guis.dirtybag;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

public class ContainerDirtyBag extends ContainerChest{

	public ContainerDirtyBag(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
		super(playerInventory, chestInventory, player);
		
	}
	
}
