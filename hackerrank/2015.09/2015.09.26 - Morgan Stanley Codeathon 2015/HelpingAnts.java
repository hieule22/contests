package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class HelpingAnts {
    private static final long MOD = (long)1e9 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long wa = power(2L, n - 1);
        long wb = (13 * power(3L, n - 2) - 1) >> 1;
        wb %= MOD;
        long res = wb - wa;
        while (res < 0) res += MOD;
        res %= MOD;
        out.println(res);
    }

    private long power(long a, int b) {
        a %= MOD;
        long r = 1;
        while (b > 0) {
            if ((b & 1) > 0)
                r = (r * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return r;
    }
}
