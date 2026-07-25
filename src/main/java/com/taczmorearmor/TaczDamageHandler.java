package com.taczmorearmor;

import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
import com.tacz.guns.resource.index.CommonGunIndex;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TaczDamageHandler {

    private static final Map<UUID, Float> PENDING_MULTIPLIER = new ConcurrentHashMap<>();

    @SubscribeEvent
    public void onGunHurtPre(EntityHurtByGunEvent.Pre event) {
        if (event.getLogicalSide().isClient()) {
            return;
        }
        // 爆头：不额外减伤
        if (event.isHeadShot()) {
            return;
        }

        Entity hurt = event.getHurtEntity();
        if (!(hurt instanceof LivingEntity living)) {
            return;
        }

        ItemStack chest = living.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.isEmpty()) {
            return;
        }

        float reduction = getReduction(chest.getItem(), event.getGunId());
        if (reduction <= 0f) {
            return;
        }

        // 减伤 65% => 最终伤害只剩 35%
        PENDING_MULTIPLIER.put(living.getUUID(), 1f - reduction);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        Float mult = PENDING_MULTIPLIER.remove(event.getEntity().getUUID());
        if (mult == null) {
            return;
        }

        float amount = event.getAmount();
        if (amount <= 0f) {
            return;
        }

        event.setAmount(amount * mult);
    }

    private float getReduction(Item chestItem, ResourceLocation gunId) {
        String gunType = resolveGunType(gunId);

        if (chestItem == ModItems.ARMOR_220.get()) {
            return switch (gunType) {
                case "pistol" -> 0.65f;
                case "smg" -> 0.25f;
                case "shotgun" -> 0.65f;
                default -> 0.05f;
            };
        }
        if (chestItem == ModItems.ARMOR_HTAC.get()) {
            return switch (gunType) {
                case "pistol" -> 0.75f;
                case "smg" -> 0.65f;
                case "shotgun" -> 0.75f;
                case "rifle", "mg" -> 0.10f;
                default -> 0.05f;
            };
        }
        if (chestItem == ModItems.ARMOR_6B2.get()) {
            return switch (gunType) {
                case "pistol" -> 0.80f;
                case "smg" -> 0.75f;
                case "shotgun" -> 0.80f;
                case "rifle", "mg" -> 0.15f;
                default -> 0.05f;
            };
        }
        if (chestItem == ModItems.ARMOR_SEK.get()) {
            return switch (gunType) {
                case "pistol", "smg", "shotgun" -> 0.80f;
                case "rifle", "mg" -> 0.65f;
                case "sniper" -> 0.10f;
                default -> 0.10f;
            };
        }
        if (chestItem == ModItems.ARMOR_6B13.get()) {
            return switch (gunType) {
                case "pistol", "smg", "shotgun" -> 0.80f;
                case "rifle", "mg" -> 0.70f;
                case "sniper" -> 0.10f;
                default -> 0.10f;
            };
        }
        if (chestItem == ModItems.ARMOR_926.get()) {
            return switch (gunType) {
                case "pistol", "smg", "shotgun" -> 0.80f;
                case "rifle", "mg" -> 0.75f;
                case "sniper" -> 0.50f;
                default -> 0.10f;
            };
        }
        if (chestItem == ModItems.ARMOR_IMTV.get()) {
            return switch (gunType) {
                case "pistol", "smg", "shotgun" -> 0.85f;
                case "rifle", "mg" -> 0.80f;
                case "sniper" -> 0.60f;
                default -> 0.15f;
            };
        }
        if (chestItem == ModItems.ARMOR_6B45.get()) {
            return switch (gunType) {
                case "pistol", "smg", "shotgun" -> 0.90f;
                case "rifle", "mg" -> 0.85f;
                case "sniper" -> 0.80f;
                default -> 0.20f;
            };
        }
        return 0f;
    }

    private String resolveGunType(ResourceLocation gunId) {
        return TimelessAPI.getCommonGunIndex(gunId)
                .map(CommonGunIndex::getType)
                .orElse("unknown");
    }
}