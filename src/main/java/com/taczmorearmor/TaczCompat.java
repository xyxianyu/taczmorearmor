package com.taczmorearmor;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;

public class TaczCompat {

    public static void tryInit() {
        if (ModList.get().isLoaded("tacz")) {
            MinecraftForge.EVENT_BUS.register(new TaczDamageHandler());
        }
    }
}