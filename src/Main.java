import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class MyArrayList<E> implements Iterable<E>, List<E>  {

    private int size;

    private final int INIT_SIZE = 10;
    private Object[] elements;

    MyArrayList(){
        elements = new Object[this.INIT_SIZE];
    }

    @Override
    public E get(int index){
        return (E)elements[index];
    }

    @Override
    public E set(int index, E element) {
        elements[index] = element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= elements.length) throw new IndexOutOfBoundsException();
        elements[size++] = element;
    }

    @Override
    public boolean add(E element){
        checkSize();
        add(size, element);
        return true;
    }

    @Override
    public boolean remove(Object element){
        remove(indexOf(element));
        return true;
    }

    @Override
    public E remove(int index){
        E e = (E)elements[index];
        elements[index] = null;
        size--;
        return e;
    }

    private void checkSize(){
        if (size >= elements.length){
            resize();
        }
    }

    @Override
    public int indexOf(Object o) {
        int ind = -1;
        for(Object current: elements){
            ind++;
            if (o.equals(current)) return ind;
        }
        return -1;
    }

    private void resize(){
        System.arraycopy(elements, 0, elements, 0, elements.length*2);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o)!=-1);
    }

    @Override
    public void clear(){
        size = 0;
        elements = new Object[this.INIT_SIZE];
    }




    // NOT IMPLEMENTED
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {

    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public Iterator<E> iterator(){
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    private class MyIterator implements java.util.Iterator<E>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public E next() {
            if (!hasNext()) throw new java.util.NoSuchElementException;
            return elements[current++];
        }
    }

}

