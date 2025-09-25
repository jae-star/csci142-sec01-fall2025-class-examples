package lock;

public class TestKeyLock {

	public static void main(String[] args) {
		KeyLock keyLock = new KeyLock(4);
		
		// Test insert key
		boolean success = keyLock.insertKey(5);
		if (success) {
			System.out.println("Wrong key, should fail!");
		}
		
		success = keyLock.insertKey(4);
		if (!success) {
			System.out.println("Correct key, this should work!");
		}
		
		// TEST turn() method
		keyLock = new KeyLock(4);
		
		// first test, key not inserted
		if (keyLock.turn()) {
			System.out.println("Key not inserted, should not turn!");
		}
		
		// second test, wrong key inserted
		keyLock.insertKey(5);
		if (keyLock.turn()) {
			System.out.println("Wrong key, should not turn!");
		}
		
		// third test, correct key so should turn
		keyLock.insertKey(4);
		
		if (!keyLock.turn()) {
			System.out.println("Correct key, should turn!");
		}
		if (!keyLock.unlock()) {
			System.out.println("Correct key, inserted, turned, so this should unlock");
		}
		
		// TEST unlock() method......
		
		// first test, try unlock() before key inserted
		
		// second test, try unlock() wrong key inserted
		
		// third test, try unlock() correct key inserted but not turned
		
		// fourth test, try unlock() correct key inserted and turned
		
		
	}

}