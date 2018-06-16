package Hard_Mode.util.compat.jei.hellfire;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import Hard_Mode.objects.blocks.machines.HellfireFurnaceRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class HellfireRecipeMaker {
	public static List<HellfireRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		HellfireFurnaceRecipes instance = HellfireFurnaceRecipes.getInstance();
		Table<ItemStack,ItemStack,ItemStack> recipes = instance.getDualSmeltingList();
		List<HellfireRecipe> jeiRecipes = Lists.newArrayList();
		
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry: recipes.columnMap().entrySet()) {
			for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
				ItemStack input1 = entry.getKey();
				ItemStack input2 = ent.getKey();
				ItemStack output = ent.getValue();
				List<ItemStack> inputs = Lists.newArrayList(input1, input2);
				HellfireRecipe recipe = new HellfireRecipe(inputs, output);
				jeiRecipes.add(recipe);
			}
		}
		
		return jeiRecipes;
	}
}
