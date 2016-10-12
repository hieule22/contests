import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;

public class BigFatInteger2 {
    private static final int MAX = (int) 1e9;
    private static final String YES = "divisible";
    private static final String NO = "not divisible";

    public String isDivisible(int A, int B, int C, int D) {
        List<Integer> primes = generatePrimes((int) Math.sqrt(MAX));
        Map<Integer, Integer> factA = factorize(A, primes);
        Map<Integer, Integer> factC = factorize(C, primes);

        for (Map.Entry<Integer, Integer> factor : factC.entrySet()) {
            int key = factor.getKey();
            if (!factA.containsKey(key))
                return NO;
            int powerC = factor.getValue();
            int powerA = factA.get(key);
            if (1L * powerC * D > 1L * powerA * B)
                return NO;
        }

        return YES;
    }

    private static List<Integer> generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < isPrime.length; ++i) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = i + i; j < isPrime.length; j += i)
                    isPrime[j] = false;
            }
        }
        return primes;
    }

    private static Map<Integer, Integer> factorize(int number, List<Integer> primes) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int prime : primes) {
            int power = 0;
            while (number % prime == 0) {
                power++;
                number /= prime;
            }
            if (power > 0) {
                result.put(prime, power);
            }
            if (number == 1)
                break;
        }

        if (number > 1) {
            result.put(number, 1);
        }

        return result;
    }

}
