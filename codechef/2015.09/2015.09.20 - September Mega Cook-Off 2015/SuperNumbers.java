package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class SuperNumbers {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long l = in.nextLong();
        long r = in.nextLong();
        int cnt = 0;
        if (l == 1) cnt++;
        long base = 1;
        while (base <= r) {
            long power = 2;
            while (base * power < l) power = (power << 1);
            while (base * power <= r) {
                power = (power << 1);
                cnt ++;
            }
            base *= 3;
        }
        out.println(cnt);
    }
}
