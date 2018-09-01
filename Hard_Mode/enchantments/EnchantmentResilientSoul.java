package Hard_Mode.enchantments;

import Hard_Mode.init.EnchantmentInit;
import Hard_Mode.util.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentResilientSoul extends Enchantment {

	public EnchantmentResilientSoul() {
		super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[] {EntityEquipmentSlot.CHEST});
		
		this.setName("resilient_soul");
		this.setRegistryName(new ResourceLocation(References.MODID+":resilient_soul"));
		
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
    	return super.canApplyTogether(ench);
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
