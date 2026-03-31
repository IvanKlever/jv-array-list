package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(T[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        this.elements = new Object[Math.max(DEFAULT_CAPACITY, elements.length)];
        int i = 0;
        for (T elem : elements) {
            this.elements[i] = elem;
            i++;
        }
        this.size = elements.length;
    }

    private void grow() {
        int newCapacity = elements.length + elements.length / 2;
        Object[] newElements = new Object[newCapacity];
        int i = 0;
        for (Object newArray : this.elements) {
            newElements[i] = newArray;
            i++;
        }
        elements = newElements;
    }

    @Override
    public void add(T value) {
        if (this.size >= this.elements.length) {
            grow();
        }
        elements[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
        if (this.size >= this.elements.length) {
            grow();
        }
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
        final T removed = (T) this.elements[index];
        for (int i = index; i < size - 1; i++) {
            this.elements[i] = this.elements[i + 1];

        }
        elements[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i <= size - 1; i++) {
            if (element == null) {
                if (this.elements[i] == null) {
                    index = i;
                    break;
                }
            } else {
                if (element.equals(this.elements[i])) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
