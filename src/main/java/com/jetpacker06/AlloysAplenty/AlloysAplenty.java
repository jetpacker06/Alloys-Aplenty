package com.jetpacker06.AlloysAplenty;

import com.jetpacker06.AlloysAplenty.item.ModItemsAndBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jetpacker06.AlloysAplenty.item.ModItemsAndBlocks.registerALl;

@Mod("alloysaplenty")
public class AlloysAplenty {
    public static final String MOD_ID = "alloysaplenty";
    public static final Logger LOGGER = LogManager.getLogger();

    public AlloysAplenty() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        registerALl();
        ModItemsAndBlocks.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
