package com.vyteamvy.ringsofworlds.item.custom;

import com.vyteamvy.ringsofworlds.ModConfig;
import com.vyteamvy.ringsofworlds.client.ModNetwork;
import com.vyteamvy.ringsofworlds.client.SpawnParticlesPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetherRing extends BaseRing {

    public NetherRing() {
        super(new Item.Properties().stacksTo(1).durability(ModConfig.NETHER_RING_1_DURABILITY).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.ringsofworlds.nether_ring.desc").withStyle(ChatFormatting.RED));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.ringsofworlds.nether_ring.usage").withStyle(ChatFormatting.RED));
            tooltip.add(Component.literal("  - Shift + Right-click on a block: ").withStyle(ChatFormatting.GRAY).append(Component.translatable("item.ringsofworlds.nether_ring.set_tp").withStyle(ChatFormatting.GREEN)));
            tooltip.add(Component.literal("  - Right-click in the air: ").withStyle(ChatFormatting.GRAY).append(Component.translatable("item.ringsofworlds.nether_ring.teleport").withStyle(ChatFormatting.GREEN)));
            BlockPos savedPos = readCoordinatesFromNBT(stack);
            if (savedPos != null) {
                tooltip.add(Component.literal(""));
                tooltip.add(Component.literal("Saved Coordinates: ").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal(savedPos.getX() + ", " + savedPos.getY() + ", " + savedPos.getZ()).withStyle(ChatFormatting.GREEN)));
            }
        } else {
            tooltip.add(Component.translatable("item.ringsofworlds.nether_ring.shift_hint").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        long lastUse = getLastUseTime(itemstack);
        long currentTime = level.getGameTime();

        if (currentTime - lastUse >= ModConfig.NETHER_RING_1_COOLDOWN) {
            if (!level.isClientSide) {
                if (level.dimension() == Level.NETHER) {
                    if (player.isShiftKeyDown()) {
                        HitResult hitResult = player.pick(20.0D, 0.0F, false);
                        if (hitResult.getType() == HitResult.Type.BLOCK) {
                            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                            saveCoordinatesToNBT(itemstack, blockHitResult.getBlockPos());
                            player.playSound(SoundEvents.AMETHYST_BLOCK_PLACE, 1.0F, 1.0F);
                            player.displayClientMessage(Component.literal("Coordinates saved!").withStyle(ChatFormatting.GRAY), true);
                        }
                    } else {
                        BlockPos savedPos = readCoordinatesFromNBT(itemstack);
                        if (savedPos != null) {
                            // Сохраняем координаты точки использования кольца
                            BlockPos usePos = new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ());

                            // Отправляем пакет на клиент для создания частиц в точке использования кольца
                            ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(usePos));

                            // Сохраняем координаты головы игрока в точке использования кольца
                            BlockPos useHeadPos = new BlockPos((int) player.getX(), (int) (player.getY() + player.getEyeHeight()), (int) player.getZ());
                            ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(useHeadPos));

                            player.teleportTo(savedPos.getX() + 0.5, savedPos.getY() + 1, savedPos.getZ() + 0.5);
                            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                            player.displayClientMessage(Component.literal("Teleported to saved coordinates!").withStyle(ChatFormatting.GRAY), true);

                            // Отправляем пакет на клиент для создания частиц в точке телепортации
                            ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(savedPos));

                            // Отправляем пакет на клиент для создания частиц на уровне головы игрока
                            BlockPos playerHeadPos = new BlockPos((int) player.getX(), (int) (player.getY() + player.getEyeHeight()), (int) player.getZ());
                            ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(playerHeadPos));
                        }
                    }
                } else {
                    player.displayClientMessage(Component.literal("The ring is too weak for this world").withStyle(ChatFormatting.GRAY), true);
                }
            }
            setLastUseTime(itemstack, currentTime);
            return InteractionResultHolder.success(itemstack);
        } else {
            player.displayClientMessage(Component.literal("Ring is on cooldown!").withStyle(ChatFormatting.GRAY), true);
            return InteractionResultHolder.fail(itemstack);
        }
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        ItemStack itemstack = player.getItemInHand(event.getHand());
        if (itemstack.getItem() instanceof NetherRing) {
            if (!player.isShiftKeyDown()) {
                ((NetherRing) itemstack.getItem()).use(event.getLevel(), player, event.getHand());
            }
        }
    }
}