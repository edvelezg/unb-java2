package data.structures;
import java.util.Arrays;

public class ArrayList<T> {
	private T[] elementData;
	private int size;

	public static final int DEFAULT_CAPACITY = 100;

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	// pre: capacity must be greater than or equal to 0. (throws
	// IllegalArgumentException)
	// post: creates an Array list with the given initial capacity
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity: " + capacity);
		}

		elementData = (T[]) new Object[capacity];
		size = 0;
	}
	
	public void ensureCapacity(int capacity) {
		if (capacity > elementData.length) {
			int newCapacity = capacity * 2 + 1;
			if (newCapacity < capacity) {
				newCapacity = capacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	private void checkCapacity(int capacity) {
		if (capacity > elementData.length) {
			throw new IllegalStateException("exceed list capacity");
		}

	}

	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("index: " + idx);

		}

	}

	public void add(T elem) {
		ensureCapacity(size+1);
		elementData[size] = elem;
		size++;
	}

	public void addElem(int idx, T val) {

		size++;
		for (int i = size; i > idx; --i) {
			elementData[i] = elementData[i - 1];
		}
		elementData[idx] = val;
	}

	public T removeLast() {
		T obj = elementData[size];
		size--;
		elementData[size] = null;
		return obj;
	}

	public int size() {
		return size;
	}

	public int indexOf(T value) {
		for (int i = 0; i < size; i++) {
			if (value.equals(elementData[i])) {
				return i;
			}
		}
		return -1; // index of -1 means element doesn't exist
	}
	
	// pre: 0 <= idx < size()
	// post: returns the integer at the given index in the list
	public T get(int idx) {
		checkIndex(idx);
		return elementData[idx];
	}

	
	public void remove(int idx) {
		checkIndex(idx);
		for (int i = idx; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size-1] = null;
		size--; // decrease size of the array
	}

	@Override
	public String toString() {
		String str;
		if (size == 0) {
			str = "[]";
		} else {
			str = "[" + elementData[0];
			for (int i = 1; i < size; i++) {
				str += ", " + elementData[i];
			}
			str += "]";
		}
		return str;
	}

	/* Convenience methods */
	public boolean contains(T val) {
		return indexOf(val) >= 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void set(int idx, T val) {
		checkIndex(idx);
		elementData[idx] = val;

	}
	
	public void addAll(ArrayList<T> other)
	{
		checkCapacity(size + other.size);
		for (int i = 0; i < other.size; i++) {
			add(other.elementData[i]);
		}
	}

	public void clear() {
		size = 0;
	}


}
