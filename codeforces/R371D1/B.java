import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        private int n;
        private InputReader in;
        private PrintWriter out;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            this.in = in;
            this.out = out;
            query(new Point(1, 1), new Point(n, n), new Rectangle());
        }

        private boolean query(Point bottomLeft, Point topRight, Rectangle rectangle) {
            if (bottomLeft.r > topRight.r || bottomLeft.c > topRight.c)
                return false;

            // Find the column of the right edge.
            int low = bottomLeft.c, high = topRight.c;
            while (low < high) {
                int mid = low + (high - low) / 2;
                int answer = ask(bottomLeft.r, bottomLeft.c, topRight.r, mid);
                if (answer > 0)
                    high = mid;
                else
                    low = mid + 1;
            }
            int rightCol = low;

            // Find the column of the left edge.
            low = bottomLeft.c;
            high = topRight.c;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                int answer = ask(bottomLeft.r, mid, topRight.r, topRight.c);
                if (answer > 0)
                    low = mid;
                else
                    high = mid - 1;
            }
            int leftCol = low;

            // Find the topmost row.
            low = bottomLeft.r;
            high = topRight.r;
            while (low < high) {
                int mid = low + (high - low) / 2;
                int answer = ask(bottomLeft.r, bottomLeft.c, mid, topRight.c);
                if (answer > 0)
                    high = mid;
                else
                    low = mid + 1;
            }
            int topRow = low;

            // Find the bottommost row.
            low = bottomLeft.r;
            high = topRight.r;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                int answer = ask(mid, bottomLeft.c, topRight.r, topRight.c);
                if (answer > 0)
                    low = mid;
                else
                    high = mid - 1;
            }
            int bottomRow = low;

            if (leftCol > rightCol) {
                Rectangle first = new Rectangle();
                query(new Point(1, leftCol), new Point(n, n), first);
                Rectangle second = new Rectangle();
                query(new Point(1, 1), new Point(n, rightCol), second);
                out.printf("! %d %d %d %d %d %d %d %d\n",
                        first.bottomLeft.r, first.bottomLeft.c, first.topRight.r, first.topRight.c,
                        second.bottomLeft.r, second.bottomLeft.c, second.topRight.r, second.topRight.c);
                return true;
            }

            if (bottomRow > topRow) {
                Rectangle first = new Rectangle();
                query(new Point(bottomRow, 1), new Point(n, n), first);
                Rectangle second = new Rectangle();
                query(new Point(1, 1), new Point(topRow, n), second);
                out.printf("! %d %d %d %d %d %d %d %d\n",
                        first.bottomLeft.r, first.bottomLeft.c, first.topRight.r, first.topRight.c,
                        second.bottomLeft.r, second.bottomLeft.c, second.topRight.r, second.topRight.c);
                return true;
            }

            rectangle.bottomLeft.r = bottomRow;
            rectangle.bottomLeft.c = leftCol;
            rectangle.topRight.r = topRow;
            rectangle.topRight.c = rightCol;
            return true;
        }

        private int ask(int r1, int c1, int r2, int c2) {
            out.printf("? %d %d %d %d\n", r1, c1, r2, c2);
            out.flush();
            return in.nextInt();
        }

        private class Point {
            private int r;
            private int c;

            public Point(int r, int c) {
                this.r = r;
                this.c = c;
            }

        }

        private class Rectangle {
            private Point bottomLeft;
            private Point topRight;

            public Rectangle() {
                bottomLeft = new Point(-1, -1);
                topRight = new Point(-1, -1);
            }

        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private static final int BUFFER_SIZE = 32768;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
