package com.taczmorearmor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BodyBootsItem extends ArmorItem {

    private final String textureName;

    public BodyBootsItem(ArmorMaterial material, Properties properties, String textureName) {
        super(material, Type.BOOTS, properties);
        this.textureName = textureName;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return ExampleMod.MOD_ID + ":textures/models/armor/" + this.textureName + "_layer_1.png";
    }
}