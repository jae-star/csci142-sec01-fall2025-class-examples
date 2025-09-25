package lock;

public class KeyLock implements Lock {
    private int key;
    private boolean isLocked;
    private boolean isInserted;

    public KeyLock(int key) {
        this.key = key;
        this.isLocked = true;
        this.isInserted = false;
    }

    public boolean insertKey(int key) {
        if (key != this.key) {
            return false;
        }
        isInserted = true;
        return true;
    }

    public boolean removeKey() {
        if (isInserted) {
            isInserted = false;
            return true;
        }
        return false;
    }

    public boolean turn() {
        if (isInserted) {
            isLocked = !isLocked; // toggle
            return true;
        }
        return false;
    }

    @Override
    public boolean lock() {
        if (isInserted) {
            isLocked = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean unlock() {
        if (isInserted) {
            isLocked = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }
}
