package com.taczmorearmor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    // ===== 胸甲 =====
    public static final RegistryObject<Item> ARMOR_220 = ITEMS.register("armor_220",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_220, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_6B2 = ITEMS.register("armor_6b2",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_6B2, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_HTAC = ITEMS.register("armor_htac",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_HTAC, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_6B13 = ITEMS.register("armor_6b13",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_6B13, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_SEK = ITEMS.register("armor_sek",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_SEK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_IMTV = ITEMS.register("armor_imtv",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_IMTV, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_926 = ITEMS.register("armor_926",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_926, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_6B45 = ITEMS.register("armor_6b45",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_6B45, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_BT201 = ITEMS.register("armor_bt201",
            () -> new BodyArmorItem(ModArmorMaterials.ARMOR_BT201, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    // ===== 护腿 =====
    public static final RegistryObject<Item> ARMOR_6B45_LEGGINGS = ITEMS.register("armor_6b45_leggings",
            () -> new BodyLeggingsItem(
                    ModArmorMaterials.ARMOR_6B45_LEGGINGS,
                    new Item.Properties(),
                    () -> ModItems.ARMOR_6B45.get()
            ));

    // ===== 靴子 =====
    public static final RegistryObject<Item> MERRELL_MOAB_3_GTX_LOW = ITEMS.register("merrell_moab_3_gtx_low",
            () -> new BodyBootsItem(ModArmorMaterials.MERRELL_MOAB_3_GTX_LOW, new Item.Properties(), "merrell_moab_3_gtx_low"));

    public static final RegistryObject<Item> LOWA_ZEPHYR_MK2_GTX_HI = ITEMS.register("lowa_zephyr_mk2_gtx_hi",
            () -> new BodyBootsItem(ModArmorMaterials.LOWA_ZEPHYR_MK2_GTX_HI, new Item.Properties(), "lowa_zephyr_mk2_gtx_hi"));

    public static final RegistryObject<Item> SALOMON_QUEST_4D_GTX_FORCES = ITEMS.register("salomon_quest_4d_gtx_forces",
            () -> new BodyBootsItem(ModArmorMaterials.SALOMON_QUEST_4D_GTX_FORCES, new Item.Properties(), "salomon_quest_4d_gtx_forces"));

    // ===== 材料 =====
    public static final RegistryObject<Item> CORDURA_NYLON = ITEMS.register("cordura_nylon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORDURA_NYLON_66_FABRIC = ITEMS.register("cordura_nylon_66_fabric",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORDURA_HEAVY_NYLON_FABRIC = ITEMS.register("cordura_heavy_nylon_fabric",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARAMID = ITEMS.register("aramid",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARAMID_FIBER_FABRIC = ITEMS.register("aramid_fiber_fabric",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HARD_ALLOY_PLATE = ITEMS.register("hard_alloy_plate",
            () -> new BallisticPlateItem(new Item.Properties(), 200,
                    () -> ModItems.ARMOR_6B2.get(),
                    () -> ModItems.ARMOR_SEK.get(),
                    () -> ModItems.ARMOR_6B13.get()
            ));

    public static final RegistryObject<Item> SILICON_CARBIDE = ITEMS.register("silicon_carbide",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_SILICON_PLATE = ITEMS.register("black_silicon_plate",
            () -> new BallisticPlateItem(new Item.Properties(), 250,
                    () -> ModItems.ARMOR_926.get(),
                    () -> ModItems.ARMOR_IMTV.get()
            ));

    public static final RegistryObject<Item> BORON_CARBIDE = ITEMS.register("boron_carbide",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_BORON_PLATE = ITEMS.register("black_boron_plate",
            () -> new BallisticPlateItem(new Item.Properties(), 200,
                    () -> ModItems.ARMOR_6B45.get()
            ));

    public static final RegistryObject<Item> EVA = ITEMS.register("eva",
            () -> new Item(new Item.Properties()));

    // ===== 修复道具 / 工具 =====
    public static final RegistryObject<Item> DT8_TAPE = ITEMS.register("dt8_tape",
            () -> new ArmorRepairItem(new Item.Properties(), 50,
                    () -> ModItems.ARMOR_220.get(),
                    () -> ModItems.ARMOR_HTAC.get(),
                    () -> ModItems.ARMOR_6B45_LEGGINGS.get()
            ));

    public static final RegistryObject<Item> LEATHERMAN_RAPTOR = ITEMS.register("leatherman_raptor",
            () -> new Item(new Item.Properties().durability(500)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}