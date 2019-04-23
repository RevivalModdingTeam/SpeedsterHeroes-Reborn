package com.speedstersreborn.network;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.network.packets.PacketTeleport;
import com.speedstersreborn.network.packets.speedstercap.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SpeedsterHeroesReborn.MODID);
    private static int id = -1;

    public static void init() {
        INSTANCE.registerMessage(PacketSetSpeedster.Handler.class, PacketSetSpeedster.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketSetSpeed.Handler.class, PacketSetSpeed.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketCapSync.Handler.class, PacketCapSync.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketTeleport.Handler.class, PacketTeleport.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketSetWallRunning.Handler.class, PacketSetWallRunning.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketSetPhasing.Handler.class, PacketSetPhasing.class, id++, Side.SERVER);
    }
}
