package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LIGHTHOUSE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        List<Pair> pairs = solve(in);
        out.println(pairs.size());
        for (Pair p : pairs)
            out.println(p.toString());
    }

    private static List<Pair> solve(InputReader in) {
        int n = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();

        Pair l0 = new Pair(a, b, 1);
        Pair l1 = new Pair(a, b, 1);
        Pair r0 = new Pair(a, b, 1);
        Pair r1 = new Pair(a, b, 1);
        Pair t0 = new Pair(a, b, 1);
        Pair t1 = new Pair(a, b, 1);
        Pair b0 = new Pair(a, b, 1);
        Pair b1 = new Pair(a, b, 1);

        for (int i = 2; i <= n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (l0.x == x) {
                if (l0.y > y) l0.setPos(x, y, i);
                else if (l1.y < y) l1.setPos(x, y , i);
            } else if (l0.x > x) {
                l1.setPos(x, y, i);
                l0.setPos(x, y, i);
            }

            if (r0.x == x) {
                if (r0.y > y) r0.setPos(x, y, i);
                else if (r1.y < y) r1.setPos(x, y, i);
            } else if (r0.x < x) {
                r0.setPos(x, y, i);
                r1.setPos(x, y, i);
            }

            if (t0.y == y) {
                if (t0.x > x) t0.setPos(x, y, i);
                else if (t1.x < x) t1.setPos(x, y, i);
            } else if (t0.y < y) {
                t0.setPos(x, y, i);
                t1.setPos(x, y, i);
            }

            if (b0.y == y) {
                if (b0.x > x) b0.setPos(x, y, i);
                else if (b1.x < x) b1.setPos(x, y, i);
            } else if (b0.y > y) {
                b0.setPos(x, y, i);
                b1.setPos(x, y, i);
            }
        }

        // Case 3: top SW, bot NE
        List<Pair> pairs = new LinkedList<Pair>();
        if (t0.x <= l0.x) pairs.add(t0.setDir("SE"));
        else if (t1.x >= r1.x) pairs.add(t1.setDir("SW"));
        else if (b0.x <= l0.x) pairs.add(b0.setDir("NE"));
        else if (b1.x >= r1.x) pairs.add(b1.setDir("NW"));
        else if (l0.y <= b0.y) pairs.add(l0.setDir("NE"));
        else if (l1.y >= t1.y) pairs.add(l1.setDir("SE"));
        else if (r0.y <= b0.y) pairs.add(r0.setDir("NW"));
        else if (r1.y >= t1.y) pairs.add(r1.setDir("SW"));
        else {
            if (t0.x < b1.x) {
                pairs.add(t0.setDir("SE"));
                pairs.add(b1.setDir("NW"));
            } else {
                pairs.add(t0.setDir("SW"));
                pairs.add(b1.setDir("NE"));
            }
        }
        return pairs;
    }
}

class Pair {
    int x, y;
    String direction;
    int index;

    public Pair(int x, int y, int index) {
        setPos(x, y, index);
    }

    public void setPos(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public Pair setDir(String dir) {
        direction = dir;
        return this;
    }

    public String toString() {
        return index + " " + direction;
    }
}
