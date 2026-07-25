package com.taczmorearmor;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Set;
import java.util.function.Supplier;

public class ArmorRepairItem extends Item {

    private final int repairAmount;
    private final Set<Supplier<Item>> compatibleArmors;

    @SafeVarargs
    public ArmorRepairItem(Properties properties, int repairAmount, Supplier<Item>... compatibleArmors) {
        super(properties);
        this.repairAmount = repairAmount;
        this.compatibleArmors = Set.of(compatibleArmors);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack repairItem = player.getItemInHand(hand);

        EquipmentSlot[] slots = {EquipmentSlot.CHEST, EquipmentSlot.LEGS};
        for (EquipmentSlot slot : slots) {
            ItemStack armor = player.getItemBySlot(slot);
            if (armor.isEmpty() || !armor.isDamaged()) {
                continue;
            }

            boolean compatible = false;
            for (Supplier<Item> supplier : compatibleArmors) {
                if (armor.getItem() == supplier.get()) {
                    compatible = true;
                    break;
                }
            }
            if (!compatible) {
                continue;
            }

            if (!level.isClientSide) {
                int newDamage = Math.max(0, armor.getDamageValue() - repairAmount);
                armor.setDamageValue(newDamage);

                if (!player.getAbilities().instabuild) {
                    repairItem.shrink(1);
                }

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.TAPE_REPAIR.get(),
                        SoundSource.PLAYERS,
                        1.0F, 1.0F);
            }
            return InteractionResultHolder.sidedSuccess(repairItem, level.isClientSide());
        }

        return InteractionResultHolder.pass(repairItem);
    }
}