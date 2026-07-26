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

    public static final RegistryObject<SoundEvent> COUNTER_STAND_BY =
            SOUND_EVENTS.register("counter_stand_by",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "counter_stand_by")));

    public static final RegistryObject<SoundEvent> COUNTER_BEEP_START =
            SOUND_EVENTS.register("counter_beep_start",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "counter_beep_start")));

    public static final RegistryObject<SoundEvent> COUNTER_BEEP_END =
            SOUND_EVENTS.register("counter_beep_end",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, "counter_beep_end")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}