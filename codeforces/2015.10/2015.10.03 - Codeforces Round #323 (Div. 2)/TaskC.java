package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int cnt = n * n;
        int[] a = new int[cnt];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < cnt; i++) {
            a[i] = in.nextInt();
            if (!map.containsKey(a[i])) map.put(a[i], 1);
            else map.put(a[i], map.get(a[i]) + 1);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = map.lastKey();
            if (map.get(res[i]) == 1) map.remove(res[i]);
            else map.put(res[i], map.get(res[i]) - 1);
            for (int j = 0; j < i; j++) {
                int gcd = findGcd(res[i], res[j]);
                if (map.get(gcd) == 2) map.remove(gcd);
                else map.put(gcd, map.get(gcd) - 2);
            }
            out.print(res[i] + " ");
        }
    }

    private int findGcd(int a, int b) {
        int temp;
        while (b > 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
