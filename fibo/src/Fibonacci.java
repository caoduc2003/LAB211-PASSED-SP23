
import java.util.Arrays;
import java.math.BigInteger;
import java.util.ArrayList;

public class Fibonacci {
    public static ArrayList<BigInteger> cache = new ArrayList<>(Arrays.asList(BigInteger.ZERO, BigInteger.ONE)); // cache

    public static BigInteger fib(int n) { // recursive
        if (n < 0) { 
            return null;
        } else if (n < cache.size()) { // if n is in cache return it
            return cache.get(n);
        } else {
            BigInteger result = fib(n - 1).add(fib(n - 2)); // plus the two previous numbers in cache to get the next number
            cache.add(result); // add the lastest number to cache 
            return result; // return the result
        }
    }

    public static String firstNFibs(int n) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            result.append(fib(i));
            
            if (i != n - 1)
                result.append(", ");
        }

        return result.toString(); 
    }

    public static void main(String[] args) {
        
        System.out.println(firstNFibs(10000));
    }
}