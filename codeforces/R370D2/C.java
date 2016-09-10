package tasks;

import util.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class C {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x = in.nextInt();
        int y = in.nextInt();
        int[] sides = {y, y, y};

        int cnt = 0;
        while (!surpass(sides, x)) {
            ++cnt;
            Arrays.sort(sides);
            sides[0] = sides[1] + sides[2] - 1;
        }
        out.println(cnt);
    }

    private boolean surpass(int[] sides, int x) {
        for (int side : sides) {
            if (side < x)
                return false;
        }
        return true;
    }
}
