package com.jetpacker06.AlloysAplenty;

import com.jetpacker06.AlloysAplenty.item.ModItemsAndBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@Mod("alloysaplenty")
public class AlloysAplenty {
    public static final String MOD_ID = "alloysaplenty";
    public static final Logger LOGGER = LogManager.getLogger();

    public AlloysAplenty() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemsAndBlocks.sequenceOfEvents(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static boolean IsThingInList(String thing, String[] list) {
        for (String i : list) {
            if (i.equals(thing)) {
                return true;
            }
        }
        return false;
    }
    public static void printAllKeysAndValuesSD(HashMap<String, Double> map) {
        for (String o : map.keySet()) {
            LOGGER.info(o + ", " + map.get(o));
        }
    }
    public static void printAllKeysAndValuesB(HashMap<RegistryObject<Block>, Integer> map) {
        for (RegistryObject<Block> o : map.keySet()) {
            LOGGER.info(o.getId() + ", " + map.get(o));
        }
    }
    public static void printAllKeysAndValues(HashMap<RegistryObject<Item>, Integer> map) {
        for (RegistryObject<Item> o : map.keySet()) {
            LOGGER.info(o.getId() + ", " + map.get(o));
        }
    }
}
