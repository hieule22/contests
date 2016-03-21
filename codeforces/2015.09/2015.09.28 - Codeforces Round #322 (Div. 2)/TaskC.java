package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) a.add(in.nextInt());
        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o2 % 10) - (o1 % 10);
            }
        });

        for (int i = 0; i < n; i++) {
            if (a.get(i) == 100) continue;
            int remain = a.get(i) % 10;
            if (k >= 10 - remain) {
                a.set(i, a.get(i) + 10 - remain);
                k -= 10 - remain;
            } else {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (k < 10) break;
            if (k >= 100 - a.get(i)) {
                k -= 100 - a.get(i);
                a.set(i, 100);
            } else {
                a.set(i, a.get(i) + 10 * (k / 10));
                k = k % 10;
            }
        }

        int res = 0;
        for (int number : a) res += number / 10;
        out.println(res);

    }

}
