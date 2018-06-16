package Hard_Mode.util.handelers;

import java.io.File;

import Hard_Mode.HardMode;
import Hard_Mode.util.References;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	public static Configuration config;
	
	public static int GUI_HELLFIRE_FURNACE = 0;
	public static int GUI_TINKER_TOOL = 1;
	public static int GUI_DIRTY_BAG = 2;
	
	public static void init(File file) {
		config = new Configuration(file);
		
		String category;
		category = "IDs";
		config.addCustomCategoryComment(category, "Set IDs for GUI");
		GUI_HELLFIRE_FURNACE = config.getInt("Hellfire Furnace GUI ID", category, 0, 0, 100, "ID for the Hellfire Furnace GUI");
		GUI_TINKER_TOOL = config.getInt("Tinker Tool GUI ID", category, 1, 0, 100, "ID for the Tinker Tool GUI");
		GUI_DIRTY_BAG = config.getInt("Dirty Bag GUI ID", category, 2, 0, 100, "ID for the Dirty Bag GUI");
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		HardMode.config = new File(event.getModConfigurationDirectory() + "/" + References.MODID);
		HardMode.config.mkdirs();
		init(new File(HardMode.config.getPath(), References.MODID + ".cfg"));
		
	}
}
