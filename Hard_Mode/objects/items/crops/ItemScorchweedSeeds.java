package Hard_Mode.objects.items.crops;

import Hard_Mode.HardMode;
import Hard_Mode.init.BlockInit;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemScorchweedSeeds extends Item implements IHasModel, IPlantable{

	public ItemScorchweedSeeds(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return BlockInit.SCORCHWEED_PLANT.getDefaultState();
	}

	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(this,0,"inventory");
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		if(facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up())) {
			worldIn.setBlockState(pos.up(), BlockInit.SCORCHWEED_PLANT.getDefaultState());
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}else {
			return EnumActionResult.FAIL;
		}
	}
	
	
}
