package Hard_Mode.objects.blocks.machines;

import java.util.Random;

import Hard_Mode.init.BlockInit;
import Hard_Mode.init.ItemInit;
import Hard_Mode.objects.blocks.machines.enchantmentRunes.EnumRuneType;
import Hard_Mode.objects.blocks.machines.enchantmentRunes.ForbiddenEnchantRecipes;
import Hard_Mode.objects.blocks.machines.enchantmentRunes.RuneCombo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityForbiddenTable extends TileEntity implements IInventory, ITickable {
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	private String customName;
	
	private int totalEnchTime = 400;
	private int enchTime = 0;
	private boolean multiBlock = false;
	private String multiBlockdDirection = "None";
	private boolean enchanting = false;
	private int tickCount = 0;
	
	private RuneCombo currentCombo;
	private Enchantment result;
	private int bookCount = 0;
	private int quartzCount = 0;
	
	private BlockLever leverBlock = (BlockLever) Blocks.LEVER;
	private PropertyBool powered = PropertyBool.create("powered");
	private IBlockState leverBlockState = leverBlock.getDefaultState().withProperty(powered, Boolean.valueOf(true));
	private boolean showParticles = leverBlockState.getValue(powered).booleanValue();
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.forbidden_table";
	}
	
	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}
	
	@Override
	public boolean isEmpty() { 
		for(ItemStack stack : this.inventory){
			if(!stack.isEmpty()) return false;
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index){
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if(index == 0 && index + 1 == 1 && !flag){
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		this.enchTime = compound.getInteger("EnchTime");
		this.totalEnchTime = compound.getInteger("TotalEnchTime");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("EnchTime", (short)this.enchTime);
		compound.setInteger("TotalEnchTime", (short)this.totalEnchTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}
	
	/** Slot Number Reference
	* 0 left foci
	* 1 center foci
	* 2 right foci
	* 3 quartz
	* 4 books
	* 5 output
	**/
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		
		if(index == 0 || index == 1 || index == 2) {
			Item item = stack.getItem();
			if(item == ItemInit.DUST_CURIOUS || item == ItemInit.DUST_VIOLENT || item == ItemInit.DUST_SPATIAL || item == ItemInit.DUST_MATERIAL) {
				return true;
			}else {
				return false;
			}
		}else if(index == 3){
			return stack.getItem() == ItemInit.INFUSED_QUARTZ ? true : false;
		}else if(index == 4){
			return stack.getItem() == ItemInit.FORBIDDEN_BOOK ? true : false;
		}else{
			return true;
		}
	}
	
	@Override
	public int getField(int id) {
		switch(id) {
		case 0:
			return this.enchTime;
		case 1:
			return this.totalEnchTime;
		case 2:
			return this.quartzCount;
		case 3:
			return this.bookCount;
		default:
			return 0;
		}
	}
	
	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			this.enchTime = value;
			break;
		case 1:
			this.totalEnchTime = value;
			break;
		case 2:
			this.quartzCount = value;
			break;
		case 3:
			this.bookCount = value;
			break;
		}
	}
	
	public boolean isEnchanting() {
		return this.enchanting;
	}
	
	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}
	
	public boolean isRuneComboValid() {
		RuneCombo combo = new RuneCombo(EnumRuneType.itemToEnum(inventory.get(0).getItem()),EnumRuneType.itemToEnum(inventory.get(1).getItem()),EnumRuneType.itemToEnum(inventory.get(2).getItem()));
		currentCombo = combo;
		this.result = ForbiddenEnchantRecipes.getInstance().getForbiddenResult(combo);
		
		
		if(result == null) {
			return false;
		}else{
			//System.out.println(result.getName());
			return true;
		}
	}
	
	public int getCurrentEnchLevel(ItemStack item, Enchantment enchant) {
		NBTTagCompound tag = item.getTagCompound();
		if(tag == null) {
			item.setTagCompound(new NBTTagCompound());
		}
		if(!item.isItemEnchanted()) {
			return 0;
		}
		
		NBTTagList enchList = tag.getTagList("ench", 10);
		for(int i = 0; i < enchList.tagCount(); i++) {
			NBTTagCompound enchCompound = enchList.getCompoundTagAt(i);
			if(enchCompound.getShort("id") == enchant.getEnchantmentID(enchant)) {
				return enchCompound.getShort("lvl");
			}
		}
		
		return 0;
	}
	
	public ItemStack removeEnchant(ItemStack item, Enchantment enchant) {
		NBTTagCompound tag = item.getTagCompound();
		if(tag == null) {
			item.setTagCompound(new NBTTagCompound());
		}
		
		if(!tag.hasKey("ench")) {
			return item;
		}
		
		NBTTagList enchList = tag.getTagList("ench", 10);
		for(int i = 0; i < enchList.tagCount(); i++) {
			NBTTagCompound enchCompound = enchList.getCompoundTagAt(i);
			if(enchCompound.getShort("id") == enchant.getEnchantmentID(enchant)) {
				enchList.removeTag(i);
			}
		}
		
		return item;
	}
	
	public boolean canEnchantItem(ItemStack item, Enchantment enchant) {
		boolean flag1 = enchant.type.canEnchantItem(item.getItem());
		if(!item.isItemEnchanted()) {
			return flag1;
		}
		ItemStack unenchantedItem = new ItemStack(item.getItem(), item.getCount(), item.getItemDamage());
		boolean flag2 = this.canEnchantItem(unenchantedItem, enchant);
		if(!flag2) {
			return flag2;
		}
		
		ItemStack itemClone = removeEnchant(item.copy(), enchant);
		
		NBTTagList enchList = itemClone.getEnchantmentTagList();
		for(int i = 0; i < enchList.tagCount(); i++) {
			NBTTagCompound enchCompound = enchList.getCompoundTagAt(i);
			Enchantment enchantment = Enchantment.getEnchantmentByID(enchCompound.getShort("id"));
			if(!enchant.isCompatibleWith(enchantment)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean canDoEnchantRecipe() {
		boolean flag1 = false;
		if(multiBlock) {
			if(isRuneComboValid()) {
				if(!inventory.get(5).isEmpty()) {
					if(canEnchantItem(inventory.get(5), result)) {
						updateBookAndQuartzCost();
						
						int currentQCount = inventory.get(3).getCount();
						int currentBCount = inventory.get(4).getCount();
						
						if(currentQCount >= quartzCount && currentBCount >= bookCount) {
							flag1 = true;
						}
						
						if(result.getMaxLevel() <= getCurrentEnchLevel(inventory.get(5), result)) {
							flag1 = false;
						}
					}
				}
			}
		}
		return flag1;
	}
	
	private void updateBookAndQuartzCost() {
		quartzCount = ForbiddenEnchantRecipes.getInstance().getQuartzCountPerLvl(result);
		bookCount = ForbiddenEnchantRecipes.getInstance().getBookCountForItem(inventory.get(5));
	}
	
	public void doEnchantRecipe() {
		inventory.get(0).shrink(1);
		inventory.get(1).shrink(1);
		inventory.get(2).shrink(1);
		inventory.get(3).shrink(quartzCount);
		inventory.get(4).shrink(bookCount);
		
		int currentLvl = getCurrentEnchLevel(inventory.get(5), result); 
		ItemStack product = removeEnchant(inventory.get(5).copy(), result);
		product.addEnchantment(result, currentLvl + 1);
		
		inventory.set(5, product);
	}
	
	public void update() {
		if(tickCount % 20 == 0) {
			if(!inventory.isEmpty()) {
				multiBlock = multiBlock();
			}
			tickCount = 0;
		}
		
		if(canDoEnchantRecipe()){
			enchanting = true;
		}else{
			quartzCount = 0;
			bookCount = 0;
			enchanting = false;
		}
		
		double particleX = this.getPos().getX();
		double particleY = this.getPos().getY();
		double particleZ = this.getPos().getZ();
		double particleHSpeed = 1.5 / 17;
		double particleVSpeed = -2.75 / 17;
		if(multiBlock) {
			if(enchanting) {
				if(showParticles) {
					if(multiBlockdDirection.equals("north")) {
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 0.5, particleY + 3.5, particleZ - 1, 0, particleVSpeed, particleHSpeed, new int[0]);
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 0.5, particleY + 3.5, particleZ - 1, 0, particleVSpeed, particleHSpeed, new int[0]);
						
					}else if(multiBlockdDirection.equals("south")) {
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 0.5, particleY + 3.5, particleZ + 2, 0, particleVSpeed, -particleHSpeed, new int[0]);
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 0.5, particleY + 3.5, particleZ + 2, 0, particleVSpeed, -particleHSpeed, new int[0]);
						
					}else if(multiBlockdDirection.equals("east")) {
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 2, particleY + 3.5, particleZ + 0.5, -particleHSpeed, particleVSpeed, 0, new int[0]);
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 2, particleY + 3.5, particleZ + 0.5, -particleHSpeed, particleVSpeed, 0, new int[0]);
						
					}else if(multiBlockdDirection.equals("west")) {
						world.spawnParticle(EnumParticleTypes.FLAME, particleX - 1, particleY + 3.5, particleZ + 0.5, particleHSpeed, particleVSpeed, 0, new int[0]);
						world.spawnParticle(EnumParticleTypes.FLAME, particleX - 1, particleY + 3.5, particleZ + 0.5, particleHSpeed, particleVSpeed, 0, new int[0]);
						
						
					}
					Random rand = new Random();
					double xMot = (rand.nextDouble()*2-1)/25;
					double yMot = (rand.nextDouble()*2-1)/25;
					double zMot = (rand.nextDouble()*2-1)/25;
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							xMot = (rand.nextDouble()*2-1)/25;
							yMot = (rand.nextDouble()*2-1)/25;
							zMot = (rand.nextDouble()*2-1)/25;
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particleX - 1 + (i * 0.33), particleY + 0.5, particleZ - 1 + (j * 0.33), xMot, yMot, zMot, new int[0]);
						}
						xMot = (rand.nextDouble()*2-1)/20;
						yMot = (rand.nextDouble()*2-1)/20;
						zMot = (rand.nextDouble()*2-1)/20;
						world.spawnParticle(EnumParticleTypes.FLAME, particleX + 0.5, particleY + 0.8, particleZ + 0.5, xMot, yMot, zMot, new int[0]);
					}
				}
				
				enchTime++;
			}else{
				enchTime = 0;
			}
		}else{
			enchTime = 0;
		}
		
		if(enchTime >= totalEnchTime && canDoEnchantRecipe()) {
			doEnchantRecipe();
			enchTime = 0;
		}
		
		tickCount++;
	}
	
	public boolean multiBlock() {
		BlockPos thisBlock = this.pos;
		String direction = "none";
		
		BlockPos[] directionBlockPos = new BlockPos[4];
		directionBlockPos[0] = thisBlock.up(3).north(2);//North
		directionBlockPos[1] = thisBlock.up(3).south(2);//South
		directionBlockPos[2] = thisBlock.up(3).east(2);//East
		directionBlockPos[3] = thisBlock.up(3).west(2);//West
		Block[] directionBlock = new Block[4];
		directionBlock[0] = world.getBlockState(directionBlockPos[0]).getBlock();//North
		directionBlock[1] = world.getBlockState(directionBlockPos[1]).getBlock();//South
		directionBlock[2] = world.getBlockState(directionBlockPos[2]).getBlock();//East
		directionBlock[3] = world.getBlockState(directionBlockPos[3]).getBlock();//West
		
		//Direction Check (opposing face of the entrance)
		int directionCount = 0;
		if(directionBlock[0] == Blocks.REDSTONE_BLOCK) {
			directionCount++;
			direction = "north";
		}
		if(directionBlock[1] == Blocks.REDSTONE_BLOCK) {
			directionCount++;
			direction = "south";
		}
		if(directionBlock[2] == Blocks.REDSTONE_BLOCK) {
			directionCount++;
			direction = "east";
		}
		if(directionBlock[3] == Blocks.REDSTONE_BLOCK) {
			directionCount++;
			direction = "west";
		}
		if(directionCount != 1) {
			return false;
		}
		
		//Layer 1
		Block[][] layer1 = new Block[5][5];
		BlockPos[][] layer1Pos = new BlockPos[5][5];
		BlockPos corner1 = thisBlock.north(2).west(2).down();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				layer1Pos[i][j] = corner1.south(i).east(j);
				layer1[i][j] = world.getBlockState(layer1Pos[i][j]).getBlock();
			}
		}
		
		//Layer 1 Check (No Directions Yet)
		boolean layer1Check = false;
		if(layer1[0][0] == BlockInit.HELLISH_BRICK &&
		   layer1[0][1] == BlockInit.HELLISH_BRICK &&
		   layer1[0][2] == BlockInit.HELLISH_BRICK &&
		   layer1[0][3] == BlockInit.HELLISH_BRICK &&
		   layer1[0][4] == BlockInit.HELLISH_BRICK &&
		   layer1[1][0] == BlockInit.HELLISH_BRICK &&
		   layer1[1][1] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[1][2] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[1][3] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[1][4] == BlockInit.HELLISH_BRICK &&
		   layer1[2][0] == BlockInit.HELLISH_BRICK &&
		   layer1[2][1] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[2][2] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[2][3] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[2][4] == BlockInit.HELLISH_BRICK &&
		   layer1[3][0] == BlockInit.HELLISH_BRICK &&
		   layer1[3][1] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[3][2] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[3][3] == BlockInit.INFUSED_OBSIDIAN &&
		   layer1[3][4] == BlockInit.HELLISH_BRICK &&
		   layer1[4][0] == BlockInit.HELLISH_BRICK &&
		   layer1[4][1] == BlockInit.HELLISH_BRICK &&
		   layer1[4][2] == BlockInit.HELLISH_BRICK &&
		   layer1[4][3] == BlockInit.HELLISH_BRICK &&
		   layer1[4][4] == BlockInit.HELLISH_BRICK
		   ){
			layer1Check = true;
		}else{
			return false;
		}
		
		//Layer 2
		Block[][] layer2 = new Block[5][5];
		BlockPos[][] layer2Pos = new BlockPos[5][5];
		BlockPos corner2 = corner1.up();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				layer2Pos[i][j] = corner2.south(i).east(j);
				layer2[i][j] = world.getBlockState(layer2Pos[i][j]).getBlock();
			}
		}
		
		//Layer 2 Check
		boolean layer2Check = false;
		if(direction.equals("north")) {
			if(layer2[0][0] == BlockInit.HELLISH_BRICK &&
			   layer2[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][4] == BlockInit.HELLISH_BRICK &&
		       layer2[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[1][1] == Blocks.AIR &&
		       layer2[1][2] == Blocks.AIR &&
		       layer2[1][3] == Blocks.AIR &&
		       layer2[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][1] == Blocks.AIR &&
		       layer2[2][2] == BlockInit.FORBIDDEN_ENCHANTING_TABLE &&
		       layer2[2][3] == Blocks.AIR &&
		       layer2[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][1] == Blocks.AIR &&
		       layer2[3][2] == Blocks.AIR &&
		       layer2[3][3] == Blocks.AIR &&
		       layer2[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][0] == BlockInit.HELLISH_BRICK &&
		       layer2[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer2Check = true;
			}
			
		}
		
		if(direction.equals("south")) {
			if(layer2[0][0] == BlockInit.HELLISH_BRICK &&
			   layer2[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][4] == BlockInit.HELLISH_BRICK &&
		       layer2[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[1][1] == Blocks.AIR &&
		       layer2[1][2] == Blocks.AIR &&
		       layer2[1][3] == Blocks.AIR &&
		       layer2[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][1] == Blocks.AIR &&
		       layer2[2][2] == BlockInit.FORBIDDEN_ENCHANTING_TABLE &&
		       layer2[2][3] == Blocks.AIR &&
		       layer2[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][1] == Blocks.AIR &&
		       layer2[3][2] == Blocks.AIR &&
		       layer2[3][3] == Blocks.AIR &&
		       layer2[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][0] == BlockInit.HELLISH_BRICK &&
		       layer2[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer2Check = true;
			}
		}
		
		if(direction.equals("east")) {
			if(layer2[0][0] == BlockInit.HELLISH_BRICK &&
			   layer2[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][4] == BlockInit.HELLISH_BRICK &&
		       layer2[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[1][1] == Blocks.AIR &&
		       layer2[1][2] == Blocks.AIR &&
		       layer2[1][3] == Blocks.AIR &&
		       layer2[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][1] == Blocks.AIR &&
		       layer2[2][2] == BlockInit.FORBIDDEN_ENCHANTING_TABLE &&
		       layer2[2][3] == Blocks.AIR &&
		       layer2[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][1] == Blocks.AIR &&
		       layer2[3][2] == Blocks.AIR &&
		       layer2[3][3] == Blocks.AIR &&
		       layer2[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][0] == BlockInit.HELLISH_BRICK &&
		       layer2[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer2Check = true;
			}
		}
		
		if(direction.equals("west")) {
			if(layer2[0][0] == BlockInit.HELLISH_BRICK &&
			   layer2[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[0][4] == BlockInit.HELLISH_BRICK &&
		       layer2[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[1][1] == Blocks.AIR &&
		       layer2[1][2] == Blocks.AIR &&
		       layer2[1][3] == Blocks.AIR &&
		       layer2[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[2][1] == Blocks.AIR &&
		       layer2[2][2] == BlockInit.FORBIDDEN_ENCHANTING_TABLE &&
		       layer2[2][3] == Blocks.AIR &&
		       layer2[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[3][1] == Blocks.AIR &&
		       layer2[3][2] == Blocks.AIR &&
		       layer2[3][3] == Blocks.AIR &&
		       layer2[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][0] == BlockInit.HELLISH_BRICK &&
		       layer2[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer2[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer2Check = true;
			}
		}
		
		if(!layer2Check) {
			return false;
		}
		
		//Layer 3
		Block[][] layer3 = new Block[5][5];
		BlockPos[][] layer3Pos = new BlockPos[5][5];
		BlockPos corner3 = corner2.up();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				layer3Pos[i][j] = corner3.south(i).east(j);
				layer3[i][j] = world.getBlockState(layer3Pos[i][j]).getBlock();
			}
		}
		
		//Layer 3 Check
		boolean layer3Check = false;
		if(direction.equals("north")) {
			if(layer3[0][0] == BlockInit.HELLISH_BRICK &&
			   layer3[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][4] == BlockInit.HELLISH_BRICK &&
		       layer3[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[1][1] == Blocks.AIR &&
		       layer3[1][2] == Blocks.AIR &&
		       layer3[1][3] == Blocks.AIR &&
		       layer3[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[2][1] == Blocks.AIR &&
		       layer3[2][2] == Blocks.AIR &&
		       layer3[2][3] == Blocks.AIR &&
		       layer3[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[3][1] == Blocks.AIR &&
		       layer3[3][2] == Blocks.AIR &&
		       layer3[3][3] == Blocks.AIR &&
		       layer3[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][0] == BlockInit.HELLISH_BRICK &&
		       layer3[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer3Check = true;
			}
			
		}
		
		if(direction.equals("south")) {
			if(layer3[0][0] == BlockInit.HELLISH_BRICK &&
		       layer3[0][4] == BlockInit.HELLISH_BRICK &&
		       layer3[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[1][1] == Blocks.AIR &&
		       layer3[1][2] == Blocks.AIR &&
		       layer3[1][3] == Blocks.AIR &&
		       layer3[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[2][1] == Blocks.AIR &&
		       layer3[2][2] == Blocks.AIR &&
		       layer3[2][3] == Blocks.AIR &&
		       layer3[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[3][1] == Blocks.AIR &&
		       layer3[3][2] == Blocks.AIR &&
		       layer3[3][3] == Blocks.AIR &&
		       layer3[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][0] == BlockInit.HELLISH_BRICK &&
		       layer3[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer3Check = true;
			}
		}
		
		if(direction.equals("east")) {
			if(layer3[0][0] == BlockInit.HELLISH_BRICK &&
			   layer3[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][4] == BlockInit.HELLISH_BRICK &&
		       layer3[1][1] == Blocks.AIR &&
		       layer3[1][2] == Blocks.AIR &&
		       layer3[1][3] == Blocks.AIR &&
		       layer3[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[2][1] == Blocks.AIR &&
		       layer3[2][2] == Blocks.AIR &&
		       layer3[2][3] == Blocks.AIR &&
		       layer3[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[3][1] == Blocks.AIR &&
		       layer3[3][2] == Blocks.AIR &&
		       layer3[3][3] == Blocks.AIR &&
		       layer3[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][0] == BlockInit.HELLISH_BRICK &&
		       layer3[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer3Check = true;
			}
		}
		
		if(direction.equals("west")) {
			if(layer3[0][0] == BlockInit.HELLISH_BRICK &&
			   layer3[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[0][4] == BlockInit.HELLISH_BRICK &&
		       layer3[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[1][1] == Blocks.AIR &&
		       layer3[1][2] == Blocks.AIR &&
		       layer3[1][3] == Blocks.AIR &&
		       layer3[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[2][1] == Blocks.AIR &&
		       layer3[2][2] == Blocks.AIR &&
		       layer3[2][3] == Blocks.AIR &&
		       layer3[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[3][1] == Blocks.AIR &&
		       layer3[3][2] == Blocks.AIR &&
		       layer3[3][3] == Blocks.AIR &&
		       layer3[4][0] == BlockInit.HELLISH_BRICK &&
		       layer3[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer3[4][4] == BlockInit.HELLISH_BRICK
		   ){
				layer3Check = true;
			}
		}
		
		if(!layer3Check) {
			return false;
		}
		
		//Layer 4
		Block[][] layer4 = new Block[5][5];
		BlockPos[][] layer4Pos = new BlockPos[5][5];
		BlockPos corner4 = corner3.up();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				layer4Pos[i][j] = corner4.south(i).east(j);
				layer4[i][j] = world.getBlockState(layer4Pos[i][j]).getBlock();
			}
		}
		
		//Layer 4 Check
		boolean layer4Check = false;
		if(direction.equals("north")) {
			if(layer4[0][0] == BlockInit.HELLISH_BRICK &&
			   layer4[4][0] == BlockInit.HELLISH_BRICK &&
		       layer4[4][4] == BlockInit.HELLISH_BRICK &&
		       layer4[0][4] == BlockInit.HELLISH_BRICK &&
		       layer4[2][2] == Blocks.AIR &&
		       
		       layer4[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[1][2] == Blocks.AIR &&
		       layer4[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&		       
		       layer4[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[0][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[0][3] == BlockInit.FORBIDDEN_BOOKCASE 
		       
		   ){
				layer4Check = true;
			}
		}
		
		if(direction.equals("south")) {
			if(layer4[0][0] == BlockInit.HELLISH_BRICK &&
			   layer4[4][0] == BlockInit.HELLISH_BRICK &&
		       layer4[4][4] == BlockInit.HELLISH_BRICK &&
		       layer4[0][4] == BlockInit.HELLISH_BRICK &&
		       layer4[2][2] == Blocks.AIR &&
		       
		       layer4[3][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[3][2] == Blocks.AIR &&
		       layer4[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&		       
		       layer4[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[4][2] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[4][3] == BlockInit.FORBIDDEN_BOOKCASE 
		       
		   ){
				layer4Check = true;
			}
		}
		
		if(direction.equals("east")) {
			if(layer4[0][0] == BlockInit.HELLISH_BRICK &&
			   layer4[4][0] == BlockInit.HELLISH_BRICK &&
		       layer4[4][4] == BlockInit.HELLISH_BRICK &&
		       layer4[0][4] == BlockInit.HELLISH_BRICK &&
		       layer4[2][2] == Blocks.AIR &&
		       
		       layer4[0][3] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[2][3] == Blocks.AIR &&
		       layer4[1][4] == BlockInit.FORBIDDEN_BOOKCASE &&		       
		       layer4[2][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[3][4] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[4][3] == BlockInit.FORBIDDEN_BOOKCASE 
		       
		   ){
				layer4Check = true;
			}
		}
		
		if(direction.equals("west")) {
			if(layer4[0][0] == BlockInit.HELLISH_BRICK &&
			   layer4[4][0] == BlockInit.HELLISH_BRICK &&
		       layer4[4][4] == BlockInit.HELLISH_BRICK &&
		       layer4[0][4] == BlockInit.HELLISH_BRICK &&
		       layer4[2][2] == Blocks.AIR &&
		       
		       layer4[0][1] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[2][1] == Blocks.AIR &&
		       layer4[4][1] == BlockInit.FORBIDDEN_BOOKCASE &&		       
		       layer4[1][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[2][0] == BlockInit.FORBIDDEN_BOOKCASE &&
		       layer4[3][0] == BlockInit.FORBIDDEN_BOOKCASE 
		       
		   ){
				layer4Check = true;
			}
		}
		
		if(!layer4Check) {
			return false;
		}
		
		//Layer 5
		Block[][] layer5 = new Block[5][5];
		BlockPos[][] layer5Pos = new BlockPos[5][5];
		BlockPos corner5 = corner4.up();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				layer5Pos[i][j] = corner5.south(i).east(j);
				layer5[i][j] = world.getBlockState(layer5Pos[i][j]).getBlock();
			}
		}
		
		//Layer 5 Check
		boolean layer5Check = false;
		if(direction.equals("north")) {
			if(layer5[0][0] == Blocks.GLOWSTONE &&
			   layer5[4][0] == Blocks.GLOWSTONE &&
		       layer5[4][4] == Blocks.GLOWSTONE &&
		       layer5[0][4] == Blocks.GLOWSTONE &&
		       layer5[2][2] == Blocks.AIR &&
		       
		       (layer5[1][2] == Blocks.AIR || layer5[1][2] == Blocks.LEVER)&&
		       layer5[0][2] == Blocks.REDSTONE_BLOCK
		       
		   ){
				if(layer5[1][2] instanceof BlockLever) {
					leverBlock = (BlockLever) layer5[1][2];
					leverBlockState = world.getBlockState(layer5Pos[1][2]);
					showParticles = leverBlockState.getValue(powered).booleanValue();
				}else{
					showParticles = true;
				}
				layer5Check = true;
			}
		}
		
		if(direction.equals("south")) {
			if(layer5[0][0] == Blocks.GLOWSTONE &&
			   layer5[4][0] == Blocks.GLOWSTONE &&
		       layer5[4][4] == Blocks.GLOWSTONE &&
		       layer5[0][4] == Blocks.GLOWSTONE &&
		       layer5[2][2] == Blocks.AIR &&
		       
		       (layer5[3][2] == Blocks.AIR || layer5[3][2] == Blocks.LEVER)&&
		       layer5[4][2] == Blocks.REDSTONE_BLOCK
		       
		   ){
				if(layer5[3][2] instanceof BlockLever) {
					leverBlock = (BlockLever) layer5[3][2];
					leverBlockState = world.getBlockState(layer5Pos[3][2]);
					showParticles = leverBlockState.getValue(powered).booleanValue();
				}else{
					showParticles = true;
				}
				layer5Check = true;
			}
		}
		
		if(direction.equals("east")) {
			if(layer5[0][0] == Blocks.GLOWSTONE &&
			   layer5[4][0] == Blocks.GLOWSTONE &&
		       layer5[4][4] == Blocks.GLOWSTONE &&
		       layer5[0][4] == Blocks.GLOWSTONE &&
		       layer5[2][2] == Blocks.AIR &&
		       
		       (layer5[2][3] == Blocks.AIR || layer5[2][3] == Blocks.LEVER)&&
		       layer5[2][4] == Blocks.REDSTONE_BLOCK
		       
		   ){
				if(layer5[2][3] instanceof BlockLever) {
					leverBlock = (BlockLever) layer5[2][3];
					leverBlockState = world.getBlockState(layer5Pos[2][3]);
					showParticles = leverBlockState.getValue(powered).booleanValue();
				}else{
					showParticles = true;
				}
				layer5Check = true;
			}
		}
		
		if(direction.equals("west")) {
			if(layer5[0][0] == Blocks.GLOWSTONE &&
			   layer5[4][0] == Blocks.GLOWSTONE &&
		       layer5[4][4] == Blocks.GLOWSTONE &&
		       layer5[0][4] == Blocks.GLOWSTONE &&
		       layer5[2][2] == Blocks.AIR &&
		       
		       (layer5[2][1] == Blocks.AIR || layer5[2][1] == Blocks.LEVER)&&
		       layer5[2][0] == Blocks.REDSTONE_BLOCK
		       
		   ){
				if(layer5[2][1] instanceof BlockLever) {
					leverBlock = (BlockLever) layer5[2][1];
					leverBlockState = world.getBlockState(layer5Pos[2][1]);
					showParticles = leverBlockState.getValue(powered).booleanValue();
				}else{
					showParticles = true;
				}
				layer5Check = true;
			}
		}
		
		multiBlockdDirection = direction;
		return layer5Check;
	}
}
