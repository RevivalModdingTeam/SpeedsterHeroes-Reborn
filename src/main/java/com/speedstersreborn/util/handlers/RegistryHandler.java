package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.core.common.events.RCRegistryEvent;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.common.suits.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onSuitMakerRecipesRegister(RCRegistryEvent.SuitMakerRecipeRegistryEvent e) {
        //e.register(recipe);
    }

    @SubscribeEvent
    public static void onSuitRegister(RCRegistryEvent.SuitRegistryEvent e) {
        e.registerAll(new AbstractSuit[] {
                new SuitKidFlash(),
                new SuitGodSpeed(),
                new SuitZoom(),
                new SuitReverseFlash()
        });
        for(SuitFlash.EnumFlashSuitSeason season : SuitFlash.EnumFlashSuitSeason.values()) {
            e.register(new SuitFlash(season));
        }
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
    	String pathToma = "D:/mcmods/1.12.2/SpeedsterHeroes-Reborn/src/main/resources/assets";
    	ModHelper.jsonGenerator().generateFiles(SpeedsterHeroesReborn.MODID, pathToma);
        ForgeRegistries.ITEMS.getValuesCollection().stream().filter(p -> p.getRegistryName().getNamespace().equals(SpeedsterHeroesReborn.MODID))
                .forEach(i -> ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory")));
        ForgeRegistries.BLOCKS.getValuesCollection().stream().filter(p -> p.getRegistryName().getNamespace().equals(SpeedsterHeroesReborn.MODID))
                .forEach(b -> ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(b.getRegistryName(), "normal")));
    }

    @SubscribeEvent
    public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
        IForgeRegistry<EntityEntry> reg = e.getRegistry();
        reg.registerAll(EntityEntries.DUMMY);
    }

    public static class EntityEntries {

        public static final EntityEntry DUMMY = EntityEntryBuilder.create().entity(EntityRingDummy.class).id(new ResourceLocation(SpeedsterHeroesReborn.MODID, "ring_dummy"), 0).name("ring_dummy").tracker(80, 3, true).build();
      //  public static final EntityEntry LIGHTNING = EntityEntryBuilder.create().entity(EntityLightning.class).id(new ResourceLocation(SpeedsterHeroesReborn.MODID, "lightning"), 1).name("lightning").tracker(80, 3, true).build();
    }

    // Use in preinit in mod.
    public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
        GameRegistry.registerTileEntity(clazz, new ResourceLocation(SpeedsterHeroesReborn.MODID, name));
    }

    @SideOnly(Side.CLIENT)
    public static void bindEntityTEISR() {
    }
}
