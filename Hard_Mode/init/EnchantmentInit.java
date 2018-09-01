package Hard_Mode.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Hard_Mode.enchantments.EnchantmentDarkBargain;
import Hard_Mode.enchantments.EnchantmentLifesteal;
import Hard_Mode.enchantments.EnchantmentResilientSoul;
import Hard_Mode.enchantments.EnchantmentRocketBoots;
import Hard_Mode.enchantments.EnchantmentSatainicWill;
import Hard_Mode.enchantments.EnchantmentSiphoningArrows;
import Hard_Mode.enchantments.EnnchantmentReckless;
import Hard_Mode.util.References;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = References.MODID)
public class EnchantmentInit {
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	
	public static final Enchantment RESILIENT_SOUL = new EnchantmentResilientSoul();
	public static final Enchantment LIFESTEAL = new EnchantmentLifesteal();
	public static final Enchantment SIPHONING_ARROWS = new EnchantmentSiphoningArrows();
	public static final Enchantment DARK_BARGAIN = new EnchantmentDarkBargain();
	public static final Enchantment ROCKET_BOOTS = new EnchantmentRocketBoots();
	public static final Enchantment SATANIC_WILL = new EnchantmentSatainicWill();
	public static final Enchantment RECKLESS = new EnnchantmentReckless();
	
	
	@SubscribeEvent
	public static void livingUpdateFunction(LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		BlockPos pos = living.getPosition();
		World world = event.getEntity().world;		
		
		
		int levelResSoul = EnchantmentHelper.getMaxEnchantmentLevel(RESILIENT_SOUL, living);//Resilient Soul
		if(living.getHealth() < 4 && levelResSoul >= 1 && levelResSoul <= 3) { 
			living.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,10,0+(levelResSoul-1),false,false));
		}
		
		
		
		int levelRktBoots = EnchantmentHelper.getMaxEnchantmentLevel(ROCKET_BOOTS, living);//Rocket Boots
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			boolean jump = Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
			if(levelRktBoots == 1) {
				if(jump && !living.onGround) {
					Random rand = new Random();
				
					living.onGround = false;
					if(living.motionY <= 1) {
						living.addVelocity(0, 0.1, 0);
					}
				
					double xMot = (rand.nextDouble()*2-1)/25;
					double yMot = (rand.nextDouble()*2-1)/25;
					double zMot = (rand.nextDouble()*2-1)/25;
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, living.posX, living.posY, living.posZ, xMot, yMot, zMot, new int[0]);
					for(int i = 0; i < 5; i++) {
						xMot = (rand.nextDouble()*2-1)/15;
						yMot = (rand.nextDouble()*2-1)/15;
						zMot = (rand.nextDouble()*2-1)/15;
						world.spawnParticle(EnumParticleTypes.FLAME, living.posX, living.posY, living.posZ, xMot, yMot, zMot, new int[0]);
					}
				
					int chance = rand.nextInt(40);
					if(chance == 1){
						living.getItemStackFromSlot(EntityEquipmentSlot.FEET).damageItem(1, living);
					}
				}
				
			}
		}
		if(levelRktBoots == 1) {
			living.fallDistance = 0.1F;
		}
		
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void keyPressEvent(KeyInputEvent event) {
		EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
		BlockPos pos = player.getPosition();
		World world = player.world;		
		
		//player.fallDistance = 0.1F;
	}
	
	@SubscribeEvent
	public static void onLivingAttack(LivingAttackEvent event) {
		float amount = event.getAmount();
		Entity source = event.getSource().getTrueSource();
		
		
		if(source instanceof EntityLivingBase) {
			
			if(!source.isDead) {
				EntityLivingBase enemy = event.getEntityLiving();
				
				int levelLifeStl = EnchantmentHelper.getMaxEnchantmentLevel(LIFESTEAL, (EntityLivingBase)source);//Lifesteal
				if(levelLifeStl >= 1 && levelLifeStl <= 3) {
					float healAmt = (amount / 8) * levelLifeStl;
					((EntityLivingBase) source).heal(healAmt);
				}
				
				int levelSiphArrow = EnchantmentHelper.getMaxEnchantmentLevel(SIPHONING_ARROWS, (EntityLivingBase)source);//Siphoning Arrows
				if(levelSiphArrow >= 1 && levelSiphArrow <= 3) {
					float healAmt = (amount / 5) * levelSiphArrow;
					((EntityLivingBase) source).heal(healAmt);
				}
				
				int levelDarkBarg = EnchantmentHelper.getMaxEnchantmentLevel(DARK_BARGAIN, (EntityLivingBase)source);//Dark Bargain
				if(levelDarkBarg >= 1 && levelDarkBarg <= 3) {
					if(!enemy.isDead) {
						enemy.hurtResistantTime = 0;
						enemy.attackEntityFrom(new DamageSource("Dark Bargain").setDamageBypassesArmor(), 2 * levelDarkBarg);
						source.hurtResistantTime = 0;
						source.attackEntityFrom(new DamageSource("Dark Bargain").setDamageBypassesArmor(), 2);
					}
				}
				
				int levelSatWil = getTotalEnchantmentLevel(SATANIC_WILL, (EntityLivingBase)source);//Satanic Will
				if(levelSatWil >= 1 && levelSatWil <= 12) {
					if(!enemy.isDead) {
						enemy.hurtResistantTime = 0;
						enemy.attackEntityFrom(new DamageSource("Satanic Will").setDamageBypassesArmor(), amount * (levelSatWil / 12));
					}
				}
			}
			
			//System.out.println(event.getEntityLiving().getHealth());
		}
	}
	
	@SubscribeEvent
	public static void blockBreak(BreakEvent event) {
		EntityPlayer living = event.getPlayer();
		Random rand = new Random();
		
		int levelReck = EnchantmentHelper.getMaxEnchantmentLevel(RECKLESS, living);//Reckless
		if(levelReck >= 1 && levelReck <= 5) {
			if(rand.nextInt(levelReck + 1) > 0) {
				living.getHeldItem(EnumHand.MAIN_HAND).damageItem(1, living);
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public static void breakSpeed(BreakSpeed event) {
		float breakSpeed = event.getNewSpeed();
		EntityLivingBase living = event.getEntityLiving();
		
		
		IBlockState state = event.getState();
		Block target = state.getBlock();
		ItemStack held = living.getHeldItem(EnumHand.MAIN_HAND);
		boolean effective = false;
		if(held.getItem() instanceof ItemTool) {
			ItemTool tool = (ItemTool) held.getItem();
			if(tool.getDestroySpeed(held, state) != 1.0F) {
				effective = true;
			}
		}
		
		if(effective) {//Reckless
			int levelReck = EnchantmentHelper.getMaxEnchantmentLevel(RECKLESS, living);
			if(levelReck >= 1 && levelReck <= 5) {
				int efficiency = levelReck * 2;
				breakSpeed += (efficiency * efficiency) + 1;
			}
		}
		
		event.setNewSpeed(breakSpeed);
	}
	
	
	
	public static int getTotalEnchantmentLevel(Enchantment enchant, EntityLivingBase entity){
        Iterable<ItemStack> iterable = enchant.getEntityEquipment(entity);

        if (iterable == null){
            return 0;
        }else{
            int i = 0;

            for (ItemStack itemstack : iterable){
                int j = EnchantmentHelper.getEnchantmentLevel(enchant, itemstack);

                i+=j;
            }
            
            return i;
        }
    }
}
