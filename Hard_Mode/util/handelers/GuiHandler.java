package Hard_Mode.util.handelers;

import Hard_Mode.objects.blocks.machines.ContainerHellfireFurnace;
import Hard_Mode.objects.blocks.machines.GuiHellfireFurnace;
import Hard_Mode.objects.blocks.machines.TileEntityHellfireFurnace;
import Hard_Mode.objects.items.guis.tinkertool.ContainerTinkerTool;
import Hard_Mode.objects.items.guis.tinkertool.GuiTinkerTool;
import Hard_Mode.util.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == ConfigHandler.GUI_HELLFIRE_FURNACE) return new ContainerHellfireFurnace(player.inventory, (TileEntityHellfireFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ConfigHandler.GUI_TINKER_TOOL) return new ContainerTinkerTool(player.inventory, world, x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == ConfigHandler.GUI_HELLFIRE_FURNACE) return new GuiHellfireFurnace(player.inventory, (TileEntityHellfireFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ConfigHandler.GUI_TINKER_TOOL) return new GuiTinkerTool(player.inventory, world, x, y, z);
		return null;
	}

}
