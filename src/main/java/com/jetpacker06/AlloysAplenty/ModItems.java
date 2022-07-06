package com.jetpacker06.AlloysAplenty;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final Item.Properties prop = new Item.Properties().tab(ItemGroups.ALLOYS_APLENTY_TAB);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlloysAplenty.MOD_ID);
    //begin items
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(prop));
    //end items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}