package com.speedstersreborn.util.handlers;


import com.revivalcore.common.capabilities.CapabilitySpeedster;
import com.revivalcore.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void Particle(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            if (cap.isSpeedster()) {
                if (player.posX != player.prevPosX || player.posZ != player.prevPosZ) {
                    player.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY + 0.5, player.posZ, 1.0, 1.0, 1.0);
                }
            }
        }
    }
}
