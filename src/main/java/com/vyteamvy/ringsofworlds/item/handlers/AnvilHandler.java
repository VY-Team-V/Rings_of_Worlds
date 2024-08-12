package com.vyteamvy.ringsofworlds.item.handlers;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "ringsofworlds", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnvilHandler {
//    @SubscribeEvent
//    public static void onAnvilUpdate(@NotNull AnvilUpdateEvent event) {
//        ItemStack left = event.getLeft();
//        ItemStack right = event.getRight();
//
//        if (left.getItem() instanceof KeyItem && right.getItem() == Items.DIAMOND) {
//            ItemStack output = left.copy();
//            left.setDamageValue(0);
//            output.setDamageValue(0);
//
//            CompoundTag nbt = left.getTag();
//            if (nbt != null) {
//                output.setTag(nbt.copy());
//            }
//
//            event.setOutput(output);
//            event.setCost(1);
//            event.setMaterialCost(1);
//        }
//    }
}
