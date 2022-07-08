package com.jetpacker06.AlloysAplenty;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //default item properties
    public static final Item.Properties prop = new Item.Properties().tab(ItemGroups.ALLOYS_APLENTY_TAB);
    //DRs
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlloysAplenty.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlloysAplenty.MOD_ID);
    //begin items
    //public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(prop));
    //end items
    public static void registerAllItems() {
        AlloysAplenty.LOGGER.info("Registering items...");
        ITEMS.register("copper_nugget", () -> new Item(prop));
        String[] metalsList = {
                "testmetal1",
                "testmetal2"
        };
        String[] itemsList = {
                "ingot",
                "nugget"
        };
        String[] blocksList = {
                "block",
                "sheet_metal"
        };
        for (String metal : metalsList) {
            for (String item : itemsList) {
                ITEMS.register(metal + "_" + item, () -> new Item(prop));
            }
        }
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}