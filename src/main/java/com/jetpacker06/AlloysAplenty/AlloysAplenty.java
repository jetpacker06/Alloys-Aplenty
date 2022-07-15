package com.jetpacker06.AlloysAplenty;

import com.jetpacker06.AlloysAplenty.register.ModItemsAndBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

import static com.jetpacker06.AlloysAplenty.register.ModItemsAndBlocks.BlockColorMap;
import static com.jetpacker06.AlloysAplenty.register.ModItemsAndBlocks.ItemColorMap;

@Mod("alloysaplenty")
public class AlloysAplenty {
    public static final String MOD_ID = "alloysaplenty";
    public static final Logger LOGGER = LogManager.getLogger();

    public AlloysAplenty() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemsAndBlocks.init(eventBus);
        eventBus.addListener(this::handleItemColors);
        eventBus.addListener(this::handleBlockColors);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static void log(Object m) {
        LOGGER.info(m);
    }
    public static void logList(String[] list) {
        for (String s : list) log(s);
    }
    public static void printAllKeysAndValues(HashMap<String, Integer> map) {
        for (String o : map.keySet()) {
            log(o + ", " + map.get(o));
        }
    }
    public static boolean isThingInList(String thing, String[] list) {
        for (String i : list) {
            if (i.equals(thing)) {
                return true;
            }
        }
        return false;
    }
    public void handleItemColors(ColorHandlerEvent.Item event) {
        ItemColors c = event.getItemColors();
        log("Coloring items...");

        for (RegistryObject<Item> item : ItemColorMap.keySet()) {
            Integer x = ItemColorMap.get(item);
            c.register((pStack, pTintIndex) -> x, item.get());
        }
    }
    public void handleBlockColors(ColorHandlerEvent.Block event) {
        log("Coloring blocks...");
        BlockColors blockcolors = event.getBlockColors();

        blockcolors.register((BlockState p_92596_, BlockAndTintGetter p_92597_, BlockPos p_92598_, int p_92599_) -> {
            return 0x2600ff;
        }, ModItemsAndBlocks.TEST_BLOCK.get());
        for (RegistryObject<Block> block : BlockColorMap.keySet()) {
            //log(block.get().getName().toString());
            Integer x = BlockColorMap.get(block);
            //log(x);
            blockcolors.register((p1, p2, p3, p4) -> x, block.get());
        }
    }
}