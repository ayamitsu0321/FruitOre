package ayamitsu.fruitore.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ayamitsu.fruitore.Proxy;
import ayamitsu.fruitore.block.TileEntityFruitOre;

public class ClientProxy extends Proxy {

	@Override
	public void load() {
		ClientRegistry.registerTileEntity(TileEntityFruitOre.class, "ayamitsu.fruitore.FruitOre", new TileEntityFruitOreRenderer());
	}

	@Override
	public int getUniqueBlockRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

}
