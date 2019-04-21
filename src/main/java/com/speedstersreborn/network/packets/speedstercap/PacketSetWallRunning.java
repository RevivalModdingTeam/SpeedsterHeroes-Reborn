package com.speedstersreborn.network.packets.speedstercap;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetWallRunning implements IMessage {


    public PacketSetWallRunning() {
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketSetWallRunning, IMessage> {

            @Override
            public IMessage onMessage (PacketSetWallRunning message, MessageContext ctx){
                ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                    EntityPlayer player = ctx.getServerHandler().player;
                    ISpeedsterCap data = CapabilitySpeedster.get(player);
                    if(data.isSpeedster()) {
                        if (data.isWallRunning()) {
                            data.setWallRunning(false);
                        } else {
                            data.setWallRunning(true);
                        }
                        data.sync();
                    }
                });
                return null;
            }
        }
}
