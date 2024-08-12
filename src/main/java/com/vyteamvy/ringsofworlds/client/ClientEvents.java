package com.vyteamvy.ringsofworlds.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "ringsofworlds", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
    }

    public static void spawnParticles(BlockPos pos) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null) {
            for (int i = 0; i < 75; i++) {
                double offsetX = mc.level.random.nextDouble() * 2 - 1;
                double offsetY = mc.level.random.nextDouble() * 2 - 1;
                double offsetZ = mc.level.random.nextDouble() * 2 - 1;
                mc.level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5 + offsetX, pos.getY() + 1 + offsetY, pos.getZ() + 0.5 + offsetZ, 0, 0, 0);
            }
        }
    }
}
