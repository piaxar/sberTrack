import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class MyArrayList<E> extends AbstractList<E> implements List<E>  {

    private Object[] elements;
    private int size = 0;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    private E elements(int index){
        return (E) elements[index];
    }
    @Override
    public E get(int index) {
        return elements(index);
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= elements.length) throw new IndexOutOfBoundsException();
        elements[index] = element;
        return elements(index);
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size+1);
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= elements.length) throw new IndexOutOfBoundsException();
        ensureCapacity(size+1);
        System.arraycopy(elements, index, elements, index + 1, size-index);
        elements[index] = element;
        size++;

    }

    private void ensureCapacity(int minSize){
        if (minSize > elements.length){

            int newSize = elements.length * 2;
            if (newSize < minSize){
                newSize = minSize;
            }
            Object[] newElements = new Object[newSize];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= elements.length) throw new IndexOutOfBoundsException();
        E o = elements(index);
        System.arraycopy(elements, index + 1, elements, index, size-index-1);
        elements[size] = null;
        size--;
        return o;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for(Object e: elements){
            index++;
            if (e.equals(o)){
                return index;
            }
        }
        return index;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= elements.length) throw new IndexOutOfBoundsException();
        Object[] arr = c.toArray();
        ensureCapacity(arr.length + size);
        int endIndex = arr.length + size;
        System.arraycopy(elements, index, elements, endIndex, arr.length);
        System.arraycopy(elements, index, arr, endIndex, arr.length);
        size+= arr.length;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public int size() {
        return size;
    }



}