package locktests;

import static org.junit.jupiter.api.Assertions.*;
import lock.KeyLock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * KeyLock test cases using those from the class and some of my
 * modifications to get them cleaned up and complete.
 * 
 * <p>Date: 09/21/2025</p>
 * 
 * @author CSCI142 class
 */
public class KeyLockTest 
{
	private KeyLock keyLock;
	
	/**
	 * Sets up a new KeyLock before each test.
	 */
	@BeforeEach
	public void setup()
	{
		keyLock = new KeyLock(6);
	}

    /**
     * Tests if the Wrong key has been inserted by attempting to 
     * call the turn() method with the wrong key value inserted.
     */
    @Test
    public void testIfWrongKey()
    {
        keyLock.insertKey(7);
        boolean works = keyLock.turn();
        
        assertFalse(works, "Opened Lock with wrong key");
    }

    /**
     * Tests if the Correct key has been inserted by attempting to 
     * call the turn() method with the correct key value inserted.
     */
	@Test
	public void testIfCorrectKey() 
	{
		keyLock.insertKey(6);
		boolean turns = keyLock.turn();
		
		assertTrue(turns, "Correct key, so should turn");
	}
	    
    /**
     * Tests if the turn() method can be called when no key value has been
     * inserted.
     */
    @Test
    public void testIfTurnsWithNoKey()
    {
        boolean turns = keyLock.turn();
        assertFalse(turns, "It is Not Supposed to Turn Without a Key");
    }

    /**
     * Tests if the unlock() method works when all requirements are fulfilled
     * (insert correct key, and turn key)
     */
    @Test    
    public void testIfUnlocks(){
	    keyLock.insertKey(6);
	    keyLock.turn();
	    keyLock.unlock();
	    assertFalse(keyLock.isLocked(), "It is Supposed to unlock successfully");
    }

    /**
     * Tests if Lock() method works when the myKeyLock is unlocked 
     */
    @Test    
    public void testIfLocks(){
    keyLock.insertKey(6);
    assertTrue(keyLock.turn(), "It is Supposed to turn with the correct key");
    assertTrue(keyLock.unlock(), "It is Supposed to unlock");
    assertFalse(keyLock.isLocked(), "It is Supposed to be unlocked");
    assertTrue(keyLock.lock(), "It is Supposed to lock");
    assertTrue(keyLock.isLocked(), "It is Supposed to turn with the correct key");
    }

    /**
     * Tests if the unlock() method works when no key has been inserted or turned
     */
	@Test
	public void testUnlockWithNoKey() 
	{
		boolean unlocks = keyLock.unlock();
		
		assertFalse(unlocks, "No inserted key: should not unlock");
	}
	
	/**
	 * Once key inserted, should be able to relock then unlock again since
	 * key is in. (NOTE: In our original design, we should have had a remove() 
	 * method to take the key out.)
	 */
	@Test
	public void testUnlockWithCorrectKey() 
	{
        keyLock.insertKey(6);
        keyLock.turn();
		keyLock.lock();
		keyLock.turn();
		boolean unlocks = keyLock.unlock();
		
		assertTrue(unlocks, "Correct key: should unlock");
	}
	
	/**
	 * Should be able to lock after unlocking.
	 */
	@Test
	public void testLockWithCorrectKey() 
	{
		keyLock.insertKey(6);
		keyLock.turn();
		keyLock.unlock();
		keyLock.turn();
		boolean locks = keyLock.lock();
		
		assertTrue(locks, "Correct key: should lock");
	}

}