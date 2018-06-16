package Hard_Mode.init;

import java.util.ArrayList;
import java.util.List;

import Hard_Mode.objects.blocks.BlockBase;
import Hard_Mode.objects.blocks.BlockForbiddenEnchantingTable;
import Hard_Mode.objects.blocks.BlockOres;
import Hard_Mode.objects.blocks.BlockScorchweedPlant;
import Hard_Mode.objects.blocks.machines.BlockHellfireFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Average Hardness is 2.0F for stone, 0.6F for dirt, 3.0F for wood | resistance is usually hardness * 5.0F
	//Harvest level is 0 (wood/gold) 1 (stone) 2 (iron) 3 (diamond) | Harvest Class is "pickaxe" "axe" etc
	//name, material, hardness, resistance, harvest level, harvest class, sound
	public static final Block FORBIDDEN_BOOKCASE = new BlockBase("forbidden_bookcase", Material.WOOD, 1.5F, 5.0F, 0, "axe", SoundType.WOOD);
	public static final Block INFUSED_OBSIDIAN = new BlockBase("infused_obsidian", Material.ROCK, 50.0F, 2000.0F, 3, "pickaxe", SoundType.STONE);
	
	public static final Block HELLISH_BRICK = new BlockBase("hellish_brick", Material.ROCK, 2.0F, 15.0F, 3, "pickaxe", SoundType.STONE);
	public static final Block HEAVENLY_BRICK = new BlockBase("heavenly_brick", Material.ROCK, 3.0F, 15.0F, 3, "pickaxe", SoundType.STONE);
	public static final Block TERRAN_BRICK = new BlockBase("terran_brick", Material.ROCK, 2.5F, 15.0F, 3, "pickaxe", SoundType.STONE);
	
	public static final Block FORBIDDEN_ENCHANTING_TABLE = new BlockForbiddenEnchantingTable("forbidden_enchanting_table", Material.ROCK, 50.0F, 2000.0F, 3, "pickaxe", SoundType.STONE);
	
	public static final Block ORE_INFINITY = new BlockOres("ore_infinity","end", 5.0F, 30.0F, 3, "pickaxe", SoundType.STONE);
	public static final Block ORE_RUNIC = new BlockOres("ore_runic","end", 5.0F, 30.0F, 3, "pickaxe", SoundType.STONE);
	public static final Block ORE_HELLCRYSTAL= new BlockOres("ore_hellcrystal","nether", 5.0F, 25.0F, 3, "pickaxe", SoundType.STONE);
	
	public static final Block HELLFIRE_FURNACE = new BlockHellfireFurnace("hellfire_furnace", Material.IRON, 2.5F, 35.0F, 3, "pickaxe", SoundType.METAL);
	
	public static final Block SCORCHWEED_PLANT = new BlockScorchweedPlant("scorchweed_plant");
	/*
	 * 
	 * 
	 */
}
