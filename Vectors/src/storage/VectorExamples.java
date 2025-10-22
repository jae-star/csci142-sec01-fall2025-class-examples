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
		Vector vector = new Vector(3000, 100);
		
		System.out.println("size = " + vector.size() + "  capacity = " + vector.capacity());
		
		for (int i = 0; i < 100; i++) {
			/*
			 *  add(i) "autoboxes" the primitive "i" and creates an
			 *  Integer object from it, just like we "explicitly" do
			 *  in the line below with Integer.valueOf()
			 */
			vector.add(i);
			vector.add(Integer.valueOf(i));
			System.out.println("size = " + vector.size() 
					+ "  capacity = " + vector.capacity()
					+ "  conentents: " + vector.getLast());
		}
		System.out.println("Done");
	}

}
