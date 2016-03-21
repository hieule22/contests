package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class EquilateralTriangle {
    private static final int MAX = 5 * (int)1e6;
    private boolean isHypotenus[];

    public EquilateralTriangle() {
        isHypotenus = new boolean[MAX + 1];
        int limit = (int)Math.sqrt(MAX);
        for (int i = 2; i <= limit; i++) {
            for (int j = 1; j < i; j++) {
                if ((i - j) % 2 == 1 && findGcd(i,j) == 1) {
                    int n = i * i + j * j;
                    for (int k = n; k <= MAX; k += n) isHypotenus[k] = true;
                }
            }
        }
    }

    private int findGcd(int a, int b) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        if (isHypotenus[n]) out.println("YES");
        else out.println("NO");
    }
}
