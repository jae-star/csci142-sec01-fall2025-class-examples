package locktests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import lock.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ComboLock test cases using those from the class and some of my
 * modifications to get them cleaned up and complete.
 *
 * <p>Date: 09/21/2025</p>
 *
 * @author CSCI142 class
 */
public class ComboLockTest 
{
	private ComboLock comboLock;
	private int[] combo;
	
	/**
	 * Sets up a new ComboLock and retrieves its combination before each test.
	 */
	@BeforeEach
	public void setup()
	{
    	comboLock = new ComboLock();
    	combo = comboLock.getCombination();
        comboLock.reset();
	}
	
    /**
     * Tests if the combination is compliant with the formula stated in the
     * Lab guidelines.
     */
    @Test
    public void testIfComboCompliant() 
    {
        boolean compliant = false;
        if ((combo[0] % 4) == (combo[2] % 4) && (combo[1] % 4) == ((combo[0] % 4) + 2) % 4)
        {
            compliant = true;
        }
        
        assertTrue(compliant, "The combination is not compliant with the guidelines.");
    }

    /**
     * Tests if the lock() method works after the ComboLock is already unlocked.
     */
	@Test
    public void testIfLocks() 
    {
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft(combo[1]);
        comboLock.turnRight(combo[2]);
        comboLock.unlock();
        
        boolean locks = comboLock.lock();
        assertTrue(locks, "Should Lock, but didn't.");
    }

	/**
	 * Tests if the unlock() method works after the ComboLock is already unlocked.
	 */
    @Test
	public void testIfUnlocks () 
	{
		comboLock.turnRight(combo[0]);
		comboLock.turnLeft(combo[1]);
		comboLock.turnRight(combo[2]);
		
		boolean unlocks = comboLock.unlock();
		assertTrue(unlocks, "Correct combo so should work.");
	}
    
    /**
     * Tests if the unlock() method works if no turns or input has been made.
     */
    @Test
    public void testIfUnlocksWithoutEntries() 
    {
        boolean unlocks = comboLock.unlock();
        assertFalse(unlocks, "Should Not Unlock, No numbers were input.");
    }

    /**
     * Tests if the unlock() method will work with only one turn/entry being made.
     */
    @Test
    public void testIfOpensWithOneEntry() 
    {
        comboLock.turnRight(combo[0]);
        boolean unlocks = comboLock.unlock();
        assertFalse(unlocks, "Should Not Unlock, Only One number entered.");
    }
	  
    /**
     * Tests if the unlock() method will work with only two turn/entry being made.
     */
    @Test
    public void testIfOpensWithTwoEntries() 
    {
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft(combo[1]);
        boolean unlocks = comboLock.unlock();
        assertFalse(unlocks, "Should Not Unlock, Only Two numbers entered.");
    }

    /**
     * Tests if the unlock() method will work with the first turn/entry being incorrect.
     */
    @Test
    public void testIfOpensWithOneWrongEntry1st() 
    {
        comboLock.turnRight((combo[0]+1)%ComboLock.MAX_TICKS);
        comboLock.turnLeft(combo[1]);
        comboLock.turnRight(combo[2]);
        boolean unlocks = comboLock.unlock();
        assertFalse(unlocks, "Should Not Unlock, The first number entered is incorrect.");
    }

    /**
     * Tests if the unlock() method will work with the second turn/entry being incorrect.
     */
    @Test
    public void testIfOpensWithOneWrongEntry2nd() 
    {
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft((combo[0]+1)%ComboLock.MAX_TICKS);
        comboLock.turnRight(combo[2]);
        boolean unlocks = comboLock.unlock();
        assertFalse(unlocks, "Should Not Unlock, The second number entered is incorrect.");
    }

    /**
     * Tests if the unlock() method will work with the third turn/entry being incorrect.
     */
    @Test
    public void testIfOpensWithOneWrongEntry3rd() 
    {
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft(combo[1]);
        comboLock.turnRight((combo[0]+1)%ComboLock.MAX_TICKS);
        boolean unlocks = comboLock.unlock();
        assertFalse(unlocks, "Should Not Unlock, The third number entered is incorrect.");
    }

    /**
     * Tests if the unlock() method will work after multiple, initial, right turns having being made.
     */
    @Test
    public void testIfOpensAfterMultipleInitialRightTurns() 
    {
    	Random r = new Random();

        comboLock.turnRight(r.nextInt(ComboLock.MAX_TICKS));
        comboLock.turnRight(r.nextInt(ComboLock.MAX_TICKS));
        comboLock.turnRight(r.nextInt(ComboLock.MAX_TICKS));
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft(combo[1]);
        comboLock.turnRight(combo[2]);
        boolean unlocks = comboLock.unlock();
        assertTrue(unlocks, "Should Unlock, turning the dial to the Right multiple times, initially, should not affect the lock.");
    }

    /**
     * Once lock is unlocked, trying to unlock again should fail since locks reset.
     */
	@Test
	public void testUnlockOnUnlocked() 
	{
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft(combo[1]);
        comboLock.turnRight(combo[2]);
        boolean unlocks = comboLock.unlock();
	        
	    unlocks = comboLock.unlock();
	    assertFalse(unlocks, "Lock is already unlocked: should not unlock.");
	}
	
	/**
	 * Test when combo is correct, but then relock.
	 */
	@Test
	public void testDoubleUnlock() 
	{
        comboLock.turnRight(combo[0]);
        comboLock.turnLeft(combo[1]);
        comboLock.turnRight(combo[2]);
        boolean unlocks = comboLock.unlock();
		
        comboLock.lock();
		
        unlocks = comboLock.unlock();
		
		assertFalse(unlocks, "Unlocking, reclocking, then unlocking again without re-entering combo.");
		
	}
}