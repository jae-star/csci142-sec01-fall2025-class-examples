package locktesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lock.KeyLock;

class TestKeyLockJUnit {
	
	private KeyLock keyLock;
	
	@BeforeEach
	void setUp() throws Exception {
		keyLock = new KeyLock(4);
	}

	@Test
	void testBadKeyInsertion() {
		assertEquals(false, keyLock.insertKey(5), "Wrong key, should fail!");
	}
	
	@Test
	void testGoodKeyInsertion() {
		assertTrue(keyLock.insertKey(4));
	}
	
}
