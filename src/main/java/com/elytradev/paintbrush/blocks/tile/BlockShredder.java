package com.elytradev.paintbrush.blocks.tile;

import com.elytradev.paintbrush.Paintbrush;
import com.elytradev.paintbrush.gui.GuiHandler;
import jline.internal.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShredder extends BlockTileEntity<TileEntityShredder>{
    public BlockShredder(){
        super(Material.IRON, "shredder");

    }

    @Override
    public Class<TileEntityShredder> getTileEntityClass() {
        return TileEntityShredder.class;
    }
    @Nullable
    @Override
    public TileEntityShredder createTileEntity(World world, IBlockState state) {
        return new TileEntityShredder();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            player.openGui(Paintbrush.instance, GuiHandler.SHREDDER, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side){
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
