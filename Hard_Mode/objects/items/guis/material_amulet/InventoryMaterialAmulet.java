package Hard_Mode.objects.items.guis.material_amulet;

import java.util.ArrayList;
import java.util.Arrays;

import Hard_Mode.util.References;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class InventoryMaterialAmulet extends InventoryBasic implements IInventoryChangedListener{
	private final ItemStack stack;
	private ArrayList<ItemStack> exchange;
	private ItemStack exchangeItem = new ItemStack(Blocks.AIR);
	private int exchangeItemCount = exchangeItem.getCount();
	private boolean filling = false;

	public InventoryMaterialAmulet(ItemStack stack) {
		super("Amulet of Exchange", false, 25);
		this.stack = stack;
		
		this.addInventoryChangeListener(this);
	}

	@Override
	public void onInventoryChanged(IInventory invBasic) {
		if(!this.getStackInSlot(0).isEmpty() && !exchangeItem.isItemEqual(this.getStackInSlot(0)) || (exchangeItem.isItemEqual(this.getStackInSlot(0)) && exchangeItemCount != this.getStackInSlot(0).getCount())) {
			exchangeItem = this.getStackInSlot(0);
			exchangeItemCount = exchangeItem.getCount();
				
			exchange = MaterialAmuletRecipes.getInstance().getListFromItem(exchangeItem);
			
			filling = true;
			updateDisplay();
		}else if(this.getStackInSlot(0).isEmpty()) {
			this.clear();
			exchange = null;
			exchangeItem = new ItemStack(Blocks.AIR);
		}else if(!this.getStackInSlot(0).isEmpty() && !filling){
			exchangeItem = this.getStackInSlot(0);
			exchange = MaterialAmuletRecipes.getInstance().getListFromItem(exchangeItem);
			int size;
			int count = 0;
			if(exchange == null) {
				size = -100;
			}else{
				size = exchange.size();
			}
			for(int i = 1; i < 25; i++) {
				if(this.getStackInSlot(i).isEmpty()) {
					count++;
				}
			}
			//System.out.println(count +" "+ size);
			if(count > 24 - size) {
				//System.out.println("clearing");
				this.clear();
				exchange = null;
				exchangeItem = new ItemStack(Blocks.AIR);
			}
		}
		
		if(!this.getStackInSlot(0).isEmpty() && !filling) {
			int j = this.getStackInSlot(0).getCount();
			int discrepency = 0;
			int lowestCount = 0;
			for(int i = 1; i < 25; i++) {
				ItemStack stack = this.getStackInSlot(i);
				if(!stack.isEmpty()) {
					if(stack.getCount() < j) {
						discrepency++;
						lowestCount = stack.getCount();
					}
				}
			}
			//System.out.println(lowestCount + " " + discrepency);
			if(discrepency == 1) {
				this.getStackInSlot(0).setCount(lowestCount);
			}
		}		
	}
	
	private void updateDisplay() {
		if(exchangeItem != null && !exchangeItem.isEmpty() && exchange != null) {
			int i = 1;
			for(ItemStack entry : exchange) {
				ItemStack stack1 = new ItemStack(entry.getItem(), exchangeItem.getCount(), entry.getItemDamage());
				
				if(exchangeItem.hasTagCompound())
                {
					stack1.setTagCompound(exchangeItem.getTagCompound().copy());
                }
				
				this.setInventorySlotContents(i, stack1);
				i++;
				//System.out.print(entry.getDisplayName() + " | ");
			}
			filling = false;
			for(int j = i; j < 25; j++) {
				this.setInventorySlotContents(j, new ItemStack(Blocks.AIR));
			}
		}else if(exchange == null){
			for(int j = 1; j < 25; j++) {
				this.setInventorySlotContents(j, new ItemStack(Blocks.AIR));
			}
		}
		//System.out.println();
	}
	
}
