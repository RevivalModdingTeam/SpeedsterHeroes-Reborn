package com.speedstersreborn.common.capabilities;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.api.SpeedAPI;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketCapSync;
import com.speedstersreborn.util.handlers.EnumHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.awt.*;

public class CapabilitySpeedster implements ISpeedsterCap {

    private EntityPlayer player;
    private int speed_level = 0;
    private boolean isSpeedster = false;
    private boolean isPhasing = false;
    private int level = 0;
    private double xp = 0.0;
    private boolean isWallRunning = false;
    private boolean hasSecondTrail = false;
    private boolean hasRainbowTrail = false;
    private int maxSpeedLevel = 5;
    private int velocityaddedspeed = 0;
    private int velocitycount = 0;
    private boolean velocity = false;
    private int pr = Color.ORANGE.getRed(), pg = Color.ORANGE.getGreen(), pb = Color.ORANGE.getBlue();
    private int lpr = pr, lpg = pg, lpb = pb;
    private int sr = Color.RED.getRed(), sg = Color.RED.getGreen(), sb = Color.RED.getBlue();

    public CapabilitySpeedster() {

    }


    public CapabilitySpeedster(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {
        if (!isSpeedster()) {
            setSpeedLevel(0);
            setWallRunning(false);
            setPhasing(false);
            SpeedAPI.setSpeedFromCap(player);
        } else {
            if (hasVelocity()) {
                if (getSpeedLevel() < maxSpeedLevel) {
                    SpeedAPI.setSpeedFromCap(player);
                }
            } else {
                SpeedAPI.setSpeedFromCap(player);
            }
        }


        if (getSpeedLevel() < 0) {
            setSpeedLevel(0);
        }
    }

    @Override
    public void sync() {
        if(player.world.isRemote) return;
        NBTTagCompound data = serializeNBT();
        NetworkHandler.INSTANCE.sendToAll(new PacketCapSync(player, data));
    }

    @Override
    public void setSpeedster(boolean speedster) {
        isSpeedster = speedster;
    }

    @Override
    public boolean isSpeedster() {
        return isSpeedster;
    }

    @Override
    public void setSpeedLevel(int level) {
        speed_level = level;
    }

    @Override
    public int getSpeedLevel() {
        return speed_level;
    }

    @Override
    public void setPhasing(boolean phase) {
        this.isPhasing = phase;
    }

    @Override
    public boolean isPhasing() {
        return isPhasing;
    }

    @Override
    public void setWallRunning(boolean wallRunning) {
        isWallRunning = wallRunning;
    }

    @Override
    public boolean isWallRunning() {
        return isWallRunning;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setXP(double xp) {
        this.xp = xp;
    }

    @Override
    public double getXP() {
        return xp;
    }

    @Override
    public void setSecondTrail(boolean secondTrail) {
        this.hasSecondTrail = secondTrail;
    }

    @Override
    public boolean hasSecondTrail() {
        return this.hasSecondTrail;
    }

    @Override
    public void setSecondaryTrailColor(Color color) {
        this.sr = color.getRed();
        this.sg = color.getGreen();
        this.sb = color.getBlue();
    }

    @Override
    public Color getSecondaryTrailColor() {
        return new Color(sr, sg, sb);
    }

    @Override
    public void setPrimaryTrailColor(Color color) {
        this.pr = color.getRed();
        this.pg = color.getGreen();
        this.pb = color.getBlue();
    }

    @Override
    public Color getPrimaryTrailColor() {
        return new Color(pr, pg, pb);
    }

    @Override
    public Color getLastTrailColor() {
        return new Color(lpr, lpg, lpb);
    }

    @Override
    public void setLastTrailColor(Color color) {
        this.lpr = color.getRed();
        this.lpg = color.getGreen();
        this.lpb = color.getBlue();
    }

    @Override
    public void setRainbowTrail(boolean rainbowTrail) {
        this.hasRainbowTrail = rainbowTrail;
    }

    @Override
    public boolean hasRainbowTrail() {
        return hasRainbowTrail;
    }

    @Override
    public void setMaxSpeedLevel(int level) {
        maxSpeedLevel = level;
    }

    @Override
    public int getMaxspeedLevel() {
        return maxSpeedLevel;
    }

    @Override
    public void setVelocity(EnumHandler.VelocityTypes types, boolean on) {
        this.velocity = on;
        this.maxSpeedLevel = maxSpeedLevel + types.getMaxAddedSpeedLevels();
        this.velocitycount = types.getTimeleft() * 20;
        this.velocityaddedspeed = types.getMaxAddedSpeedLevels();
    }

    @Override
    public void setVelocity(boolean on) {
        this.velocity = on;
    }

    @Override
    public void setVelocityTime(int time) {
        this.velocitycount = time;
    }

    @Override
    public int getVelocityTime() {
        return velocitycount;
    }

    @Override
    public int getAddedSpeed() {
        return velocityaddedspeed;
    }

    @Override
    public boolean hasVelocity() {
        return velocity;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("speed_level", speed_level);
        nbt.setBoolean("is_speedster", isSpeedster);
        nbt.setInteger("velocity_count", velocitycount);
        nbt.setBoolean("is_phasing", isPhasing);
        nbt.setInteger("max_level", maxSpeedLevel);
        nbt.setDouble("xp_level", xp);
        nbt.setInteger("level", level);
        nbt.setBoolean("velocity", velocity);
        nbt.setBoolean("is_wall_run", isWallRunning);
        nbt.setBoolean("has_second_trail", hasSecondTrail);
        nbt.setInteger("added_speed_velocity", velocityaddedspeed);
        nbt.setInteger("pr", pr);nbt.setInteger("pg", pg);nbt.setInteger("pb", pb);
        nbt.setInteger("lpr", lpr);nbt.setInteger("lpg", lpg);nbt.setInteger("lpb", lpb);
        nbt.setInteger("sr", sr);nbt.setInteger("sg", sg);nbt.setInteger("sb", sb);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        speed_level = nbt.getInteger("speed_level");
        isSpeedster = nbt.getBoolean("is_speedster");
        isPhasing = nbt.getBoolean("is_phasing");
        xp = nbt.getDouble("xp_level");
        level = nbt.getInteger("level");
        isWallRunning = nbt.getBoolean("is_wall_run");
        hasSecondTrail = nbt.getBoolean("has_second_trail");
        maxSpeedLevel = nbt.getInteger("max_level");
        velocity = nbt.getBoolean("velocity");
        velocitycount = nbt.getInteger("velocity_count");
        velocityaddedspeed = nbt.getInteger("added_speed_velocity");
        pr = nbt.getInteger("pr");pg = nbt.getInteger("pg");pb = nbt.getInteger("pb");
        lpr = nbt.getInteger("lpr");lpg = nbt.getInteger("lpg");lpb = nbt.getInteger("lpb");
        sr = nbt.getInteger("sr");sg = nbt.getInteger("sg");sb = nbt.getInteger("sb");
    }


    @Mod.EventBusSubscriber(modid = SpeedsterHeroesReborn.MODID)
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer)
                event.addCapability(new ResourceLocation(SpeedsterHeroesReborn.MODID, "speedsters_cap"), new CapSpeedstersStorage.SpeedsterCapProvider((EntityPlayer) event.getObject()));
        }

        @SubscribeEvent
        public static void update(LivingEvent.LivingUpdateEvent event) {
            CapabilitySpeedster cap = event.getEntityLiving().getCapability(CapSpeedstersStorage.CAPABILITY, null);
            if (cap != null) {
                cap.update();
                cap.sync();
            }
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            Capability.IStorage storage = CapSpeedstersStorage.CAPABILITY.getStorage();

            ISpeedsterCap oldCap = get(event.getOriginal());
            ISpeedsterCap newCap = get(event.getEntityPlayer());

            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(CapSpeedstersStorage.CAPABILITY, oldCap, null);
            storage.readNBT(CapSpeedstersStorage.CAPABILITY, newCap, null, nbt);
            get(event.getEntityPlayer()).sync();
        }

        @SubscribeEvent
        public static void playerTracking(PlayerEvent.StartTracking event) {
            get(event.getEntityPlayer()).sync();
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        get(event.player).sync();
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        get(event.player).sync();
    }

    @SubscribeEvent
    public static void onDeathEvent(LivingDeathEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            get((EntityPlayer) e.getEntityLiving()).sync();
        }
    }

    @Nonnull
    public static ISpeedsterCap get(EntityPlayer player) {
        if (player.hasCapability(CapSpeedstersStorage.CAPABILITY, null)) {
            return player.getCapability(CapSpeedstersStorage.CAPABILITY, null);
        }
        throw new IllegalStateException("Missing Cap - SpeedsterHeroesReborn");
    }
}