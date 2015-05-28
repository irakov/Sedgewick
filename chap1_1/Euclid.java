package chap1_1;

//page 4
public class Euclid {
    public static void main(final String[] args){
        System.out.println("gcd(8, 3): "+gcd(8, 3));
        System.out.println("gcd(12, 9): "+gcd(12, 9));
    }

    private static int gcd(final int p, final int q){
        if(q == 0) return p;
        final int r = p %q;
        return gcd(q, r);
    }
}
