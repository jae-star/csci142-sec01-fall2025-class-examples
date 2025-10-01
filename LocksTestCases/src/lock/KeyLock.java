package lock;

public class KeyLock implements Lock {
	private int key;
	private boolean isLocked;
	private boolean isInserted;
	
	public KeyLock(int key) {
		// TODO Auto-generated method stub
	}
	
	public boolean insertKey(int key) {
		return false;	
	}
	
	public boolean removeKey() {
		return false;
	}
	
	public boolean turn() {
		return false;
	}

	@Override
	public boolean lock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLocked() {
		// TODO Auto-generated method stub
		return false;
	}

}
