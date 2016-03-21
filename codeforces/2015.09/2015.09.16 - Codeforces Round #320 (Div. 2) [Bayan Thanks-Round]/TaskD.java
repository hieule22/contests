package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int[] a = new int[n + 2];
        for (int i = 1; i <= n; i++) a[i] = in.nextInt();

        int[] prefix = new int[n + 2];
        int[] suffix = new int[n + 2];
        long factor = pow(x, k);
        for (int i = 1; i <= n; i++) prefix[i] = prefix[i - 1] | a[i];
        for (int i = n; i >= 1; i--) suffix[i] = suffix[i + 1] | a[i];
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, (prefix[i - 1] | (a[i] * factor)) | suffix[i + 1]);
        }
        out.println(res);
    }

    private static long pow(int x, int e) {
        if (e == 0 || x == 1) return 1;
        long res = pow(x, e >> 1);
        res *= res;
        if ((e & 1) == 1) res *= x;
        return res;
    }


}
