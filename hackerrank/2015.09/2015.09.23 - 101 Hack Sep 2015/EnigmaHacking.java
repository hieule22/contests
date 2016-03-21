package taskdirectory;

import com.sun.javaws.exceptions.InvalidArgumentException;
import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.rmi.MarshalException;
import java.util.*;

public class EnigmaHacking {

    private static final String NO_RESULT = "Bad Luck Allen";
    private static final int ALPHABET_SIZE = 26;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long k = in.nextLong();
        char[] s = in.next().toCharArray();

        List<Segment> frontier = new LinkedList<Segment>();
        frontier.add(new Segment(0, n - 1, 0, n - 1));
        for (int i = 0; i < m; i++) {
            int low = in.nextInt() - 1;
            int high = in.nextInt() - 1;
            List<Segment> left = new LinkedList<Segment>();
            List<Segment> right = new LinkedList<Segment>();
            List<Segment> middle = new LinkedList<Segment>();
            for (Segment seg : frontier) {
                if (seg.r1 < low) left.add(seg);
                else if (seg.l1 > high) right.add(seg);
                else {
                    if (seg.l1 < low) {
                        if (seg.increase) left.add(new Segment(seg.l1, low - 1, seg.l2, seg.l2 + low - 1 - seg.l1));
                        else left.add(new Segment(seg.l1, low - 1, seg.l2, seg.l2 - (low - 1 - seg.l1)));
                    }
                    if (seg.r1 > high) {
                        if (seg.increase) right.add(new Segment(high + 1, seg.r1, seg.r2 - (seg.r1 - high - 1), seg.r2));
                        else right.add(new Segment(high + 1, seg.r1, seg.r2 + (seg.r1 - high - 1), seg.r2));
                    }
                    int l = Math.max(low, seg.l1);
                    int r = Math.min(high, seg.r1);
                    if (l <= r) {
                        if (seg.increase) middle.add(new Segment(l, r, seg.l2 + (l - seg.l1), seg.l2 + (r - seg.l1)));
                        else middle.add(new Segment(l, r, seg.l2 - (l - seg.l1), seg.l2 - (r - seg.l1)));
                    }
                }
            }
            Collections.reverse(middle);
            frontier = new LinkedList<Segment>();
            frontier.addAll(left);
            for (Segment seg : middle) {
                frontier.add(new Segment(low + (high - seg.r1), low + (high - seg.l1), seg.r2, seg.l2));
            }
            frontier.addAll(right);
        }

        int[] pos = new int[n];
        for (Segment seg : frontier) {
            for (int i = 0; i < seg.size(); i++) {
                if (seg.increase) pos[seg.l2 + i] = seg.l1 + i;
                else pos[seg.l2 - i] = seg.l1 + i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (s[i] != '?' && s[pos[i]] != '?' && s[i] != s[pos[i]]) {
                out.println(NO_RESULT);
                return;
            }
        }

        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        List<Integer> root = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (s[i] != '?') visited[i] = 0;
            else if (visited[i] == -1) {
                boolean isNewComp = true;
                int color = n + i;
                int cur = i;
                while (isNewComp && visited[cur] != color) {
                    if (visited[cur] != -1) {
                        isNewComp = false;
                    } else {
                        visited[cur] = color;
                        cur = pos[cur];
                    }
                }
                if (isNewComp) root.add(i);
            }
        }
//        System.out.println(root.size());
        if (k > (long)Math.pow(ALPHABET_SIZE, root.size())) {
            out.println(NO_RESULT);
            return;
        }

        if (root.size() > 0) {
            while (k > 0) {
                long tail = (long) Math.pow(ALPHABET_SIZE, root.size() - 1);
                long offset = k / tail;
                k -= offset * tail;
                if (k % tail != 0) offset++;
                if (!root.isEmpty()) {
                    int top = root.remove(0);
                    s[top] = (char) ('a' + offset - 1);
                }
            }
        }

        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            if (s[i] != '?') {
                int cur = i;
                while (!Character.isLetter(res[cur])) {
                    res[cur] = s[i];
                    cur = pos[cur];
                }
            }
        }

        for (char c : res)
            out.print(c);
        out.println();
    }

    private class Segment {
        int l1, r1, l2, r2;
        boolean increase;

        public Segment(int l1, int r1, int l2, int r2) {
            if (r1 - l1 != Math.abs(r2 - l2))
                throw new IllegalArgumentException();
            this.l1 = l1;
            this.r1 = r1;
            this.l2 = l2;
            this.r2 = r2;
            increase = r2 > l2;
        }

        public int size() {
            return r1 - l1 + 1;
        }
    }
}
