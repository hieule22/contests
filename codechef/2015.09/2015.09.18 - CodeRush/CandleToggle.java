package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class CandleToggle {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long l = in.nextLong();
        long r = in.nextLong();
        long low = (long)Math.sqrt(l - 1);
        long high = (long)Math.sqrt(r);
        if (low * low < l) low ++;
        if (high * high > r) high --;
        long count = 0;
        if (low <= high) count = high - low + 1;
        out.println(count);
    }
}
