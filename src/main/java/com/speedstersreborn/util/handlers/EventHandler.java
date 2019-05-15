package com.speedstersreborn.util.handlers;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.SHRConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;


@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent playerEvent) {
        EntityPlayer player = playerEvent.player;
        if (!player.world.isRemote && SHRConfig.speedstersHeroesReborn.updatechecker) {
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            if (version.status.equals(ForgeVersion.Status.OUTDATED)) {
                TextComponentString msg = new TextComponentString(TextFormatting.BLUE + "[SpeedsterHeroesReborn] : New Update Available!");
                msg.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/speedsters-heroes-reborn"));
                msg.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open Website")));
                player.sendMessage(msg);
            }
        }
    }

    @SubscribeEvent
    public static void MakeFire(LivingEvent.LivingUpdateEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            if(EventHandlePower.isMoving(player) && cap.isSpeedster() && cap.getSpeedLevel() >= 6) {
                BlockPos blockPos = new BlockPos(player.posX, player.posY - 1, player.posZ);
                if(player.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) {
                    player.world.setBlockState(player.getPosition(), Blocks.FIRE.getDefaultState());
                }
            }
        }
    }
}
