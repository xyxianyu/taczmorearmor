package com.taczmorearmor;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod {

    public static final String MOD_ID = "taczmorearmor";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ExampleMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // 注册物品
        ModItems.register(modEventBus);

        // 注册创造模式物品栏
        ModCreativeTabs.register(modEventBus);

        // 注册音效
        ModSounds.register(modEventBus);

        // 注册自定义合成
        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("TACZ: More Types of Body Armor 已加载！");
        event.enqueueWork(() -> {
            try {
                Class.forName("com.taczmorearmor.TaczCompat")
                        .getMethod("tryInit")
                        .invoke(null);
            } catch (Throwable t) {
                LOGGER.info("未检测到 TACZ，跳过枪械减伤联动。");
            }
        });
    }
}