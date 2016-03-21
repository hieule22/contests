package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class REBXOR {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 5];
        for (int i = 1; i <= n; i++) a[i] = in.nextInt();
        out.println(solve(a, n));
    }

    public static int solve(int[] a, int n) {
        int[] prefix = new int[n + 5];
        int[] before = new int[n + 5];
        int[] after = new int[n + 5];

        int height = 0;
        for (int i = 1; i <= n; i++) {
            height = Math.max(a[i], height);
        }
        height = Integer.toBinaryString(height).length();

        Node root = new Node();

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] ^ a[i];

            before[i] = Math.max(prefix[i], a[i]);

            int max = Math.max(before[i], before[i - 1]);
            boolean done = true;

            int res = 0;
            Node cur = root;
            for (int h = height; h > 0; h--) {
                if (cur.c0 == null && cur.c1 == null) break;
                if (isBitSet(prefix[i], h)) {
                    if (cur.c0 != null) {
                        cur = cur.c0;
                        res += 1 << (h - 1);
                    } else cur = cur.c1;
                } else {
                    if (cur.c1 != null) {
                        cur = cur.c1;
                        res += 1 << (h - 1);
                    } else cur = cur.c0;
                }
                if (isBitSet(res, h) && !isBitSet(max, h)) done = false;
                if (done)
                    if (!isBitSet(res, h) && isBitSet(max, h)) break;
            }

            before[i] = Math.max(before[i], res);
            before[i] = Math.max(before[i], before[i - 1]);

            cur = root;
            for (int h = height; h > 0; h--) {
                if (isBitSet(prefix[i], h)) {
                    if (cur.c1 == null) cur.c1 = new Node();
                    cur.c1.limit = i;
                    cur = cur.c1;
                } else {
                    if (cur.c0 == null) cur.c0 = new Node();
                    cur.c0.limit = i;
                    cur = cur.c0;
                }
            }
        }

        after[n] = a[n];
        for (int i = n - 1; i >= 2; i--) {
            after[i] = Math.max(prefix[i - 1], a[i]);
            int max = Math.max(after[i], after[i + 1]);
            boolean done = true;

            int res = 0;
            Node cur = root;
            for (int h = height; h > 0; h--) {
                if (cur.c0 == null && cur.c1 == null) break;
                if (isBitSet(prefix[i - 1], h)) {
                    if (cur.c0 != null && cur.c0.limit > i) {
                        cur = cur.c0;
                        res += 1 << (h - 1);
                    } else cur = cur.c1;
                } else {
                    if (cur.c1 != null && cur.c1.limit > i) {
                        cur = cur.c1;
                        res += 1 << (h - 1);
                    } else cur = cur.c0;
                }
                if (isBitSet(res, h) && !isBitSet(max, h)) done = false;
                if (done)
                    if (!isBitSet(res, h) && isBitSet(max, h)) break;
            }

            after[i] = Math.max(after[i], res);
            after[i] = Math.max(after[i], after[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < n; i++) res = Math.max(res, before[i] + after[i + 1]);
        return res;
    }

    private static boolean isBitSet(int n, int pos) {
        return ((n >> (pos - 1)) & 1) == 1;
    }
}

class Node {
    int limit;
    Node c0, c1;
}