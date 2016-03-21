package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = in.nextInt();
        boolean[] cracked = new boolean[n + 1];
        int remain = n;
        boolean forward = true;
        int cur = 1;
        int res = 0;
        while (remain > 0) {
            if (forward) {
                while (cur < n + 1) {
                    if (!cracked[cur]) {
                        if (n - remain >= a[cur]) {
                            remain--;
                            cracked[cur] = true;
                        }
                    }
                    cur++;
                }
                if (remain == 0) break;
                else {
                    forward = false;
                    res ++;
                    cur = n;
                }
            } else {
                while (cur > 0) {
                    if (!cracked[cur]) {
                        if (n - remain >= a[cur]) {
                            remain --;
                            cracked[cur] = true;
                        }
                    }
                    cur--;
                }
                if (remain == 0) break;
                else {
                    forward = true;
                    res ++;
                    cur = 1;
                }
            }
        }
        out.println(res);
    }
}
