package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOOLapis extends FruitOreObject {

	public FOOLapis(int blockId) {
		super(blockId);
	}

	public FOOLapis(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.dyePowder.itemID && itemStack.getItemDamage() == 4;
	}

}
