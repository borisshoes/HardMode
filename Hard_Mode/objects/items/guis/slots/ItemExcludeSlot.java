package Hard_Mode.objects.items.guis.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemExcludeSlot extends Slot{

	Item excludeItem;
	
	public ItemExcludeSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, Item item) {
		super(inventoryIn, index, xPosition, yPosition);
		excludeItem = item;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(stack.getItem() == excludeItem) {
			return false;
		}else {
			return true;
		}
	}

}
