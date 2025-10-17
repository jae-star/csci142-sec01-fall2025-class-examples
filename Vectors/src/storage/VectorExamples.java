package storage;

import java.util.Vector;

public class VectorExamples {

	public static void main(String[] args) {
		
		/*
		 * Array: block of fixed size, contiguous memory
		 * holding homogeneous data
		 */
//		int[] values = {1, 2, 3, 4, 5};
//		
//		values[5] = 6;
//		
//		for (int i = 0; i < 6; i++) {
//			System.out.println(values[i]);
//		}
		System.out.println("Begin");
		Vector vector = new Vector();
		
		System.out.println("size = " + vector.size() + "  capacity = " + vector.capacity());
		
		for (int i = 0; i < 11; i++) {
			vector.add(i);
			System.out.println("size = " + vector.size() 
					+ "  capacity = " + vector.capacity()
					+ "  conentents: " + vector.getLast());
		}
		System.out.println("Done");
	}

}
