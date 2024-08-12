package com.vyteamvy.ringsofworlds;

import com.vyteamvy.ringsofworlds.block.ModBlocks;
import com.vyteamvy.ringsofworlds.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RingsOfWorlds.MODID);

    public static final RegistryObject<CreativeModeTab> RINGS_OF_WORLDS_TAB = CREATIVE_MODE_TABS.register("ringsofworlds_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ULTIMATE_RING.get()))
                    .title(Component.translatable("creativetab.ringsofworlds_tab"))
                    .displayItems((displayParameters, pOutput) -> {
                        pOutput.accept(ModItems.STANDART_RING.get());
                        pOutput.accept(ModItems.OVERWORLD_RING.get());
                        pOutput.accept(ModItems.NETHER_RING.get());
                        pOutput.accept(ModItems.ENDER_RING.get());
                        pOutput.accept(ModItems.OVERWORLD_RING_2.get());
                        pOutput.accept(ModItems.NETHER_RING_2.get());
                        pOutput.accept(ModItems.ENDER_RING_2.get());
                        pOutput.accept(ModItems.OVERWORLD_RING_3.get());
                        pOutput.accept(ModItems.NETHER_RING_3.get());
                        pOutput.accept(ModItems.ENDER_RING_3.get());
                        pOutput.accept(ModItems.ULTIMATE_RING.get());
                        pOutput.accept(ModItems.INTERDIMENSIONAL_SHARD.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> RINGS_OF_WORLDS_ABSINTIUM_TAB = CREATIVE_MODE_TABS.register("ringsofworlds_absintium_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ABSINTIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.ringsofworlds_absintium_tab"))
                    .displayItems((displayParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.ABSINTIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.ABSINTIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_ABSINTIUM_ORE.get());
                        pOutput.accept(ModBlocks.RAW_ABSINTIUM_BLOCK.get());
                        pOutput.accept(ModItems.RAW_ABSINTIUM.get());
                        pOutput.accept(ModItems.ABSINTIUM_INGOT.get());
                        pOutput.accept(ModItems.ABSINTIUM_NUGGET.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> RINGS_OF_WORLDS_STEEL_TAB = CREATIVE_MODE_TABS.register("ringsofworlds_steel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.ringsofworlds_steel_tab"))
                    .displayItems((displayParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.STEEL_BLOCK.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.STEEL_NUGGET.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
