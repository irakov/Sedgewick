package chap3_5;//page 503

import chap3_4.LinearProbingHashST;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SparseVector {
    private static final double ERROR = 0.000001;
    private LinearProbingHashST<Integer, Double> st;
    private int size;

    public SparseVector(int size) {
        this.size = size;
        st = new LinearProbingHashST<Integer, Double>();
    }

    public static void main(String[] args) {
        SparseVector a[] = new SparseVector[5];
        a[0] = new SparseVector(5);
        a[0].put(1, 0.9);

        a[1] = new SparseVector(5);
        a[1].put(2, 0.36);
        a[1].put(3, 0.36);
        a[1].put(4, 0.18);

        a[2] = new SparseVector(5);
        a[2].put(3, 0.90);

        a[3] = new SparseVector(5);
        a[3].put(0, 0.90);

        a[4] = new SparseVector(5);
        a[4].put(0, 0.47);
        a[4].put(2, 0.47);

        double x[] = new double[5];
        x[0] = 0.05;
        x[1] = 0.04;
        x[2] = 0.36;
        x[3] = 0.37;
        x[4] = 0.19;

        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        for (int i = 0; i < 5; i++)
            output.println(a[i].dot(x));
    }

    public int size() {
        return size;
    }

    public void put(int i, double x) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        if (Math.abs(x) < ERROR)
            st.delete(i);
        else
            st.put(i, x);
    }

    public void delete(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        st.delete(i);
    }

    public double get(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        if (!st.contains(i)) return 0;
        else return st.get(i);
    }

    public double dot(double[] that) {
        if (that.length != size) throw new IllegalArgumentException();
        double sum = 0;
        for (int i : st.keys())
            sum += that[i] * st.get(i);
        return sum;
    }

    public double dot(SparseVector that) {
        if (that.size != size) throw new IllegalArgumentException();
        double sum = 0;
        for (int i : st.keys())
            sum += this.get(i) * that.get(i);
        if (Math.abs(sum) < ERROR) return 0;
        return sum;
    }

    public SparseVector plus(SparseVector that) {
        if (size != that.size) throw new IllegalArgumentException();
        SparseVector result = new SparseVector(size);
        for (int i : st.keys()) result.put(i, st.get(i));
        for (int i : that.st.keys()) {
            double sum = that.st.get(i) + result.get(i);
            if (Math.abs(sum) < ERROR)
                result.delete(i);
            else
                result.put(i, that.st.get(i) + result.get(i));
        }
        return result;
    }

    public String toString() {
        String s = "";
        for (int i : st.keys())
            s += "(" + i + "," + st.get(i) + ") ";
        return s;
    }
}		