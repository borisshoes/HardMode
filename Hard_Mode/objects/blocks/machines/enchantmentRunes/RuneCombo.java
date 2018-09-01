package Hard_Mode.objects.blocks.machines.enchantmentRunes;

public class RuneCombo {
	public EnumRuneType firstDust;
	public EnumRuneType secondDust;
	public EnumRuneType thirdDust;
	
	public RuneCombo (EnumRuneType rune1, EnumRuneType rune2, EnumRuneType rune3) {
		firstDust = rune1;
		secondDust = rune2;
		thirdDust = rune3;
	}
	
	public EnumRuneType getRune1() {
		return firstDust;
	}
	
	public EnumRuneType getRune2() {
		return secondDust;
	}
	
	public EnumRuneType getRune3() {
		return thirdDust;
	}
	
	public static String runeToString(RuneCombo combo) {
		String output = "";
		if(combo.firstDust == EnumRuneType.CURIOUS) {
			output = "C";
		}else if(combo.firstDust == EnumRuneType.VIOLENT) {
			output = "V";
		}else if(combo.firstDust == EnumRuneType.MATERIAL) {
			output = "M";
		}else if(combo.firstDust == EnumRuneType.SPATIAL) {
			output = "S";
		}else if(combo.firstDust == EnumRuneType.NONE) {
			output = "-";
		}else{
			output = "!";
		}  
		
		if(combo.secondDust == EnumRuneType.CURIOUS) {
			output += "C";
		}else if(combo.secondDust == EnumRuneType.VIOLENT) {
			output += "V";
		}else if(combo.secondDust == EnumRuneType.MATERIAL) {
			output += "M";
		}else if(combo.secondDust == EnumRuneType.SPATIAL) {
			output += "S";
		}else if(combo.secondDust == EnumRuneType.NONE) {
			output += "-";
		}else{
			output += "!";
		} 
		
		if(combo.thirdDust == EnumRuneType.CURIOUS) {
			output += "C";
		}else if(combo.thirdDust == EnumRuneType.VIOLENT) {
			output += "V";
		}else if(combo.thirdDust == EnumRuneType.MATERIAL) {
			output += "M";
		}else if(combo.thirdDust == EnumRuneType.SPATIAL) {
			output += "S";
		}else if(combo.thirdDust == EnumRuneType.NONE) {
			output += "-";
		}else{
			output += "!";
		} 
		
		return output;
	}
}
