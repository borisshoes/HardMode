package Hard_Mode;
import java.io.File;

import Hard_Mode.proxy.CommonProxy;
import Hard_Mode.tabs.HardModeTab;
import Hard_Mode.util.References;
import Hard_Mode.util.handelers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class HardMode {
	
	public static File config;
	
	@Instance
	public static HardMode instance;
	
	public static final CreativeTabs hardmodetab = new HardModeTab("hardmodetab");
	
	@SidedProxy(clientSide = References.CLIENT, serverSide = References.COMMON)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegistries(event);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		RegistryHandler.initRegistries(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries(event);
	}
}
