package storage;

import java.util.Vector;

/**
 * A storage implementation that uses a Vector to store elements.
 * 
 * @param <T> The type of elements to be stored, must extend Number.
 * 
 * If I want String or other types as well as Number types, take out
 * the "extends Number" below
 */
public class VectorStorage<T extends Number> implements Storage<T> {

	private Vector<T> data;
	
	public VectorStorage() {
		data = new Vector<T>();
	}
	
	@Override
	public boolean add(T o) {
		return data.add(o);
	}

	@Override
	public T remove(int index) throws ArrayIndexOutOfBoundsException {
		return data.remove(index);
	}

	@Override
	public int count() {
		return data.size();
	}

}
