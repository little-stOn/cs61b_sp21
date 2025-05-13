package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> , Iterable<T>{
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = array.length/2 - 1;
        nextLast = array.length/2;
    }

    @Override
    public void addFirst(T x) {
        if(size == array.length) {
            resizeForAdd(array.length * 2);
        }
        array[nextFirst] = x;
        if(nextFirst == 0){
            nextFirst = array.length-1;
        }
        else{
            nextFirst--;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size == array.length) {
            resizeForAdd(array.length * 2);
        }
        array[nextLast] = x;
        if(nextLast == array.length-1){
            nextLast = 0;
        }
        else {
            nextLast++;
        }
        size++;
    }

    @Override
    public int size(){
        return size;
    }


    public void resizeForAdd(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int count = 0;
        for(int i = nextFirst +1 ; count < size ; count++,i++  ){
            if(i==array.length){
                i=0;
            }
            newArray[newArray.length/2 - array.length/2 + count] = array[i];
        }
        nextFirst = newArray.length/2 - array.length/2 - 1;
        nextLast = newArray.length/2 + array.length/2;
        array = newArray;
    }

    public void resizeForRemove(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int count = 0;
        for(int i = nextFirst +1 ; count < size ; count++,i++  ){
            newArray[newArray.length/2 - size/2 + count] = array[i];
        }
        nextFirst = newArray.length/2 - size/2 -1;
        nextLast = nextFirst + size + 1;
        array = newArray;
    }

    @Override
    public void printDeque(){
        int count = 0;
        for(int i = nextFirst+1 ; count < size ; count++,i++  ){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        if(size <= array.length/4 && array.length >= 16){
            resizeForRemove(array.length/2);
        }
        T rec;
        if(nextFirst == array.length-1){
            rec = array[0];
            array[0] = null;
            nextFirst = 0;
        }
        else{
            rec = array[nextFirst+1];
            array[nextFirst+1] = null;
            nextFirst++;
        }
        size--;
        return rec;
    }

    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        if(size <= array.length/4 && array.length >= 16){
            resizeForRemove(array.length/2);
        }
        T rec;
        if(nextLast == 0){
            rec = array[array.length-1];
            array[array.length-1] = null;
            nextLast = array.length - 1;
        }
        else{
            rec = array[nextLast-1];
            array[nextLast-1] = null;
            nextLast--;
        }
        size--;
        return rec;
    }

    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        int realIndex = (nextFirst + 1 + index) % array.length;
        return array[realIndex];
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        ArrayDequeIterator(){
            pos = nextFirst + 1;
        }

        @Override
        public boolean hasNext() {
            return !(pos == nextLast - 1);
        }

        public T next(){
            T t = array[pos];
            pos++;
            return t;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque) || ((Deque<?>) o).size() != this.size()) {
            return false;
        }
        if (o == this) {
            return true;
        }
        for (int i = 0; i < this.size(); i++) {
            Object item = ((Deque<?>) o).get(i);
            if (!(this.get(i).equals(item))) {
                return false;
            }
        }
        return true;
    }

}
