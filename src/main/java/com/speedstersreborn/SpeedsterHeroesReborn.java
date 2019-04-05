package com.speedstersreborn;

import com.speedstersreborn.common.items.ItemZoomRing;
import com.speedstersreborn.proxy.IProxy;
import com.speedstersreborn.tabs.SHRTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SpeedsterHeroesReborn.MODID, name = SpeedsterHeroesReborn.NAME, version = SpeedsterHeroesReborn.VERSION)
public class SpeedsterHeroesReborn
{
    @Mod.Instance
    public static SpeedsterHeroesReborn instance;

    @SidedProxy(clientSide = "com.speedstersreborn.proxy.ClientProxy", serverSide = "com.speedstersreborn.proxy.CommonProxy")
    public static IProxy proxy;

    public static final String MODID = "shr";
    public static final String NAME = "Speedsters Heroes Reborn";
    public static final String VERSION = "0.0.1";
    public static final CreativeTabs shrtab = new SHRTab("shrtab");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
