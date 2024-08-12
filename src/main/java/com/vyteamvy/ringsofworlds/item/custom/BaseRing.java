package com.vyteamvy.ringsofworlds.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class BaseRing extends Item {

    private static final String LAST_USE_TAG = "lastUse";

    public BaseRing(Properties properties) {
        super(properties);
    }

    protected long getLastUseTime(ItemStack stack) {
        CompoundTag nbt = stack.getTag();
        return nbt != null && nbt.contains(LAST_USE_TAG) ? nbt.getLong(LAST_USE_TAG) : 0;
    }

    protected void setLastUseTime(ItemStack stack, long time) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putLong(LAST_USE_TAG, time);
    }

    protected void saveCoordinatesToNBT(ItemStack itemstack, BlockPos pos) {
        CompoundTag tag = itemstack.getOrCreateTag();
        tag.putInt("SavedX", pos.getX());
        tag.putInt("SavedY", pos.getY());
        tag.putInt("SavedZ", pos.getZ());
    }

    protected BlockPos readCoordinatesFromNBT(ItemStack itemstack) {
        CompoundTag tag = itemstack.getTag();
        if (tag != null && tag.contains("SavedX") && tag.contains("SavedY") && tag.contains("SavedZ")) {
            int x = tag.getInt("SavedX");
            int y = tag.getInt("SavedY");
            int z = tag.getInt("SavedZ");
            return new BlockPos(x, y, z);
        }
        return null;
    }
}
