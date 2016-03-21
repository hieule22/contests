package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        if (isPrime(n)) {
            out.println(1);
            out.println(n);
            return;
        }
        n -= 3;
        int half = n >> 1;
        for (int i = 2; i <= half; i++) {
            if (isPrime(i) && isPrime(n - i)) {
                out.println(3);
                out.println(3 + " " + i + " " + (n - i));
                return;
            }
        }
        assert false;
    }

    private boolean isPrime(int n) {
        if (n == 1) return false;
        int square = (int)Math.sqrt(n);
        for (int i = 2; i <= square; i++)
            if (n % i == 0) return false;
        return true;
    }
}
