package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskC {
    private static final int MAX = (int) 1e6;
    private static final int WIDTH = 1000;
    private static final int CNT = MAX / WIDTH;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point(in.nextInt(), in.nextInt(), i + 1);
        }
        Arrays.sort(p, new XComparator());
        int cur = 0;
        for (int i = 1; i <= CNT; i++) {
            if (cur == n) break;
            int begin = cur;
            while (cur < n && p[cur].x <= i * WIDTH) cur++;
            Arrays.sort(p, begin, cur);
            if ((i & 1) != 0) {
                for (int j = begin; j < cur; j++) out.print(p[j].index + " ");
            } else {
                for (int j = cur - 1; j >= begin; j--) out.print(p[j].index + " ");
            }
        }
    }

    class XComparator implements Comparator<Point> {
        public int compare(Point o1, Point o2) {
            return o1.x - o2.x;
        }
    }

    class Point implements Comparable<Point> {
        int x, y, index;

        public Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        public int compareTo(Point o) {
            return y - o.y;
        }
    }
}