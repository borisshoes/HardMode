package Hard_Mode.util.compat.jei.hellfire;

import java.awt.Color;
import java.util.List;

import Hard_Mode.objects.blocks.machines.HellfireFurnaceRecipes;
import Hard_Mode.util.compat.jei.JEICompat;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class HellfireRecipe implements IRecipeWrapper{
	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public HellfireRecipe(List<ItemStack> inputs, ItemStack output) {
		this.inputs = inputs;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		HellfireFurnaceRecipes recipes = HellfireFurnaceRecipes.getInstance();
		float experience = recipes.getHellfireExperience(output);
		
		if(experience > 0) {
			String experienceString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
			FontRenderer renderer = minecraft.fontRenderer;
			int stringWidth = renderer.getStringWidth(experienceString);
			renderer.drawString(experienceString, recipeWidth - stringWidth, 0, Color.GRAY.getRGB());
		}
	}
}