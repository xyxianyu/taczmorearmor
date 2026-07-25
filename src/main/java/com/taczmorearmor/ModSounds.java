package com.taczmorearmor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ExampleMod.MOD_ID);

    public static final RegistryObject<SoundEvent> EQUIP_BODY_ARMOR =
            SOUND_EVENTS.register("equip_body_armor",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "equip_body_armor")));

    public static final RegistryObject<SoundEvent> EQUIP_LEGGINGS =
            SOUND_EVENTS.register("equip_leggings",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "equip_leggings")));

    public static final RegistryObject<SoundEvent> PLATE_INSERT =
            SOUND_EVENTS.register("plate_insert",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "plate_insert")));

    public static final RegistryObject<SoundEvent> TAPE_REPAIR =
            SOUND_EVENTS.register("tape_repair",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "tape_repair")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}