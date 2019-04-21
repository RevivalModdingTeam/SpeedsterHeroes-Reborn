package com.speedstersreborn.util;

import com.revivalcore.RevivalCore;
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

        @Mod.EventBusSubscriber
        public static class Event {
            @SubscribeEvent
            public void onConfigChanged(ConfigChangedEvent e) {
                if (e.getModID().equals(RevivalCore.MODID))
                    ConfigManager.sync(RevivalCore.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
