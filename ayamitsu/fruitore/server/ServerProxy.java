package ayamitsu.fruitore.server;

import cpw.mods.fml.common.registry.GameRegistry;
import ayamitsu.fruitore.Proxy;
import ayamitsu.fruitore.block.TileEntityFruitOre;

public class ServerProxy extends Proxy {

	@Override
	public void load() {
		GameRegistry.registerTileEntity(TileEntityFruitOre.class, "ayamitsu.fruitore.FruitOre");
	}

	@Override
	public int getUniqueBlockRenderId() {
		return -1;
	}

}
