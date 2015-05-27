package chap4_1;//algorithm 3.4(439)
//3.3.39,3.3.40,3.3.41(453-455)
//3.3.33(452)

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        chap4_2.RedBlackBST<String, Integer> bst = new chap4_2.RedBlackBST<String, Integer>();
        for (int i = 0; input.hasNext(); i++) {
            String key = input.next();
            bst.put(key, i);
        }
        for (String s : bst.keys()) output.println(s + " " + bst.get(s));
        output.println();
        output.println(bst.isRedBlackBST());
    }

    public void put(Key key, Value value) {
        if (value == null) delete(key);
        else {
            root = put(root, key, value);
            root.color = BLACK;
        }
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, RED, 1);

        int comp = key.compareTo(node.key);
        if (comp < 0) node.left = put(node.left, key, value);
        else if (comp > 0) node.right = put(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.nodesCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp < 0) return get(node.left, key);
        else if (comp > 0) return get(node.right, key);
        else return node.value;
    }

    public void delete(Key key) {
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) node = rotateRight(node);
            if (key.compareTo(node.key) == 0 && node.right == null) return null;
            if (!isRed(node.right) && !isRed(node.right.left)) node = moveRedRight(node);
            if (key.compareTo(node.key) == 0) {
                Node temp = min(node.right);
                node.key = temp.key;
                node.value = temp.value;
                node.right = deleteMin(node.right);
            } else node.right = delete(node.right, key);
        }
        return balance(node);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.nodesCount;
    }

    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        return max(node.right);
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp == 0) return node;
        if (comp < 0) return floor(node.left, key);
        Node temp = floor(node.right, key);
        if (temp != null) return temp;
        return node;
    }

    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node != null) return node.key;
        return null;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp == 0) return node;
        if (comp < 0) {
            Node temp = ceiling(node.left, key);
            if (temp != null) return temp;
            return node;
        }
        return ceiling(node.right, key);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int comp = key.compareTo(node.key);
        if (comp < 0) return rank(node.left, key);
        if (comp > 0) return 1 + size(node.left) + rank(node.right, key);
        return size(node.left);
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) return null;
        Node node = select(root, k);
        return node.key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int temp = size(node.left);
        if (temp < k) return select(node.right, k - temp - 1);
        if (temp > k) return select(node.left, k);
        return node;
    }

    public void deleteMin() {
        if (isEmpty()) return;

        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return null;
        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);
        node.left = deleteMin(node.left);
        return balance(node);
    }

    public void deleteMax() {
        if (isEmpty()) return;

        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node node) {
        if (node == null) return null;
        if (isRed(node.left)) node = rotateRight(node);
        if (node.right == null) return node;
        if (!isRed(node.right) && !isRed(node.right.left))
            node = moveRedRight(node);
        node = deleteMax(node.right);
        return balance(node);
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<Key>();
        keys(root, list, lo, hi);
        return list;
    }

    private void keys(Node node, List<Key> list, Key lo, Key hi) {
        if (node == null) return;
        int compLo = lo.compareTo(node.key);
        if (compLo < 0) keys(node.left, list, lo, hi);
        int compHi = hi.compareTo(node.key);
        if (compLo <= 0 && compHi >= 0) list.add(node.key);
        if (compHi > 0) keys(node.right, list, lo, hi);

    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private Node rotateRight(Node node) {
        if (node == null) return null;
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = temp.right.color;
        temp.right.color = RED;
        temp.nodesCount = node.nodesCount;
        node.nodesCount = size(node.left) + size(node.right) + 1;
        return temp;
    }

    private Node rotateLeft(Node node) {
        if (node == null) return null;
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = temp.left.color;
        temp.left.color = RED;
        temp.nodesCount = node.nodesCount;
        node.nodesCount = size(node.left) + size(node.right) + 1;
        return temp;
    }

    private void flipColors(Node node) {
        if (node == null) return;
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node moveRedLeft(Node node) {
        if (node == null) return null;
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        if (node == null) return null;
        flipColors(node);
        if (isRed(node.left.left)) node = rotateRight(node);
        return node;
    }

    private Node balance(Node node) {
        if (node == null) return null;
        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.nodesCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    private boolean isBinaryTree(Node node) {
        if (node == null) return true;
        if (node.left != null && node.nodesCount <= node.left.nodesCount) return false;
        if (node.right != null && node.nodesCount <= node.right.nodesCount) return false;
        return isBinaryTree(node.left) && isBinaryTree(node.right);
    }

    private boolean isOrdered(Node node, Key min, Key max) {
        if (node == null) return true;
        if (node.key.compareTo(min) < 0 || node.key.compareTo(max) > 0) return false;
        if (node.left != null && node.left.key.compareTo(node.key) >= 0) return false;
        if (node.right != null && node.right.key.compareTo(node.key) <= 0) return false;
        return isOrdered(node.left, min, max) && isOrdered(node.right, min, max);
    }

    private boolean hasNoDuplicates(Node node) {
        if (node == null) return true;
        if (node.left != null && node.left.key == node.key) return false;
        if (node.right != null && node.right.key == node.key) return false;
        return hasNoDuplicates(node.left) && hasNoDuplicates(node.right);
    }

    private boolean isBST() {
        if (!isBinaryTree(root)) return false;
        if (!isOrdered(root, min(), max())) return false;
        if (!hasNoDuplicates(root)) return false;
        return true;
    }

    private boolean is23(Node node) {
        if (node == null) return true;
        if (isRed(node.right)) return false;
        if (isRed(node) && isRed(node.left)) return false;
        return is23(node.left) && is23(node.right);
    }

    private boolean isBalanced() {
        int black = 0;
        Node node = root;
        while (node != null) {
            if (!isRed(node)) black++;
            node = node.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node node, int black) {
        if (node == null) return black == 0;
        if (!isRed(node)) black--;
        return isBalanced(node.left, black) && isBalanced(node.right, black);
    }

    public boolean isRedBlackBST() {
        if (!isBST()) return false;
        if (!is23(root)) return false;
        return isBalanced();
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;
        private int nodesCount;

        public Node(Key key, Value value, boolean color, int nodesCount) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.nodesCount = nodesCount;
        }

    }
}