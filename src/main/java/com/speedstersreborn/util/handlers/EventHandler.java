package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.config.SHRConfig;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            if (EventHandlePower.isMoving(player) && cap.isSpeedster() && cap.getSpeedLevel() >= 6) {
                BlockPos blockPos = new BlockPos(player.posX, player.posY - 1, player.posZ);
                if (player.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) {
                    player.world.setBlockState(player.getPosition(), Blocks.FIRE.getDefaultState());
                }
            }
        }
    }

    @SubscribeEvent
    public static void setTrailsFromSuit(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntityLiving();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            if (CapabilitySpeedster.get(player).isSpeedster()) {
                AbstractSuit suit = AbstractSuit.getSuit(player);
                if (suit != null) {
                    if (cap.getPrimaryTrailColor().getRGB() != suit.getTrailRGB().getRGB()) {
                        cap.setLastTrailColor(cap.getPrimaryTrailColor());
                        cap.setPrimaryTrailColor(suit.getTrailRGB());
                    }
                } else {
                    if (cap.getPrimaryTrailColor().getRGB() != cap.getLastTrailColor().getRGB()) {
                        cap.setPrimaryTrailColor(cap.getLastTrailColor());
                    }
                }
                cap.sync();
            }
        }
    }


    @SubscribeEvent
    public static void setRainBowTrails(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            List<Color> list = new ArrayList<>();
            Random r = new Random();
            list.add(Color.red);
            list.add(Color.green);
            list.add(Color.orange);
            list.add(Color.magenta);

            if (cap.hasRainbowTrail()) {
                cap.setPrimaryTrailColor(list.get(r.nextInt(list.size())));
                cap.sync();
            } else {
                if (cap.getPrimaryTrailColor().getRGB() != cap.getLastTrailColor().getRGB())
                    cap.setPrimaryTrailColor(cap.getLastTrailColor());
            }
        }
    }

    @SubscribeEvent
    public static void setPowerEnabled(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            IMetaCap capa = CapabilityMeta.get(player);

            if(capa.isPowerEnabled() != cap.isSpeedster())
                cap.setSpeedster(capa.isPowerEnabled());
        }
    }

     @SubscribeEvent
    public static void SyncOnleave(PlayerEvent.PlayerLoggedInEvent e) {
        CapabilitySpeedster.get(e.player).sync();
     }
}
