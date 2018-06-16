package Hard_Mode.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
//import scala.actors.threadpool.Arrays;

public class WorldGenCustomStructures implements IWorldGenerator{
	/*public static final WorldGenStructure DIMENSIONAL_DUNGEON_END = new WorldGenStructure("dimensional_dungeon_end");
	public static final WorldGenStructure DIMENSIONAL_DUNGEON_OVERWORLD = new WorldGenStructure("dimensional_dungeon_overworld");
	public static final WorldGenStructure DIMENSIONAL_DUNGEON_NETHER = new WorldGenStructure("dimensional_dungeon_nether");*/

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case 1:
			break;
		case 0:
			generateStructure(new WorldGenStructure("dimensional_dungeon_end"), world, random, chunkX, chunkZ, 200, Blocks.STONE, BiomePlains.class);
			generateStructure(new WorldGenStructure("dimensional_dungeon_overworld"), world, random, chunkX, chunkZ, 200, Blocks.STONE, BiomePlains.class);
			generateStructure(new WorldGenStructure("dimensional_dungeon_nether"), world, random, chunkX, chunkZ, 200, Blocks.STONE, BiomePlains.class);
			break;
		case -1:
			break;
		}	
	}
	
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>...classes) {
		
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		y-=20;
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT) {
			if(classesList.contains(biome)) {
				if(random.nextInt(chance)==0) {
					generator.generate(world, random, pos);
				}
			}
		}
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0){
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
}
