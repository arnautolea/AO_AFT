//gaseste toate numere prime din sirul [1-15] si inmulteste factorialele lor

package org.myatf;

public class HomeworkDorina {
    public static void main(String[] args) {

        long pOF = 1;

        for (int i = 1; i <= 15; i++) {
            if (isPrime(i)) {
                long factorial = 1;
                for (int n = 1; n <= i; n++) {
                    factorial *= n;

                }
                pOF *= factorial;
                System.out.println("factorial: " + factorial);
            }
        }

        System.out.println("Product of factorials for prime numbers: " + pOF);
    }

    public static boolean isPrime(int num) {

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;

    }
}
