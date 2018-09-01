package Hard_Mode.objects.blocks.machines;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import Hard_Mode.init.BlockInit;
import Hard_Mode.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class HellfireFurnaceRecipes {
	private static final HellfireFurnaceRecipes INSTANCE = new HellfireFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static HellfireFurnaceRecipes getInstance(){
		return INSTANCE;
	}
	
	private HellfireFurnaceRecipes() {
	  //addHellfireRecipe(new ItemStack(), new ItemStack(), new ItemStack(), 5.0F);
		addHellfireRecipe(new ItemStack(ItemInit.SCORCHWEED_ITEM), new ItemStack(ItemInit.SCORCHWEED_ITEM), new ItemStack(ItemInit.FORBIDDEN_INK, 4), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.REDSTONE_ORE), new ItemStack(Blocks.REDSTONE_ORE), new ItemStack(Items.REDSTONE, 16), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.LAPIS_ORE), new ItemStack(Blocks.LAPIS_ORE), new ItemStack(Items.DYE, 16, 4), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.QUARTZ_ORE), new ItemStack(Blocks.QUARTZ_ORE), new ItemStack(Items.QUARTZ, 8), 5.0F);
		
		addHellfireRecipe(new ItemStack(BlockInit.ORE_RUNIC), new ItemStack(BlockInit.ORE_RUNIC), new ItemStack(ItemInit.RUNIC_GEMSTONE, 4), 5.0F);
		addHellfireRecipe(new ItemStack(BlockInit.ORE_HELLCRYSTAL), new ItemStack(BlockInit.ORE_HELLCRYSTAL), new ItemStack(ItemInit.HELLCRYSTAL, 4), 5.0F);
		addHellfireRecipe(new ItemStack(BlockInit.ORE_INFINITY), new ItemStack(BlockInit.ORE_INFINITY), new ItemStack(ItemInit.DUST_VIOLENT, 8), 5.0F);
		
		addHellfireRecipe(new ItemStack(ItemInit.FORBIDDEN_INK), new ItemStack(Items.QUARTZ), new ItemStack(ItemInit.INFUSED_QUARTZ, 2), 5.0F);
		addHellfireRecipe(new ItemStack(Items.QUARTZ), new ItemStack(ItemInit.FORBIDDEN_INK), new ItemStack(ItemInit.INFUSED_QUARTZ, 2), 5.0F);
		
		addHellfireRecipe(new ItemStack(ItemInit.INFUSED_QUARTZ), new ItemStack(Blocks.OBSIDIAN), new ItemStack(BlockInit.INFUSED_OBSIDIAN), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.OBSIDIAN), new ItemStack(ItemInit.INFUSED_QUARTZ), new ItemStack(BlockInit.INFUSED_OBSIDIAN), 5.0F);
		
		addHellfireRecipe(new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.PURPUR_BLOCK), new ItemStack(BlockInit.HEAVENLY_BRICK, 4), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.PURPUR_BLOCK), new ItemStack(Blocks.END_STONE), new ItemStack(BlockInit.HEAVENLY_BRICK, 4), 5.0F);
		
		addHellfireRecipe(new ItemStack(Blocks.NETHER_BRICK), new ItemStack(Blocks.NETHERRACK), new ItemStack(BlockInit.HELLISH_BRICK, 4), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.NETHER_BRICK), new ItemStack(BlockInit.HELLISH_BRICK, 4), 5.0F);
		
		addHellfireRecipe(new ItemStack(Blocks.STONEBRICK), new ItemStack(Blocks.STONE), new ItemStack(BlockInit.TERRAN_BRICK, 4), 5.0F);
		addHellfireRecipe(new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONEBRICK), new ItemStack(BlockInit.TERRAN_BRICK, 4), 5.0F);
		
		Map<ItemStack, ItemStack> normalRecipes = FurnaceRecipes.instance().getSmeltingList();
		
		for(Map.Entry<ItemStack, ItemStack> entry : normalRecipes.entrySet()) {
			if(getHellfireResult(entry.getKey(), entry.getKey()) != ItemStack.EMPTY && getHellfireResult(entry.getKey(), entry.getKey()) != new ItemStack(Blocks.AIR)) {
			}else {
				if(entry.getValue().getMaxStackSize() >= entry.getValue().getCount() * 4) {
					addHellfireRecipe(entry.getKey(), entry.getKey(), new ItemStack(entry.getValue().getItem(), entry.getValue().getCount() * 4, entry.getValue().getMetadata()), FurnaceRecipes.instance().getSmeltingExperience(entry.getKey()));
				}
			}
		}
		
	}

	
	public void addHellfireRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if(getHellfireResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getHellfireResult(ItemStack input1, ItemStack input2) {
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2){
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() {
		return this.smeltingList;
	}
	
	public float getHellfireExperience(ItemStack stack){
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
