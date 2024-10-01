package Collections;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final float EXPANSION_COEFFICIENT = 1.5f;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public ArrayList(int size) {
        data = new Object[size];
        this.size = size;
    }

    public ArrayList(Collection<T> collection) {
        data = new Object[collection.size()];
        this.size = collection.size();
        for (T element : collection) {
            add(element);
        }
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        if (size == data.length) {
            data = increasingArray(size, data);
        }
        size++;
        data[size] = item;
    }


    public T get(int index) {
        checkOutOfBounds(index);
        return (T) data[index];
    }

    public void remove(int index) {
        checkOutOfBounds(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
    }

    public void addAll(Collection<? extends T> collection) {
        if (!collection.isEmpty()) {
            for (T element : collection) {
                this.add(element);
            }
        }
    }

    private Object[] increasingArray(int size, Object[] data) {
        return Arrays.copyOf(data, (int) Math.ceil(size * EXPANSION_COEFFICIENT));
    }

    private void checkOutOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}