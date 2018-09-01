package Hard_Mode.objects.items;

import Hard_Mode.HardMode;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import Hard_Mode.util.handelers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class ItemBase extends Item implements IHasModel{
	private String itemName;
	
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		itemName = name;
		
		if(itemName.equals("spatial_amulet")||itemName.equals("violent_amulet")||itemName.equals("material_amulet")||itemName.equals("curious_amulet")||itemName.equals("curious_ring")||itemName.equals("material_ring")||itemName.equals("violent_ring")||itemName.equals("spatial_ring")||itemName.equals("inferno_box")||itemName.equals("dirty_bag")||itemName.equals("tinker_tool")) {
			setMaxStackSize(1);//Sets the trinkets to not be stackable.
		}
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(this,0,"inventory");
		
	}

	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(itemName.equals("spatial_ring")) {
			if(!entityLiving.getEntityWorld().isRemote) {
				if(entityLiving instanceof EntityPlayerMP) {
					EntityPlayerMP entityplayermp = (EntityPlayerMP)entityLiving;
					
					RayTraceResult result = entityLiving.rayTrace(128.0, 1.0F);
					if(result.typeOfHit != Type.MISS) {
						BlockPos pos;
						if(result.typeOfHit == Type.BLOCK) {
							pos = result.getBlockPos();
						}else{
							pos = result.entityHit.getPosition();
						}
						if (entityplayermp.connection.getNetworkManager().isChannelOpen() && !entityplayermp.isPlayerSleeping()){
							EnderTeleportEvent event = new EnderTeleportEvent(entityLiving, pos.getX(), pos.getY(), pos.getZ(), 0.0F);
							if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)){ 
			                    if (entityLiving.isRiding()){
			                    	entityLiving.dismountRidingEntity();
			                    }
			                    entityLiving.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
		                    }
		                }					
					}								
				}						
			}
		}
		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
        if(itemName.equals("spatial_ring")) {
        	ItemStack itemstack = playerIn.getHeldItem(handIn);


            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERPEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote){
                EntityEnderPearl entityenderpearl = new EntityEnderPearl(worldIn, playerIn);
                entityenderpearl.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(entityenderpearl);
            }
            
            /*if (playerIn.getHealth() < playerIn.getMaxHealth())
            {
            	playerIn.heal(1.0F);
            }*/
            
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else if(itemName.equals("spatial_amulet")) {
        	ItemStack itemstack = playerIn.getHeldItem(handIn);
        	InventoryEnderChest inventoryenderchest = playerIn.getInventoryEnderChest();
            playerIn.displayGUIChest(inventoryenderchest);
            playerIn.addStat(StatList.ENDERCHEST_OPENED);
            
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else if(itemName.equals("tinker_tool")) {
        	ItemStack itemstack = playerIn.getHeldItem(handIn);
        	playerIn.openGui(HardMode.instance, ConfigHandler.GUI_TINKER_TOOL, worldIn, 0, 0, 0);
        	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else if(itemName.equals("dirty_bag")) {
        	ItemStack itemstack = playerIn.getHeldItem(handIn);
        	playerIn.openGui(HardMode.instance, ConfigHandler.GUI_DIRTY_BAG, worldIn, 0, 0, 0);
        	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else if(itemName.equals("material_amulet")) {
        	ItemStack itemstack = playerIn.getHeldItem(handIn);
        	playerIn.openGui(HardMode.instance, ConfigHandler.GUI_MATERIAL_AMULET, worldIn, 0, 0, 0);
        	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else{
        	ItemStack itemstack = playerIn.getHeldItem(handIn);
        	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
    }
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(!worldIn.isRemote) {
			if (entityIn instanceof EntityPlayer){
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                if(itemName.equals("violent_amulet")) {
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,10,1,false,false));
                }else if(itemName.equals("curious_amulet")) {
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,310,0,false,false));
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.SPEED,10,0,false,false));
                }else if(itemName.equals("violent_ring")) {
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,10,0,false,false));
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,10,0,false,false));
                }else if(itemName.equals("material_ring")) {
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.HASTE,10,1,false,false));
                }else if(itemName.equals("curious_ring")) {
                	entityplayer.addPotionEffect(new PotionEffect(MobEffects.SATURATION,10,0,false,false));
                }
            }

		}
    }
}
//new PotionEffect(Potion, duration, amplifier, cameFromBeacon, showParticles)
//player.addPotionEffect(PotionEffect)