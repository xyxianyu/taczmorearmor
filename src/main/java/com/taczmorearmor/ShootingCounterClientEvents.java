package com.taczmorearmor;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT)
public class ShootingCounterClientEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        ShootingCounterManager.clientTick();
    }

    /** 左键 = 记枪；开着任何界面（含物品栏）时不记 */
    @SubscribeEvent
    public static void onInteraction(InputEvent.InteractionKeyMappingTriggered event) {
        if (!event.isAttack()) {
            return;
        }
        if (!ShootingCounterManager.isRunning()) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null) {
            return; // 物品栏 / 菜单里左键不计
        }
        ShootingCounterManager.recordShot();
    }

    /** 本地玩家死亡 → 停止计时 */
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }

        // 自己死了：停表
        if (event.getEntity() == mc.player) {
            if (ShootingCounterManager.isActive()) {
                ShootingCounterManager.requestStop();
            }
            return;
        }

        // 计时中击杀别人：Kills+1
        if (!ShootingCounterManager.isRunning()) {
            return;
        }
        if (!(event.getSource().getEntity() instanceof Player killer)) {
            return;
        }
        if (killer.getUUID().equals(mc.player.getUUID())) {
            ShootingCounterManager.recordKill();
        }
    }

    /** 扔出「射击计数器」→ 停止计时 */
    @SubscribeEvent
    public static void onItemToss(ItemTossEvent event) {
        if (!ShootingCounterManager.isActive()) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || event.getPlayer() == null) {
            return;
        }
        if (!event.getPlayer().getUUID().equals(mc.player.getUUID())) {
            return;
        }
        ItemStack stack = event.getEntity().getItem();
        if (stack.is(ModItems.SHOOTING_COUNTER.get())) {
            ShootingCounterManager.requestStop();
        }
    }
}