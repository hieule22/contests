package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class StockValues {

    private int[] p;
    private int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = in.nextInt();

        int max = brute(0);
        out.println(max);
    }

    private int brute(int index)  {
        if (index == 0) return brute(index + 1);
        else if (index == n - 1) {
            int res = 0;
            for (int i = 0; i < n / 2; i++)
                res += Math.abs(p[i] - p[n - i - 1]);
            return res;
        } else if (p[index - 1] % 2 == 1 || p[index + 1] % 2 == 1) {
            return brute(index + 1);
        } else {
            int temp = p[index];
            p[index] = (p[index - 1] + p[index + 1]) / 2;
            int res = brute(index + 1);
            p[index] = temp;
            res = Math.max(res, brute(index + 1));
            return res;
        }
    }
}
