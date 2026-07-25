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

public class BallisticPlateItem extends Item {

    private final int repairAmount;
    private final Set<Supplier<Item>> compatibleArmors;

    @SafeVarargs
    public BallisticPlateItem(Properties properties, int repairAmount, Supplier<Item>... compatibleArmors) {
        super(properties);
        this.repairAmount = repairAmount;
        this.compatibleArmors = Set.of(compatibleArmors);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack plate = player.getItemInHand(hand);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (chest.isEmpty() || !chest.isDamaged()) {
            return InteractionResultHolder.pass(plate);
        }

        boolean compatible = false;
        for (Supplier<Item> supplier : compatibleArmors) {
            if (chest.getItem() == supplier.get()) {
                compatible = true;
                break;
            }
        }

        if (!compatible) {
            return InteractionResultHolder.pass(plate);
        }

        if (!level.isClientSide) {
            int newDamage = Math.max(0, chest.getDamageValue() - repairAmount);
            chest.setDamageValue(newDamage);

            if (!player.getAbilities().instabuild) {
                plate.shrink(1);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.PLATE_INSERT.get(),
                    SoundSource.PLAYERS,
                    1.0F, 1.0F);
        }

        return InteractionResultHolder.sidedSuccess(plate, level.isClientSide());
    }
}