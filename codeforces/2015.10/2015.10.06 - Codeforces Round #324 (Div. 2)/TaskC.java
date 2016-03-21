package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        int delta = 0;
        for (int i = 0; i < n; i++)
            if (a[i] != b[i]) delta++;
        if (delta > 2 * t) {
            out.println(-1);
            return;
        }
        char[] res = new char[n];
        if (delta <= t) {
            int extra = t - delta;
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) {
                    res[i] = getDifferentChar(a[i], b[i]);
                } else if (extra > 0) {
                    res[i] = getDifferentChar(a[i]);
                    extra--;
                } else res[i] = a[i];
            }
        } else {
            int dA = delta - t;
            int dB = delta - t;
            for (int i = 0; i < n; i++) {
                if (a[i] == b[i]) res[i] = a[i];
                else {
                    if (dA > 0) {
                        dA --;
                        res[i] = a[i];
                    } else if (dB > 0) {
                        dB --;
                        res[i] = b[i];
                    } else {
                        for (int j = 'a'; j <= 'z'; j++)
                            res[i] = getDifferentChar(a[i], b[i]);
                    }
                }
            }
        }
        for (char c : res) out.print(c);
        out.println();
    }

    private char getDifferentChar(char... args) {
        for (int i = 'a'; i <= 'z'; i++) {
            boolean distinct = true;
            for (char c : args)
                if ((char)i == c) {
                    distinct = false;
                    break;
                }
            if (distinct) return (char)i;
        }
        throw new IllegalArgumentException();
    }
}
