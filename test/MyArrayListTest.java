import Collections.MyArrayList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyArrayListTest {

    @Test
    void testAddAndGet() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testRemove() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("three", list.get(1));
    }

    @Test
    void testAddAll() {
        MyArrayList<Integer> list = new MyArrayList<>();
        List<Integer> additionalList = Arrays.asList(4, 5, 6);
        list.addAll(additionalList);

        assertEquals(3, list.size());
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(6, list.get(2));
    }

    @Test
    void testBubbleSort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);

        MyArrayList.bubbleSort(list);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testConstructorWithCollection() {
        List<String> initialList = Arrays.asList("a", "b", "c");
        MyArrayList<String> list = new MyArrayList<>(initialList);

        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }
}