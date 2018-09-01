package Hard_Mode.enchantments;

import Hard_Mode.init.EnchantmentInit;
import Hard_Mode.util.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnnchantmentReckless extends Enchantment {
	public EnnchantmentReckless() {
		super(Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		
		this.setName("reckless");
		this.setRegistryName(new ResourceLocation(References.MODID+":reckless"));
		
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
    	return 5;
    }
    
    @Override
    protected boolean canApplyTogether(Enchantment ench) {
    	return super.canApplyTogether(ench) && ench != Enchantments.EFFICIENCY;
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
