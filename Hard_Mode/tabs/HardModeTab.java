package Hard_Mode.tabs;

import Hard_Mode.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class HardModeTab extends CreativeTabs{
	public HardModeTab(String label) {
		super("hardmodetab");
		this.setBackgroundImageName("items.png");
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.FORBIDDEN_INK);
	}
}
