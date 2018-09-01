package Hard_Mode.objects.blocks.machines;

import java.util.Random;

import Hard_Mode.HardMode;
import Hard_Mode.init.BlockInit;
import Hard_Mode.objects.blocks.BlockBase;
import Hard_Mode.util.handelers.ConfigHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockForbiddenEnchantingTable extends BlockBase implements ITileEntityProvider{

	public static final AxisAlignedBB FORBIDDEN_ENCHANTING_TABLE_AABB = new AxisAlignedBB(0,0,0,1,0.75,1);
	
	public BlockForbiddenEnchantingTable(String name, Material material, float hardness, float resistance, int harvestLvl, String harvestClass, SoundType sound) {
		super(name, material, hardness, resistance, harvestLvl, harvestClass, sound);
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityForbiddenTable();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockInit.FORBIDDEN_ENCHANTING_TABLE);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlockInit.FORBIDDEN_ENCHANTING_TABLE);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityForbiddenTable tileentity = (TileEntityForbiddenTable)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(HardMode.instance, ConfigHandler.GUI_FORBIDDEN_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
}
