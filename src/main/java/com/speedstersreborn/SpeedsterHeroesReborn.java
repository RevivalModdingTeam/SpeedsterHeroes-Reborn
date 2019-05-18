package com.speedstersreborn;

import com.speedstersreborn.common.capabilities.CapSpeedstersStorage;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.common.items.SHRItems;
import com.speedstersreborn.common.tileentity.TileTreadMill;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.proxy.IProxy;
import com.speedstersreborn.util.handlers.RegistryHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SpeedsterHeroesReborn.MODID, name = SpeedsterHeroesReborn.NAME, version = SpeedsterHeroesReborn.VERSION,dependencies = "required-after:revivalcore@[0.1.3,)", updateJSON = SpeedsterHeroesReborn.UPDATEURL)
public class SpeedsterHeroesReborn
{
    @Mod.Instance
    public static SpeedsterHeroesReborn instance;

    @SidedProxy(clientSide = "com.speedstersreborn.proxy.ClientProxy", serverSide = "com.speedstersreborn.proxy.CommonProxy")
    public static IProxy proxy;
    public static Logger logger;

    public static final String MODID = "shr";
    public static final String NAME = "Speedsters Heroes Reborn";
    public static final String VERSION = "0.0.4";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/RevivalModdingTeam/RevivalModding-ModBugs/master/updatespeedster.json";


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
        NetworkHandler.init();
        SHRItems.init();
        CapabilityManager.INSTANCE.register(ISpeedsterCap.class, new CapSpeedstersStorage(), CapabilitySpeedster::new);
        RegistryHandler.registerTileEntity(TileTreadMill.class, "tile_treadmill");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
