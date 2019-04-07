package com.speedstersreborn.network;

import com.revivalcore.core.RevivalCore;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.network.packets.PacketSetSpeed;
import com.speedstersreborn.network.packets.PacketSetSpeedster;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SpeedsterHeroesReborn.MODID);
    private static int id = -1;

    public static void init() {
        INSTANCE.registerMessage(PacketSetSpeedster.Handler.class, PacketSetSpeedster.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketSetSpeed.Handler.class, PacketSetSpeed.class, id++, Side.SERVER);
    }
}
