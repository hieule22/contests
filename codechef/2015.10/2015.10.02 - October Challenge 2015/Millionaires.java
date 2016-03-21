package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class Millionaires {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        int[] prize = new int[n + 1];
        for (int i = 0; i < n + 1; i++) prize[i] = in.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++)
            if (a[i] == b[i]) cnt++;
        int res = 0;
        for (int i = 0; i <= cnt; i++) res = Math.max(res, prize[i]);
        if (cnt == n) res = prize[n];
        out.println(res);
    }
}
