package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        boolean[] row = new boolean[n + 1];
        boolean[] col = new boolean[n + 1];
        int cnt = n * n;
        List<Integer> days = new LinkedList<>();
        for (int i = 1; i <= cnt; i++) {
            int h = in.nextInt();
            int v = in.nextInt();
            if (!row[h] && !col[v]) {
                days.add(i);
                row[h] = true;
                col[v] = true;
            }
        }
        for (int day : days) out.print(day + " ");
        out.println();
    }
}
