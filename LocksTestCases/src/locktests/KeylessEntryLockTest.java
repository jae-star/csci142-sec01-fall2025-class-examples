package locktests;

import static org.junit.jupiter.api.Assertions.*;

import lock.KeylessEntryLock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * KeylessEntryLock test cases using those from the class and some of my
 * modifications to get them cleaned up and complete.
 * 
 * <p>Date: 09/21/2025</p>
 * 
 * @author CSCI142 class
 */

public class KeylessEntryLockTest 
{
	private KeylessEntryLock keylessEntryLock;
	private int[] masterCombo;
	private String masterComboString;

	@BeforeEach
	public void setup()
	{
		keylessEntryLock = new KeylessEntryLock(5);
		masterCombo = keylessEntryLock.getMasterCode();
		masterComboString = this.intArrayToString(masterCombo);
	}

	@Test
	public void testOneUserCodeCorrect() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*113211321");
		
		assertTrue(keylessEntryLock.addedUserCode(), "Add User Code should work");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1321");
		
		unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertTrue(unlocks, "Lock should be unlocked");
	}
	
	@Test
	public void testTwoUserCodesCorrect() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Open lock with both
		 */
		valid = valid && this.pushSequence("6654");		
		unlocks = keylessEntryLock.unlock();
		assertTrue(unlocks, "First unlock should work");
		
		keylessEntryLock.lock();
		
		valid = valid && this.pushSequence("0000");		
		unlocks = keylessEntryLock.unlock();
		assertTrue(unlocks, "Second unlock should work");
		
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	@Test
	public void testWrongMasterCode() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		String badMasterCode = "888888";
		int diff = masterComboString.compareTo(badMasterCode);
		
		if (diff == 0)
		{
			badMasterCode = "777777";
		}
		valid = this.pushSequence(badMasterCode);
		valid = valid && this.pushSequence("*113211321");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1321");
		
		unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertFalse(unlocks, "User code should fail since bad master code used");
	}

	
	@Test
	public void testWrongUserCodeFirstKey() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*113211321");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("2321");
		
		unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertFalse(unlocks, "Incorrect user code, should fail");
	}

	@Test
	public void testWrongUserCodeSecondKey() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*113211321");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1421");
		
		unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertFalse(unlocks, "Incorrect user code, should fail");
	}
	
	@Test
	public void testWrongUserCodeThirdKey() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*113211321");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1351");
		
		unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertFalse(unlocks, "Incorrect user code, should fail");
	}

	@Test
	public void testWrongUserCodeFourthKey() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*113211321");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1325");
		
		unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertFalse(unlocks, "Incorrect user code, should fail");
	}


	@Test
	public void testChangeMasterCode() 
	{
		boolean valid;
		
		/*
		 * Enter 6DPC then change 6DPC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*3009933009933");
		assertTrue(valid, "All sequences fine, should be true");
		assertTrue(keylessEntryLock.changedMasterCode(), "Master code should have changed");
	}

	/**
	 * More detailed approach to check if master code changed. First change it,
	 * then see if new user code can be added and used.  NOTE: while we can
	 * get the default master code from the KeylessEntryLock class, we cannot 
	 * get any new master code once it has been changed. If we forget the new 
	 * master code, we are out of luck.  For a real lock to reset it, we must
	 * take the lock off the door and disconnect the battery.  When battery is
	 * plugged back in, the default code is set and no user codes are set.
	 */
	@Test
	public void testChangeMasterCodeAlternate() 
	{
		boolean valid;
		String newMasterCode = "123987";
		
		/*
		 * Enter 6DPC then change 6DPC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*3");
		valid = valid && this.pushSequence(newMasterCode);
		valid = valid && this.pushSequence(newMasterCode);
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(newMasterCode);
		valid = valid && this.pushSequence("*113211321");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1321");
		
		boolean unlocks = keylessEntryLock.unlock();
		assertTrue(valid, "All sequences fine, should be true");
		assertTrue(unlocks, "Should unlock new master code changed");
	}
	
	@Test
	public void testDeleteOneUserCode() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Now delete first user codes
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*2");
		valid = valid && this.pushSequence("66546654");
		
		/*
		 * Open lock with both combos, with first failing and second passing
		 */
		valid = valid && this.pushSequence("6654");		
		unlocks = keylessEntryLock.unlock();
		assertFalse(unlocks, "First unlock should not work");
		
		keylessEntryLock.lock();
		
		valid = valid && this.pushSequence("0000");		
		unlocks = keylessEntryLock.unlock();
		assertTrue(unlocks, "Second unlock should work");
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	@Test
	public void testDeleteUserCodesIncorrectly() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Now delete first user code incorrectly
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*2");
		valid = valid && this.pushSequence("66536654");
		
		/*
		 * Now delete second user code incorrectly
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*2");
		valid = valid && this.pushSequence("00001000");
		
		/*
		 * Open lock with both combos, both passing
		 */
		valid = valid && this.pushSequence("6654");		
		unlocks = keylessEntryLock.unlock();
		assertTrue(unlocks, "First unlock should still work");
		
		keylessEntryLock.lock();
		
		valid = valid && this.pushSequence("0000");		
		unlocks = keylessEntryLock.unlock();
		assertTrue(unlocks, "Second unlock should work");
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	@Test
	public void testDeleteOneUserCodeAlternate() 
	{
		boolean valid;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Now delete first user code
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*2");
		valid = valid && this.pushSequence("66546654");
		
		boolean deleted = keylessEntryLock.deletedUserCode();
		
		assertTrue(deleted, "Should have deleted a user code");
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	@Test
	public void testDeleteTwoUserCodes() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Now delete first user codes
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*2");
		valid = valid && this.pushSequence("66546654");
		
		/*
		 * Now delete second user codes
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*2");
		valid = valid && this.pushSequence("00000000");
		
		/*
		 * Open lock with both combos, with both failing
		 */
		valid = valid && this.pushSequence("6654");		
		unlocks = keylessEntryLock.unlock();
		assertFalse(unlocks, "First unlock should not work");
		
		keylessEntryLock.lock();
		
		valid = valid && this.pushSequence("0000");		
		unlocks = keylessEntryLock.unlock();
		assertFalse(unlocks, "Second unlock should not work");
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	@Test
	public void testDeleteAllUserCodes() 
	{
		boolean valid;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Now delete all user codes
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*6");
		valid = valid && this.pushSequence(masterComboString);
		
		assertTrue(valid, "All sequences fine, should be true");
		
		/*
		 * Check if all user codes deleted
		 */
		boolean deleted = keylessEntryLock.deletedAllUserCodes();
		
		assertTrue(deleted, "All user codes should be deleted");
	}
		
	@Test
	public void testDeleteAllUserCodesAlternate() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*166546654");
		assertTrue(keylessEntryLock.addedUserCode(), "Add First User Code should work");
		
		/*
		 * Add second 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*100000000");
		assertTrue(keylessEntryLock.addedUserCode(), "Add Second User Code should work");
		
		/*
		 * Now delete all user codes
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*6");
		valid = valid && this.pushSequence(masterComboString);
		
		/*
		 * Open lock with both
		 */
		valid = valid && this.pushSequence("6654");		
		unlocks = keylessEntryLock.unlock();
		assertFalse(unlocks, "First unlock should not work");
		
		keylessEntryLock.lock();
		
		valid = valid && this.pushSequence("0000");		
		unlocks = keylessEntryLock.unlock();
		assertFalse(unlocks, "Second unlock should not work");
		
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	@Test
	public void testIfNoUserCode() 
	{
		boolean valid = keylessEntryLock.addedUserCode();
		boolean unlocks = keylessEntryLock.unlock();
		assertFalse(valid, "No user code added");
		assertFalse(unlocks, "Should no unlock with no user code");
	}

	@Test
	public void testIfLockWorks() 
	{
		boolean valid;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence(masterComboString);
		valid = valid && this.pushSequence("*113211321");
		assertTrue(keylessEntryLock.addedUserCode(), "Add User Code should work");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1321");
		
		keylessEntryLock.unlock();
		keylessEntryLock.lock();
		boolean locks = keylessEntryLock.isLocked();
		assertTrue(locks, "Lock should be locked");
		assertTrue(valid, "All sequences fine, should be true");
	}

	@Test
	public void testIfNoMasterCodeEntered() 
	{
		boolean valid, unlocks;
		
		/*
		 * Enter 6DPC then create new 4DUC
		 */
		valid = this.pushSequence("*113211321");
		assertFalse(keylessEntryLock.addedUserCode(), "Add User Code should not work");
		
		/*
		 * Open lock
		 */
		valid = valid && this.pushSequence("1321");
		
		unlocks = keylessEntryLock.unlock();
		assertFalse(unlocks, "Lock should not be unlocked");
		assertTrue(valid, "All sequences fine, should be true");
	}
	
	/*
	 * Helper function to allow sequence of pushKey() calls without 
	 * cluttering main code.
	 */
	private boolean pushSequence(String sequence)
	{
		boolean valid = true;
		char button;
		
		for(int i=0; i<sequence.length(); i++)
		{
			button = sequence.charAt(i);
			valid = valid && keylessEntryLock.pushButton(button);
		}
		return valid;
	}
	
	/*
	 * Create a string of characters in sequence from an integer array.
	 */
	private String intArrayToString(int[] values)
	{
		String string = "";
		
		for(int value : values)
		{
			string += Integer.toString(value);
		}
		return string;
	}
}