package ayamitsu.fruitore.block;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import ayamitsu.fruitore.object.FruitOreObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

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
		Packet packet = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 255, nbt);
		return packet;
	}

	// forge
	@Override
	public void onDataPacket(INetworkManager networkmanager, Packet132TileEntityData packet) {
		this.readFromNBT(packet.customParam1);
	}

}
