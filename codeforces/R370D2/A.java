package tasks;

import util.io.InputReader;
import java.io.PrintWriter;

public class A {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; ++i)
            a[i] = in.nextLong();
        for (int i = 0; i < n - 1; ++i) {
            long b = a[i] + a[i + 1];
            out.print(b + " ");
        }
        out.println(a[n - 1]);
    }
}
