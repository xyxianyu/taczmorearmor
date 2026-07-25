package com.taczmorearmor;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.Set;

public class ArmorWorkbenchRepairRecipe extends CustomRecipe {

    private static final int REPAIR_AMOUNT = 200;
    private static final int TOOL_DAMAGE = 10;

    public ArmorWorkbenchRepairRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    private static final Set<Item> REPAIRABLE = Set.of();

    private boolean isRepairable(ItemStack stack) {
        if (stack.isEmpty() || !stack.isDamageableItem()) return false;
        Item item = stack.getItem();
        return item == ModItems.ARMOR_220.get()
                || item == ModItems.ARMOR_HTAC.get()
                || item == ModItems.ARMOR_6B45_LEGGINGS.get();
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack armor = ItemStack.EMPTY;
        ItemStack tape = ItemStack.EMPTY;
        ItemStack tool = ItemStack.EMPTY;
        int extra = 0;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            if (isRepairable(stack) && armor.isEmpty()) {
                armor = stack;
            } else if (stack.getItem() == ModItems.DT8_TAPE.get() && tape.isEmpty()) {
                tape = stack;
            } else if (stack.getItem() == ModItems.LEATHERMAN_RAPTOR.get() && tool.isEmpty()) {
                tool = stack;
            } else {
                extra++;
            }
        }

        return !armor.isEmpty()
                && armor.isDamaged()
                && !tape.isEmpty()
                && !tool.isEmpty()
                && extra == 0
                && tool.getDamageValue() < tool.getMaxDamage();
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        ItemStack armor = ItemStack.EMPTY;
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (isRepairable(stack)) {
                armor = stack.copy();
                break;
            }
        }
        if (armor.isEmpty()) return ItemStack.EMPTY;

        int newDamage = Math.max(0, armor.getDamageValue() - REPAIR_AMOUNT);
        armor.setDamageValue(newDamage);
        return armor;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {
        NonNullList<ItemStack> remains = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.getItem() == ModItems.LEATHERMAN_RAPTOR.get()) {
                ItemStack tool = stack.copy();
                tool.setCount(1);
                if (tool.hurt(TOOL_DAMAGE, net.minecraft.util.RandomSource.create(), null)) {
                    remains.set(i, ItemStack.EMPTY);
                } else {
                    remains.set(i, tool);
                }
            }
        }
        return remains;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ARMOR_WORKBENCH_REPAIR.get();
    }
}