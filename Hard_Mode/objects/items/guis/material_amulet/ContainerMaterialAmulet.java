package Hard_Mode.objects.items.guis.material_amulet;

import Hard_Mode.objects.items.guis.slots.TakeOnlySlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMaterialAmulet extends Container{

	private World worldObj;
	private InventoryMaterialAmulet inventory;
	
	public ContainerMaterialAmulet(InventoryPlayer player, InventoryMaterialAmulet inventory, World world, int xPos, int yPos, int zPos) {
		this.worldObj = world;
		this.inventory = inventory;
		
		this.addSlotToContainer(new Slot(inventory, 0, 80, 11));
		
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				int index = x + y*9 + 1;
				if(index == 4 || index == 5 || index == 6) {
					
				}else if(index >= 7) {
					this.addSlotToContainer(new TakeOnlySlot(player.player, inventory, index - 3, 8 + x * 18, 17 + y * 18));
				}else {
					this.addSlotToContainer(new TakeOnlySlot(player.player, inventory, index, 8 + x * 18, 17 + y * 18));
				}
				
			}
		}
		
		
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));//Calculates inventory slots (never changes from gui to gui)
			}
		}
		
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x*18, 142));
		}
	}
	
	
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		if(!this.worldObj.isRemote) {
			ItemStack itemstack = inventory.getStackInSlot(0);
			if(itemstack != null) {
				playerIn.dropItem(itemstack, false);
			}
		}
	}
	
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
        return itemstack;
	}

}
