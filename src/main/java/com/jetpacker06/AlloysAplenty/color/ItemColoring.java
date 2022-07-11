package com.jetpacker06.AlloysAplenty.color;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = "alloysaplenty")
public class ItemColoring {
    //@SubscribeEvent
    //public static void registerItemColors(ColorHandlerEvent.Item event) {
    //    ItemColor itemColor = new ItemColor() {
    //        @Override
    //        public int getColor(ItemStack pStack, int pTintIndex) {
    //            return pTintIndex;
    //        }
    //    };
    //    //event.getItemColors().register(itemColor, );
    //    event.getItemColors().register((p_92708_, p_92709_) -> {
    //        return p_92709_ > 0 ? -1 : ((DyeableLeatherItem)p_92708_.getItem()).getColor(p_92708_);
    //    }, Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS, Items.LEATHER_HORSE_ARMOR);
    //    event.getItemColors().register();
    //}
}