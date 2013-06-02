package ayamitsu.fruitore.block;

import java.util.List;
import java.util.Random;

import ayamitsu.fruitore.object.FruitOreObject;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * grow level is Block Meta, ID and Meta into TileEntity
 */
public class BlockFruitOre extends BlockContainer {

	public BlockFruitOre(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setTickRandomly(true);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFruitOre();
	}

	@Override
	public int idDropped(int meta, Random random, int dortune) {
		return 0;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int blockX, int blockY, int blockZ, int side, ItemStack itemStack) {
		if (itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey(FruitOreObject.FRUIT_ORE_OBJECT)) {
			NBTTagCompound fruitNBT = itemStack.getTagCompound().getCompoundTag(FruitOreObject.FRUIT_ORE_OBJECT);

			if (fruitNBT.hasKey(FruitOreObject.FRUIT_ORE_OBJECT_ID)) {
				if (FruitOreObject.fruitsList[fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_ID)] != null) {
					//return true;// debug

					if (this.canPlaceBlockAt(world, blockX, blockY, blockZ)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canBlockStay(par1World, par2, par3, par4);
    }

	@Override
	public void onBlockPlacedBy(World world, int blockX, int blockY, int blockZ, EntityLiving living, ItemStack itemStack) {
		NBTTagCompound fruitNBT = itemStack.getTagCompound().getCompoundTag(FruitOreObject.FRUIT_ORE_OBJECT);
		int fruitId = fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_ID);
		int fruitMeta = fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_META);

		TileEntity tileEntity = world.getBlockTileEntity(blockX, blockY, blockZ);

		if (tileEntity instanceof TileEntityFruitOre) {
			((TileEntityFruitOre)tileEntity).setFruitIdAndMeta(fruitId, fruitMeta);
		}
	}

	@Override
	public boolean canBlockStay(World world, int blockX, int blockY, int blockZ) {
		// 真上が普通のBlock//で、その上に液体、もしくは普通のBlockがあり、その上に液体があれば
		return true;//world.isBlockNormalCube(blockX, blockY + 1, blockZ);// && (world.getBlockMaterial(blockX, blockY + 2, blockZ).isLiquid() || world.isBlockNormalCube(blockX, blockY + 2, blockZ) && world.getBlockMaterial(blockX, blockY + 3, blockZ).isLiquid());
	}

	protected void checkFruitChange(World world, int blockX, int blockY, int blockZ) {
		if (!this.canBlockStay(world, blockX, blockY, blockZ)) {
			this.harvestFruit(world, blockX, blockY, blockZ);
			world.setBlockToAir(blockX, blockY, blockZ);
		}
	}

	protected void harvestFruit(World world, int blockX, int blockY, int blockZ) {
		TileEntity tileEntity = world.getBlockTileEntity(blockX, blockY, blockZ);

		if (tileEntity instanceof TileEntityFruitOre) {
			TileEntityFruitOre tileFruitOre = (TileEntityFruitOre)tileEntity;
			FruitOreObject fruitOre = FruitOreObject.fruitsList[tileFruitOre.getFruitId()];

			if (fruitOre != null) {
				ItemStack dropItem = fruitOre.getDropItem(tileFruitOre.getFruitMeta(), world.rand, world.getBlockMetadata(blockX, blockY, blockZ));

				if (dropItem != null) {
					this.dropBlockAsItem_do(world, blockX, blockY, blockZ, dropItem);// drop item
				}
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        this.checkFruitChange(par1World, par2, par3, par4);
    }

	@Override
	public void updateTick(World world, int blockX, int blockY, int blockZ, Random random) {
		this.checkFruitChange(world, blockX, blockY, blockZ);

		TileEntity tileEntity = world.getBlockTileEntity(blockX, blockY, blockZ);

		if (tileEntity instanceof TileEntityFruitOre) {
			TileEntityFruitOre fruitOreTile = (TileEntityFruitOre)tileEntity;
			FruitOreObject fruitOreObject = FruitOreObject.fruitsList[fruitOreTile.getFruitId()];

			if (fruitOreObject != null) {
				int meta = world.getBlockMetadata(blockX, blockY, blockZ);

				if (this.canGrowFruit(fruitOreObject, world, blockX, blockY, blockZ, meta, random)) {
					this.growFruit(world, blockX, blockY, blockZ, meta);
				}
			}
		}
	}

	protected boolean canGrowFruit(FruitOreObject fruitOreObject, World world, int blockX, int blockY, int blockZ, int meta, Random random) {
		return meta < fruitOreObject.getMaxGrowLevel() && random.nextInt(fruitOreObject.getGrowthRandomRate()) == 0;
	}

	protected void growFruit(World world, int blockX, int blockY, int blockZ, int meta) {
		world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta++, 2);
	}

	@Override
	public void breakBlock(World world, int blockX, int blockY, int blockZ, int par5, int par6) {
		this.harvestFruit(world, blockX, blockY, blockZ);
		super.breakBlock(world, blockX, blockY, blockZ, par5, par6);
	}

	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));

		if (id != this.blockID) {
			for (int i = 0; i < FruitOreObject.fruitsList.length; i++) {
				if (FruitOreObject.fruitsList[i] != null) {
					FruitOreObject.fruitsList[i].getSubFruits(id, tab, list);
				}
			}
		}
	}

}
