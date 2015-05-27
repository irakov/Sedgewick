package chap1_4;//1.4.18
//page 210

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LocalMinInArray {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        LocalMinInArray l = new LocalMinInArray();
        int[] a = {-5, -1, 1, 9, 10, 7, 11};
        Result res = l.find(a);
        if (res == null)
            output.println("none found");
        else
            output.println(a[res.position]);
    }

    public Result find(int[] a) {
        return find(a, 0, a.length - 1);
    }

    private Result find(int[] a, int left, int right) {
        while (right - left >= 2 && left >= 0 && right <= a.length - 1) {
            int middle = (right + left + 1) / 2;
            if (a[middle - 1] > a[middle] && a[middle + 1] > a[middle])
                return new Result(middle);
            else {
                Result result = null;
                if (a[middle - 1] < a[middle + 1])
                    result = find(a, left, middle);
                if (result == null)
                    return find(a, middle + 1, right);
                else return result;
            }
        }

        return null;
    }

    public class Result {
        public int position;

        public Result(int pos) {
            position = pos;
        }
    }
}