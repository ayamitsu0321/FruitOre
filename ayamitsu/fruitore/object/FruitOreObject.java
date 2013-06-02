package ayamitsu.fruitore.object;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

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
		return 0;//7;
	}

	public int getGrowthRandomRate() {
		return 16;
	}

	protected int getDropValue(int meta, int growLevel) {
		return growLevel >= this.getHarvetableLevel() ? 3 : 0;
	}

	public ItemStack getDropItem(int meta, Random random, int growLevel) {
		int value = this.getDropValue(meta, growLevel);

		if (value > 0) {
			try {
				return this.fruitItem != null ? new ItemStack(this.fruitItem.itemID, value, this.fruitItem.getItemDamage()) : new ItemStack(Block.blocksList[this.fruitId].idDropped(meta, random, 0), value, Block.blocksList[this.fruitId].damageDropped(meta));
			} catch (NullPointerException e) {
				e.printStackTrace();// missing fruit object !
			}
		}

		return null;
	}

	public ItemStack getRenderItem(int meta, Random random) {
		return this.fruitItem != null ? this.fruitItem : (this.fruitItem = new ItemStack(Block.blocksList[this.fruitId].idDropped(meta, random, 0), 1, Block.blocksList[this.fruitId].damageDropped(meta)));
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
		fruitsList[Block.oreIron.blockID] = new FruitOreObject(Block.oreIron.blockID);
	}

}
