package lock;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeylessEntryLock extends KeyLock {

    public static final int MAX_NUM_USER_CODES = 10;
    public static final int USER_CODE_LENGTH = 4;
    public static final int MASTER_CODE_LENGTH = 6;

    private boolean isReset;
    private boolean isNewUserCode;
    private boolean isDeletedUserCode;
    private boolean isChangedMasterCode;
    private boolean areAllUserCodesDeleted;

    private int[] masterCode;
    private int[][] userCodes; // rows of 4-digit user codes, -1 means empty
    private int[] attempt;     // (not strictly required but kept per original)
    private StringBuilder inputBuffer; // minimal state to collect keypresses

    // keypad lock state (Keyless can lock/unlock by keypad)
    private boolean locked;

    public KeylessEntryLock(int keyValue) {
        super(keyValue);

        // default master code 1,2,3,4,5,6 (you can change if your class examples used a specific default)
        masterCode = new int[MASTER_CODE_LENGTH];
        for (int i = 0; i < MASTER_CODE_LENGTH; i++) {
            masterCode[i] = i + 1;
        }

        // initialize userCodes to -1 (empty)
        userCodes = new int[MAX_NUM_USER_CODES][USER_CODE_LENGTH];
        for (int i = 0; i < MAX_NUM_USER_CODES; i++) {
            Arrays.fill(userCodes[i], -1);
        }

        attempt = new int[USER_CODE_LENGTH];
        inputBuffer = new StringBuilder();

        isReset = true;
        isNewUserCode = false;
        isDeletedUserCode = false;
        isChangedMasterCode = false;
        areAllUserCodesDeleted = false;

        locked = true; // starts locked
    }

    /**
     * Add user code string (4 digits). Puts in first empty slot. Returns true if added.
     */
    private boolean addUserCodeString(String s4) {
        if (s4 == null || s4.length() != USER_CODE_LENGTH) return false;
        int[] code = new int[USER_CODE_LENGTH];
        for (int i = 0; i < USER_CODE_LENGTH; i++) code[i] = s4.charAt(i) - '0';

        // check for duplicates
        for (int i = 0; i < MAX_NUM_USER_CODES; i++) {
            if (Arrays.equals(userCodes[i], code)) return false;
        }

        for (int i = 0; i < MAX_NUM_USER_CODES; i++) {
            if (userCodes[i][0] == -1) {
                userCodes[i] = code;
                return true;
            }
        }
        return false; // no space
    }

    /**
     * Delete first match of s4 user code. Returns true if deleted.
     */
    private boolean deleteUserCodeString(String s4) {
        if (s4 == null || s4.length() != USER_CODE_LENGTH) return false;
        int[] code = new int[USER_CODE_LENGTH];
        for (int i = 0; i < USER_CODE_LENGTH; i++) code[i] = s4.charAt(i) - '0';

        for (int i = 0; i < MAX_NUM_USER_CODES; i++) {
            if (Arrays.equals(userCodes[i], code)) {
                Arrays.fill(userCodes[i], -1);
                return true;
            }
        }
        return false;
    }

    private String masterAsString() {
        StringBuilder sb = new StringBuilder();
        for (int d : masterCode) sb.append(d);
        return sb.toString();
    }

    private String digitsOnly(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Process inputBuffer looking for complete command patterns:
     *  - <master> * 1 <new4> <new4>  => add user code
     *  - <master> * 2 <old4> <old4>  => delete one user code
     *  - <master> * 6 <master_again> => delete all user codes (confirmation)
     *  - <master> * 3 <new6> <new6>  => change master code
     *
     * When a pattern is matched and processed, the processed region is removed from the front
     * of the inputBuffer (so we don't reprocess it).
     */
    private void processBuffer() {
        String s = inputBuffer.toString();
        String master = masterAsString();

        // ADD USER CODE: master + "*1" + 8 digits (4 + 4)
        Pattern pAdd = Pattern.compile(Pattern.quote(master) + "\\*1(\\d{4})(\\d{4})");
        Matcher mAdd = pAdd.matcher(s);
        if (mAdd.find()) {
            String new4 = mAdd.group(1);
            String repeat = mAdd.group(2);
            if (new4.equals(repeat)) {
                boolean added = addUserCodeString(new4);
                isNewUserCode = added;
            } else {
                isNewUserCode = false;
            }
            // remove processed portion up to match end
            inputBuffer.delete(0, mAdd.end());
            return;
        }

        // DELETE ONE USER CODE: master + "*2" + 8 digits (4 + 4)
        Pattern pDel = Pattern.compile(Pattern.quote(master) + "\\*2(\\d{4})(\\d{4})");
        Matcher mDel = pDel.matcher(s);
        if (mDel.find()) {
            String toDel = mDel.group(1);
            String repeat = mDel.group(2);
            if (toDel.equals(repeat)) {
                boolean deleted = deleteUserCodeString(toDel);
                isDeletedUserCode = deleted;
            } else {
                isDeletedUserCode = false;
            }
            inputBuffer.delete(0, mDel.end());
            return;
        }

        // DELETE ALL: master + "*6" + master  (confirmation)
        Pattern pDelAll = Pattern.compile(Pattern.quote(master) + "\\*6" + Pattern.quote(master));
        Matcher mDelAll = pDelAll.matcher(s);
        if (mDelAll.find()) {
            // clear all user codes
            for (int i = 0; i < MAX_NUM_USER_CODES; i++) Arrays.fill(userCodes[i], -1);
            areAllUserCodesDeleted = true;
            inputBuffer.delete(0, mDelAll.end());
            return;
        }

        // CHANGE MASTER: master + "*3" + 6digits + 6digits (repeat)
        Pattern pChange = Pattern.compile(Pattern.quote(master) + "\\*3(\\d{6})(\\d{6})");
        Matcher mChange = pChange.matcher(s);
        if (mChange.find()) {
            String new1 = mChange.group(1);
            String new2 = mChange.group(2);
            if (new1.equals(new2)) {
                // set masterCode to new digits
                for (int i = 0; i < MASTER_CODE_LENGTH; i++) {
                    masterCode[i] = new1.charAt(i) - '0';
                }
                isChangedMasterCode = true;
            } else {
                isChangedMasterCode = false;
            }
            inputBuffer.delete(0, mChange.end());
            return;
        }

        // No full pattern matched yet — keep buffer until more input arrives
    }

    /**
     * Accept a single keypress.
     * returns true if the keypress is syntactically acceptable (digit or '*'), false otherwise.
     */
    public boolean pushButton(char button) {
        if (!(Character.isDigit(button) || button == '*')) {
            return false;
        }
        inputBuffer.append(button);
        // process any completed commands in the buffer
        processBuffer();
        return true;
    }

    public boolean addedUserCode() {
        boolean val = isNewUserCode;
        isNewUserCode = false; // consume flag
        return val;
    }

    public boolean deletedUserCode() {
        boolean val = isDeletedUserCode;
        isDeletedUserCode = false;
        return val;
    }

    public boolean deletedAllUserCodes() {
        boolean val = areAllUserCodesDeleted;
        areAllUserCodesDeleted = false;
        return val;
    }

    public boolean changedMasterCode() {
        boolean val = isChangedMasterCode;
        isChangedMasterCode = false;
        return val;
    }

    public int[] getMasterCode() {
        return Arrays.copyOf(masterCode, masterCode.length);
    }

    /**
     * Unlock: check the last USER_CODE_LENGTH digits currently in the buffer
     * (or the entire buffer's trailing digits) against stored user codes.
     */
    @Override
    public boolean unlock() {
        // get digits from buffer
        String digits = digitsOnly(inputBuffer.toString());
        if (digits.length() < USER_CODE_LENGTH) return false;
        String last4 = digits.substring(digits.length() - USER_CODE_LENGTH);

        // compare to user codes
        for (int i = 0; i < MAX_NUM_USER_CODES; i++) {
            int[] uc = userCodes[i];
            if (uc[0] == -1) continue;
            StringBuilder sb = new StringBuilder();
            for (int d : uc) sb.append(d);
            if (sb.toString().equals(last4)) {
                locked = false;
                inputBuffer.setLength(0);
                return true;
            }
        }

        // also allow unlocking with master code if the trailing digits equal master (optional)
        if (digits.length() >= MASTER_CODE_LENGTH) {
            String tailMaster = digits.substring(digits.length() - MASTER_CODE_LENGTH);
            if (tailMaster.equals(masterAsString())) {
                locked = false;
                inputBuffer.setLength(0);
                return true;
            }
        }

        return false;
    }

    /**
     * Allow keypad lock (independent of physical key). Returns true on success.
     */
    @Override
    public boolean lock() {
        locked = true;
        inputBuffer.setLength(0);
        return true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }
}
