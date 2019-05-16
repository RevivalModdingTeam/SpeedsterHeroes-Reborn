package com.speedstersreborn.common.capabilities;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.api.SpeedAPI;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketCapSync;
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

public class CapabilitySpeedster implements ISpeedsterCap {

    private EntityPlayer player;
    private int speed_level = 0;
    private boolean isSpeedster = false;
    private boolean isPhasing = false;
    private int level = 0;
    private double xp = 0.0;
    private boolean isWallRunning = false;

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
            SpeedAPI.setSpeedFromCap(player);
        }

        if (getSpeedLevel() < 0) {
            setSpeedLevel(0);
        }
    }

    @Override
    public void sync() {
        NetworkHandler.INSTANCE.sendToAll(new PacketCapSync(player, serializeNBT()));
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
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("speed_level", speed_level);
        nbt.setBoolean("is_speedster", isSpeedster);
        nbt.setBoolean("is_phasing", isPhasing);
        nbt.setDouble("xp_level", xp);
        nbt.setInteger("level", level);
        nbt.setBoolean("is_wall_run", isWallRunning);
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
            if (cap != null)
                cap.update();
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