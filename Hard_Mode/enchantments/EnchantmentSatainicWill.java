package Hard_Mode.enchantments;

import Hard_Mode.init.EnchantmentInit;
import Hard_Mode.util.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentSatainicWill extends Enchantment {
	public EnchantmentSatainicWill() {
		super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {EntityEquipmentSlot.CHEST,EntityEquipmentSlot.FEET,EntityEquipmentSlot.LEGS,EntityEquipmentSlot.HEAD});
		
		this.setName("satanicwill");
		this.setRegistryName(new ResourceLocation(References.MODID+":satanicwill"));
		
		EnchantmentInit.ENCHANTMENTS.add(this);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel * 25;    
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
    	return this.getMinEnchantability(enchantmentLevel) + 50;
    }
    
    @Override
    public int getMaxLevel() {
    	return 3;
    }
    
    @Override
    protected boolean canApplyTogether(Enchantment ench) {
    	return super.canApplyTogether(ench) && ench != Enchantments.PROTECTION && ench != Enchantments.BLAST_PROTECTION && ench != Enchantments.FIRE_PROTECTION && ench != Enchantments.PROJECTILE_PROTECTION;
    }
    
    @Override
    public boolean isTreasureEnchantment() {
    	return true;
    }
    
    @Override
    public boolean isAllowedOnBooks() {
    	return false;
    }
}
