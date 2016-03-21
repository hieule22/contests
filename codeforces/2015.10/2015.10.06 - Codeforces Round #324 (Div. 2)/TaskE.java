package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] p = new int[n + 1];
        int[] q = new int[n + 1];
        int[] pos = new int[n + 1];
        for (int i = 1; i <= n; i++) p[i] = in.nextInt();
        for (int i = 1; i <= n; i++) {
            q[i] = in.nextInt();
            pos[q[i]] = i;
        }
        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (i < pos[p[i]]) total += pos[p[i]] - i;
        }

        List<Swap> swaps = new LinkedList<>();
        boolean isOk = false;
        int actual = 0;
        while (!isOk) {
            isOk = true;
            for (int i = 1; i <= n; i++) {
                if (i < pos[p[i]]) {
                    isOk = false;
                    for (int j = i + 1; j <= pos[p[i]]; j++) {
                        if (pos[p[j]] < j && pos[p[j]] <= i && j <= pos[p[i]]) {
                            int temp = p[i];
                            p[i] = p[j];
                            p[j] = temp;
                            swaps.add(new Swap(i, j));
                            actual += j - i;
                            break;
                        }
                    }
                }
            }
        }

        assert actual == total;
        out.println(total);
        out.println(swaps.size());
        for (Swap swap : swaps) out.println(swap.x + " " + swap.y);
    }

    class Swap {
        int x, y;

        public Swap(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
