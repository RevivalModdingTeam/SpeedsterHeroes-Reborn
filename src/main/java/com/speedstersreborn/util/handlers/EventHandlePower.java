package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.core.common.events.PowerToggleEvent;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
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
            ICoreCapability coreCapability = CoreCapabilityImpl.getInstance(player);
            PlayerAbilityData abilityData = coreCapability.getAbilityData();
            PlayerMetaPowerData metaPowerData = coreCapability.getMetaPowerData();

            if (cap.isSpeedster() || cap.hasVelocity()) {
                setXPAdd(player, cap, abilityData);
                runWater(player, cap);
                runWall(player, cap);
                runAbilities(player, cap);
                phasing(player, cap);
                whileRunning(player, cap, metaPowerData);
            } else if(!cap.isSpeedster()) {
            	if(isMoving(player)) {
            		abilityData.addXP(0.005F * (player.isSprinting() ? 1.5F : 1F));
            	}
            }
            updateVelocity(player, cap, metaPowerData);
        }
    }

    public static void setXPAdd(EntityPlayer player, ISpeedsterCap cap, PlayerAbilityData abilityCap) {
        if (!player.world.isRemote) {
            if (!player.capabilities.isCreativeMode) {
                if (isMoving(player)) {
                    player.spawnRunningParticles();
                    if (ModHelper.getIsDev())
                        PlayerHelper.sendMessage(player, "XP: " + abilityCap.getXP(), true);
                }
                //updateLevel(cap, abilityCap);
            }
        }
    }

    public static void runWater(EntityPlayer player, ISpeedsterCap cap) {
        if (player.isSprinting() && cap.getSpeedLevel() >= 1 && player.world.getBlockState(player.getPosition().add(0, -1, 0)).getBlock() instanceof BlockLiquid) {
            player.posY -= player.motionY;
            player.motionY = 0D;
            player.fallDistance = 0.0F;
            player.onGround = true;
        }
    }

    public static void runWall(EntityPlayer player, ISpeedsterCap cap) {
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

    public static void phasing(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isPhasing() && player.world.isBlockFullCube(new BlockPos(player.posX, player.posY - 0.1F, player.posZ))) {
            player.noClip = true;
            player.motionY = 0;
            player.onGround = true;
            player.sendPlayerAbilities();
        }
    }

    public static void runAbilities(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.getSpeedLevel() >= 1) {
            player.stepHeight = 1.3f;
            player.sendPlayerAbilities();
        }
    }

    public static void whileRunning(EntityPlayer player, ISpeedsterCap cap, PlayerMetaPowerData capmeta) {
        FoodStats food = player.getFoodStats();
        if (!player.world.isRemote && cap.getSpeedLevel() > 1 && isMoving(player) && !player.isCreative()) {
            if (player.isBurning()) {
                player.extinguish();
            }
            if (food.getFoodLevel() <= 1) {
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 2, false, true));
            }

            if (food.getFoodLevel() != 20) {
                //   double exhaustion = (((food.getFoodLevel() - 20.5) * -1) * Math.max(cap.getSpeedLevel(), 0)) / 968;
                //     capmeta.setExhaustionLevel(capmeta.getexhaustionLevel() + exhaustion); // TODO Higher the exhaustion number in core cap for meta
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
            PlayerMetaPowerData metaPowerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();

            if (metaPowerData.hasMetaPowers() && MetaHelper.getMetaPowerName(metaPowerData.getMetaPower()).equals(MetaPowerStrings.SPEEDSTER)) {
                cap.setSpeedster(metaPowerData.isPowerActivated());

                if (cap.getVelocityTime() > 1) {
                    if (cap.isSpeedster()) {
                        cap.setVelocity(true);
                    }else{
                        cap.setVelocity(false);
                    }
                }
            } else {
                cap.setSpeedster(metaPowerData.hasMetaPowers());
            }
        }
    }

    @SubscribeEvent
    public static void getTogglePowerEvent(PowerToggleEvent e) {
        // TODO what is this doing there?
        EntityPlayer player = e.player;
        //IMetaCap capa = CapabilityMeta.get(player);
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
    }

    private static void updateLevel(ISpeedsterCap cap, PlayerAbilityData aCap) {
    	// TODO: Create level up event and use it here (event must be in core)
            aCap.setLevel(aCap.getLevel() + 1);
            if (cap.getMaxspeedLevel() < 20)
                cap.setMaxSpeedLevel(cap.getMaxspeedLevel() + 5);
    }

    public static boolean shouldchange(ISpeedsterCap cap, PlayerMetaPowerData capa) {

        if (!cap.hasVelocity() && cap.getVelocityTime() > 0) {
            return true;
        }

        if (cap.hasVelocity() && cap.getVelocityTime() > 0) {
            return true;
        }

        if (cap.hasVelocity() && !(cap.getVelocityTime() > 0)) {
            return true;
        }

        if (!cap.hasVelocity() && !(cap.getVelocityTime() > 0)) {
            return false;
        }
        return false;
    }

    private static void updateVelocity(EntityPlayer player, ISpeedsterCap cap, PlayerMetaPowerData capa) {
        if (cap.getVelocityTime() >= 1) {
            cap.setVelocityTime(cap.getVelocityTime() - 1);
            if (cap.getVelocityTime() <= 0) {
                cap.setMaxSpeedLevel(cap.getMaxspeedLevel() - cap.getAddedSpeed());
                if (cap.getSpeedLevel() > cap.getMaxspeedLevel()) {
                    int remove = cap.getSpeedLevel() - cap.getMaxspeedLevel();
                    cap.setSpeedLevel(cap.getSpeedLevel() - remove);
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

