package Hard_Mode.objects.items.guis.dirtybag;

import Hard_Mode.HardMode;
import Hard_Mode.init.ItemInit;
import Hard_Mode.util.IHasModel;
import Hard_Mode.util.handelers.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemDirtyBag extends Item implements IHasModel{
	private String itemName;
	
	public ItemDirtyBag(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(HardMode.hardmodetab);
		itemName = name;
		

		setMaxStackSize(1);//Sets the trinkets to not be stackable.
	
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		HardMode.proxy.registerItemRenderer(this,0,"inventory");
		
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		new InventoryDirtyBag(stack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			playerIn.openGui(HardMode.instance, ConfigHandler.GUI_DIRTY_BAG, worldIn, 0, 0, 0);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
