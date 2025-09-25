package lock;

import java.util.Arrays;

public class KeylessEntryLock extends KeyLock {

    public static final int MAX_NUM_USER_CODES = 10;
    public static final int USER_CODE_LENGTH = 4;
    public static final int MASTER_CODE_LENGTH = 6;

    private int[] masterCode;
    private int[][] userCodes;
    private int userCount;

    private boolean isReset;
    private boolean isNewUserCode;
    private boolean isDeletedUserCode;
    private boolean isChangedMasterCode;
    private boolean areAllUserCodesDeleted;
    private int[] attempt;
    private int attemptIndex;

    public KeylessEntryLock(int keyValue, int[] masterCode) {
        super(keyValue);
        if (masterCode.length != MASTER_CODE_LENGTH) {
            throw new IllegalArgumentException("Master code must be 6 digits");
        }
        this.masterCode = Arrays.copyOf(masterCode, MASTER_CODE_LENGTH);
        this.userCodes = new int[MAX_NUM_USER_CODES][USER_CODE_LENGTH];
        this.userCount = 0;
        this.attempt = new int[MASTER_CODE_LENGTH]; // used for code entry
        this.attemptIndex = 0;
    }

    public boolean pushButton(char button) {
        // Simplified: simulate pressing * and numeric codes
        // Real logic would involve a state machine
        return true;
    }

    public boolean addedUserCode() {
        return isNewUserCode;
    }

    public boolean deletedUserCode() {
        return isDeletedUserCode;
    }

    public boolean deletedAllUserCodes() {
        return areAllUserCodesDeleted;
    }

    public boolean changedMasterCode() {
        return isChangedMasterCode;
    }

    public int[] getMasterCode() {
        return Arrays.copyOf(masterCode, MASTER_CODE_LENGTH);
    }
}
