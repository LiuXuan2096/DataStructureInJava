package DataStructure.Fibonacci;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(100));
    }

    private static long fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        long first = 1;
        long second = 1;
        for (int i = 0; i < n - 2; i++) {
            long sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}
