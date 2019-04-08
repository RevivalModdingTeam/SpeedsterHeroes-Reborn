package com.speedstersreborn.client;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.PacketSetSpeed;
import com.speedstersreborn.network.packets.PacketSetSpeedster;
import com.speedstersreborn.util.helper.PlayerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {

    public static KeyBinding SPEEDSTER;
    public static KeyBinding UP;

    public static void init() {
        SPEEDSTER = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.speedster", Keyboard.KEY_X, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(SPEEDSTER);
        UP = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.up", Keyboard.KEY_N, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(UP);
    }

    @SubscribeEvent
    public static void onClientTick(InputUpdateEvent e) {
        if (SPEEDSTER.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeedster());
        }

        if (UP.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeed());
        }
    }

    @SubscribeEvent
    public static void keyInput(InputUpdateEvent e) {
        if (Minecraft.getMinecraft().player == null)
            return;

        if (PlayerHelper.getboolean(Minecraft.getMinecraft().player, "ontread")) {
            EntityPlayer entityPlayer = Minecraft.getMinecraft().player;
           /* MovementInput moveType = e.getMovementInput();
            moveType.rightKeyDown = false;
            moveType.leftKeyDown = false;
            moveType.backKeyDown = false;
            moveType.jump = false;
            moveType.moveForward = 0.1F;
            moveType.sneak = false;
            moveType.moveStrafe = 0.0F;*/
            BlockPos pos = new BlockPos(entityPlayer.getEntityData().getInteger("x"), entityPlayer.getEntityData().getInteger("y"), entityPlayer.getEntityData().getInteger("z"));
            Minecraft.getMinecraft().player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        }
    }
}
