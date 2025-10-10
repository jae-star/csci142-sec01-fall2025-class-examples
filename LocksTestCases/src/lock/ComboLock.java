 package lock;

public class ComboLock implements Lock{
	public final static int COMBO_LENGTH = 3;
	public final static int MAX_TICKS = 39;
	
	private int[] combination;
	private int[] attempt;
	private boolean isLocked;
	private boolean isReset;
	
	public ComboLock() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean turnRight(int ticks) {
	    return false;  
	}

	public boolean turnLeft(int ticks) {
	    return false;
	}
	
	public void reset() {
	    isReset = true;
	}

	public boolean isReset() {
	    return isReset;
	}


	@Override
	public boolean unlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLocked() {
	    return isLocked;
	}
	
	public int[] getCombination() {
	    return combination;
	}

	@Override
	public boolean lock() {
	    isLocked = true;
	    return isLocked; // true after locking
	}

}