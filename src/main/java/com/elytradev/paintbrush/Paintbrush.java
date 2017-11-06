package com.elytradev.paintbrush;

import com.elytradev.paintbrush.blocks.ModBlocks;
import com.elytradev.paintbrush.gui.GuiHandler;
import com.elytradev.paintbrush.items.ModItems;
import com.elytradev.paintbrush.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid=Paintbrush.modId, name=Paintbrush.name, version=Paintbrush.version)
public class Paintbrush {
    public static final String modId = "paintbrush";
    public static final String name = "Paintbrush";
    public static final String version = "1";

    @Mod.Instance(modId)
    public static Paintbrush instance;
    @SidedProxy(serverSide = "com.elytradev.paintbrush.proxy.CommonProxy",clientSide = "com.elytradev.paintbrush.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final PaintTab creativeTab = new PaintTab();



    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ModItems.registerOreDict();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerItemModels();
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }
    }
}
