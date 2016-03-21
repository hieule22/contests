package hieule;



import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class MSTEP {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int cnt = n * n;
        int[] row = new int[cnt + 1];
        int[] col = new int[cnt + 1];

        for (int r = 1; r <= n; r++)
            for (int c = 1; c <= n; c++) {
                int val = in.nextInt();
                row[val] = r;
                col[val] = c;
            }

        int steps = 0;
        for (int i = 2; i <= cnt; i++) {
            steps += Math.abs(row[i] - row[i - 1]);
            steps += Math.abs(col[i] - col[i - 1]);
        }
        out.println(steps);
    }
}
