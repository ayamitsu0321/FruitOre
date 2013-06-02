package ayamitsu.fruitore;

import java.io.IOException;

import ayamitsu.fruitore.block.BlockFruitOre;
import ayamitsu.fruitore.block.TileEntityFruitOre;
import ayamitsu.fruitore.item.ItemFruitOreSapling;
import ayamitsu.fruitore.item.RecipeFruitOreSapling;
import ayamitsu.util.io.Configuration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
	modid = "ayamitsu.fruitore",
	name = "FruitOre",
	version = "1.0.0"
)
public class FruitOre {

	@Mod.Instance("ayamitsu.fruitore")
	public static FruitOre instance;

	@SidedProxy(clientSide = "ayamitsu.fruitore.client.ClientProxy", serverSide = "ayamitsu.fruitore.server.ServerProxy")
	public static Proxy proxy;

	public static Block fruitOreBlock;

	public static int fruitOreBlockId;

	public static Item fruitOreSaplingItem;

	public static int fruitOreSaplingItemId;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration conf = new Configuration(event.getSuggestedConfigurationFile());

		try {
			conf.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.fruitOreBlockId = conf.getProperty("fruitOreBlockId", 1800).getInt();
		this.fruitOreSaplingItemId = conf.getProperty("fruitOreSaplingItemId", 14000).getInt();

		try {
			conf.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Mod.Init
	public void init(FMLInitializationEvent event) {
		this.fruitOreBlock = new BlockFruitOre(this.fruitOreBlockId, Material.iron).setUnlocalizedName("ayamitsu.fruitore.fruitore").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this.fruitOreBlock, "ayamitsu.fruitore.fruitore");
		LanguageRegistry.instance().addNameForObject(this.fruitOreBlock, "en_US", "Fruit Ore Block");

		this.fruitOreSaplingItem = new ItemFruitOreSapling(this.fruitOreSaplingItemId - 256, this.fruitOreBlock.blockID).setUnlocalizedName("atamitsu.fruitore.fruitoresapling").setCreativeTab(CreativeTabs.tabBlock);

		GameRegistry.addRecipe(new RecipeFruitOreSapling());

		this.proxy.load();
	}

}
