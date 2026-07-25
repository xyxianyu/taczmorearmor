package com.taczmorearmor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    // 胸甲 —— protection 顺序: 头盔, 胸甲, 护腿, 靴子
    ARMOR_220("armor_220", 5, new int[]{0, 3, 0, 0}, 15, ModSounds.EQUIP_BODY_ARMOR, 0.0f, 0.0f, () -> Ingredient.EMPTY),
    ARMOR_HTAC("armor_htac", 15, new int[]{0, 6, 0, 0}, 9, ModSounds.EQUIP_BODY_ARMOR, 0.0f, 0.0f, () -> Ingredient.EMPTY),
    ARMOR_6B2("armor_6b2", 15, new int[]{0, 7, 0, 0}, 9, ModSounds.EQUIP_BODY_ARMOR, 0.0f, 0.0f, () -> Ingredient.EMPTY),
    ARMOR_SEK("armor_sek", 15, new int[]{0, 7, 0, 0}, 9, ModSounds.EQUIP_BODY_ARMOR, 1.0f, 0.0f, () -> Ingredient.EMPTY),
    ARMOR_6B13("armor_6b13", 15, new int[]{0, 8, 0, 0}, 9, ModSounds.EQUIP_BODY_ARMOR, 1.0f, 0.0f, () -> Ingredient.EMPTY),
    ARMOR_926("armor_926", 33, new int[]{0, 8, 0, 0}, 10, ModSounds.EQUIP_BODY_ARMOR, 2.0f, 0.0f, () -> Ingredient.EMPTY),
    ARMOR_IMTV("armor_imtv", 33, new int[]{0, 9, 0, 0}, 10, ModSounds.EQUIP_BODY_ARMOR, 3.0f, 0.1f, () -> Ingredient.EMPTY),
    ARMOR_6B45("armor_6b45", 37, new int[]{0, 10, 0, 0}, 15, ModSounds.EQUIP_BODY_ARMOR, 4.0f, 0.2f, () -> Ingredient.EMPTY),
    ARMOR_BT201("armor_bt201", 71, new int[]{0, 10, 0, 0}, 15, ModSounds.EQUIP_BODY_ARMOR, 4.0f, 0.1f, () -> Ingredient.EMPTY), // 暂不改

    // 护腿
    ARMOR_6B45_LEGGINGS("armor_6b45_leggings", 37, new int[]{0, 0, 6, 0}, 15, ModSounds.EQUIP_LEGGINGS, 3.0f, 0.1f, () -> Ingredient.EMPTY),

    // 靴子
    MERRELL_MOAB_3_GTX_LOW("merrell_moab_3_gtx_low", 15, new int[]{0, 0, 0, 2}, 9, () -> SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.EMPTY),
    LOWA_ZEPHYR_MK2_GTX_HI("lowa_zephyr_mk2_gtx_hi", 33, new int[]{0, 0, 0, 3}, 10, () -> SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, () -> Ingredient.EMPTY),
    SALOMON_QUEST_4D_GTX_FORCES("salomon_quest_4d_gtx_forces", 33, new int[]{0, 0, 0, 3}, 10, () -> SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0f, 0.0f, () -> Ingredient.EMPTY);

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final Supplier<SoundEvent> equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue,
                      Supplier<SoundEvent> equipSound, float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound.get();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return ExampleMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}