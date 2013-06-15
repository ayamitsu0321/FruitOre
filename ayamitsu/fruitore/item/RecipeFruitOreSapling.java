package ayamitsu.fruitore.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ayamitsu.fruitore.FruitOre;
import ayamitsu.fruitore.object.FruitOreObject;

public class RecipeFruitOreSapling implements IRecipe {

	protected ItemStack outputItem;

	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world) {
		List<ItemStack> items = new ArrayList<ItemStack>();

		for (int i = 0; i < inventorycrafting.getSizeInventory(); i++) {
			if (inventorycrafting.getStackInSlot(i) != null) {
				items.add(inventorycrafting.getStackInSlot(i));
			}
		}

		if (items.size() != 2) {
			return false;
		}

		ItemStack fruitSaplingBase = null;
		ItemStack fruitOreMaterial = null;

		for (int i = 0; i < items.size(); i++) {
			ItemStack itemStack = items.get(i);

			if (itemStack.itemID == FruitOre.fruitOreSaplingItem.itemID && (itemStack.getTagCompound() == null || !itemStack.getTagCompound().hasKey(FruitOreObject.FRUIT_ORE_OBJECT))) {
				fruitSaplingBase = itemStack;
			} else {
				for (int j = 0; j < FruitOreObject.fruitsList.length; j++) {
					if (FruitOreObject.fruitsList[j] != null && FruitOreObject.fruitsList[j].matchRecipe(itemStack)) {
						fruitOreMaterial = itemStack;
					}
				}
			}
		}

		if (fruitSaplingBase != null && fruitOreMaterial != null) {
			this.outputItem = new ItemStack(FruitOre.fruitOreSaplingItem);
			this.outputItem.setTagCompound(new NBTTagCompound());
			NBTTagCompound fruitNBT = new NBTTagCompound();
			fruitNBT.setInteger(FruitOreObject.FRUIT_ORE_OBJECT_ID, fruitOreMaterial.itemID);
			fruitNBT.setInteger(FruitOreObject.FRUIT_ORE_OBJECT_META, fruitOreMaterial.getItemDamage());
			this.outputItem.getTagCompound().setCompoundTag(FruitOreObject.FRUIT_ORE_OBJECT, fruitNBT);
			return true;
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
		return this.outputItem.copy();
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.outputItem;
	}

}
