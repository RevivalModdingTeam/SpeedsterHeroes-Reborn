package com.speedstersreborn.util.config;

import com.revivalmodding.revivalcore.RevivalCore;
import com.speedstersreborn.SpeedsterHeroesReborn;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = SpeedsterHeroesReborn.MODID, name = SpeedsterHeroesReborn.NAME + " Config")
public class SHRConfig {

    @Config.LangKey("config.tab.speedsterheroesreborn")
    public static final SpeedstersHeroesReborn speedstersHeroesReborn = new SpeedstersHeroesReborn();

    public static class SpeedstersHeroesReborn {
        @Config.LangKey("config.shr.updatechecker")
        @Config.Comment("Toggle the update checker")
        public boolean updatechecker = true;
        
        @Config.LangKey("config.shr.speedlevelmultiplier")
        @Config.Comment({"Modify the speed which is additionally added per speed level","Final speed is = level * speedMultiplier"})
        public float speedIncreaseOverLevel = 0.125f;
        
        @Config.LangKey("config.shr.speedindicator")
        @Config.Comment("Modify rendering position of the speed indicators")
        public CFGOverlayPosition speedIndicator = new CFGOverlayPosition(0, 0);
        
        @Config.LangKey("config.shr.speedunit")
        public CFGSpeedIndicatorUnit speedUnit = CFGSpeedIndicatorUnit.BLOCKS_PER_SECOND;
        
        @Mod.EventBusSubscriber
        public static class Event {
            @SubscribeEvent
            public static void onConfigChanged(ConfigChangedEvent e) {
                if (e.getModID().equals(SpeedsterHeroesReborn.MODID))
                    ConfigManager.sync(SpeedsterHeroesReborn.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
