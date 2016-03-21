package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskE {
    private static final double EPSILON = 1e-6;
    private static final double LIMIT = 2 * 1e5;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            prefix[i] = prefix[i - 1] + a[i];
        }

        int[] min = new int[n + 1];
        int[] max = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            min[i] = max[i] = prefix[i];
            for (int j = i + 1; j <= n; j++) {
                min[i] = Math.min(min[i], prefix[j] - prefix[j - i]);
                max[i] = Math.max(max[i], prefix[j] - prefix[j - 1]);
            }
        }

        double low = -LIMIT;
        double high = LIMIT;
        while (Math.abs(high - low) >= EPSILON) {
            double leftThird = low + (high - low) / 3;
            double rightThird = high - (high - low) / 3;
            if (findMax(leftThird, min, max, n) < findMax(rightThird, min, max, n))
                high = rightThird;
            else
                low = leftThird;
        }

        out.println(low);
    }

    public static double findMax(double x, int[] min, int[] max, int n) {
        double res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, Math.abs(min[i] - x * i));
            res = Math.max(res, Math.abs(max[i] - x * i));
        }
        return res;
    }
}
