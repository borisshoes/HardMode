package Hard_Mode.objects.items.guis.infernobox;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InventoryInfernoBox extends InventoryBasic implements IInventory, ITickable{

	private final ItemStack stack;//This item
	private final ItemInfernoBox item;
	public int burnTime = 0;
	public int currentBurnTime = 0;
	public int cookTime = 0;
	public int totalCookTime = 400;
	private World world;
	
	public InventoryInfernoBox(ItemStack stack, World world) {
		super("Inferno Box", false, 3);
		this.stack = stack;
		this.world = world;
		cookTime = stack.getItemDamage();
		this.item = (ItemInfernoBox) stack.getItem();
		readFromNBT();
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		readFromNBT();
		super.openInventory(player);
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		burnTime = item.burnTime;
		currentBurnTime = item.currentBurnTime;
		cookTime = item.cookTime;
		totalCookTime = item.totalCookTime;
		writeToNBT();
		super.closeInventory(player);
	}
	
	@Override
	public void markDirty() {
		burnTime = item.burnTime;
		currentBurnTime = item.currentBurnTime;
		cookTime = item.cookTime;
		totalCookTime = item.totalCookTime;
		writeToNBT();
		super.markDirty();
	}

	public void writeToNBT() {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		
		NBTTagList list = new NBTTagList();
		NBTTagList list1 = new NBTTagList();
		
		for(int i=0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			
			if(stack != null) {
				NBTTagCompound slotTag = new NBTTagCompound();
				slotTag.setInteger("slotIndex", i);
				stack.writeToNBT(slotTag);
				list.appendTag(slotTag);
			}
		}
		
		NBTTagCompound burnVal1 = new NBTTagCompound();
		burnVal1.setInteger("burnTime",burnTime);
		NBTTagCompound burnVal2 = new NBTTagCompound();
		burnVal2.setInteger("currentBurnTime",currentBurnTime);
		NBTTagCompound burnVal3 = new NBTTagCompound();
		burnVal3.setInteger("cookTime",cookTime);
		NBTTagCompound burnVal4 = new NBTTagCompound();
		burnVal4.setInteger("totalCookTime",totalCookTime);
		list1.appendTag(burnVal1);
		list1.appendTag(burnVal2);
		list1.appendTag(burnVal3);
		list1.appendTag(burnVal4);
		
		tag.setTag("contents", list);
		tag.setTag("burnValues", list1);
	}
	
	public void readFromNBT() {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null) {
			writeToNBT();
			return;
		}
		
		NBTTagList list = tag.getTagList("contents", Constants.NBT.TAG_COMPOUND);
		NBTTagList list1 = tag.getTagList("burnValues", Constants.NBT.TAG_COMPOUND);
		
		if(list == null || list1 == null) {
			writeToNBT();
			return;
		}
		
		for(int i=0; i < list.tagCount(); i++) {
			NBTTagCompound slotTag = list.getCompoundTagAt(i);
			int index = slotTag.getInteger("slotIndex");
			ItemStack stack = new ItemStack(slotTag);
			setInventorySlotContents(index, stack);
		}
		
		NBTTagCompound burnVal1 = list1.getCompoundTagAt(0);
		NBTTagCompound burnVal2 = list1.getCompoundTagAt(1);
		NBTTagCompound burnVal3 = list1.getCompoundTagAt(2);
		NBTTagCompound burnVal4 = list1.getCompoundTagAt(3);
		burnTime = burnVal1.getInteger("burnTime");
		currentBurnTime = burnVal2.getInteger("currentBurnTime");
		cookTime = burnVal3.getInteger("cookTime");
		totalCookTime = burnVal4.getInteger("totalCookTime");
	}
	
	public ItemStack getInventoryStack(int slotNum) {
		return getStackInSlot(slotNum);
	}
	
	public void setInventoryStack(int slotNum, ItemStack stack) {
		setInventorySlotContents(slotNum, stack);
		writeToNBT();
	}
		
	@Override
	public int getField(int id){
        return item.getField(id);
    }

	@Override
	public void update() {
		readFromNBT();
		burnTime = item.burnTime;
		currentBurnTime = item.currentBurnTime;
		cookTime = item.cookTime;
		totalCookTime = item.totalCookTime;
	}
}