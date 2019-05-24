package com.speedstersreborn.network.packets.speedstercap;

import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetSpeedster implements IMessage {


    public PacketSetSpeedster() {
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketSetSpeedster, IMessage> {
        @Override
        public IMessage onMessage(PacketSetSpeedster message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                ISpeedsterCap data = CapabilitySpeedster.get(player);
                if (data.isSpeedster() || data.hasVelocity()) {
                    data.setSpeedster(false);
                    PlayerHelper.sendMessage(player, "Disabled Speedster Power", true);
                } else {
                    data.setSpeedster(true);
                    PlayerHelper.sendMessage(player, "You're a speedster!", true);
                }
                data.sync();
            });
            return null;
        }
    }

}
