package com.elytradev.paintbrush.items;

import com.elytradev.paintbrush.Paintbrush;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBase extends Item implements IItemBase {
    protected String name;
    private String oreName;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(Paintbrush.modId+"."+name);
        setRegistryName(name);
    }

    public ItemBase(String name, int maxStack) {
        this(name);
        this.maxStackSize = maxStack;
    }

    public ItemBase(String name, int maxStack, String oreDict){
        this(name,maxStack);
        this.oreName = oreDict;
    }

    public void registerItemModel() {
        Paintbrush.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public void initOreDict() {
        if(oreName==null){return;}
        OreDictionary.registerOre(oreName, this);
    }
}
