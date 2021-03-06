package Hard_Mode.objects.blocks.machines.slots;

import Hard_Mode.init.ItemInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotForbiddenTableBooks extends Slot{

	public SlotForbiddenTableBooks(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		//return true;
		return stack.getItem() == ItemInit.FORBIDDEN_BOOK;
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}

}
