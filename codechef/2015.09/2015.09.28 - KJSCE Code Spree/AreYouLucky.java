package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class AreYouLucky {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        int n = in.nextInt();
        for (int i = 0; i < t; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            out.println(powerModulo(x, y, n));
        }
    }

    private long powerModulo(int x, int y, int n) {
        if (x == 1 || y == 0) return 1;
        long res = powerModulo(x, y >> 1, n);
        res = (res * res) % n;
        if ((y & 1) == 1) res = (res * x) % n;
        return res;
    }
}
