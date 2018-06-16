package Hard_Mode.util.handelers;

import Hard_Mode.objects.blocks.machines.TileEntityHellfireFurnace;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityHellfireFurnace.class, "hellfire_furnace");
	}
}
