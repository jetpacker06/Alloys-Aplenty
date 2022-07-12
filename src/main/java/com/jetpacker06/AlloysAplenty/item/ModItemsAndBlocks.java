package com.jetpacker06.AlloysAplenty.item;

import com.jetpacker06.AlloysAplenty.AlloysAplenty;
import net.minecraft.client.color.item.ItemColors;
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
    public static HashMap<String, String> NewMetalsAndColors = new HashMap<>();
    public static HashMap<String, String> ExistingMetalsAndColors = new HashMap<>();
    //methods to initialize the above maps
    private static void initMetalsAndColors() {
        NewMetalsAndColors.put("silver", "0xff002bd");
        NewMetalsAndColors.put("nickel", "0xff002bd");
        NewMetalsAndColors.put("lead", "0xff002bd");
        NewMetalsAndColors.put("tin", "0xff002bd");
        NewMetalsAndColors.put("aluminum", "0xff002bd");
        NewMetalsAndColors.put("platinum", "0xff002bd");
        NewMetalsAndColors.put("magnesium", "0xff002bd");
        NewMetalsAndColors.put("boron", "0xff002bd");
        NewMetalsAndColors.put("lithium", "0xff002bd");
        NewMetalsAndColors.put("titanium", "0xff002bd");
        NewMetalsAndColors.put("cobalt", "0xff002bd");
        NewMetalsAndColors.put("chrome", "0xff002bd");
        NewMetalsAndColors.put("iridium", "0xff002bd");
        NewMetalsAndColors.put("osmium", "0xff002bd");
        NewMetalsAndColors.put("uranium", "0xff002bd");
        NewMetalsAndColors.put("thorium", "0xff002bd");
        NewMetalsAndColors.put("electrum", "0xff002bd");
        NewMetalsAndColors.put("brass", "0xff002bd");
        NewMetalsAndColors.put("bronze", "0xff002bd");
        NewMetalsAndColors.put("steel", "0xff002bd");
        NewMetalsAndColors.put("constantan", "0xff002bd");
        NewMetalsAndColors.put("invar", "0xff002bd");
        NewMetalsAndColors.put("terne", "0xff002bd");
        NewMetalsAndColors.put("corinthian_bronze", "0xff002bd");
        NewMetalsAndColors.put("sterling_silver", "0xff002bd");
        NewMetalsAndColors.put("titanium_gold", "0xff002bd");
        NewMetalsAndColors.put("sterling_platinum", "0xff002bd");
        NewMetalsAndColors.put("mag_thor", "0xff002bd");
        NewMetalsAndColors.put("beskar", "0xff002bd");
    }
    private static void initExistingMetalsAndColors() {
        ExistingMetalsAndColors.put("iron", "0xff002bd");
        ExistingMetalsAndColors.put("gold", "0xff002bd");
        ExistingMetalsAndColors.put("copper", "0xff002bd");
    }
    //maps that are used for coloring
    public static HashMap<RegistryObject<Item>, String> ItemColorMap = new HashMap<>();
    public static HashMap<RegistryObject<Block>, String> BlockColorMap = new HashMap<>();
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
        // log("Begin existing list");
        // AlloysAplenty.logList(ExistingMetalsList);
        // log("begin new list");
        // AlloysAplenty.logList(NewMetalsList);
        // log("end new list");

        if (AlloysAplenty.isThingInList(metalName, ExistingMetalsList)) {
            ItemColorMap.put(item, ExistingMetalsAndColors.get(metalName));
        } else if (AlloysAplenty.isThingInList(metalName, NewMetalsList)) {
            ItemColorMap.put(item, NewMetalsAndColors.get(metalName));
        }

       //
       // try {
       //     String d = ExistingMetalsAndColors.get(metalName);
       //     ItemColorMap.put(item, d);
       // } catch (NullPointerException e) {
       //     String d = NewMetalsAndColors.get(metalName);
       //     ItemColorMap.put(item, d);
       // }
        registeredItems++;
        return item;
    }
    //block and blockitem register methods
    private static void registerBlock(String name, Supplier<Block> block, CreativeModeTab tab) {
        log(name);
        String metalName = name.split("1")[0];
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);
        try {
            String d = ExistingMetalsAndColors.get(metalName);
            BlockColorMap.put(toReturn, d);
        } catch (NullPointerException e) {
            String d = NewMetalsAndColors.get(metalName);
            BlockColorMap.put(toReturn, d);
        }
        registerBlockItem(name, toReturn, tab);
        registeredBlocks++;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        log(name);
        RegistryObject<Item> blockitem = ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        String[] splitName = name.split("1");
        String metalName = splitName[0];
        //try {
        //    String value = ExistingMetalsAndColors.get(metalName);
        //    ItemColorMap.put(blockitem, value);
        //} catch (NullPointerException e) {
        //    String value = NewMetalsAndColors.get(metalName);
        //    ItemColorMap.put(blockitem, value);
        //}
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

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(iProp));
}