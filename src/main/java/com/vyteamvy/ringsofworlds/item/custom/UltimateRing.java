package com.vyteamvy.ringsofworlds.item.custom;

import com.vyteamvy.ringsofworlds.CustomRarity;
import com.vyteamvy.ringsofworlds.ModConfig;
import com.vyteamvy.ringsofworlds.client.ModNetwork;
import com.vyteamvy.ringsofworlds.client.SpawnParticlesPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
import java.util.Objects;

public class UltimateRing extends BaseRing {

    private BlockPos savedPos;
    private String savedDimension;

    public UltimateRing() {
        super(new Item.Properties().stacksTo(1).durability(ModConfig.ULTIMATE_RING_DURABILITY).rarity(CustomRarity.MYSTIC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.ringsofworlds.ultimate_ring.desc").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.ringsofworlds.ultimate_ring.usage").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("  - Shift + Right-click on a block: ").withStyle(ChatFormatting.GRAY).append(Component.translatable("item.ringsofworlds.ultimate_ring.set_tp").withStyle(ChatFormatting.GREEN)));
            tooltip.add(Component.literal("  - Right-click in the air: ").withStyle(ChatFormatting.GRAY).append(Component.translatable("item.ringsofworlds.ultimate_ring.teleport").withStyle(ChatFormatting.GREEN)));

            if (savedPos != null && savedDimension != null) {
                tooltip.add(Component.literal(""));
                tooltip.add(Component.literal("Saved Coordinates: ").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal(savedPos.getX() + ", " + savedPos.getY() + ", " + savedPos.getZ()).withStyle(ChatFormatting.GREEN)));
                tooltip.add(Component.literal("Saved Dimension: ").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal(savedDimension).withStyle(ChatFormatting.GREEN)));
            }
        } else {
            tooltip.add(Component.translatable("item.ringsofworlds.ultimate_ring.shift_hint").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player.isShiftKeyDown()) {
                HitResult hitResult = player.pick(20.0D, 0.0F, false);
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                    savedPos = blockHitResult.getBlockPos();
                    savedDimension = level.dimension().location().toString();
                    player.playSound(SoundEvents.AMETHYST_BLOCK_PLACE, 1.0F, 1.0F);
                    player.displayClientMessage(Component.literal("Coordinates saved!").withStyle(ChatFormatting.GRAY), true);
                }
            } else {
                if (savedPos != null && savedDimension != null) {
                    BlockPos usePos = new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ());

                    ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(usePos));

                    BlockPos useHeadPos = new BlockPos((int) player.getX(), (int) (player.getY() + player.getEyeHeight()), (int) player.getZ());
                    ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(useHeadPos));

                    ServerLevel destinationWorld = null;
                    if (savedDimension.equals(Level.OVERWORLD.location().toString())) {
                        destinationWorld = Objects.requireNonNull(level.getServer()).getLevel(Level.OVERWORLD);
                    } else if (savedDimension.equals(Level.NETHER.location().toString())) {
                        destinationWorld = Objects.requireNonNull(level.getServer()).getLevel(Level.NETHER);
                    } else if (savedDimension.equals(Level.END.location().toString())) {
                        destinationWorld = Objects.requireNonNull(level.getServer()).getLevel(Level.END);
                    }

                    if (destinationWorld != null) {
                        if (!level.dimension().location().toString().equals(savedDimension)) {
                            player.changeDimension(destinationWorld);
                        }
                        player.teleportTo(savedPos.getX() + 0.5, savedPos.getY() + 1, savedPos.getZ() + 0.5);

                        ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(savedPos));

                        BlockPos playerHeadPos = new BlockPos((int) player.getX(), (int) (player.getY() + player.getEyeHeight()), (int) player.getZ());
                        ModNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SpawnParticlesPacket(playerHeadPos));

                        itemstack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                        player.displayClientMessage(Component.literal("Teleported to saved coordinates!").withStyle(ChatFormatting.GRAY), true);
                    }
                }
            }
        }
        return InteractionResultHolder.success(itemstack);
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        ItemStack itemstack = player.getItemInHand(event.getHand());
        if (itemstack.getItem() instanceof UltimateRing) {
            if (!player.isShiftKeyDown()) {
                ((UltimateRing) itemstack.getItem()).use(event.getLevel(), player, event.getHand());
            }
        }
    }
}