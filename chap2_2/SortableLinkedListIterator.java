package chap2_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortableLinkedListIterator<Item extends Comparable<Item>> implements Iterator<Item> {
    private SortableLinkedList<Item>.Node currentNode;

    public SortableLinkedListIterator(SortableLinkedList<Item> linkedList) {
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