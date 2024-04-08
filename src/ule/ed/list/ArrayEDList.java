package ule.ed.list;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayEDList<T> implements IEDList<T> {
	static final int DEFAULT_CAPACITY = 10;

	private T[] data;
	private int count;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	private class ArrayEDListIterator<T> implements Iterator<T> {
		private int current = 0;

		@Override
		public boolean hasNext() {
			return (current < count);
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current++;
			return (T) data[current - 1];

		}
	}

	/// TODO : AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES

	// FIN ITERADORES

	@SuppressWarnings("unchecked")
	public ArrayEDList() {
		count = 0;
		data = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	@SuppressWarnings("unchecked")
	public ArrayEDList(int capacity) {
		count = 0;
		data = (T[]) (new Object[capacity]);
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return (count == 0);
	}

	@Override
	public void addFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}

		if (size() == data.length - 1) {
			expandCapacity();
		}

		for (int i = size(); i > 0; i--) {
			data[i] = data[i - 1];
		}
		count++;
		data[0] = elem;
	}

	@Override
	public void addLast(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}

		if (size() == data.length - 1) {
			expandCapacity();
		}

		for (int i = size(); i > 0; i--) {
			data[i] = data[i - 1];
		}
		count++;
		data[size()] = elem;
	}

	@Override
	public void addPenult(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}

		if (size() == data.length - 1) {
			expandCapacity();
		}
		
		if (count == 0) {
			data[0] = elem;
			count++;
		} else {
			for (int i = size(); i > 0; i--) {
				data[i] = data[i - 1];
			}
			data[size() - 1] = elem;
			count++;
		}
	}

	@Override
	public void addPos(T elem, int position) {
		if (position < 0 || position > count) {
			throw new IndexOutOfBoundsException();
		}
		if (count == data.length - 1) {
			expandCapacity();
		}
		for (int i = count; i > position; i--) {
			data[i] = data[i - 1];
		}
		data[position] = elem;
		count++;
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("No se uede eliminar el primero porque la lista está vacía");
		}
		T removed = data[0];
		for (int i = 0; i < count - 1; i++) {
			data[i] = data[i + 1];
		}
		count--;
		return removed;
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO
		if (isEmpty()) {
			throw new EmptyCollectionException("No se puede eleminar la última porque la lista está vacía");
		} else {
			return data[size() - 1];
		}
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO
		if (isEmpty()) {
			throw new EmptyCollectionException("No se puede eliminr la penúltima porque la lista está vacía");
		} else {

		}
		return null;
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO
		if (isEmpty()) {
			throw new EmptyCollectionException("No se puede eliminar el elemento dado porque la lista está vacía");
		} else {

		}
		return 0;
	}

	@Override
	public T getElemPos(int position) {
		// TODO
		if (position <= 0 || position >= count) {
			throw new IllegalArgumentException();
		return data[position];
		}
	}

	@Override
	public int getPosLast(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}
		for (int i = count - 1; i >= 0; i--) {
			if (data[i].equals(elem)) {
				return i;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("No se puede eliminar porque la lista está vacía");
		}

		if (elem == null) {
			throw new NullPointerException();
		}

		int removed = 0;
		for (int i = count - 1; i >= 0; i--) {
			if (data[i].equals(elem)) {
				for (int j = i; j < count - 1; j++) {
					data[j] = data[j + 1];
				}
				count--;
				removed++;
			}
		}
		return removed;
	}

	@Override
	public String toString() {
		String result = "(";
		for (int index = 0; index < count; index++) {
			result = result + data[index].toString() + " ";
		}
		return result + ")";
	}

	@Override
	public void clear() {
		// TODO
	}

	@Override
	public int getPosFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < count; i++) {
			if (data[i].equals(elem)) {
				return i;
			}
		}
		throw new NoSuchElementException("Element not found.");
	}

	@Override
	public IEDList<T> listOfRepeatedElems() {
		IEDList<T> repeatedList = new ArrayEDList<T>(count);
		for (int i = 0; i < count; i++) {
			T currentElem = data[i];
			int pos = getPosFirst(currentElem);
			if (pos != i) {
				repeatedList.addLast(currentElem);
			}
		}
		return repeatedList;
	}

	@Override
	public int countElem(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}
		int count = 0;
		for (int i = 0; i < count; i++) {
			if (data[i].equals(elem)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int currentIndex = 0;
			@Override
			public boolean hasNext() {
				return currentIndex < count;
			}
		
			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T item = data[currentIndex];
				currentIndex++;
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public Iterator<T> evenPositionsIterator() {
		return new Iterator<T>() {
			private int currentIndex = 0;
			@Override
			public boolean hasNext() {
				return currentIndex < count && currentIndex % 2 == 0;
			}
	
			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T item = data[currentIndex];
				currentIndex += 2;
				return item;
			}
		};
	}

	@Override
	public Iterator<T> oddPositionsIterator() {
		return new Iterator<T>() {
			private int currentIndex = 0;
			@Override
			public boolean hasNext() {
				return currentIndex < count && currentIndex % 2 == 1;
			}
	
			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T item = data[currentIndex];
				currentIndex += 2;
				return item;
			}
		};
	}

	@Override
	public Iterator<T> OddEvenIterator() {
		return new Iterator<T>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < count;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T item = data[currentIndex];

				if (currentIndex % 2 == 0) {
					currentIndex++;
				}

				if (currentIndex < count) {
					currentIndex++;
				}
				return item;
			}
		};
	}

	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		T[] larger = (T[]) (new Object[data.length * 2]);
		for (int index = 0; index < data.length; index++) {
			larger[index] = data[index];
		}
		data = larger;
	}

	private boolean contains(T target) {
		boolean search = false;
		for (int index = 0; index < count && search == false; index++) {
			if (data[index].equals(target)) {
				search = true;
			}
		}
		return search;
	}

}
