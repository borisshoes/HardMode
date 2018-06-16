package Hard_Mode.objects.blocks.machines.slots;

import Hard_Mode.init.ItemInit;
import Hard_Mode.objects.blocks.machines.TileEntityHellfireFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHellfireFurnaceBooster extends Slot{

	public SlotHellfireFurnaceBooster(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		//return true;
		return stack.getItem() == ItemInit.DUST_VIOLENT || stack.getItem() == ItemInit.HELLCRYSTAL;
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
