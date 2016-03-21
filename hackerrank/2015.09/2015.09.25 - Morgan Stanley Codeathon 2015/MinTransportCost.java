package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class MinTransportCost {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] x = in.next().toCharArray();
        char[] y = in.next().toCharArray();
        int a = in.nextInt(); // delete
        int b = in.nextInt(); // insert
        int c = in.nextInt(); // replace

        int[] fx = new int[26];
        for (char m : x) fx[m - 'a']++;

        int[] fy = new int[26];
        for (char m : y) fy[m - 'a']++;

        int less = 0, more = 0;
        for (int i = 0; i < 26; i++) {
            if (fx[i] > fy[i]) more += (fx[i] - fy[i]);
            else less += (fy[i] - fx[i]);
        }

        int cost = 0;
        if (c < a + b) {
            int change = Math.min(less, more);
            cost += c * change;
            less -= change;
            more -= change;
        } else {
            int change = Math.min(less, more);
            cost += (a + b) * change;
            less -= change;
            more -= change;
        }
        if (less > 0) cost += less * b;
        else cost += more * a;
        out.println(cost);
    }
}
