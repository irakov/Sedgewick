package chap1_3;//page 165
//chap1.chap1_1.3.29

import java.util.Iterator;

public class CircularLinkedList<Item extends Comparable<Item>> implements Iterable<Item> {
    private int size;
    private Node last;

    public static void main(String[] args) {
    }

    public void insertFirst(Item item) {
        if (last == null) {
            last = new Node();
            last.item = item;
            last.next = last;
        } else {
            Node oldFirst = last.next;
            Node newFirst = new Node();
            newFirst.item = item;
            last.next = newFirst;
            newFirst.next = oldFirst;
        }
        size++;
    }

    public Item removeFirst() {
        if (last == null)
            return null;
        Item result = last.next.item;
        if (last.next == last)
            last = null;
        else
            last.next = last.next.next;
        size--;
        return result;
    }

    public void insertLast(Item item) {
        if (last == null)
            insertFirst(item);
        else {
            Node newLast = new Node();
            newLast.item = item;
            newLast.next = last.next;
            last.next = newLast;
            last = newLast;
            size++;
        }
    }

    public Node getLast() {
        return last;
    }

    public Node getFirst() {
        if (last == null)
            return null;
        return last.next;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size != 0;
    }

    public Iterator<Item> iterator() {
        return new CircularLinkedListIterator<Item>(this);
    }

    public class Node {
        Item item;
        Node next;
    }
}