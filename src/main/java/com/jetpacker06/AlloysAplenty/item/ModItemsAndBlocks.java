package com.jetpacker06.AlloysAplenty.item;

import com.jetpacker06.AlloysAplenty.AlloysAplenty;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class ModItemsAndBlocks {
    //track how many items are registered
    private static int registeredItems = 0;
    private static int registeredBlocks = 0;
    //maps that contain metal names and their corresponding color hex
    public static HashMap<String, Double> NewMetalsAndColors = new HashMap<>();
    public static HashMap<String, Double> ExistingMetalsAndColors = new HashMap<>();
    //methods to initialize the above maps
    private static void initMetalsAndColors() {
        NewMetalsAndColors.put("silver", 1d);
        NewMetalsAndColors.put("nickel", 1d);
        NewMetalsAndColors.put("lead", 1d);
        NewMetalsAndColors.put("tin", 1d);
        NewMetalsAndColors.put("aluminum", 1d);
        NewMetalsAndColors.put("platinum", 1d);
        NewMetalsAndColors.put("magnesium", 1d);
        NewMetalsAndColors.put("boron", 1d);
        NewMetalsAndColors.put("lithium", 1d);
        NewMetalsAndColors.put("titanium", 1d);
        NewMetalsAndColors.put("cobalt", 1d);
        NewMetalsAndColors.put("chrome", 1d);
        NewMetalsAndColors.put("iridium", 1d);
        NewMetalsAndColors.put("osmium", 1d);
        NewMetalsAndColors.put("uranium", 1d);
        NewMetalsAndColors.put("thorium", 1d);
        NewMetalsAndColors.put("electrum", 1d);
        NewMetalsAndColors.put("brass", 1d);
        NewMetalsAndColors.put("bronze", 1d);
        NewMetalsAndColors.put("steel", 1d);
        NewMetalsAndColors.put("constantan", 1d);
        NewMetalsAndColors.put("invar", 1d);
        NewMetalsAndColors.put("terne", 1d);
        NewMetalsAndColors.put("corinthian_bronze", 1d);
        NewMetalsAndColors.put("sterling_silver", 1d);
        NewMetalsAndColors.put("titanium_gold", 1d);
        NewMetalsAndColors.put("sterling_platinum", 1d);
        NewMetalsAndColors.put("mag_thor", 1d);
        NewMetalsAndColors.put("beskar", 1d);
    }
    private static void initExistingMetalsAndColors() {
        ExistingMetalsAndColors.put("iron", 1d);
        ExistingMetalsAndColors.put("gold", 1d);
        ExistingMetalsAndColors.put("copper", 1d);
    }
    //maps that are used for coloring
    public static HashMap<RegistryObject<Item>, Integer> ItemColorMap = new HashMap<>();
    public static HashMap<RegistryObject<Block>, Integer> BlockColorMap = new HashMap<>();
    //default item and block properties
    private static final Item.Properties iProp = new Item.Properties().tab(ItemGroups.ALLOYS_APLENTY_TAB);
    private static final Block.Properties bProp = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK);
    //deferred registers
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlloysAplenty.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlloysAplenty.MOD_ID);
    //logger
    private static void log(Object o) {
        AlloysAplenty.LOGGER.info(o);
    }
    //item register method
    private static RegistryObject<Item> ingredient(String name, Item.Properties pProperties) {
        log(name);
        RegistryObject<Item> item = ITEMS.register(name, () -> new Item(pProperties));
        String[] splitName = name.split("1");
        String metalName = splitName[0];
        //log(metalName);
        try {
            double d = ExistingMetalsAndColors.get(metalName);
            ItemColorMap.put(item, (int)d);
        } catch (NullPointerException e) {
            double d = NewMetalsAndColors.get(metalName);
            ItemColorMap.put(item, (int)d);
        }
        registeredItems++;
        return item;
    }
    //block and blockitem register methods
    private static  RegistryObject<Block> registerBlock(String name, Supplier<Block> block, CreativeModeTab tab) {
        log(name);
        String metalName = name.split("1")[0];
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);
        try {
            double d = ExistingMetalsAndColors.get(metalName);
            BlockColorMap.put(toReturn, (int)d);
        } catch (NullPointerException e) {
            double d = NewMetalsAndColors.get(metalName);
            BlockColorMap.put(toReturn, (int)d);
        }
        registerBlockItem(name, toReturn, tab);
        registeredBlocks++;
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        RegistryObject<Item> blockitem = ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return blockitem;
    }
    //sequence of events
    public static void BootUp(IEventBus eventBus) {
        log("Initializing maps");
        initMetalsAndColors();
        initExistingMetalsAndColors();
        log("Registering items");
        registerAll();
        log("Registered "+ registeredItems + " items and "+ registeredBlocks + " blocks.");
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
    //register method
    public static void registerAll() {
        ingredient("copper1nugget", iProp);
        String[] newMetalsList = NewMetalsAndColors.keySet().toArray(new String[0]);
        String[] existingMetalsList = ExistingMetalsAndColors.keySet().toArray(new String[0]);

        String[] newItemsList = {
                "sheet",
                "gear",
                "coin",
                "rod",
                "wire",
                "screw"
        };
        String[] existingItemsList = {
                "ingot",
                "nugget"
        };
        String[] newBlocksList = {
                "sheet_metal"
        };
        for (String metal : newMetalsList) {
            for (String item : newItemsList) {
                ingredient(metal + "1" + item, iProp);
            }
            for (String item : existingItemsList) {
                ingredient(metal + "1" + item, iProp);
            }
            for (String block : newBlocksList) {
                registerBlock(metal + "1" + block, () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
            }
            registerBlock(metal + "1block", () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
        }
        for (String metal : existingMetalsList) {
            for (String item : newItemsList) {
                ingredient(metal + "1" + item, iProp);
            }
            for (String block : newBlocksList) {
                registerBlock(metal + "1" + block, () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
            }
        }
    }

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(iProp));
}