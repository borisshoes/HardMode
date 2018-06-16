package Hard_Mode.objects.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockForbiddenEnchantingTable extends BlockBase{

	public static final AxisAlignedBB FORBIDDEN_ENCHANTING_TABLE_AABB = new AxisAlignedBB(0,0,0,1,0.75,1);
	
	public BlockForbiddenEnchantingTable(String name, Material material, float hardness, float resistance, int harvestLvl, String harvestClass, SoundType sound) {
		super(name, material, resistance, resistance, harvestLvl, harvestClass, sound);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FORBIDDEN_ENCHANTING_TABLE_AABB;
	}
}
