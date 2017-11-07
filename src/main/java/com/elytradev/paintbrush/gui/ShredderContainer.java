package com.elytradev.paintbrush.gui;

import com.elytradev.concrete.inventory.gui.ConcreteContainer;
import com.elytradev.concrete.inventory.gui.widget.*;
import com.elytradev.paintbrush.Paintbrush;
import com.elytradev.paintbrush.blocks.tile.TileEntityShredder;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class ShredderContainer extends ConcreteContainer{
    private ResourceLocation arrowBG = new ResourceLocation(Paintbrush.modId,"textures/gui/arrow_bg.png");
    private ResourceLocation arrowFG = new ResourceLocation(Paintbrush.modId,"textures/gui/arrow_fg.png");

    public ShredderContainer(IInventory player, IInventory container, TileEntityShredder te){
        super(player, container);
        WGridPanel panel = new WGridPanel();
        setRootPanel(panel);
        WItemSlot slotIn = WItemSlot.of(container, 0);
        WItemSlot slotOut = WItemSlot.outputOf(container, 1);
        panel.add(slotIn, 2, 2);
        panel.add(slotOut, 6, 2);

        WPanel playerInv = this.createPlayerInventoryPanel();
        panel.add(playerInv, 0, 4);

        WBar progressTicks = new WBar(arrowBG, arrowFG, container, 0, 1, WBar.Direction.RIGHT);
        panel.add(progressTicks, 3, 2, 2, 1);
    }
}
