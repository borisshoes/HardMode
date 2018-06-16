package Hard_Mode.world.gen;

import java.util.Random;

import Hard_Mode.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator{
	private WorldGenerator ore_hellcrystal, ore_runic, ore_infinity;
	
	public WorldGenCustomOres() {//BlockState, max ores in vein, what block it replaces (default stone, optional)
		ore_hellcrystal = new WorldGenMinable(BlockInit.ORE_HELLCRYSTAL.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_infinity = new WorldGenMinable(BlockInit.ORE_INFINITY.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_runic = new WorldGenMinable(BlockInit.ORE_RUNIC.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.END_STONE));
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds.");
		
		int heightDiff = maxHeight - minHeight + 1;
		for(int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:
			runGenerator(ore_hellcrystal, world, random, chunkX, chunkZ, 15, 0, 20);//chance, minHeight, maxHeight (as shown above)
			break;
		case 1:
			runGenerator(ore_infinity, world, random, chunkX, chunkZ, 15, 0, 256);//chance, minHeight, maxHeight (as shown above)
			runGenerator(ore_runic, world, random, chunkX, chunkZ, 15, 0, 256);//chance, minHeight, maxHeight (as shown above)
			break;
		}
		
	}
	
}
