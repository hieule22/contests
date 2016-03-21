package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class LaurenAndInversion {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        boolean hasInversions = false;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            if (a[i] < a[i - 1]) hasInversions = true;
        }
        if (!hasInversions) {
            out.println("Cool Array");
            return;
        }
        int maxInversions = 0, left = 0, right = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int cnt = 0;
                for (int k = i; k <= j; k++) {
                    if (a[k] > a[i]) cnt --;
                    else if (a[k] < a[i]) cnt ++;
                    if (a[k] > a[j]) cnt ++;
                    else if (a[k] < a[j]) cnt --;
                }
                if (cnt > maxInversions) {
                    left = i;
                    right = j;
                    maxInversions = cnt;
                }
            }
        }
        out.println(left + " " + right);
    }
}
