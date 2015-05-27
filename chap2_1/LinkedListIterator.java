package chap2_1;

import chap4_2.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<Item extends Comparable<Item>> implements Iterator<Item> {
    private chap4_2.LinkedList<Item>.Node currentNode;

    public LinkedListIterator(LinkedList<Item> linkedList) {
        currentNode = linkedList.getFirst();
    }

    public boolean hasNext() {
        return currentNode != null;
    }

    public Item next() {
        if (currentNode == null)
            throw new NoSuchElementException();
        Item returnValue = currentNode.item;
        currentNode = currentNode.next;
        return returnValue;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}