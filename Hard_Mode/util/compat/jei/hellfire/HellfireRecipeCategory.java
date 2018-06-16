package Hard_Mode.util.compat.jei.hellfire;

import Hard_Mode.util.References;
import Hard_Mode.util.compat.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import mezz.jei.api.recipe.IRecipeWrapper;


public class HellfireRecipeCategory extends AbstractHellfireRecipeCategory<HellfireRecipe>{
	private final IDrawable background;
	private final String name;
	
	public HellfireRecipeCategory(IGuiHelper helper) {
		super(helper);
		
		background = helper.createDrawable(TEXTURES, 4, 12, 150, 60);
		name = "Hellfire Furnace";
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		animatedFlame.draw(minecraft, 7, 10);
		animatedArrow.draw(minecraft, 75, 5);
	}
	
	@Override
	public String getTitle() {
		return name;
	}
	
	@Override
	public String getModName() {
		return References.NAME;
	}
	
	@Override
	public String getUid() {
		return RecipeCategories.HELLFIRE;
	}
	
	public void setRecipe(IRecipeLayout recipeLayout, HellfireRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 47, 1);
		stacks.init(input2, true, 103, 1);
		stacks.init(output, false, 75, 42);
		stacks.set(ingredients);
		
	};
}
