package chap1_1;

//page 59
public class P1_1_29 {
    public static void main(final String[] args) {
        int[] values = {0, 1, 2, 3, 4, 6, 6, 7, 8, 9};
        System.out.println(rank(values, 5));
    }

    public static int rank(final int[] values, final int key) {
        if (values[0] > key) {
            return -1;
        }

        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            final int middle = left + (right - left) / 2;
            if (values[middle] < key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        if (values[right] == key) {
            return right;
        }

        return right + 1;
    }
}
