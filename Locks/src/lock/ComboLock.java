package lock;

import java.util.Arrays;

public class ComboLock implements Lock {
    public final static int COMBO_LENGTH = 3;
    public final static int MAX_TICKS = 39;

    private int[] combination;
    private int[] attempt;
    private boolean isLocked;
    private boolean isReset;
    private int attemptIndex;

    public ComboLock(int[] combo) {
        if (combo.length != COMBO_LENGTH) {
            throw new IllegalArgumentException("Combination must be length 3");
        }
        this.combination = Arrays.copyOf(combo, COMBO_LENGTH);
        this.attempt = new int[COMBO_LENGTH];
        this.isLocked = true;
        this.isReset = true;
        this.attemptIndex = 0;
    }

    public boolean turnRight(int ticks) {
        if (attemptIndex < COMBO_LENGTH) {
            attempt[attemptIndex++] = ticks % (MAX_TICKS + 1);
            return true;
        }
        return false;
    }

    public boolean turnLeft(int ticks) {
        if (attemptIndex < COMBO_LENGTH) {
            attempt[attemptIndex++] = ticks % (MAX_TICKS + 1);
            return true;
        }
        return false;
    }

    public void reset() {
        attemptIndex = 0;
        Arrays.fill(attempt, -1);
        isReset = true;
    }

    public boolean isReset() {
        return isReset;
    }

    @Override
    public boolean lock() {
        isLocked = true;
        reset();
        return true;
    }

    @Override
    public boolean unlock() {
        if (Arrays.equals(combination, attempt)) {
            isLocked = false;
            reset();
            return true;
        }
        return false;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    public int[] getCombination() {
        return Arrays.copyOf(combination, COMBO_LENGTH);
    }
}
