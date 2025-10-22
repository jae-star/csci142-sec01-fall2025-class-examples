package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import storage.VectorStorage;

class VectorTesting {

	private VectorStorage<Float> data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new VectorStorage<Float>();
	}

	@Test
	void testAddElement() {
		assertTrue(data.add(5.0f), "Should be able to add integer 5");
		assertEquals(1, data.count(), "Should be size of 1, only one element added");
	}
	
	@Test
	void testRemoveFromEmpty() {
		try {
			data.remove(0);
			fail("Should not be able to remove from empty VectorStorage");
		} catch (ArrayIndexOutOfBoundsException e) {
			// This is correct, this is where the exception goes.
			// Therefore, print nothing, this is good!
		}
	}

}
