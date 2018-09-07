package Hard_Mode.init;

import java.util.ArrayList;
import java.util.List;

import Hard_Mode.objects.armor.ArmorBase;
import Hard_Mode.objects.items.ItemBase;
import Hard_Mode.objects.items.crops.ItemScorchweedSeeds;
import Hard_Mode.objects.items.guis.dirtybag.ItemDirtyBag;
import Hard_Mode.objects.items.guis.infernobox.ItemInfernoBox;
import Hard_Mode.objects.tools.ToolAxe;
import Hard_Mode.objects.tools.ToolHoe;
import Hard_Mode.objects.tools.ToolPickaxe;
import Hard_Mode.objects.tools.ToolShovel;
import Hard_Mode.objects.tools.ToolSword;
import Hard_Mode.util.References;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Items
	public static final Item FORBIDDEN_INK = new ItemBase("forbidden_ink");
	public static final Item FORBIDDEN_BOOK = new ItemBase("forbidden_book");
	public static final Item INFUSED_QUARTZ = new ItemBase("infused_quartz");
	
	public static final Item DIRTY_BAG = new ItemDirtyBag("dirty_bag");
	public static final Item INFERNO_BOX = new ItemInfernoBox("inferno_box");
	public static final Item TINKER_TOOL = new ItemBase("tinker_tool");
	
	public static final Item RUNIC_ROD = new ItemBase("runic_rod");
	public static final Item OBSIDIAN_ROD = new ItemBase("obsidian_rod");
	public static final Item DIAMOND_ROD = new ItemBase("diamond_rod");
	
	public static final Item AMULET_CURIOUS = new ItemBase("curious_amulet");
	public static final Item AMULET_MATERIAL = new ItemBase("material_amulet");
	public static final Item AMULET_SPATIAL = new ItemBase("spatial_amulet");
	public static final Item AMULET_VIOLENT = new ItemBase("violent_amulet");
	
	public static final Item GEMSTONE_CURIOUS = new ItemBase("curious_gemstone");
	public static final Item GEMSTONE_MATERIAL = new ItemBase("material_gemstone");
	public static final Item GEMSTONE_SPATIAL = new ItemBase("spatial_gemstone");
	public static final Item GEMSTONE_VIOLENT = new ItemBase("violent_gemstone");
	
	public static final Item DUST_CURIOUS = new ItemBase("curious_dust");
	public static final Item DUST_MATERIAL = new ItemBase("material_dust");
	public static final Item DUST_SPATIAL = new ItemBase("spatial_dust");
	public static final Item DUST_VIOLENT = new ItemBase("violent_dust");
	
	public static final Item FRAGMENT_CURIOUS = new ItemBase("curious_fragment");
	public static final Item FRAGMENT_MATERIAL = new ItemBase("material_fragment");
	public static final Item FRAGMENT_SPATIAL = new ItemBase("spatial_fragment");
	public static final Item FRAGMENT_VIOLENT = new ItemBase("violent_fragment");
	
	public static final Item RING_CURIOUS = new ItemBase("curious_ring");
	public static final Item RING_MATERIAL = new ItemBase("material_ring");
	public static final Item RING_SPATIAL = new ItemBase("spatial_ring");
	public static final Item RING_VIOLENT = new ItemBase("violent_ring");
	
	public static final Item TREASURE_KEY = new ItemBase("treasure_key");
	
	public static final Item SCORCHWEED_ITEM = new ItemBase("scorchweed_item");
	public static final Item SCORCHWEED_SEEDS = new ItemScorchweedSeeds("scorchweed_seeds");
	
	public static final Item HELLCRYSTAL = new ItemBase("hellcrystal");
	public static final Item RUNIC_GEMSTONE = new ItemBase("runic_gemstone");
	
	
	
	//material                 name, harvest lvl, max durability, efficiency, damage, enchantability
	public static final ToolMaterial TOOL_HELLCRYSTAL = EnumHelper.addToolMaterial("tool_hellcrystal", 3, 750, 13.0F, 4.0F, 15).setRepairItem(new ItemStack(ItemInit.HELLCRYSTAL)); 
	public static final ToolMaterial TOOL_RUNIC = EnumHelper.addToolMaterial("tool_runic", 3,3000, 9.0F, 3.0F, 30).setRepairItem(new ItemStack(ItemInit.RUNIC_GEMSTONE));
	//                      name, texture name, durability, damage reduction amounts (boots, leg, chest, helm), enchantability, sound, toughness
	public static final ArmorMaterial ARMOR_HELLCRYSTAL = EnumHelper.addArmorMaterial("armor_hellcrystal", References.MODID + ":hellcrystal", 20, new int[]{5, 8, 10 ,5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F).setRepairItem(new ItemStack(ItemInit.HELLCRYSTAL));
	public static final ArmorMaterial ARMOR_RUNIC = EnumHelper.addArmorMaterial("armor_runic", References.MODID + ":runic", 45, new int[]{4, 7, 9 ,4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F).setRepairItem(new ItemStack(ItemInit.RUNIC_GEMSTONE));

	
	//Tools
	public static final Item HELLCRYSTAL_AXE = new ToolAxe("hellcrystal_axe", TOOL_HELLCRYSTAL, 9.0F, -3.0F);
	public static final Item HELLCRYSTAL_PICKAXE = new ToolPickaxe("hellcrystal_pickaxe", TOOL_HELLCRYSTAL);
	public static final Item HELLCRYSTAL_SHOVEL = new ToolShovel("hellcrystal_shovel", TOOL_HELLCRYSTAL);
	public static final Item HELLCRYSTAL_HOE = new ToolHoe("hellcrystal_hoe", TOOL_HELLCRYSTAL);
	public static final Item HELLCRYSTAL_SWORD = new ToolSword("hellcrystal_sword", TOOL_HELLCRYSTAL);
	
	public static final Item RUNIC_AXE = new ToolAxe("runic_axe", TOOL_RUNIC, 8.0F, -3.0F);
	public static final Item RUNIC_PICKAXE = new ToolPickaxe("runic_pickaxe", TOOL_RUNIC);
	public static final Item RUNIC_SHOVEL = new ToolShovel("runic_shovel", TOOL_RUNIC);
	public static final Item RUNIC_HOE = new ToolHoe("runic_hoe", TOOL_RUNIC);
	public static final Item RUNIC_SWORD = new ToolSword("runic_sword", TOOL_RUNIC);
	
	
	//Armor
	public static final Item HELLCRYSTAL_HELMET = new ArmorBase("hellcrystal_helmet", ARMOR_HELLCRYSTAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item HELLCRYSTAL_CHESTPLATE = new ArmorBase("hellcrystal_chestplate", ARMOR_HELLCRYSTAL, 1, EntityEquipmentSlot.CHEST);
	public static final Item HELLCRYSTAL_LEGGINGS = new ArmorBase("hellcrystal_leggings", ARMOR_HELLCRYSTAL, 2, EntityEquipmentSlot.LEGS);
	public static final Item HELLCRYSTAL_BOOTS = new ArmorBase("hellcrystal_boots", ARMOR_HELLCRYSTAL, 1, EntityEquipmentSlot.FEET);
	
	public static final Item RUNIC_HELMET = new ArmorBase("runic_helmet", ARMOR_RUNIC, 1, EntityEquipmentSlot.HEAD);
	public static final Item RUNIC_CHESTPLATE = new ArmorBase("runic_chestplate", ARMOR_RUNIC, 1, EntityEquipmentSlot.CHEST);
	public static final Item RUNIC_LEGGINGS = new ArmorBase("runic_leggings", ARMOR_RUNIC, 2, EntityEquipmentSlot.LEGS);
	public static final Item RUNIC_BOOTS = new ArmorBase("runic_boots", ARMOR_RUNIC, 1, EntityEquipmentSlot.FEET);
	
}
