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

import java.util.function.Supplier;

public class ModItemsAndBlocks {
    //default item properties
    public static final Item.Properties iProp = new Item.Properties().tab(ItemGroups.ALLOYS_APLENTY_TAB);
    //default block properties
    public static final Block.Properties bProp = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK);
    //DRs
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlloysAplenty.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlloysAplenty.MOD_ID);
    public static void registerItem() {

    }
    //Main method
    public static void registerALl() {
        int registered = 0;
        ITEMS.register("copper_nugget", () -> new Item(iProp));
        String[] newMetalsList = {
                //metals
                "silver", "nickel", "lead", "tin", "aluminum", "platinum", "magnesium", "boron", "lithium", "titanium", "cobalt", "chrome", "iridium", "osmium", "uranium", "thorium",
                //alloys
                "electrum", "brass", "bronze", "steel", "constantan", "invar", "terne", "corinthian_bronze", "sterling_silver", "titanium_gold", "sterling_platinum", "mag_thor", "beskar"
        };
        String[] existingMetalsList = {
                "iron", "gold", "copper"
        };
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
                ITEMS.register(metal + "_" + item, () -> new Item(iProp));
            }
            for (String item : existingItemsList) {
                ITEMS.register(metal + "_" + item, () -> new Item(iProp));
            }
            for (String block : newBlocksList) {
                registerBlock(metal + "_" + block, () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
            }
            registerBlock(metal + "_block", () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
        }
        for (String metal : existingMetalsList) {
            for (String item : newItemsList) {
                ITEMS.register(metal + "_" + item, () -> new Item(iProp));
            }
            for (String block : newBlocksList) {
                registerBlock(metal + "_" + block, () -> new Block(bProp), ItemGroups.ALLOYS_APLENTY_TAB);
            }
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}