package ayamitsu.fruitore.object;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FOOCoal extends FruitOreObject {

	public FOOCoal(int blockId) {
		super(blockId);
	}

	public FOOCoal(int blockId, ItemStack itemStack) {
		super(blockId, itemStack);
	}

	@Override
	public boolean matchRecipe(ItemStack itemStack) {
		return itemStack.itemID == Item.coal.itemID && itemStack.getItemDamage() == 0;
	}

}
