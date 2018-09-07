package Hard_Mode.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import Hard_Mode.HardMode;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ToolAxe extends ItemAxe implements IHasModel{
		
	public ToolAxe(String name, ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		this.attackDamage = damage;
		this.attackSpeed = speed;
		
		ItemInit.ITEMS.add(this);
	}


	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(this,0,"inventory");
		
	}
}
