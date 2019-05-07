package com.speedstersreborn.util;

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
        public float speedIncreaseOverLevel = 0.15f;
        
        @Config.LangKey("config.shr.category.devutils")
        public DeveloperUtils devUtils = new DeveloperUtils();

        @Mod.EventBusSubscriber
        public static class Event {
            @SubscribeEvent
            public void onConfigChanged(ConfigChangedEvent e) {
                if (e.getModID().equals(RevivalCore.MODID))
                    ConfigManager.sync(RevivalCore.MODID, Config.Type.INSTANCE);
            }
        }
    }
    
    // bunch of utilities for devs to debug things
    public static class DeveloperUtils
    {
    	@Config.LangKey("config.shr.xpdebug")
    	@Config.Comment("Toggle the xp debugger")
    	public boolean xpDebugInfo = true;
    }
}
