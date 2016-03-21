package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int max = 0;
        int cur = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            if (a[i] >= a[i - 1]) cur ++;
            else {
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        max = Math.max(max, cur);
        out.println(max);
    }
}
