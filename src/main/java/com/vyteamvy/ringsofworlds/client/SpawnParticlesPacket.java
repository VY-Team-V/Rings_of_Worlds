package com.vyteamvy.ringsofworlds.client;

import com.vyteamvy.ringsofworlds.client.ClientEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnParticlesPacket {
    private final BlockPos pos;

    public SpawnParticlesPacket(BlockPos pos) {
        this.pos = pos;
    }

    public SpawnParticlesPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientEvents.spawnParticles(pos);
        });
        ctx.get().setPacketHandled(true);
    }
}
