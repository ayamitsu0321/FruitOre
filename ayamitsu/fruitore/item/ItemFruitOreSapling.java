package ayamitsu.fruitore.item;

import java.lang.reflect.Field;
import java.util.List;

import ayamitsu.fruitore.object.FruitOreObject;
import ayamitsu.util.reflect.Reflector;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * this metadata is ID of fruit,
 * this is intermediate material
 */
public class ItemFruitOreSapling extends ItemBlock {

	public ItemFruitOreSapling(int id, int blockId) {
		super(id);
		try {
			Field field = ItemBlock.class.getDeclaredField(Reflector.isRenameTable() ? "blockID" : "field_77885_a");
			field.setAccessible(true);
			field.set(this, blockId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		NBTTagCompound nbttagcompound = itemStack.getTagCompound();

		if (nbttagcompound != null && nbttagcompound.hasKey(FruitOreObject.FRUIT_ORE_OBJECT)) {
			NBTTagCompound fruitNBT = nbttagcompound.getCompoundTag(FruitOreObject.FRUIT_ORE_OBJECT);
			int fruitId = fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_ID);
			int fruitMeta = fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_META);
			list.add((new StringBuilder()).append("Fruit:").append(FruitOreObject.fruitsList[fruitId].getDisplayName(fruitMeta)).toString());
		}
	}

}