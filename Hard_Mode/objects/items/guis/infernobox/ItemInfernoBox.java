package Hard_Mode.objects.items.guis.infernobox;

import Hard_Mode.HardMode;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import Hard_Mode.util.handelers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInfernoBox extends Item implements IHasModel{
	private String itemName;
	
	private InventoryInfernoBox inventory = null;
	public int burnTime = 0;
	public int currentBurnTime = 0;
	public int cookTime = 0;
	public int totalCookTime = 400;
	
	public ItemInfernoBox(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		itemName = name;
		
		setMaxStackSize(1);//Sets the trinkets to not be stackable.
		setMaxDamage(400);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(this,0,"inventory");
		
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		inventory = new InventoryInfernoBox(stack, worldIn);
		setDamage(stack, 200);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			//playerIn.openGui(HardMode.instance, ConfigHandler.GUI_INFERNO_BOX, worldIn, 0, 0, 0);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return false;
    }
	
	//Furnace Stuff
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected){
		inventory = new InventoryInfernoBox(stack, worldIn);
		inventory.readFromNBT();
		burnTime = inventory.burnTime;
		currentBurnTime = inventory.currentBurnTime;
		cookTime = inventory.cookTime;
		totalCookTime = inventory.totalCookTime;
		
		
		boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!worldIn.isRemote)
        {
            ItemStack itemstack = inventory.getInventoryStack(1);

            if (this.isBurning() || !itemstack.isEmpty() && !inventory.getInventoryStack(0).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.burnTime = getItemBurnTime(itemstack);
                    this.currentBurnTime = this.burnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            //inventory.setInventoryStack(1, new ItemStack(itemstack.getItem(), itemstack.getCount()-1, itemstack.getItemDamage(), itemstack.getTagCompound()));
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                inventory.setInventoryStack(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if(cookTime > 0) {
                    	setDamage(stack, totalCookTime - cookTime);
                    }else{
                    	setDamage(stack, 0);
                    }
                    
                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(inventory.getInventoryStack(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                //BlockFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            inventory.markDirty();
        }
        
		inventory.burnTime = burnTime;
		inventory.currentBurnTime = currentBurnTime;
		inventory.cookTime = cookTime;
		inventory.totalCookTime = totalCookTime;
		inventory.writeToNBT();
		inventory.readFromNBT();
    }
	
	public int getItemBurnTime(ItemStack fuel) {
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

			//return ForgeEventFactory.getItemBurnTime(fuel);
			return 0;
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack itemstack = inventory.getInventoryStack(0);
			ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
			ItemStack itemstack2 = inventory.getInventoryStack(2);
			
			if(itemstack2.isEmpty()) {
				inventory.setInventoryStack(2, itemstack1.copy());
			}else if(itemstack2.getItem() == itemstack1.getItem()) {
				//inventory.setInventoryStack(1, new ItemStack(itemstack2.getItem(), itemstack2.getCount() + itemstack1.getCount(), itemstack2.getItemDamage(), itemstack2.getTagCompound()));
				itemstack2.grow(itemstack1.getCount());
			}
			
			if(itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !inventory.getInventoryStack(1).isEmpty() && inventory.getInventoryStack(1).getItem() == Items.BUCKET) {
				inventory.setInventoryStack(1, new ItemStack(Items.WATER_BUCKET));
			}
			
			//inventory.setInventoryStack(1, new ItemStack(itemstack.getItem(), itemstack.getCount()-1, itemstack.getItemDamage(), itemstack.getTagCompound()));
			itemstack.shrink(1);
			inventory.readFromNBT();						
		}
	}
	
	private boolean canSmelt() {
		if(inventory.getInventoryStack(0).isEmpty()) {
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(inventory.getInventoryStack(0));
			
			if(itemstack.isEmpty()) {
				return false;
			}else{
				ItemStack itemstack1 = inventory.getInventoryStack(2);
				
				if(itemstack1.isEmpty()) {
					return true;
				}else if(!itemstack1.isItemEqual(itemstack)){
					return false;
				}else if(itemstack1.getCount() + itemstack.getCount() <= 64 && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()){
					return true;
				}else{
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
				}
			}
		}
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(0) > 0;
	}
	
	public int getCookTime(ItemStack input) {
		return 400;
	}
	
	public int getField(int id){
        switch (id){
            case 0:
                return this.burnTime;
            case 1:
            	return this.currentBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }
}
