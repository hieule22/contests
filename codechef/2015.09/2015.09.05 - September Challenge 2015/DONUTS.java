package hieule;



import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class DONUTS {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] chain = new int[m];
        for (int i = 0; i < m; i++) chain[i] = in.nextInt();

        Arrays.sort(chain);
        int cnt = m;
        int link = 0;
        for (int i = 0; i < m; i++) {
            link += chain[i];
            if (link >= cnt - 1) break;
            else cnt --;
        }
        out.println(cnt - 1);

    }
}
