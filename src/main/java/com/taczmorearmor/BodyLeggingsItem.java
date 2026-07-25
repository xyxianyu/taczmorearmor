package com.taczmorearmor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BodyLeggingsItem extends ArmorItem {

    private final Supplier<Item> requiredChestplate;

    public BodyLeggingsItem(ArmorMaterial material, Properties properties, Supplier<Item> requiredChestplate) {
        super(material, Type.LEGGINGS, properties);
        this.requiredChestplate = requiredChestplate;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        if (armorType == EquipmentSlot.LEGS && entity instanceof Player player) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            return !chest.isEmpty() && chest.getItem() == this.requiredChestplate.get();
        }
        return super.canEquip(stack, armorType, entity);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return ExampleMod.MOD_ID + ":textures/models/armor/armor_6b45_leggings_layer_1.png";
    }
}