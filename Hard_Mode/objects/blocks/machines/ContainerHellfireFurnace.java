package Hard_Mode.objects.blocks.machines;

import Hard_Mode.objects.blocks.machines.slots.SlotHellfireFurnaceFuel;
import Hard_Mode.objects.blocks.machines.slots.SlotHellfireFurnaceOutput;
import Hard_Mode.objects.blocks.machines.slots.SlotHellfireFurnaceBooster;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHellfireFurnace extends Container{

	private final TileEntityHellfireFurnace tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime, boostTime, totalBoostTime;
	
	public ContainerHellfireFurnace(InventoryPlayer player, TileEntityHellfireFurnace tileentity) {
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 52, 14));
		this.addSlotToContainer(new Slot(tileentity, 1, 108, 14));
		this.addSlotToContainer(new SlotHellfireFurnaceFuel(tileentity, 2, 11, 43));
		this.addSlotToContainer(new SlotHellfireFurnaceOutput(player.player, tileentity, 3, 80, 55));
		this.addSlotToContainer(new SlotHellfireFurnaceBooster(tileentity, 4, 149, 43));
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));//Calculates inventory slots (never changes from gui to gui)
			}
		}
		
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x*18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.burnTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentBurnTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalCookTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			if(this.boostTime != this.tileentity.getField(4)) listener.sendWindowProperty(this, 4, this.tileentity.getField(4));
			if(this.totalBoostTime != this.tileentity.getField(5)) listener.sendWindowProperty(this, 5, this.tileentity.getField(5));
		}
		
		this.cookTime = this.tileentity.getField(2);
		this.burnTime = this.tileentity.getField(0);
		this.currentBurnTime = this.tileentity.getField(1);
		this.totalCookTime = this.tileentity.getField(3);
		this.boostTime = this.tileentity.getField(4);
		this.totalBoostTime = this.tileentity.getField(5);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		/*Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 3) {
				if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 2 && index != 1 && index != 0) {		
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if(!HellfireFurnaceRecipes.getInstance().getHellfireResult(stack1, slot1.getStack()).isEmpty()){
					if(!this.mergeItemStack(stack1, 0, 2, false)) {
						return ItemStack.EMPTY;
					}
					else if(TileEntityHellfireFurnace.isItemFuel(stack1)){
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityHellfireFurnace.isItemFuel(stack1)){
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityHellfireFurnace.isItemFuel(stack1)){
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(index >= 4 && index < 31){
						if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false)){
						return ItemStack.EMPTY;
					}
				}
			} 
			else if(!this.mergeItemStack(stack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty()){
				slot.putStack(ItemStack.EMPTY);
			}
			else{
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
		*/
		return stack;
	}

}
