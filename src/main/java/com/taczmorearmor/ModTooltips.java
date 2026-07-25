package com.taczmorearmor;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModTooltips {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        List<Component> tip = event.getToolTip();

        // 名称深蓝、等级蓝、效果按正负面、介绍灰
        if (item == ModItems.ARMOR_220.get()) {
            add(tip, "220防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "HG1级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型为Source Tactical Gear公司生产的ITA2100防弹衣，主要针对普通手枪弹及低速弹药，可使用布基胶带修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_HTAC.get()) {
            add(tip, "H-Tac特勤防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "HG2级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型为M-Tac低可视度护甲，能够防御大多数警用和民用手枪弹、低速弹药以及高威力左轮枪弹，可使用布基胶带修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_6B2.get()) {
            add(tip, "6B2防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "HG2级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型为苏联时期的6B2防弹背心，能够防御警用和民用手枪弹、低速弹药以及高威力左轮枪弹，可使用硬质合金插板修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_SEK.get()) {
            add(tip, "SEK堡垒防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "RF1级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型为俄罗斯5.45Design公司研发的SEK单兵作战系统中的防弹携行背心，主要针对普通步枪弹，可使用硬质合金插板修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_6B13.get()) {
            add(tip, "6B13防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "RF1级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型是俄罗斯的6B13防弹衣，可抵御普通步枪弹，可使用硬质合金插板修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_926.get()) {
            add(tip, "926复合防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "RF2级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型是美国Crye Precision公司研发的APC第四代先进插板背心，可抵御有一定穿甲能力的步枪弹，可使用黑硅插板修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_IMTV.get()) {
            add(tip, "IMTV武士进攻型防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "RF2级", ChatFormatting.BLUE);
            add(tip, "-12%移速", ChatFormatting.RED);
            add(tip, "原型是美国Point Blank Enterprises公司生产的IMTV改进型模块化战术背心，可抵御具备穿甲能力的步枪弹，可使用黑硅插板修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_6B45.get()) {
            add(tip, "6B45重装型防弹衣", ChatFormatting.DARK_BLUE);
            add(tip, "RF3级", ChatFormatting.BLUE);
            add(tip, "缓慢I", ChatFormatting.RED);
            add(tip, "原型是俄罗斯现役的6B45防弹衣，拥有最高级别的防护等级，可搭配配套的护腿，可使用黑硼插板修复", ChatFormatting.GRAY);
        } else if (item == ModItems.ARMOR_6B45_LEGGINGS.get()) {
            add(tip, "VKBO迷彩作战裤", ChatFormatting.DARK_BLUE);
            add(tip, "RF3级", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型为俄罗斯VKBO防风服，此护腿与6B45防弹衣绑定，在下腹部提供相应级别的防弹保护", ChatFormatting.GRAY);
        } else if (item == ModItems.LOWA_ZEPHYR_MK2_GTX_HI.get()) {
            add(tip, "LOWA ZEPHYR MK2", ChatFormatting.DARK_BLUE);
            add(tip, "无防弹效果", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "原型为德国LOWA旗下ZEPHYR（逆行者）系列的第二代升级款，可使用皮革修复", ChatFormatting.GRAY);
        } else if (item == ModItems.SALOMON_QUEST_4D_GTX_FORCES.get()) {
            add(tip, "Salomon Quest 4D Forces GTX", ChatFormatting.DARK_BLUE);
            add(tip, "无防弹效果", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "萨洛蒙（Salomon）于2014年推出的军警版（Forces）产品线中的重型徒步登山鞋，可使用皮革修复", ChatFormatting.GRAY);
        } else if (item == ModItems.MERRELL_MOAB_3_GTX_LOW.get()) {
            add(tip, "Merrell MOAB 3 GTX", ChatFormatting.DARK_BLUE);
            add(tip, "无防弹效果", ChatFormatting.BLUE);
            add(tip, "无特殊效果", ChatFormatting.GRAY);
            add(tip, "迈乐（Merrell）旗下非常经典的徒步鞋系列，海豹突击队曾采购，可使用线修复", ChatFormatting.GRAY);
        } else if (item == ModItems.DT8_TAPE.get()) {
            add(tip, "3M公司推出的多用途布基胶带（Duct Tape）", ChatFormatting.GRAY);
        } else if (item == ModItems.LEATHERMAN_RAPTOR.get()) {
            add(tip, "莱泽曼猛禽救援剪（Leatherman Raptor Rescue）是一款专为急救人员、护理人员及第一响应者设计的专业折叠医疗剪刀，当然也能胜任普通剪刀的工作，可配合DT8胶带或皮革修复物品", ChatFormatting.GRAY);
        }
    }

    private static void add(List<Component> tip, String text, ChatFormatting color) {
        tip.add(Component.literal(text).withStyle(color));
    }
}