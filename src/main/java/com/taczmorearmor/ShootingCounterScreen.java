package com.taczmorearmor;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * 射击计数器界面。
 */
public class ShootingCounterScreen extends Screen {

    private static final int BTN_W = 100;
    private static final int BTN_H = 20;
    private static final int BTN_GAP = 8;

    public ShootingCounterScreen() {
        super(Component.translatable("screen.taczmorearmor.shooting_counter"));
    }

    @Override
    protected void init() {
        super.init();

        int cx = this.width / 2;
        int top = 50;

        this.addRenderableWidget(Button.builder(Component.literal("开始"), b -> {
            ShootingCounterManager.requestStart();
        }).bounds(cx - BTN_W - BTN_GAP / 2, top, BTN_W, BTN_H).build());

        this.addRenderableWidget(Button.builder(Component.literal("结束"), b -> {
            ShootingCounterManager.requestStop();
        }).bounds(cx + BTN_GAP / 2, top, BTN_W, BTN_H).build());

        int row2 = top + BTN_H + BTN_GAP;
        this.addRenderableWidget(Button.builder(
                Component.literal(modeLabel()),
                b -> {
                    ShootingCounterManager.toggleMode();
                    b.setMessage(Component.literal(modeLabel()));
                }
        ).bounds(cx - BTN_W / 2, row2, BTN_W, BTN_H).build());

        int row3 = row2 + BTN_H + BTN_GAP;
        this.addRenderableWidget(Button.builder(Component.literal("-1s"), b -> {
            ShootingCounterManager.addTimedSeconds(-1);
        }).bounds(cx - BTN_W - BTN_GAP / 2, row3, BTN_W, BTN_H).build());

        this.addRenderableWidget(Button.builder(Component.literal("+1s"), b -> {
            ShootingCounterManager.addTimedSeconds(1);
        }).bounds(cx + BTN_GAP / 2, row3, BTN_W, BTN_H).build());
    }

    private static String modeLabel() {
        return ShootingCounterManager.getMode() == ShootingCounterManager.Mode.TIMED
                ? "模式: 定时"
                : "模式: 手动";
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        int cx = this.width / 2;

        graphics.drawCenteredString(this.font, this.title, cx, 20, 0xFFFFFF);

        graphics.drawCenteredString(this.font,
                ShootingCounterManager.getStatusText(),
                cx, 36, 0xA0A0A0);

        int timeY = 50 + (BTN_H + BTN_GAP) * 2 + BTN_H + 6;
        graphics.drawCenteredString(this.font,
                "定时时长: " + ShootingCounterManager.getTimedSeconds() + "s",
                cx, timeY, 0xFFFFFF);

        int resultTop = timeY + 24;
        int line = 12;
        int left = cx - 120;
        int color = 0xE0E0E0;

        graphics.drawString(this.font, "最近成绩", left, resultTop, 0xFFFF55, false);
        graphics.drawString(this.font, "1st (首发): " + ShootingCounterManager.getLastFirst(),
                left, resultTop + line, color, false);
        graphics.drawString(this.font, "Avg (平均Split): " + ShootingCounterManager.getLastAvg(),
                left, resultTop + line * 2, color, false);
        graphics.drawString(this.font, "Total (总时间): " + ShootingCounterManager.getLastTotal(),
                left, resultTop + line * 3, color, false);
        graphics.drawString(this.font, "Shots (总弹数): " + ShootingCounterManager.getLastShots(),
                left, resultTop + line * 4, color, false);
        graphics.drawString(this.font, "Kills (击杀数): " + ShootingCounterManager.getLastKills(),
                left, resultTop + line * 5, color, false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}