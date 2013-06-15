package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOODiamond extends FruitOreObject {

	public FOODiamond(int blockId) {
		super(blockId);
	}

	public FOODiamond(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.diamond.itemID;
	}

}
