package ayamitsu.fruitore.network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals("fruitore.te")) {
			this.handleTileEntityPacket(packet, ((EntityPlayer)player).worldObj);
		}
	}

	protected void handleTileEntityPacket(Packet250CustomPayload packet, World world) {
		NBTTagCompound nbt = null;

		try {
			nbt = CompressedStreamTools.decompress(packet.data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (nbt != null) {
			int x = nbt.getInteger("x");
			int y = nbt.getInteger("y");
			int z = nbt.getInteger("z");
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

			if (tileEntity != null) {
				tileEntity.readFromNBT(nbt);
			}
		}
	}

}
