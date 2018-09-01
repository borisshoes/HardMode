package Hard_Mode.util.handelers;

import Hard_Mode.objects.blocks.machines.ContainerForbiddenTable;
import Hard_Mode.objects.blocks.machines.ContainerHellfireFurnace;
import Hard_Mode.objects.blocks.machines.GuiForbiddenTable;
import Hard_Mode.objects.blocks.machines.GuiHellfireFurnace;
import Hard_Mode.objects.blocks.machines.TileEntityForbiddenTable;
import Hard_Mode.objects.blocks.machines.TileEntityHellfireFurnace;
import Hard_Mode.objects.items.guis.dirtybag.ContainerDirtyBag;
import Hard_Mode.objects.items.guis.dirtybag.GuiDirtyBag;
import Hard_Mode.objects.items.guis.dirtybag.InventoryDirtyBag;
import Hard_Mode.objects.items.guis.infernobox.ContainerInfernoBox;
import Hard_Mode.objects.items.guis.infernobox.GuiInfernoBox;
import Hard_Mode.objects.items.guis.infernobox.InventoryInfernoBox;
import Hard_Mode.objects.items.guis.infernobox.ItemInfernoBox;
import Hard_Mode.objects.items.guis.material_amulet.ContainerMaterialAmulet;
import Hard_Mode.objects.items.guis.material_amulet.GuiMaterialAmulet;
import Hard_Mode.objects.items.guis.material_amulet.InventoryMaterialAmulet;
import Hard_Mode.objects.items.guis.tinkertool.ContainerTinkerTool;
import Hard_Mode.objects.items.guis.tinkertool.GuiTinkerTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == ConfigHandler.GUI_HELLFIRE_FURNACE) return new ContainerHellfireFurnace(player.inventory, (TileEntityHellfireFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ConfigHandler.GUI_TINKER_TOOL) return new ContainerTinkerTool(player.inventory, world, x, y, z);
		if(ID == ConfigHandler.GUI_DIRTY_BAG) return new ContainerDirtyBag(player.inventory, new InventoryDirtyBag(player.inventory.getCurrentItem()), player);
		if(ID == ConfigHandler.GUI_INFERNO_BOX) return new ContainerInfernoBox(player.inventory, new InventoryInfernoBox(player.inventory.getCurrentItem(), world), player);
		if(ID == ConfigHandler.GUI_FORBIDDEN_TABLE) return new ContainerForbiddenTable(player.inventory, (TileEntityForbiddenTable)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ConfigHandler.GUI_MATERIAL_AMULET) return new ContainerMaterialAmulet(player.inventory, new InventoryMaterialAmulet(player.inventory.getCurrentItem()), world, x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == ConfigHandler.GUI_HELLFIRE_FURNACE) return new GuiHellfireFurnace(player.inventory, (TileEntityHellfireFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ConfigHandler.GUI_TINKER_TOOL) return new GuiTinkerTool(player.inventory, world, x, y, z);
		if(ID == ConfigHandler.GUI_DIRTY_BAG) return new GuiDirtyBag(new InventoryDirtyBag(player.inventory.getCurrentItem()));
		if(ID == ConfigHandler.GUI_INFERNO_BOX) return new GuiInfernoBox(player.inventory, new InventoryInfernoBox(player.inventory.getCurrentItem(), world), (ItemInfernoBox) player.inventory.getCurrentItem().getItem());
		if(ID == ConfigHandler.GUI_FORBIDDEN_TABLE) return new GuiForbiddenTable(player.inventory, (TileEntityForbiddenTable)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ConfigHandler.GUI_MATERIAL_AMULET) return new GuiMaterialAmulet(player.inventory, new InventoryMaterialAmulet(player.inventory.getCurrentItem()), world, x, y, z);
		return null;
	}

}
