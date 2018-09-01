package Hard_Mode.objects.blocks.machines.enchantmentRunes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import Hard_Mode.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ForbiddenEnchantRecipes {
	private static final ForbiddenEnchantRecipes INSTANCE = new ForbiddenEnchantRecipes();
	private final Map<RuneCombo, Enchantment> enchantList = Maps.<RuneCombo, Enchantment>newHashMap();
	
	public static ForbiddenEnchantRecipes getInstance() {
		return INSTANCE;
	}
	
	private ForbiddenEnchantRecipes() {
		//addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType, EnumRuneType, EnumRuneType), Enchantment.getEnchantmentByLocation(""));
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.MATERIAL, EnumRuneType.CURIOUS), Enchantments.AQUA_AFFINITY);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.MATERIAL, EnumRuneType.CURIOUS), Enchantments.BANE_OF_ARTHROPODS);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.VIOLENT, EnumRuneType.MATERIAL), Enchantments.BLAST_PROTECTION);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.VIOLENT, EnumRuneType.CURIOUS), Enchantments.BINDING_CURSE);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.CURIOUS, EnumRuneType.VIOLENT), Enchantments.VANISHING_CURSE);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.SPATIAL, EnumRuneType.CURIOUS), Enchantments.DEPTH_STRIDER);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.MATERIAL, EnumRuneType.MATERIAL), Enchantments.EFFICIENCY);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.CURIOUS, EnumRuneType.SPATIAL, EnumRuneType.CURIOUS), Enchantments.FEATHER_FALLING);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.VIOLENT, EnumRuneType.MATERIAL), Enchantments.FIRE_ASPECT);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.MATERIAL, EnumRuneType.SPATIAL), Enchantments.FIRE_PROTECTION);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.MATERIAL, EnumRuneType.VIOLENT), Enchantments.FLAME);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.CURIOUS, EnumRuneType.MATERIAL), Enchantments.FORTUNE);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.CURIOUS, EnumRuneType.CURIOUS, EnumRuneType.CURIOUS), Enchantments.FROST_WALKER);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.SPATIAL, EnumRuneType.SPATIAL), Enchantments.INFINITY);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.VIOLENT, EnumRuneType.SPATIAL), Enchantments.KNOCKBACK);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.CURIOUS, EnumRuneType.MATERIAL), Enchantments.LOOTING);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.SPATIAL, EnumRuneType.CURIOUS), Enchantments.LUCK_OF_THE_SEA);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.CURIOUS, EnumRuneType.SPATIAL, EnumRuneType.MATERIAL), Enchantments.LURE);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.MATERIAL, EnumRuneType.SPATIAL), Enchantments.MENDING);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.VIOLENT, EnumRuneType.VIOLENT), Enchantments.POWER);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.SPATIAL, EnumRuneType.VIOLENT), Enchantments.PROJECTILE_PROTECTION);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.MATERIAL, EnumRuneType.VIOLENT), Enchantments.PROTECTION);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.SPATIAL, EnumRuneType.VIOLENT), Enchantments.PUNCH);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.CURIOUS, EnumRuneType.CURIOUS, EnumRuneType.SPATIAL), Enchantments.RESPIRATION);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.VIOLENT, EnumRuneType.VIOLENT), Enchantments.SHARPNESS);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.MATERIAL, EnumRuneType.MATERIAL), Enchantments.SILK_TOUCH);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.CURIOUS, EnumRuneType.MATERIAL, EnumRuneType.VIOLENT), Enchantments.SMITE);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.CURIOUS, EnumRuneType.SPATIAL), Enchantments.SWEEPING);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.MATERIAL, EnumRuneType.VIOLENT), Enchantments.THORNS);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.SPATIAL, EnumRuneType.MATERIAL), Enchantments.UNBREAKING);
		
		//addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType, EnumRuneType, EnumRuneType), EnchantmentInit);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.SPATIAL, EnumRuneType.VIOLENT), EnchantmentInit.DARK_BARGAIN);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.SPATIAL, EnumRuneType.VIOLENT, EnumRuneType.VIOLENT), EnchantmentInit.LIFESTEAL);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.VIOLENT, EnumRuneType.MATERIAL), EnchantmentInit.RECKLESS);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.SPATIAL, EnumRuneType.MATERIAL), EnchantmentInit.RESILIENT_SOUL);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.CURIOUS, EnumRuneType.VIOLENT, EnumRuneType.CURIOUS), EnchantmentInit.ROCKET_BOOTS);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.MATERIAL, EnumRuneType.VIOLENT, EnumRuneType.SPATIAL), EnchantmentInit.SATANIC_WILL);
		this.addForbiddenEnchantRecipe(new RuneCombo(EnumRuneType.VIOLENT, EnumRuneType.VIOLENT, EnumRuneType.SPATIAL), EnchantmentInit.SIPHONING_ARROWS);
	}
	
	public void addForbiddenEnchantRecipe(RuneCombo combo, Enchantment enchant) {
		if(getForbiddenResult(combo) != null) return;
		this.enchantList.put(combo, enchant);
	}
	
	public Enchantment getForbiddenResult(RuneCombo combo) {
		for(Entry<RuneCombo, Enchantment> entry : this.enchantList.entrySet()) {
			if(RuneCombo.runeToString(combo).equals(RuneCombo.runeToString(entry.getKey()))) {
				return entry.getValue();
			}
		}

		return null;
	}
	
	public int getQuartzCountPerLvl(Enchantment enchant) {
		int maxLvl = enchant.getMaxLevel();
		int weight = enchant.getRarity().getWeight();
		
		if(weight == 10) {
			weight = 1;
		}
		
		return Math.round(64 / weight / maxLvl);
	}
	
	public int getBookCountForItem(ItemStack stack) {
		int totalLevels = 0;
		NBTTagList enchantments = stack.getEnchantmentTagList();
		for(int i = 0; i < enchantments.tagCount(); i++) {
			totalLevels += enchantments.getCompoundTagAt(i).getShort("lvl");
		}
		return totalLevels * 3 + 3;
	}
}
