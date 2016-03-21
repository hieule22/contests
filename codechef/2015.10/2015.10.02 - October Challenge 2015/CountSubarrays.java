package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class CountSubarrays {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n, before = 0, after;
        n = in.nextInt();
        long res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            after = in.nextInt();
            if (after >= before) cnt++;
            else {
                res += ((cnt * (cnt + 1)) >> 1);
                cnt = 1;
            }
            before = after;
        }
        res += ((cnt * (cnt + 1)) >> 1);
        out.println(res);
    }
}
