package com.elytradev.paintbrush.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static ItemBase steelWool = new ItemBase("steel_wool",1);

    public static IItemBase[] allItems = {
            steelWool
    };

    public static void register(IForgeRegistry<Item> registry) {
        for (int i = 0; i < allItems.length ; i++) {
            registry.register((Item) allItems[i]);
        }
    }

    public static void registerModels() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].registerItemModel();
        }
    }

    public static void registerOreDict() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].initOreDict();
        }
    }
}
