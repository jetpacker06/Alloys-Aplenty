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
    //track registry counts
    public static int registeredItems = 0;
    public static int registeredBlocks = 0;
    //maps that contain metal names and their corresponding color hex
    public static HashMap<String, Integer> NewMetalsAndColors = new HashMap<>();
    public static HashMap<String, Integer> ExistingMetalsAndColors = new HashMap<>();
    //methods to initialize the above maps
    private static void initMetalsAndColors() {
        NewMetalsAndColors.put("silver", 0xC0C0C0);
        NewMetalsAndColors.put("nickel", 0xaba48a);
        NewMetalsAndColors.put("lead", 0x5c6274);
        NewMetalsAndColors.put("tin", 0x6e7174);
        NewMetalsAndColors.put("aluminum", 0xd0d5db);
        NewMetalsAndColors.put("platinum", 0x8dc9e1);
        NewMetalsAndColors.put("magnesium", 0x5d7a78);
        NewMetalsAndColors.put("boron", 0x837e79);
        NewMetalsAndColors.put("lithium", 0xd1d1d1);
        NewMetalsAndColors.put("titanium", 0xc0c3c1);
        NewMetalsAndColors.put("cobalt", 0x1e2b67);
        NewMetalsAndColors.put("chrome", 0xdbe4eb);
        NewMetalsAndColors.put("iridium", 0x393339);
        NewMetalsAndColors.put("osmium", 0x81fdfa);
        NewMetalsAndColors.put("uranium", 0x021f00);
        NewMetalsAndColors.put("thorium", 0x1c1c1b);
        NewMetalsAndColors.put("zinc", 0x9ec3bd);
        NewMetalsAndColors.put("electrum", 0xf2e279);
        NewMetalsAndColors.put("brass", 0xE1C16E);
        NewMetalsAndColors.put("bronze", 0xcd7f32);
        NewMetalsAndColors.put("steel", 0x2a3439);
        NewMetalsAndColors.put("constantan", 0xbf3300);
        NewMetalsAndColors.put("invar", 0x8d9596);
        NewMetalsAndColors.put("terne", 0x393d4a);
        NewMetalsAndColors.put("corinthian_bronze", 0xf5a353);
        NewMetalsAndColors.put("sterling_silver", 0xc5dced);
        NewMetalsAndColors.put("titanium_gold", 0xEAD963);
        NewMetalsAndColors.put("sterling_platinum", 0x99b9d1);
        NewMetalsAndColors.put("mag_thor", 0x2c1030);
        NewMetalsAndColors.put("beskar", 0x272329);
    }
    private static void initExistingMetalsAndColors() {
        ExistingMetalsAndColors.put("iron", 0xA8A8A8);
        ExistingMetalsAndColors.put("gold", 0xFDF55F);
        ExistingMetalsAndColors.put("copper", 0xC15A36);
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
    private static RegistryObject<Item> registerItem(String name, Item.Properties pProperties) {
        log(name);
        RegistryObject<Item> item = ITEMS.register(name, () -> new Item(pProperties));
        String[] splitName = name.split("1");
        String metalName = splitName[0];
        //get the hex color of the item based on the metal name
        String[] ExistingMetalsList = ExistingMetalsAndColors.keySet().toArray(new String[ExistingMetalsAndColors.size()]);
        String[] NewMetalsList = NewMetalsAndColors.keySet().toArray(new String[ExistingMetalsAndColors.size()]);
        if (AlloysAplenty.isThingInList(metalName, ExistingMetalsList)) {
            ItemColorMap.put(item, ExistingMetalsAndColors.get(metalName));
        } else if (AlloysAplenty.isThingInList(metalName, NewMetalsList)) {
            ItemColorMap.put(item, NewMetalsAndColors.get(metalName));
        }
        registeredItems++;
        return item;
    }
    //block and blockitem register methods
    private static void registerBlock(String name, Supplier<Block> block, CreativeModeTab tab) {
        String metalName = name.split("1")[0];
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);
        String[] ExistingMetalsList = ExistingMetalsAndColors.keySet().toArray(new String[ExistingMetalsAndColors.size()]);
        String[] NewMetalsList = NewMetalsAndColors.keySet().toArray(new String[ExistingMetalsAndColors.size()]);
        if (AlloysAplenty.isThingInList(metalName, ExistingMetalsList)) {
            BlockColorMap.put(toReturn, ExistingMetalsAndColors.get(metalName));
        } else if (AlloysAplenty.isThingInList(metalName, NewMetalsList)) {
            BlockColorMap.put(toReturn, NewMetalsAndColors.get(metalName));
        }
        registerBlockItem(name, toReturn, tab);
        registeredBlocks++;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        RegistryObject<Item> blockitem = ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        String[] splitName = name.split("1");
        String metalName = splitName[0];
        String[] ExistingMetalsList = ExistingMetalsAndColors.keySet().toArray(new String[ExistingMetalsAndColors.size()]);
        String[] NewMetalsList = NewMetalsAndColors.keySet().toArray(new String[ExistingMetalsAndColors.size()]);

        if (AlloysAplenty.isThingInList(metalName, ExistingMetalsList)) {
            ItemColorMap.put(blockitem, ExistingMetalsAndColors.get(metalName));
        } else if (AlloysAplenty.isThingInList(metalName, NewMetalsList)) {
            ItemColorMap.put(blockitem, NewMetalsAndColors.get(metalName));
        }
    }
    //sequence of events
    public static void init(IEventBus eventBus) {
        log("Initializing maps");
        initMetalsAndColors();
        initExistingMetalsAndColors();
        AlloysAplenty.printAllKeysAndValues(NewMetalsAndColors);
        AlloysAplenty.printAllKeysAndValues(ExistingMetalsAndColors);
        log("Registering items");
        registerAll();
        log("Registered "+ registeredItems + " items and "+ registeredBlocks + " blocks.");
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
    //register method
    public static void registerAll() {
        registerItem("copper1nugget", iProp);
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
                registerItem(metal + "1" + item, iProp);
            }
            for (String item : existingItemsList) {
                registerItem(metal + "1" + item, iProp);
            }
            for (String block : newBlocksList) {
                registerBlock(metal + "1" + block, () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
            }
            registerBlock(metal + "1block", () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
        }
        for (String metal : existingMetalsList) {
            for (String item : newItemsList) {
                registerItem(metal + "1" + item, iProp);
            }
            for (String block : newBlocksList) {
                registerBlock(metal + "1" + block, () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
            }
        }
    }
}