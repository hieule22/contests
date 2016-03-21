package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class BANKROB {
    private static final double TOTAL = 1e9;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt();
        double p = in.nextDouble();
        double a, b;
        if ((m & 1) == 1) {
            a = (power(p, m) + 1) * TOTAL / (p + 1.0);
            b = TOTAL - a;
        } else {
            a = (1 - power(p, m)) * TOTAL / (p + 1.0);
            b = TOTAL - a;
        }
        out.printf("%.6f %.6f\n", a, b);
    }

    private static double power(double base, int exponent) {
        if (base == 0) return 0;
        if (base == 1) return 1;
        if (exponent == 0) return 1;
        double res = power(base, exponent >> 1);
        res *= res;
        if ((exponent & 1) != 0)
            res *= base;
        return res;
    }

}
