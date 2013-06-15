package ayamitsu.fruitore.object;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FruitOreObject {

	/** for write and read nbt **/
	public static final String FRUIT_ORE_OBJECT = "FruitOreObject";
	public static final String FRUIT_ORE_OBJECT_ID = "FruitOreObjectID";
	public static final String FRUIT_ORE_OBJECT_META = "FruitOreObjectMeta";

	public static final FruitOreObject[] fruitsList = new FruitOreObject[Block.blocksList.length];

	/** correspond block ID **/
	protected int fruitId;

	/** drop item, if null, refer from corresponded block **/
	protected ItemStack fruitItem;

	protected ItemStack renderItem;

	protected int fruitColor = 0xffffff;

	public FruitOreObject(int blockId) {
		if (this.fruitsList[blockId] != null) {
			throw new IllegalArgumentException("Fruit Slot " + blockId + " is already occupied by " + this.fruitsList[blockId] + " when adding " + this);
		}

		this.fruitId = blockId;
	}

	public FruitOreObject(int blockId, ItemStack itemStack) {
		if (this.fruitsList[blockId] != null) {
			throw new IllegalArgumentException("Fruit Slot " + blockId + " is already occupied by " + this.fruitsList[blockId] + " when adding " + this);
		}

		this.fruitId = blockId;
		this.fruitItem = itemStack;
	}

	public int getMaxGrowLevel() {
		return 7;
	}

	public int getHarvetableLevel() {
		return 7;
	}

	public int getGrowthRandomRate() {
		return 5;
	}

	protected int getDropValue(int meta, int growLevel) {
		return growLevel >= this.getHarvetableLevel() ? 3 : 0;
	}

	public ItemStack getDropItem(int meta, Random random, int growLevel) {
		int value = this.getDropValue(meta, growLevel);

		if (value > 0) {
			try {
				return this.fruitItem != null ? new ItemStack(this.fruitItem.itemID, value, this.fruitItem.getItemDamage()) : new ItemStack(this.fruitId, value, Block.blocksList[this.fruitId].damageDropped(meta));
			} catch (NullPointerException e) {
				e.printStackTrace();// missing fruit object !
			}
		}

		return null;
	}

	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == this.fruitId;
	}

	public ItemStack getRenderItem(int meta) {
		return this.renderItem != null ? this.renderItem : (this.renderItem = new ItemStack(this.fruitId, 1, Block.blocksList[this.fruitId].damageDropped(meta)));
	}

	public int getFruitColor(int meta) {
		return this.fruitColor;
	}

	public FruitOreObject setFruitColor(int color) {
		this.fruitColor = color;
		return this;
	}

	public float getFruitAlpha(int meta) {
		return 0.8F;
	}

	@SideOnly(Side.CLIENT)
	public String getDisplayName(int meta) {
		Block block = Block.blocksList[this.fruitId];
		return block != null ? block.getLocalizedName() : "null";
	}

	@SideOnly(Side.CLIENT)
	public void getSubFruits(int id, CreativeTabs tab, List list) {
		ItemStack itemStack = new ItemStack(id, 1, 0);
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		NBTTagCompound fruitNBT = new NBTTagCompound();
		fruitNBT.setInteger(FRUIT_ORE_OBJECT_ID, this.fruitId);
		fruitNBT.setInteger(FRUIT_ORE_OBJECT_META, 0);
		nbttagcompound.setCompoundTag(FRUIT_ORE_OBJECT, fruitNBT);
		itemStack.setTagCompound(nbttagcompound);
		list.add(itemStack);
	}

	static {
		// register fruits
		fruitsList[Block.oreIron.blockID] = new FruitOreObject(Block.oreIron.blockID).setFruitColor(0xd1af93);
		fruitsList[Block.oreGold.blockID] = new FruitOreObject(Block.oreGold.blockID).setFruitColor(0xfcee4b);
		fruitsList[Block.oreDiamond.blockID] = new FruitOreObject(Block.oreDiamond.blockID).setFruitColor(0x6dcefb);
		fruitsList[Block.oreLapis.blockID] = new FruitOreObject(Block.oreLapis.blockID).setFruitColor(0x1542cc);
		fruitsList[Block.oreRedstone.blockID] = new FruitOreObject(Block.oreRedstone.blockID).setFruitColor(0xaa0404);
		fruitsList[Block.oreEmerald.blockID] = new FruitOreObject(Block.oreEmerald.blockID).setFruitColor(0x24af5a);
		fruitsList[Block.glowStone.blockID] = new FruitOreObject(Block.glowStone.blockID).setFruitColor(0xffbc5e);
		fruitsList[Block.oreNetherQuartz.blockID] = new FruitOreObject(Block.oreNetherQuartz.blockID).setFruitColor(0xe7e1d8);
		fruitsList[Block.oreCoal.blockID] = new FruitOreObject(Block.oreCoal.blockID).setFruitColor(0x343434);
	}

}
