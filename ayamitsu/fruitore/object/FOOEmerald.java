package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOOEmerald extends FruitOreObject {

	public FOOEmerald(int blockId) {
		super(blockId);
	}

	public FOOEmerald(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.emerald.itemID;
	}

}
