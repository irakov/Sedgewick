package chap1_1;

//page 22
public class Sqrt {

    private static double eps = 0.00000001D;

    public static void main(final String[] args) {
        System.out.println(sqrt(2));
    }

    private static double sqrt(final double c) {
        if (c < 0) {
            return Double.NaN;
        }

        double t = c;

        while (Math.abs(t - c / t) > eps * t) {
            t = (c / t + t) / 2;
        }

        return t;
    }
}
