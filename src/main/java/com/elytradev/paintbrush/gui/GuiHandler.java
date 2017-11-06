package com.elytradev.paintbrush.gui;

import com.elytradev.concrete.inventory.IContainerInventoryHolder;
import com.elytradev.concrete.inventory.gui.ConcreteContainer;
import com.elytradev.concrete.inventory.gui.client.ConcreteGui;
import com.elytradev.paintbrush.blocks.tile.TileEntityShredder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {
    public static final int SHREDDER = 0;

    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case SHREDDER:
                TileEntityShredder shredder = (TileEntityShredder) world.getTileEntity(new BlockPos(x, y, z));
                return new ShredderContainer(player.inventory, shredder.getContainerInventory(), shredder);
            default:
                return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case SHREDDER:
                ConcreteContainer container = new ShredderContainer(player.inventory, ((IContainerInventoryHolder)world.getTileEntity(new BlockPos(x,y,z))).getContainerInventory(),(TileEntityShredder) world.getTileEntity(new BlockPos(x,y,z)));
                return new ConcreteGui(container);
            default:
                return null;
        }
    }
}
