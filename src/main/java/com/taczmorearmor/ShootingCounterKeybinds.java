package com.taczmorearmor;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

public class ShootingCounterKeybinds {

    public static final String CATEGORY = "key.categories.taczmorearmor";

    public static final KeyMapping START_KEY = new KeyMapping(
            "key.taczmorearmor.shooting_counter_start",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_BRACKET, // [
            CATEGORY
    );

    public static final KeyMapping STOP_KEY = new KeyMapping(
            "key.taczmorearmor.shooting_counter_stop",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_BRACKET, // ]
            CATEGORY
    );

    @Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register {
        @SubscribeEvent
        public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(START_KEY);
            event.register(STOP_KEY);
        }
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT)
    public static class Handle {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase != TickEvent.Phase.END) {
                return;
            }
            while (START_KEY.consumeClick()) {
                ShootingCounterManager.requestStart();
            }
            while (STOP_KEY.consumeClick()) {
                ShootingCounterManager.requestStop();
            }
        }
    }
}