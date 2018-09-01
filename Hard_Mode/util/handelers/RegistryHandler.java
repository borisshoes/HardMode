package Hard_Mode.util.handelers;

import Hard_Mode.HardMode;
import Hard_Mode.init.BlockInit;
import Hard_Mode.init.EnchantmentInit;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import Hard_Mode.world.gen.WorldGenCustomOres;
import Hard_Mode.world.gen.WorldGenCustomStructures;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ItemInit.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries(FMLPreInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);//number is the priority over other world generators (0 is most important).
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
		
		ConfigHandler.registerConfig(event);
	}
	public static void initRegistries(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(HardMode.instance, new GuiHandler());
	}
	public static void postInitRegistries(FMLPostInitializationEvent event) {
	
	}
	
	public static void otherRegistries() {
		
		
	}
}
