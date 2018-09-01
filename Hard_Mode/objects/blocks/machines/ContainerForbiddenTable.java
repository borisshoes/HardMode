package Hard_Mode.objects.blocks.machines;

import Hard_Mode.objects.blocks.machines.slots.SlotForbiddenTableBooks;
import Hard_Mode.objects.blocks.machines.slots.SlotForbiddenTableFoci;
import Hard_Mode.objects.blocks.machines.slots.SlotForbiddenTableQuartz;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerForbiddenTable extends Container{
	private final TileEntityForbiddenTable tileentity;
	private int enchTime, totalEnchTime;
	
	public ContainerForbiddenTable(InventoryPlayer player, TileEntityForbiddenTable tileentity) {
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new SlotForbiddenTableFoci(tileentity, 0, 26, 6));
		this.addSlotToContainer(new SlotForbiddenTableFoci(tileentity, 1, 80, 6));
		this.addSlotToContainer(new SlotForbiddenTableFoci(tileentity, 2, 134, 6));
		this.addSlotToContainer(new SlotForbiddenTableQuartz(tileentity, 3, 53, 29));
		this.addSlotToContainer(new SlotForbiddenTableBooks(tileentity, 4, 107, 29));
		this.addSlotToContainer(new Slot(tileentity, 5, 80, 56));
		
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
			
			if(this.enchTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.totalEnchTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
		}
		
		this.enchTime = this.tileentity.getField(0);
		this.totalEnchTime = this.tileentity.getField(1);
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
		return stack;
	}
}
