package Hard_Mode.objects.blocks;

import java.util.Random;

import Hard_Mode.HardMode;
import Hard_Mode.init.BlockInit;
import Hard_Mode.init.ItemInit;
import Hard_Mode.objects.blocks.item.ItemBlockVariants;
import Hard_Mode.util.IHasModel;
import Hard_Mode.util.IMetaName;
import Hard_Mode.util.handelers.EnumHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockOres extends Block implements IHasModel, IMetaName{
	private String name, dimension;
	
	public BlockOres(String name, String dimension, float hardness, float resistance, int harvestLvl, String harvestClass, SoundType sound) {
		super(Material.ROCK);
		
		setHardness(hardness);
		setResistance(resistance);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		setHarvestLevel(harvestClass, harvestLvl);
		setSoundType(sound);
		
		this.name = name;
		this.dimension = dimension;
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return name;
	}

	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(name.equals("ore_hellcrystal")) {
			return ItemInit.HELLCRYSTAL;
		}else if (name.equals("ore_runic")){
			return ItemInit.RUNIC_GEMSTONE;
		}else if(name.equals("ore_infinity")) {
			int random = rand.nextInt(4);
			
			if(random == 0) {
				return ItemInit.DUST_VIOLENT;
			}else if(random == 1) {
				return ItemInit.DUST_CURIOUS;
			}else if(random ==2) {
				return ItemInit.DUST_SPATIAL;
			}else {
				return ItemInit.DUST_MATERIAL;
			}
			
		}else {
			return null;
		}
	}
	
	@Override 
	public int quantityDropped(Random random){
        if(name.equals("ore_infinity")) {
        	return random.nextInt(4)+1; //Drops 1-4
        }else{
        	return 1;
        }
    }
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }
}
