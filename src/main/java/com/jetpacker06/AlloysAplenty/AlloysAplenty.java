package com.jetpacker06.AlloysAplenty;

import com.jetpacker06.AlloysAplenty.item.ModItemsAndBlocks;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

import static com.jetpacker06.AlloysAplenty.item.ModItemsAndBlocks.ItemColorMap;

@Mod("alloysaplenty")
public class AlloysAplenty {
    public static final String MOD_ID = "alloysaplenty";
    public static final Logger LOGGER = LogManager.getLogger();

    public AlloysAplenty() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemsAndBlocks.init(eventBus);
        eventBus.addListener(this::handleColorEvent);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static void log(Object m) {
        LOGGER.info(m);
    }
    public static void logList(String[] list) {
        for (String s : list) log(s);
    }
    public static boolean isThingInList(String thing, String[] list) {
        for (String i : list) {
            if (i.equals(thing)) {
                return true;
            }
        }
        return false;
    }
    public void handleColorEvent(ColorHandlerEvent.Item event) {
        ItemColors c = event.getItemColors();
        log("It's color time!");

        for (RegistryObject<Item> item : ItemColorMap.keySet()) {
            Integer x = ItemColorMap.get(item);
            c.register((pStack, pTintIndex) -> x, item.get());
        }
    }
    public static void printAllKeysAndValues(HashMap<String, Integer> map) {
        for (String o : map.keySet()) {
            log(o + ", " + map.get(o));
        }
    }
}