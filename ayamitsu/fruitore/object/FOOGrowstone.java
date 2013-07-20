package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOOGrowstone extends FruitOreObject {

	public FOOGrowstone(int blockId) {
		super(blockId);
	}

	public FOOGrowstone(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.glowstone.itemID;
	}

}
