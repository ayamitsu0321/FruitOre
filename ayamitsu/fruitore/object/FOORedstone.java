package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOORedstone extends FruitOreObject {

	public FOORedstone(int blockId) {
		super(blockId);
	}

	public FOORedstone(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.redstone.itemID;
	}

}
