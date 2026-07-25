package com.taczmorearmor;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class BootWorkbenchRepairRecipe extends CustomRecipe {

    private static final int REPAIR_AMOUNT = 100;
    private static final int TOOL_DAMAGE = 10;

    public BootWorkbenchRepairRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    private boolean isBoot(ItemStack stack) {
        if (stack.isEmpty() || !stack.isDamageableItem()) return false;
        Item item = stack.getItem();
        return item == ModItems.MERRELL_MOAB_3_GTX_LOW.get()
                || item == ModItems.LOWA_ZEPHYR_MK2_GTX_HI.get()
                || item == ModItems.SALOMON_QUEST_4D_GTX_FORCES.get();
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack boot = ItemStack.EMPTY;
        ItemStack leather = ItemStack.EMPTY;
        ItemStack tool = ItemStack.EMPTY;
        int extra = 0;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            if (isBoot(stack) && boot.isEmpty()) {
                boot = stack;
            } else if (stack.getItem() == Items.LEATHER && leather.isEmpty()) {
                leather = stack;
            } else if (stack.getItem() == ModItems.LEATHERMAN_RAPTOR.get() && tool.isEmpty()) {
                tool = stack;
            } else {
                extra++;
            }
        }

        return !boot.isEmpty()
                && boot.isDamaged()
                && !leather.isEmpty()
                && !tool.isEmpty()
                && extra == 0
                && tool.getDamageValue() < tool.getMaxDamage();
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (isBoot(stack)) {
                ItemStack result = stack.copy();
                result.setDamageValue(Math.max(0, result.getDamageValue() - REPAIR_AMOUNT));
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {
        NonNullList<ItemStack> remains = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.getItem() == ModItems.LEATHERMAN_RAPTOR.get()) {
                ItemStack tool = stack.copy();
                tool.setCount(1);
                if (tool.hurt(TOOL_DAMAGE, RandomSource.create(), null)) {
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
        return ModRecipes.BOOT_WORKBENCH_REPAIR.get();
    }
}