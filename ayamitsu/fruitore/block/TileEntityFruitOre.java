package ayamitsu.fruitore.block;

import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import ayamitsu.fruitore.object.FruitOreObject;

public class TileEntityFruitOre extends TileEntity {

	protected int fruitId;
	protected int fruitMeta;

	public int getFruitId() {
		return fruitId;
	}

	public void setFruitId(int fruitId) {
		this.fruitId = fruitId;
	}

	public int getFruitMeta() {
		return fruitMeta;
	}

	public void setFruitMeta(int fruitMeta) {
		this.fruitMeta = fruitMeta;
	}

	public void setFruitIdAndMeta(int fruitId, int fruitMeta) {
		this.fruitId = fruitId;
		this.fruitMeta = fruitMeta;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagCompound fruitNBT = new NBTTagCompound();
		fruitNBT.setInteger(FruitOreObject.FRUIT_ORE_OBJECT_ID, this.fruitId);
		fruitNBT.setInteger(FruitOreObject.FRUIT_ORE_OBJECT_META, this.fruitMeta);
		nbttagcompound.setCompoundTag(FruitOreObject.FRUIT_ORE_OBJECT, fruitNBT);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);

		if (nbttagcompound.hasKey(FruitOreObject.FRUIT_ORE_OBJECT)) {
			NBTTagCompound fruitNBT = nbttagcompound.getCompoundTag(FruitOreObject.FRUIT_ORE_OBJECT);
			this.fruitId = fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_ID);
			this.fruitMeta = fruitNBT.getInteger(FruitOreObject.FRUIT_ORE_OBJECT_META);
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		byte[] data = null;

		try {
			data = CompressedStreamTools.compress(nbt);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (data != null) {
			Packet packet = new Packet250CustomPayload("fruitore.te", data);
			return packet;
		}

		return null;
	}

}
