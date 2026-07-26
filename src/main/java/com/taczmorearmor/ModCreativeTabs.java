package com.taczmorearmor;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TACZ_ARMOR_TAB = CREATIVE_MODE_TABS.register("tacz_armor_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ARMOR_220.get()))
                    .title(Component.translatable("creativetab.taczmorearmor.armor_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ARMOR_220.get());
                        output.accept(ModItems.ARMOR_6B2.get());
                        output.accept(ModItems.ARMOR_HTAC.get());
                        output.accept(ModItems.ARMOR_6B13.get());
                        output.accept(ModItems.ARMOR_SEK.get());
                        output.accept(ModItems.ARMOR_IMTV.get());
                        output.accept(ModItems.ARMOR_926.get());
                        output.accept(ModItems.ARMOR_6B45.get());
                        output.accept(ModItems.ARMOR_BT201.get());
                        output.accept(ModItems.ARMOR_6B45_LEGGINGS.get());
                        output.accept(ModItems.MERRELL_MOAB_3_GTX_LOW.get());
                        output.accept(ModItems.LOWA_ZEPHYR_MK2_GTX_HI.get());
                        output.accept(ModItems.SALOMON_QUEST_4D_GTX_FORCES.get());
                        output.accept(ModItems.CORDURA_NYLON.get());
                        output.accept(ModItems.CORDURA_NYLON_66_FABRIC.get());
                        output.accept(ModItems.CORDURA_HEAVY_NYLON_FABRIC.get());
                        output.accept(ModItems.ARAMID.get());
                        output.accept(ModItems.ARAMID_FIBER_FABRIC.get());
                        output.accept(ModItems.HARD_ALLOY_PLATE.get());
                        output.accept(ModItems.SILICON_CARBIDE.get());
                        output.accept(ModItems.BLACK_SILICON_PLATE.get());
                        output.accept(ModItems.BORON_CARBIDE.get());
                        output.accept(ModItems.BLACK_BORON_PLATE.get());
                        output.accept(ModItems.EVA.get());
                        output.accept(ModItems.DT8_TAPE.get());
                        output.accept(ModItems.LEATHERMAN_RAPTOR.get());
                        output.accept(ModItems.SHOOTING_COUNTER.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}