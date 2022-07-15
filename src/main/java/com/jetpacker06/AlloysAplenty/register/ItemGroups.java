package com.jetpacker06.AlloysAplenty.register;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemGroups {
    public static final CreativeModeTab ALLOYS_APLENTY_TAB = new CreativeModeTab("alloys_aplenty") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.COAL);
        }
    };
}
