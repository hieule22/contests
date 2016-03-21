package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
    private static final long MOD = (long)1e9 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long total = power(3, 3 * n);
        long complement = power(7, n);
        long res = total - complement;
        while (res < 0) res += MOD;
        out.println(res);
    }

    private long power(int base, int exponent) {
        if (base == 1 || exponent == 0) return 1;
        long res = power(base, exponent >> 1);
        res = (res * res) % MOD;
        if ((exponent & 1) == 1) res = (res * base) % MOD;
        return res;
    }
}
