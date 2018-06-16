package Hard_Mode.objects.blocks;

import Hard_Mode.HardMode;
import Hard_Mode.init.BlockInit;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel{
	public BlockBase(String name, Material material, float hardness, float resistance, int harvestLvl, String harvestClass, SoundType sound) {
		super(material);
		
		setHardness(hardness);
		setResistance(resistance);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		setHarvestLevel(harvestClass, harvestLvl);
		setSoundType(sound);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	/*public BlockBase(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}*/
	
	
	
	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
		
	}
	
}
