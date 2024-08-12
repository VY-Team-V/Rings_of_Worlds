package com.vyteamvy.ringsofworlds.block;

import com.vyteamvy.ringsofworlds.RingsOfWorlds;
import com.vyteamvy.ringsofworlds.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RingsOfWorlds.MODID);

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f, 6f)));

    public static final RegistryObject<Block> ABSINTIUM_BLOCK = registerBlock("absintium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f, 6f)));

    public static final RegistryObject<Block> ABSINTIUM_ORE = registerBlock("absintium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(6f, 6f)));

    public static final RegistryObject<Block> DEEPSLATE_ABSINTIUM_ORE = registerBlock("deepslate_absintium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).strength(7f, 6f)));

    public static final RegistryObject<Block> RAW_ABSINTIUM_BLOCK = registerBlock("raw_absintium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).strength(6f, 6f)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}