package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class SumNim {
    int n;
    int[] a;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        int ans = count(0, 0, 0);
        out.println(ans >> 1);
    }

    private int count(int xor, int cur, int index) {
        if (index == n) {
            if ((xor ^ cur) == 0) return 1;
            else return 0;
        }
        int r1 = count(xor ^ cur, a[index], index + 1);
//        System.out.println(r1);
        int r2 = count(xor, cur + a[index], index + 1);
        return r1 + r2;
    }
}
