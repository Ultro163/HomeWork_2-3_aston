package Collections;

import java.util.Arrays;
import java.util.Collection;

/**
 * Кастомная реализация динамического списка, который автоматически расширяется при добавлении элементов.
 *
 * @param <T> тип элементов, хранящихся в этом списке
 */
public class MyArrayList<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final float EXPANSION_COEFFICIENT = 1.5f;
    private Object[] data;
    private int size;

    /**
     * Конструктор по умолчанию. Инициализирует список с начальной вместимостью по умолчанию.
     */
    public MyArrayList() {
        data = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Конструктор, создающий список и заполняющий его элементами из переданной коллекции.
     *
     * @param collection коллекция элементов, которые будут добавлены в список
     */
    public MyArrayList(Collection<? extends T> collection) {
        data = collection.toArray();
        size = data.length;
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка. Если массив заполнен, увеличивает его размер.
     *
     * @param item элемент для добавления
     */
    public void add(T item) {
        if (size == data.length) {
            data = increasingArray(size, data);
        }
        data[size++] = item;
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkOutOfBounds(index);
        return (T) data[index];
    }

    /**
     * Удаляет элемент по указанному индексу, сдвигая оставшиеся элементы влево.
     *
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void remove(int index) {
        checkOutOfBounds(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
    }

    /**
     * Добавляет все элементы из переданной коллекции в список.
     *
     * @param collection коллекция элементов для добавления
     */
    public void addAll(Collection<? extends T> collection) {
        if (!collection.isEmpty()) {
            for (T element : collection) {
                this.add(element);
            }
        }
    }

    /**
     * Сортирует список методом пузырьковой сортировки. Предполагается, что элементы реализуют интерфейс Comparable.
     *
     * @param list список, который будет отсортирован
     * @param <T>  тип элементов, реализующих интерфейс Comparable
     */
    public static <T extends Comparable<? super T>> void bubbleSort(MyArrayList<T> list) {
        boolean swapped;
        for (int i = 0; i < list.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.data[j] = list.get(j + 1);
                    list.data[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Увеличивает размер массива на основании коэффициента расширения.
     *
     * @param size текущий размер массива
     * @param data массив, который нужно увеличить
     * @return новый увеличенный массив
     */
    private Object[] increasingArray(int size, Object[] data) {
        return Arrays.copyOf(data, (int) Math.ceil(size * EXPANSION_COEFFICIENT));
    }

    /**
     * Проверяет, что указанный индекс находится в пределах допустимого диапазона.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    private void checkOutOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
