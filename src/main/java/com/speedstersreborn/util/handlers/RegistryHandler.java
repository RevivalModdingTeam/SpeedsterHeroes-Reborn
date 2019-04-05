package com.speedstersreborn.util.handlers;

import com.revivalcore.common.events.RVRecipeRegistryEvent;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.blocks.SHRBlocks;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.common.items.SHRItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

    @EventBusSubscriber
    public class RegistryHandler {

        @SubscribeEvent
        public static void onItemRegister(RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(SHRItems.ITEM_LIST.toArray(new Item[0]));
        }

        @SubscribeEvent
        public static void onBlockRegister(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(SHRBlocks.BLOCK_LIST.toArray(new Block[0]));
        }

        @SubscribeEvent
        public static void onSuitMakerRecipesRegister(RVRecipeRegistryEvent.SuitMakerRecipeRegistryEvent e) {
            //e.register(recipe);
        }


        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            for (Item item : SHRItems.ITEM_LIST) {
                if (item instanceof IHasModel) {
                    ((IHasModel) item).registerModels();
                }
            }

            for (Block block : SHRBlocks.BLOCK_LIST) {
                if (block instanceof IHasModel) {
                    ((IHasModel) block).registerModels();
                }
            }
        }

        @SubscribeEvent
        public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
            IForgeRegistry<EntityEntry> reg = e.getRegistry();
            reg.registerAll(EntityEntries.DUMMY);
        }

        public static class EntityEntries {

            public static final EntityEntry DUMMY = EntityEntryBuilder.create().entity(EntityRingDummy.class).id(new ResourceLocation(SpeedsterHeroesReborn.MODID, "ring_dummy"), 0).name("ring_dummy").tracker(80, 3, true).build();
        }

        // Use in preinit in mod.
        public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
            GameRegistry.registerTileEntity(clazz, new ResourceLocation(SpeedsterHeroesReborn.MODID, name));
        }


        public static class TileRegistry {
            public static void init() {

            }

            @SideOnly(Side.CLIENT)
            public static void bindEntityTEISR() {

            }
        }
    }
