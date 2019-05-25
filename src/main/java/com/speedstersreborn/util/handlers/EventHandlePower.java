package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.meta.util.PEnumHandler;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by Josia50
 * on 10/04/2019.
 */

@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void mainPowers(LivingEvent.LivingUpdateEvent e) {

        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            IMetaCap capmeta = CapabilityMeta.get(player);

            if (MetaHelper.getMetaPowerName(capmeta.getMetaPower()) == MetaPowerStrings.SPEEDSTER || cap.isSpeedster()) {
                setXPAdd(player, cap);
                runWater(player, cap);
                runWall(player, cap);
                runAbilities(player, cap);
                phasing(player, cap);
                whileRunning(player, cap, capmeta);
            }
            updateVelocity(player, cap, capmeta);
        }
    }

    public static void setXPAdd(EntityPlayer player, ISpeedsterCap cap) {
        if (!player.world.isRemote) {
            if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
                if (isMoving(player)) {
                    cap.setXP(cap.getXP() + 0.01 * cap.getSpeedLevel());
                    player.spawnRunningParticles();
                    if (ModHelper.getIsDev())
                        PlayerHelper.sendMessage(player, "XP: " + cap.getXP(), true);
                }
                updateLevel(cap);
            }
        }
    }

    public static void runWater(EntityPlayer player, ISpeedsterCap cap) {
        if (player.isSprinting() && cap.isSpeedster() && cap.getSpeedLevel() >= 1 && player.world.getBlockState(player.getPosition().add(0, -1, 0)).getBlock() instanceof BlockLiquid) {
            player.posY -= player.motionY;
            player.motionY = 0D;
            player.fallDistance = 0.0F;
            player.onGround = true;
        }
    }

    public static void runWall(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isSpeedster()) {
            if (player.collidedHorizontally) {
                if (cap.isWallRunning()) {
                    if (cap.getSpeedLevel() >= 3) {
                        player.motionY = 0.8D;
                        player.fallDistance = 0F;
                        cap.setWallRunning(true);
                        player.sendPlayerAbilities();
                    }
                }
            }
        }
    }

    public static void phasing(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isPhasing() && player.world.isBlockFullCube(new BlockPos(player.posX, player.posY - 0.1F, player.posZ))) {
            player.noClip = true;
            player.motionY = 0;
            player.onGround = true;
            player.sendPlayerAbilities();
        }
    }

    public static void runAbilities(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isSpeedster() && cap.getSpeedLevel() >= 1) {
            player.stepHeight = 1.3f;
            player.sendPlayerAbilities();
        }
    }

    public static void whileRunning(EntityPlayer player, ISpeedsterCap cap, IMetaCap capmeta) {
        FoodStats food = player.getFoodStats();
        if (!player.world.isRemote && cap.isSpeedster() && cap.getSpeedLevel() > 1 && isMoving(player) && !player.isCreative()) {
            if (player.isBurning()) {
                player.extinguish();
            }
            if (food.getFoodLevel() <= 1) {
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 2, false, true));
            }

            if (food.getFoodLevel() != 20) {
                double exhaustion = (((food.getFoodLevel() - 20.5) * -1) * Math.max(cap.getSpeedLevel(), 0)) / 968;
                capmeta.setExhaustionLevel(capmeta.getexhaustionLevel() + exhaustion); // TODO Higher the exhaustion number in core cap for meta
            }
        }
        if (player.getHealth() < player.getMaxHealth()) {
            //player.shouldHeal();
            // player.setHealth(player.getHealth() + Math.max(0.00002f, 0.00001f)); // TODO You can't die with this pls fix :)
        }

        if (cap.getHungerTimer() > 1) {
            if (food.getFoodLevel() < 20)
                food.setFoodLevel(20);
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }

    @SubscribeEvent
    public static void setSpeedsterPowerEnabled(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            IMetaCap capa = CapabilityMeta.get(player);

            if (MetaHelper.hasPower(player, PEnumHandler.MetaPower.SPEEDSTER.getName())) {
                if (capa.isPowerEnabled() != cap.isSpeedster())
                    cap.setSpeedster(capa.isPowerEnabled());
            }
        }
    }

    private static void updateLevel(ISpeedsterCap cap) {
        final double xp = cap.getXP();
        final double required = (cap.getLevel() + 1) * 100 + 100.0D;
        if (xp >= required) {
            cap.setLevel(cap.getLevel() + 1);
            if (cap.getMaxspeedLevel() < 20)
                cap.setMaxSpeedLevel(cap.getMaxspeedLevel() + 5);
        }
    }

    private static void updateVelocity(EntityPlayer player, ISpeedsterCap cap, IMetaCap capa) {
        if (cap.hasVelocity()) {
            cap.setVelocityTime(cap.getVelocityTime() - 1);
            if (cap.getVelocityTime() <= 0) {
                cap.setMaxSpeedLevel(cap.getMaxspeedLevel() - cap.getAddedSpeed());
                if (cap.getSpeedLevel() > cap.getMaxspeedLevel()) {
                    int remove = cap.getSpeedLevel() - cap.getMaxspeedLevel();
                    cap.setSpeedLevel(cap.getSpeedLevel() - remove);
                }
                if (!MetaHelper.getMetaPowerName(capa.getMetaPower()).equals(MetaPowerStrings.SPEEDSTER)) {
                    capa.setPowerEnabled(false); // TODO Make render & cap work when velocity enabled. Not trough main core cap
                }
                cap.setVelocity(false);
            }
        }
        if (player.isDead) {
            cap.clearV9();
        }
    }
    /*
 System.out.println("Max Speed: " + cap.getMaxspeedLevel());
        System.out.println("IsSpeedster: " + cap.isSpeedster());
        System.out.println("isPowerenabled: " + capa.isPowerEnabled());
        System.out.println("velocity: " + cap.hasVelocity());
        System.out.println("Speedlevel: " + cap.getSpeedLevel());
 */

    @SubscribeEvent
    public static void SyncOnLogout(PlayerEvent.PlayerLoggedOutEvent e) {
        CapabilitySpeedster.get(e.player).sync();
    }

    @SubscribeEvent
    public static void SyncOnLogIN(PlayerEvent.PlayerLoggedInEvent e) {
        CapabilitySpeedster.get(e.player).sync();
    }
}

