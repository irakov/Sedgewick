package chap3_5;//3.5.17(page 509)

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathSET<Key extends Comparable<Key>> {
    private HashSET<Key> universe;
    private HashSET<Key> set;

    public MathSET(Iterable<Key> universe) {
        this.universe = new HashSET<Key>();
        for (Key key : universe)
            this.universe.add(key);
        this.set = new HashSET<Key>();
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        List<Integer> universeA = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        MathSET<Integer> setA = new MathSET<Integer>(universeA);
        List<Integer> universeB = new ArrayList<Integer>(Arrays.asList(2, 4, 6, 8, 10));
        MathSET<Integer> setB = new MathSET<Integer>(universeB);

        setA.add(1);
        setA.add(2);
        output.println("setA");
        output.println(setA.toString());

        setB.add(2);
        setB.add(4);
        output.println("setB");
        output.println(setB.toString());

        MathSET<Integer> setACompl = setA.complement();
        output.println("setA complement");
        output.println(setACompl.toString());
    }

    public void add(Key key) {
        if (!universe.contains(key)) throw new IllegalArgumentException();
        set.add(key);
    }

    public MathSET<Key> complement() {
        MathSET<Key> complement = new MathSET<Key>(universe.keys());
        for (Key key : universe.keys())
            if (!contains(key)) complement.add(key);
        return complement;
    }

    public void union(MathSET<Key> a) {
        for (Key key : a.universe.keys())
            universe.add(key);
        for (Key key : a.set.keys())
            set.add(key);
    }

    public void intersection(MathSET<Key> a) {
        for (Key key : universe.keys())
            if (!a.universe.contains(key)) universe.delete(key);
        for (Key key : set.keys())
            if (!a.set.contains(key)) set.delete(key);
    }

    public void delete(Key a) {
        set.delete(a);
    }

    public boolean contains(Key key) {
        return set.contains(key);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public Iterable<Key> elements() {
        return set.keys();
    }

    public String toString() {
        return set.toString();
    }
}