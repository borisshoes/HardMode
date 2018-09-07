package Hard_Mode.objects.blocks.machines;

import Hard_Mode.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityHellfireFurnace extends TileEntity implements IInventory, ITickable, ISidedInventory{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
	private String customName;
	
	private static final int[] SLOTS_TOP = new int[] {0, 1};
    private static final int[] SLOTS_BOTTOM = new int[] {3};
    private static final int[] SLOTS_SIDES = new int[] {2, 4};
    
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	private int boostTime;//current boost time
	private int totalBoostTime;//boost time per item
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.hellfire_furnace";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() { 
		for(ItemStack stack : this.inventory){
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index){
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if(index == 0 && index + 1 == 1 && !flag){
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.totalCookTime = this.getCookTime(stack, stack1);
			this.cookTime = 0;
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.inventory.get(2));
		this.boostTime = getBoostValue((ItemStack)this.inventory.get(4));
		this.totalBoostTime = compound.getInteger("TotalBoostTime");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setInteger("BoostTime", (short)this.boostTime);
		compound.setInteger("TotalBoostTime", (short)this.totalBoostTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	public boolean isBoosted() {
		return this.boostTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(0) > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBoosted(IInventory inventory) {
		return inventory.getField(4) > 0;
	}
	
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		if(this.isBurning()) --this.burnTime;
		if(this.isBoosted()) --this.boostTime;
		
		
		
		if(!this.world.isRemote) {
			ItemStack stack = (ItemStack)this.inventory.get(2);
			ItemStack booster = (ItemStack)this.inventory.get(4);
			
			if(this.isBurning()) {
				if(!isBoosted()) {
					if(!booster.isEmpty()) {
						Item item = booster.getItem();
						this.totalBoostTime = getBoostValue(booster);
						this.boostTime = totalBoostTime;
						booster.shrink(1);
						
						if(booster.isEmpty()) {
							ItemStack item1 = item.getContainerItem(stack);
							this.inventory.set(4, item1);
						}
					}
				}
			}
			
			if(this.isBurning() || !stack.isEmpty() && !((((ItemStack)this.inventory.get(0)).isEmpty()) || ((ItemStack)this.inventory.get(1)).isEmpty())) {
				if(!this.isBurning() && this.canSmelt()) {//Starts recipe
					this.burnTime = getItemBurnTime(stack);
					this.currentBurnTime = this.burnTime;
					
					if(this.isBurning()) {
						
						flag1 = true;
						
						if(!stack.isEmpty()) {
							Item item = stack.getItem();
							stack.shrink(1);
							
							if(stack.isEmpty()) {
								ItemStack item1 = item.getContainerItem(stack);
								this.inventory.set(2, item1);
							}
						}
					}
				} 
				if(this.isBurning() && this.canSmelt()) {
					if(isBoosted()) {
						this.cookTime += 2;
					}
					++this.cookTime;
					
					if(this.cookTime >= this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1));
						this.smeltItem();
						flag1 = true;
					}
				} 
				else this.cookTime = 0;
			} 
			else if(!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}
			if(flag != this.isBurning()) {
				flag1 = true;
				BlockHellfireFurnace.setState(this.isBurning(), this.world, this.pos);
			}
		} 
		if(flag1) this.markDirty();
	}
	
	public int getCookTime(ItemStack input1, ItemStack input2) {
		return 200;//How long it takes to cook one item in ticks
	}
	
	private boolean canSmelt() {
		if(((ItemStack)this.inventory.get(0)).isEmpty() || ((ItemStack)this.inventory.get(1)).isEmpty()) return false;
		else {
			ItemStack result = HellfireFurnaceRecipes.getInstance().getHellfireResult((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1));	
			if(result.isEmpty()) return false;
			else{
				ItemStack output = (ItemStack)this.inventory.get(3);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack input1 = (ItemStack)this.inventory.get(0);
			ItemStack input2 = (ItemStack)this.inventory.get(1);
			ItemStack result = HellfireFurnaceRecipes.getInstance().getHellfireResult(input1, input2);
			ItemStack output = (ItemStack)this.inventory.get(3);
			
			if(output.isEmpty()) this.inventory.set(3, result.copy());
			else if(output.getItem() == result.getItem()) output.grow(result.getCount());
			
			input1.shrink(1);
			input2.shrink(1);
		}
	}
	
	public static int getItemBurnTime(ItemStack fuel) {
		if(fuel.isEmpty()) return 0;
		else {
			Item item = fuel.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB) return 150;
				if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
				if (block == Blocks.COAL_BLOCK) return 16000;
			}

			if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
			if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
			if (item == Items.STICK) return 100;
			if (item == Items.COAL) return 1600;
			if (item == Items.LAVA_BUCKET) return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
			if (item == Items.BLAZE_ROD) return 2400;

			return ForgeEventFactory.getItemBurnTime(fuel);
		}
	}
		
	public static boolean isItemFuel(ItemStack fuel){
		return getItemBurnTime(fuel) > 0;
	}
	
	public static boolean isItemBooster(ItemStack booster) {
		return booster.getItem() == ItemInit.DUST_VIOLENT || booster.getItem() == ItemInit.HELLCRYSTAL;
	}
	
	public static int getBoostValue(ItemStack booster) {
		if(booster.getItem() == ItemInit.DUST_VIOLENT){
			return 1600;
		}else if(booster.getItem() == ItemInit.HELLCRYSTAL) {
			return 6400;
		}else{
			return 0;
		}
		
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		
		if(index == 3) {
			return false;
		}else if(index != 2) {
			return true;
		}else if(index==4) {
			return isItemBooster(stack) ? true : false;
		}else {
			return isItemFuel(stack);
		}
	}
	
	public int[] getSlotsForFace(EnumFacing side){
        if (side == EnumFacing.DOWN){
            return SLOTS_BOTTOM;
        }
        else{
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }
	
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction){
        return this.isItemValidForSlot(index, itemStackIn);
    }
	
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction){
        return true;
    }
	
	public String getGuiID() {
		return "hm:hellfire_furnace";
	}

	@Override
	public int getField(int id) {
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		case 4:
			return this.boostTime;
		case 5:
			return this.totalBoostTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		case 4:
			this.boostTime = value;
			break;
		case 5:
			this.totalBoostTime = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 6;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}



}
