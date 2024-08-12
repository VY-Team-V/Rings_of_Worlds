package com.vyteamvy.ringsofworlds.item;

import com.vyteamvy.ringsofworlds.CustomRarity;
import com.vyteamvy.ringsofworlds.RingsOfWorlds;
import com.vyteamvy.ringsofworlds.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RingsOfWorlds.MODID);

    public static final RegistryObject<Item> STANDART_RING = ITEMS.register("standart_ring",
            StandartRing::new);

    public static final RegistryObject<Item> OVERWORLD_RING = ITEMS.register("overworld_ring",
            OverworldRing::new);

    public static final RegistryObject<Item> OVERWORLD_RING_2 = ITEMS.register("overworld_ring_2",
            OverworldRing2::new);

    public static final RegistryObject<Item> OVERWORLD_RING_3 = ITEMS.register("overworld_ring_3",
            OverworldRing3::new);

    public static final RegistryObject<Item> NETHER_RING = ITEMS.register("nether_ring",
            NetherRing::new);

    public static final RegistryObject<Item> NETHER_RING_2 = ITEMS.register("nether_ring_2",
            NetherRing2::new);

    public static final RegistryObject<Item> NETHER_RING_3 = ITEMS.register("nether_ring_3",
            NetherRing3::new);

    public static final RegistryObject<Item> ENDER_RING = ITEMS.register("ender_ring",
            EnderRing::new);

    public static final RegistryObject<Item> ENDER_RING_2 = ITEMS.register("ender_ring_2",
            EnderRing2::new);

    public static final RegistryObject<Item> ENDER_RING_3 = ITEMS.register("ender_ring_3",
            EnderRing3::new);

    public static final RegistryObject<Item> ULTIMATE_RING = ITEMS.register("ultimate_ring",
            UltimateRing::new);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ABSINTIUM_INGOT = ITEMS.register("absintium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ABSINTIUM_NUGGET = ITEMS.register("absintium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ABSINTIUM = ITEMS.register("raw_absintium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INTERDIMENSIONAL_SHARD = ITEMS.register("interdimensional_shard",
            () -> new Item(new Item.Properties().rarity(CustomRarity.LEGENDARY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
