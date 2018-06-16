package Hard_Mode.objects.blocks.machines.slots;

import Hard_Mode.objects.blocks.machines.TileEntityHellfireFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHellfireFurnaceFuel extends Slot{

	public SlotHellfireFurnaceFuel(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityHellfireFurnace.isItemFuel(stack);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
	
}
