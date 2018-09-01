package Hard_Mode.objects.blocks.machines.enchantmentRunes;

import Hard_Mode.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public enum EnumRuneType {
	MATERIAL{
		public String getName() {
			return "material";
		}
	},
	
	SPATIAL{
		public String getName() {
			return "spatial";
		}
	},
	
	VIOLENT{
		public String getName() {
			return "violent";
		}
	},
	
	CURIOUS{
		public String getName() {
			return "curious";
		}
	},
	
	NONE{
		public String getName() {
			return "none";
		}
	};
	
	private EnumRuneType() {
		
	}
	
	public static EnumRuneType itemToEnum(Item rune) {
		if(rune == ItemInit.DUST_CURIOUS) {
			return EnumRuneType.CURIOUS;
		}else if(rune == ItemInit.DUST_MATERIAL) {
			return EnumRuneType.MATERIAL;
		}else if(rune == ItemInit.DUST_SPATIAL) {
			return EnumRuneType.SPATIAL;
		}else if(rune == ItemInit.DUST_VIOLENT) {
			return EnumRuneType.VIOLENT;
		}else{
			return EnumRuneType.NONE;
		}
	}
	
	public static Item enumToItem(EnumRuneType rune) {
		if(rune == EnumRuneType.CURIOUS) {
			return ItemInit.DUST_CURIOUS;
		}else if(rune == EnumRuneType.MATERIAL) {
			return ItemInit.DUST_MATERIAL;
		}else if(rune == EnumRuneType.VIOLENT) {
			return ItemInit.DUST_VIOLENT;
		}else if(rune == EnumRuneType.SPATIAL) {
			return ItemInit.DUST_SPATIAL;
		}else {
			return Item.getItemFromBlock(Blocks.AIR);
		}
	}
}
