package com.taczmorearmor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 客户端射击计数逻辑。
 * 状态：IDLE → STANDING_BY(2秒) → RUNNING → 结束算成绩。
 * Avg：去掉最快、最慢各一个 Split 后的平均；开枪 &lt; 3 时显示 /
 */
public class ShootingCounterManager {

    public enum Mode {
        TIMED,
        MANUAL
    }

    public enum Phase {
        IDLE,
        STANDING_BY,
        RUNNING
    }

    private static Phase phase = Phase.IDLE;
    private static Mode mode = Mode.TIMED;
    private static int timedSeconds = 8;

    private static long standByStartMs = 0L;
    private static long runStartMs = 0L;
    private static long timedEndMs = 0L;

    private static final List<Long> shotTimesMs = new ArrayList<>();
    private static int kills = 0;

    private static String lastFirst = "-";
    private static String lastAvg = "/";
    private static String lastTotal = "-";
    private static int lastShots = 0;
    private static int lastKills = 0;

    public static Phase getPhase() {
        return phase;
    }

    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode m) {
        mode = m;
    }

    public static void toggleMode() {
        mode = (mode == Mode.TIMED) ? Mode.MANUAL : Mode.TIMED;
    }

    public static int getTimedSeconds() {
        return timedSeconds;
    }

    public static void setTimedSeconds(int seconds) {
        timedSeconds = Math.max(1, Math.min(120, seconds));
    }

    public static void addTimedSeconds(int delta) {
        setTimedSeconds(timedSeconds + delta);
    }

    public static boolean isRunning() {
        return phase == Phase.RUNNING;
    }

    public static boolean isActive() {
        return phase == Phase.STANDING_BY || phase == Phase.RUNNING;
    }

    public static void requestStart() {
        if (phase != Phase.IDLE) {
            return;
        }
        phase = Phase.STANDING_BY;
        standByStartMs = System.currentTimeMillis();
        shotTimesMs.clear();
        kills = 0;
        playLocal(ModSounds.COUNTER_STAND_BY.get(), 1.0f, 1.0f);
    }

    public static void requestStop() {
        if (phase == Phase.STANDING_BY) {
            phase = Phase.IDLE;
            return;
        }
        if (phase == Phase.RUNNING) {
            finishAndCompute();
        }
    }

    public static void clientTick() {
        long now = System.currentTimeMillis();

        if (phase == Phase.STANDING_BY) {
            if (now - standByStartMs >= 2000L) {
                phase = Phase.RUNNING;
                runStartMs = now;
                timedEndMs = runStartMs + timedSeconds * 1000L;
                playLocal(ModSounds.COUNTER_BEEP_START.get(), 1.0f, 1.0f);
            }
            return;
        }

        if (phase == Phase.RUNNING && mode == Mode.TIMED) {
            if (now >= timedEndMs) {
                finishAndCompute();
            }
        }
    }

    public static void recordShot() {
        if (phase != Phase.RUNNING) {
            return;
        }
        shotTimesMs.add(System.currentTimeMillis());
    }

    public static void recordKill() {
        if (phase != Phase.RUNNING) {
            return;
        }
        kills++;
    }

    private static void finishAndCompute() {
        phase = Phase.IDLE;
        playLocal(ModSounds.COUNTER_BEEP_END.get(), 1.0f, 1.0f);

        lastShots = shotTimesMs.size();
        lastKills = kills;

        if (shotTimesMs.isEmpty()) {
            lastFirst = "-";
            lastAvg = "/";
            lastTotal = formatMs(System.currentTimeMillis() - runStartMs);
            return;
        }

        long firstSplit = shotTimesMs.get(0) - runStartMs;
        lastFirst = formatMs(firstSplit);

        List<Long> splits = new ArrayList<>();
        splits.add(firstSplit);
        for (int i = 1; i < shotTimesMs.size(); i++) {
            splits.add(shotTimesMs.get(i) - shotTimesMs.get(i - 1));
        }

        if (splits.size() < 3) {
            lastAvg = "/";
        } else {
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            long sum = 0L;
            for (long s : splits) {
                sum += s;
                if (s < min) min = s;
                if (s > max) max = s;
            }
            long trimmedSum = sum - min - max;
            int trimmedCount = splits.size() - 2;
            lastAvg = formatMs(trimmedSum / trimmedCount);
        }

        lastTotal = formatMs(shotTimesMs.get(shotTimesMs.size() - 1) - runStartMs);
    }

    private static String formatMs(long ms) {
        if (ms < 0) ms = 0;
        double sec = ms / 1000.0;
        return String.format(Locale.ROOT, "%.3fs", sec);
    }

    private static void playLocal(SoundEvent sound, float volume, float pitch) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null || sound == null) {
            return;
        }
        mc.level.playLocalSound(player.getX(), player.getY(), player.getZ(),
                sound, SoundSource.PLAYERS, volume, pitch, false);
    }

    public static String getLastFirst() { return lastFirst; }
    public static String getLastAvg() { return lastAvg; }
    public static String getLastTotal() { return lastTotal; }
    public static int getLastShots() { return lastShots; }
    public static int getLastKills() { return lastKills; }

    public static String getStatusText() {
        return switch (phase) {
            case IDLE -> "空闲";
            case STANDING_BY -> "Standing by...";
            case RUNNING -> mode == Mode.TIMED
                    ? "计时中 (定时 " + timedSeconds + "s)"
                    : "计时中 (手动结束)";
        };
    }

    public static String getResultSummary() {
        return "1st: " + lastFirst
                + "  |  Avg: " + lastAvg
                + "  |  Total: " + lastTotal
                + "  |  Shots: " + lastShots
                + "  |  Kills: " + lastKills;
    }
}