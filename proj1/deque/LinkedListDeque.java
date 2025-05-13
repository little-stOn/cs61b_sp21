package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    public class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n , Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    // construct a DLList with Node
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null,null,null);
        sentinel.next = new Node(x,sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    //write an addFirst
    public void addFirst(T x) {
        sentinel.next = new Node(x,sentinel.next,sentinel);
        sentinel.prev.prev = sentinel.next;
        size++;
    }

    public void addLast(T x) {
        sentinel.prev = new Node(x,sentinel,sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node current = sentinel.next;
        for(int i = 0; i < size; i++){
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return x;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T x = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return x;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        Node current = sentinel.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index,Node current){
        if(index >= size){
            return null;
        }
        if(index == 0){
            return current.item;
        }
        return getRecursiveHelper(index - 1, current.next);
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
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

    private class LinkedListIterator implements Iterator<T>{
        private Node node;
        LinkedListIterator() {
            node = sentinel.next;
        }

        public boolean hasNext(){
            return node != sentinel;   // the loop is over
        }

        public T next(){
            T ret = node.item;
            node = node.next;
            return ret;
        }
    }
}
