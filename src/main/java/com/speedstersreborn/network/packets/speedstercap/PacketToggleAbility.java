package com.speedstersreborn.network.packets.speedstercap;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.speedstersreborn.common.ability.AbilityPhase;
import com.speedstersreborn.common.ability.AbilitySpeed;
import com.speedstersreborn.common.ability.AbilityWallRunning;
import com.speedstersreborn.common.ability.SHRAbilities;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketToggleAbility implements IMessage {
	
	int id;
	
	public PacketToggleAbility() {
	}
	
	public PacketToggleAbility(int keyID) {
		this.id = keyID;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<PacketToggleAbility, IMessage> {
		
		@Override
		public IMessage onMessage(PacketToggleAbility message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServer().addScheduledTask(() -> {
				IAbilityCap cap = IAbilityCap.Impl.get(player);
				//TODO: temporary, delete later
				if(cap.getAbilities(player).size() < 3) {
					cap.getAbilities(player).clear();
					cap.addAbility(SHRAbilities.SPEED, player);
					cap.addAbility(SHRAbilities.WALL_RUNNING, player);
					cap.addAbility(SHRAbilities.PHASE, player);
				}
				
				AbilityBase ability = cap.getAbilities(player).size() > message.id ? cap.getAbilities(player).get(message.id) : null;
				if(ability != null) {
					ability.toggleAbility();
				}
			});
			return null;
		}
	}
}
