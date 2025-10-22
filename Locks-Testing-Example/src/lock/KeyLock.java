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
		return false;
	}
	
	public boolean turn() {
		if (isInserted) {
			return true;
		}
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
