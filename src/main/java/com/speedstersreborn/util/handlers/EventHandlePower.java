package com.speedstersreborn.util.handlers;

import com.revivalcore.capabilities.CapabilitySpeedster;
import com.revivalcore.capabilities.ISpeedsterCap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void handlePower(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

          //  if(!cap.isSpeedster) {
               // player.setAIMoveSpeed(0.1f); // TODO new Core version release to do this
            }//
        }
    }
