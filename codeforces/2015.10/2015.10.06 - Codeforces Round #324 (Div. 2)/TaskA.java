package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        if (t == 10 && n == 1) {
            out.println(-1);
            return;
        }
        int[] res = new int[n];
        if (t == 10) {
            res[0] = 1;
        } else {
            for (int i = 0; i < n; i++) res[i] = t;
        }
        for (int digit :res) out.print(digit);
        out.println();
    }
}
