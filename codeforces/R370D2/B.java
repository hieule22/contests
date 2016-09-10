package tasks;

import util.io.InputReader;
import java.io.PrintWriter;

public class B {
    private static final char[] direction = {'R', 'L', 'U', 'D'};

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] moves = in.next().toCharArray();
        if (moves.length % 2 == 1) {
            out.println(-1);
            return;
        }

        long[] cnt = new long[4];
        for (char move : moves) {
          for (int i = 0; i < direction.length; ++i) {
            if (move == direction[i])
              cnt[i]++;
          }
        }

        long result = Math.abs(cnt[0] - cnt[1]) + Math.abs(cnt[2] - cnt[3]);
        out.println(result / 2);
    }
}
