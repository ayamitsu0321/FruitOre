package ayamitsu.fruitore.client;

import ayamitsu.fruitore.Proxy;
import ayamitsu.fruitore.block.TileEntityFruitOre;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

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
