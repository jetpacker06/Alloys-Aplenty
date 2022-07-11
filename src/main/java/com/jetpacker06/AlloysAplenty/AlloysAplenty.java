package com.jetpacker06.AlloysAplenty;

import com.jetpacker06.AlloysAplenty.item.ModItemsAndBlocks;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("alloysaplenty")
public class AlloysAplenty {
    public static final String MOD_ID = "alloysaplenty";
    public static final Logger LOGGER = LogManager.getLogger();

    public AlloysAplenty() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemsAndBlocks.BootUp(eventBus);
        eventBus.addListener(this::handleColorEvent);
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
    public void handleColorEvent(ColorHandlerEvent.Item event) {
        AlloysAplenty.LOGGER.info("It's color time!");
        event.getItemColors().register((pStack, pTintIndex) -> 0x4634eb, ModItemsAndBlocks.TEST_ITEM.get());
    }
}
