package com.taczmorearmor;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ExampleMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ArmorWorkbenchRepairRecipe>> ARMOR_WORKBENCH_REPAIR =
            SERIALIZERS.register("armor_workbench_repair",
                    () -> new SimpleCraftingRecipeSerializer<>(ArmorWorkbenchRepairRecipe::new));

    public static final RegistryObject<RecipeSerializer<BootWorkbenchRepairRecipe>> BOOT_WORKBENCH_REPAIR =
            SERIALIZERS.register("boot_workbench_repair",
                    () -> new SimpleCraftingRecipeSerializer<>(BootWorkbenchRepairRecipe::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}