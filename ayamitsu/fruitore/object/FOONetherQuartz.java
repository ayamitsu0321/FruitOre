package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOONetherQuartz extends FruitOreObject {

	public FOONetherQuartz(int blockId) {
		super(blockId);
	}

	public FOONetherQuartz(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.netherQuartz.itemID;
	}

}
