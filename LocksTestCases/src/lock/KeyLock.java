package lock;

public class KeyLock implements Lock {
	private int key;
	private boolean isLocked;
	private boolean isInserted;
	
	public KeyLock(int key) {
		this.key = key;         // store the correct key
	    this.isLocked = true;   // starts locked by default
	    this.isInserted = false; // no key yet
	}
	
	public boolean insertKey(int key) {
	    if (key != this.key) {
	        return false; // wrong key
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
	        isLocked = !isLocked; // flip between locked and unlocked
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
