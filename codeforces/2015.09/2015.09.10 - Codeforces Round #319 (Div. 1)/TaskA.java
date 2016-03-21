package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int cur = 1;
        List<Integer> q = new LinkedList<Integer>();
        for (int i = 2; i <= n; i++) {
            boolean unique = true;
            for (int j = 1; j < i; j++) {
                boolean match = true;
                for (int factor : q) {
                    if ((i % factor == 0 && j % factor != 0) ||
                            (i % factor != 0 && j % factor == 0)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    unique = false;
                    break;
                }
            }
            if (!unique) q.add(i);
        }
        out.println(q.size());
        for (int f : q) out.print(f + " ");

    }
}
