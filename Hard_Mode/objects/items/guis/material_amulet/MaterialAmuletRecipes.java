package Hard_Mode.objects.items.guis.material_amulet;

import java.util.ArrayList;
import java.util.Arrays;

import Hard_Mode.init.BlockInit;
import Hard_Mode.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MaterialAmuletRecipes {
	private static final MaterialAmuletRecipes INSTANCE = new MaterialAmuletRecipes();
	private final ArrayList<ArrayList<ItemStack>> recipes = new ArrayList<ArrayList<ItemStack>>();

	public static MaterialAmuletRecipes getInstance() {
		return INSTANCE;
	}
	
	private MaterialAmuletRecipes() {
		//this.addAmuletRecipe(new ArrayList<>(Arrays.asList()));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.GEMSTONE_CURIOUS), 
														   new ItemStack(ItemInit.GEMSTONE_MATERIAL),
														   new ItemStack(ItemInit.GEMSTONE_SPATIAL),
														   new ItemStack(ItemInit.GEMSTONE_VIOLENT))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.FRAGMENT_CURIOUS), 
														   new ItemStack(ItemInit.FRAGMENT_MATERIAL),
														   new ItemStack(ItemInit.FRAGMENT_SPATIAL),
														   new ItemStack(ItemInit.FRAGMENT_VIOLENT))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.DUST_CURIOUS), 
														   new ItemStack(ItemInit.DUST_MATERIAL),
														   new ItemStack(ItemInit.DUST_SPATIAL),
														   new ItemStack(ItemInit.DUST_VIOLENT))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(BlockInit.TERRAN_BRICK),
														   new ItemStack(BlockInit.HELLISH_BRICK),
														   new ItemStack(BlockInit.HEAVENLY_BRICK))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.STONE, 1, 0),
														   new ItemStack(Blocks.STONE, 1, 1),
														   new ItemStack(Blocks.STONE, 1, 3),
														   new ItemStack(Blocks.STONE, 1, 5),
														   new ItemStack(Blocks.COBBLESTONE),
														   new ItemStack(Blocks.SAND, 1, 0),
														   new ItemStack(Blocks.SAND, 1, 1),
														   new ItemStack(Blocks.GRAVEL),
														   new ItemStack(Blocks.DIRT, 1, 0),
														   new ItemStack(Blocks.DIRT, 1, 1),
														   new ItemStack(Blocks.GRASS),
														   new ItemStack(Blocks.MOSSY_COBBLESTONE))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Items.RECORD_11),
														   new ItemStack(Items.RECORD_13),
														   new ItemStack(Items.RECORD_BLOCKS),
														   new ItemStack(Items.RECORD_CAT),
														   new ItemStack(Items.RECORD_CHIRP),
														   new ItemStack(Items.RECORD_FAR),
														   new ItemStack(Items.RECORD_MALL),
														   new ItemStack(Items.RECORD_MELLOHI),
														   new ItemStack(Items.RECORD_STAL),
														   new ItemStack(Items.RECORD_STRAD),
														   new ItemStack(Items.RECORD_WAIT),
														   new ItemStack(Items.RECORD_WARD))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Items.SKULL, 1, 0),
														   new ItemStack(Items.SKULL, 1, 1),
														   new ItemStack(Items.SKULL, 1, 2),
														   new ItemStack(Items.SKULL, 1, 3),
														   new ItemStack(Items.SKULL, 1, 4))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.TALLGRASS, 1, 1),
														   new ItemStack(Blocks.TALLGRASS, 1, 2),
														   new ItemStack(Blocks.DEADBUSH),
														   new ItemStack(Blocks.YELLOW_FLOWER),
														   new ItemStack(Blocks.RED_FLOWER, 1, 0),
														   new ItemStack(Blocks.RED_FLOWER, 1, 1),
														   new ItemStack(Blocks.RED_FLOWER, 1, 2),
														   new ItemStack(Blocks.RED_FLOWER, 1, 3),
														   new ItemStack(Blocks.RED_FLOWER, 1, 4),
														   new ItemStack(Blocks.RED_FLOWER, 1, 5),
														   new ItemStack(Blocks.RED_FLOWER, 1, 6),
														   new ItemStack(Blocks.RED_FLOWER, 1, 7),
														   new ItemStack(Blocks.RED_FLOWER, 1, 8),
														   new ItemStack(Blocks.BROWN_MUSHROOM),
														   new ItemStack(Blocks.RED_MUSHROOM),
														   new ItemStack(Blocks.VINE),
														   new ItemStack(Blocks.WATERLILY),
														   new ItemStack(Blocks.DOUBLE_PLANT, 1, 0),
														   new ItemStack(Blocks.DOUBLE_PLANT, 1, 1),
														   new ItemStack(Blocks.DOUBLE_PLANT, 1, 2),
														   new ItemStack(Blocks.DOUBLE_PLANT, 1, 3),
														   new ItemStack(Blocks.DOUBLE_PLANT, 1, 4),
														   new ItemStack(Blocks.DOUBLE_PLANT, 1, 5))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.BRICK_BLOCK),
														   new ItemStack(Blocks.STONEBRICK, 1, 0),
														   new ItemStack(Blocks.STONEBRICK, 1, 1),
														   new ItemStack(Blocks.STONEBRICK, 1, 2),
														   new ItemStack(Blocks.STONEBRICK, 1, 3),
														   new ItemStack(Blocks.STONE, 1, 2),
														   new ItemStack(Blocks.STONE, 1, 4),
														   new ItemStack(Blocks.STONE, 1, 6),
														   new ItemStack(Blocks.SANDSTONE, 1, 0),
														   new ItemStack(Blocks.SANDSTONE, 1, 1),
														   new ItemStack(Blocks.SANDSTONE, 1, 2),
														   new ItemStack(Blocks.RED_SANDSTONE, 1, 0),
														   new ItemStack(Blocks.RED_SANDSTONE, 1, 1),
														   new ItemStack(Blocks.RED_SANDSTONE, 1, 2))));
		
		//--Colored items--
		ArrayList<ItemStack> wool = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			wool.add(new ItemStack(Blocks.WOOL, 1, i));
		}
		this.addAmuletRecipe(wool);
		
		ArrayList<ItemStack> glass = new ArrayList<>();
		glass.add(new ItemStack(Blocks.GLASS));
		for(int i = 0; i < 16; i++) {
			glass.add(new ItemStack(Blocks.STAINED_GLASS, 1, i));
		}
		this.addAmuletRecipe(glass);
		
		ArrayList<ItemStack> terracotta = new ArrayList<>();
		terracotta.add(new ItemStack(Blocks.HARDENED_CLAY));
		for(int i = 0; i < 16; i++) {
			terracotta.add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i));
		}
		this.addAmuletRecipe(terracotta);
		
		ArrayList<ItemStack> concreteDust = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
		concreteDust.add(new ItemStack(Blocks.CONCRETE_POWDER, 1, i));
		}
		this.addAmuletRecipe(concreteDust);
		
		ArrayList<ItemStack> concrete = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			concrete.add(new ItemStack(Blocks.CONCRETE, 1, i));
		}
		this.addAmuletRecipe(concrete);
		
		ArrayList<ItemStack> glassPane = new ArrayList<>();
		glassPane.add(new ItemStack(Blocks.GLASS_PANE));
		for(int i = 0; i < 16; i++) {
			glassPane.add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, i));
		}
		this.addAmuletRecipe(glassPane);
		
		ArrayList<ItemStack> carpet = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			carpet.add(new ItemStack(Blocks.CARPET, 1, i));
		}
		this.addAmuletRecipe(carpet);
		
		ArrayList<ItemStack> bed = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			bed.add(new ItemStack(Blocks.BED, 1, i));
		}
		this.addAmuletRecipe(bed);
		
		ArrayList<ItemStack> dye = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			dye.add(new ItemStack(Items.DYE, 1, i));
		}
		this.addAmuletRecipe(dye);
		
		ArrayList<ItemStack> banner = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			banner.add(new ItemStack(Items.BANNER, 1, i));
		}
		this.addAmuletRecipe(banner);
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.WHITE_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.ORANGE_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.MAGENTA_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.YELLOW_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.LIME_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.PINK_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.GRAY_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.SILVER_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.CYAN_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.PURPLE_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.BLUE_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.GREEN_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.RED_GLAZED_TERRACOTTA),
														   new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.WHITE_SHULKER_BOX),
														   new ItemStack(Blocks.ORANGE_SHULKER_BOX),
														   new ItemStack(Blocks.MAGENTA_SHULKER_BOX),
														   new ItemStack(Blocks.LIGHT_BLUE_SHULKER_BOX),
														   new ItemStack(Blocks.YELLOW_SHULKER_BOX),
														   new ItemStack(Blocks.LIME_SHULKER_BOX),
														   new ItemStack(Blocks.PINK_SHULKER_BOX),
														   new ItemStack(Blocks.GRAY_SHULKER_BOX),
														   new ItemStack(Blocks.SILVER_SHULKER_BOX),
														   new ItemStack(Blocks.CYAN_SHULKER_BOX),
														   new ItemStack(Blocks.PURPLE_SHULKER_BOX),
														   new ItemStack(Blocks.BLUE_SHULKER_BOX),
														   new ItemStack(Blocks.BROWN_SHULKER_BOX),
														   new ItemStack(Blocks.GREEN_SHULKER_BOX),
														   new ItemStack(Blocks.RED_SHULKER_BOX),
														   new ItemStack(Blocks.BLACK_SHULKER_BOX))));
		
		//Woods
		ArrayList<ItemStack> planks = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			planks.add(new ItemStack(Blocks.PLANKS, 1, i));
		}
		this.addAmuletRecipe(planks);
		
		ArrayList<ItemStack> slabs = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			slabs.add(new ItemStack(Blocks.WOODEN_SLAB, 1, i));
		}
		this.addAmuletRecipe(slabs);
		
		ArrayList<ItemStack> sapling = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			sapling.add(new ItemStack(Blocks.SAPLING, 1, i));
		}
		this.addAmuletRecipe(sapling);
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.LOG, 1, 0),
														   new ItemStack(Blocks.LOG, 1, 1),
														   new ItemStack(Blocks.LOG, 1, 2),
														   new ItemStack(Blocks.LOG, 1, 3),
														   new ItemStack(Blocks.LOG2, 1, 0),
														   new ItemStack(Blocks.LOG2, 1, 1))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.LEAVES, 1, 0),
														   new ItemStack(Blocks.LEAVES, 1, 1),
														   new ItemStack(Blocks.LEAVES, 1, 2),
														   new ItemStack(Blocks.LEAVES, 1, 3),
														   new ItemStack(Blocks.LEAVES2, 1, 0),
														   new ItemStack(Blocks.LEAVES2, 1, 1))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.OAK_STAIRS),
														   new ItemStack(Blocks.SPRUCE_STAIRS),
														   new ItemStack(Blocks.BIRCH_STAIRS),
														   new ItemStack(Blocks.JUNGLE_STAIRS),
														   new ItemStack(Blocks.ACACIA_STAIRS),
														   new ItemStack(Blocks.DARK_OAK_STAIRS))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.OAK_FENCE),
														   new ItemStack(Blocks.SPRUCE_FENCE),
														   new ItemStack(Blocks.BIRCH_FENCE),
														   new ItemStack(Blocks.JUNGLE_FENCE),
														   new ItemStack(Blocks.ACACIA_FENCE),
														   new ItemStack(Blocks.DARK_OAK_FENCE))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Blocks.OAK_FENCE_GATE),
														   new ItemStack(Blocks.SPRUCE_FENCE_GATE),
														   new ItemStack(Blocks.BIRCH_FENCE_GATE),
														   new ItemStack(Blocks.JUNGLE_FENCE_GATE),
														   new ItemStack(Blocks.ACACIA_FENCE_GATE),
														   new ItemStack(Blocks.DARK_OAK_FENCE_GATE))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Items.OAK_DOOR),
														   new ItemStack(Items.SPRUCE_DOOR),
														   new ItemStack(Items.BIRCH_DOOR),
														   new ItemStack(Items.JUNGLE_DOOR),
														   new ItemStack(Items.ACACIA_DOOR),
														   new ItemStack(Items.DARK_OAK_DOOR))));
		
		this.addAmuletRecipe(new ArrayList<>(Arrays.asList(new ItemStack(Items.BOAT),
														   new ItemStack(Items.SPRUCE_BOAT),
														   new ItemStack(Items.BIRCH_BOAT),
														   new ItemStack(Items.JUNGLE_BOAT),
														   new ItemStack(Items.ACACIA_BOAT),
														   new ItemStack(Items.DARK_OAK_BOAT))));
	}
	
	private void addAmuletRecipe(ArrayList<ItemStack> list) {
		this.recipes.add(list);
	}
	
	public ArrayList<ItemStack> getListFromItem(ItemStack item){
		for(ArrayList<ItemStack> entry : recipes) {
			for(ItemStack ent : entry) {
				if(item.isItemEqual(ent)) {
					return entry;
				}
			}
		}
		
		return null;
	}
	
}
