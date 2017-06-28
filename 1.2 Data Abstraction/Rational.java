/* A representation of rational numbers in Java

To compile:
javac Rational.java

*/

public class Rational {
    private long num;
    private long denom;
    
    // Class constructor
    Rational(int numerator, int denominator) {
        long nd_gcd = gcd(numerator, denominator);
        
        // Simplify fraction if necessary        
        if (nd_gcd != 0) {
            num = numerator/nd_gcd;
            denom = denominator/nd_gcd;
        } else {
            num = numerator;
            denom = denominator;
        }
    }
    
    /* Implements Euclid's algorithm to find the GCD */
    private static long gcd(int n, int d) {
        if (d == 0) return n;
        int r = n % d;
        return gcd(d, r);
    }
    
    public Rational plus(Rational b) {
        // Rewrite both fractions with common denominator
        // which is the product of both denominators
        long common_denom = denom * b.denom;
        
        Rational ret = new Rational(
            (int) num * (int) b.denom + (int) b.num * (int) denom,
            (int) common_denom);
        return ret;
    }
    
    public Rational minus(Rational b) {
        // Simply negate the other number and use plus
        Rational neg_b = new Rational(
            (int) b.num * -1, (int) b.denom);
        
        return plus(neg_b);
    }
    
    public Rational times(Rational b) {
        Rational ret = new Rational(
            (int) num * (int) b.num,
            (int) denom * (int) b.denom);
            
        return ret;
    }
    
    public Rational dividedBy(Rational b) {
        Rational ret = new Rational(
            (int) num * (int) b.denom,
            (int) denom * (int) b.num);        
            
        return ret;
    }
    
    public boolean equals(Object that) {
        return (that.toString().equals(toString()));
    }
    
    public String toString() {
        if (denom == 1) {
            // For integers, simply return integers
            return Long.toString(num);
        } else {
            return num + "/" + denom;
        }
    }
}