package com.vyteamvy.ringsofworlds.item.custom;

import com.vyteamvy.ringsofworlds.CustomRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StandartRing extends Item {

    public StandartRing() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.ringsofworlds.standart_ring.desc").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("item.ringsofworlds.standart_ring.shift_hint").withStyle(ChatFormatting.GRAY));
        }
    }
}
