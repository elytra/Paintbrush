package com.elytradev.paintbrush;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PaintTab extends CreativeTabs {
    public PaintTab(){
        super(Paintbrush.modId);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.DYE,1,0); //TODO make this the paintbrush
    }
}
