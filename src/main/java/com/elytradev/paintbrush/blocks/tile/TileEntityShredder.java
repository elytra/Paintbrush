package com.elytradev.paintbrush.blocks.tile;

import com.elytradev.concrete.inventory.ConcreteItemStorage;
import com.elytradev.concrete.inventory.IContainerInventoryHolder;
import com.elytradev.concrete.inventory.ValidatedInventoryView;
import com.elytradev.concrete.inventory.Validators;
import com.elytradev.paintbrush.Paintbrush;
import com.elytradev.paintbrush.items.ModItems;
import com.google.common.base.Predicates;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class TileEntityShredder extends TileEntity implements ITickable, IContainerInventoryHolder{
    private static final int processLength = 20;
    private int currentProcessTime;
    public static final int SLOT_IN = 0;
    public static final int SLOT_OUT = 1;
    public ConcreteItemStorage items;

    public TileEntityShredder(){
        super();
        this.items = new ConcreteItemStorage(2).withValidators((i)->(validateItem(i)), Validators.NOTHING).withName(Paintbrush.proxy.localize("tile.paintbrush.shredder.name"));
        items.listen(this::markDirty);
    }

    //TODO implement real recipes
    private boolean validateItem(ItemStack i){
        if(i.getItem() == Items.IRON_INGOT){
            return true;
        }
        return false;
    }

    @Override
    public IInventory getContainerInventory() {
        ValidatedInventoryView view = new ValidatedInventoryView(items);
        if(world.isRemote) {
            return view;
        }
        else {
            return view.withField(0, () -> currentProcessTime)
                    .withField(1, () -> processLength);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tag = super.writeToNBT(compound);
        tag.setTag("Inventory", items.serializeNBT());
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        items.deserializeNBT(compound.getCompoundTag("Inventory"));
    }

    @Override
    public void update() {
        if (!world.isRemote && checkProcess()){
            currentProcessTime++;
            if(items.getStackInSlot(SLOT_IN).isEmpty()){currentProcessTime = 0;}
            if (currentProcessTime >= processLength) {  // in case it somehow gets over the length, it needs to still process the items
                items.extractItem(SLOT_IN, 1, false);
                items.insertItem(SLOT_OUT, new ItemStack(ModItems.steelWool, 16), false);
                currentProcessTime = 0; //reset process time for next item
            }
        }
        else if(!world.isRemote){
            currentProcessTime = 0;
        }
    }

    private boolean checkProcess(){
        ItemStack itemExtracted = items.extractItem(SLOT_IN, 1, true); // items.extractItem(int slot, int amount, boolean simulate)
        ItemStack itemInserted = items.insertItem(SLOT_OUT, new ItemStack(ModItems.steelWool, 16), true); //items.insertItem(int slot, new ItemStack(item, int amount), boolean simulate)
        return ((!itemExtracted.isEmpty()) && itemInserted.isEmpty());
    }


    @Override
    public void markDirty() {
        super.markDirty();
        // again, I've copy-pasted this like 12 times, should probably go into Concrete
        if (!hasWorld() || getWorld().isRemote) return;
        WorldServer ws = (WorldServer)getWorld();
        Chunk c = getWorld().getChunkFromBlockCoords(getPos());
        SPacketUpdateTileEntity packet = new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
        for (EntityPlayerMP player : getWorld().getPlayers(EntityPlayerMP.class, Predicates.alwaysTrue())) {
            if (ws.getPlayerChunkMap().isPlayerWatchingChunk(player, c.x, c.z)) {
                player.connection.sendPacket(packet);
            }
        }
    }
}
