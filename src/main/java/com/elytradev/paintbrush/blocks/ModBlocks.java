package com.elytradev.paintbrush.blocks;

import com.elytradev.paintbrush.Paintbrush;
import com.elytradev.paintbrush.blocks.tile.BlockShredder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
    public static BlockShredder shredder = (BlockShredder) new BlockShredder().setCreativeTab(Paintbrush.creativeTab);
    public static IBlockBase[] allBlocks = {shredder};

    public static void register(IForgeRegistry<Block> registry) {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            registry.register(block.toBlock());
        }
        GameRegistry.registerTileEntity(shredder.getTileEntityClass(),shredder.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            registry.register(block.createItemBlock());
        }
    }

    public static void registerItemModels() {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            block.registerItemModel(Item.getItemFromBlock(block.toBlock()));
        }
    }
}
