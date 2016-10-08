import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class C {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        private static final int[][] directions = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            // Generate all collisions.
            Map<TaskC.Move, Long> collisions = new HashMap<>();
            TaskC.Move current = new TaskC.Move(new TaskC.Point(0, 0), 1, 1);
            long time = 0;
            do {
                if (collisions.containsKey(current))
                    break;
                collisions.put(new TaskC.Move(current), time);
                TaskC.Move next = advance(current, n, m);
                time += (long) Math.abs(next.point.x - current.point.x);
                current = next;
            } while (!isCorner(current.point, n, m));

            int k = in.nextInt();
            for (int i = 0; i < k; ++i) {
                TaskC.Point target = new TaskC.Point(in.nextInt(), in.nextInt());
                long minTime = Long.MAX_VALUE;
                for (int j = 0; j < directions.length; ++j) {
                    TaskC.Move move = backtrack(target, n, m, directions[j][0], directions[j][1]);
                    if (collisions.containsKey(move)) {
                        long temp = collisions.get(move) + Math.abs(target.x - move.point.x);
                        minTime = Math.min(minTime, temp);
                    }
                }
                if (minTime == Long.MAX_VALUE) {
                    out.println(-1);
                } else {
                    out.println(minTime);
                }
            }
        }

        private static TaskC.Move advance(TaskC.Move move, int n, int m) {
            return extend(move, n, m);
        }

        private static TaskC.Move backtrack(TaskC.Point point, int n, int m, int dx, int dy) {
            TaskC.Move move = new TaskC.Move(point, -dx, -dy);
            TaskC.Move nextMove = extend(move, n, m);
            nextMove.dx = dx;
            nextMove.dy = dy;
            return nextMove;
        }

        private static TaskC.Move extend(TaskC.Move move, int n, int m) {
            int x = move.point.x + move.dx * n;
            int y = move.point.y + move.dy * m;

            x = Math.max(0, x);
            x = Math.min(n, x);

            y = Math.max(0, y);
            y = Math.min(m, y);

            int ddx = Math.abs(x - move.point.x);
            int ddy = Math.abs(y - move.point.y);

            TaskC.Point point;
            if (ddx <= ddy) {
                point = new TaskC.Point(x, move.point.y + move.dy * ddx);
            } else {
                point = new TaskC.Point(move.point.x + move.dx * ddy, y);
            }

            int dx = -move.dx;
            int dy = -move.dy;
            if (dx < 0 && point.x == n || dx > 0 && point.x == 0)
                return new TaskC.Move(point, dx, move.dy);
            return new TaskC.Move(point, move.dx, dy);
        }

        private static boolean isCorner(TaskC.Point point, int n, int m) {
            return (point.x == 0 && point.y == 0) || (point.x == 0 && point.y == m) ||
                    (point.x == n && point.y == 0) || (point.x == n && point.y == m);
        }

        private static class Move {
            TaskC.Point point;
            int dx;
            int dy;

            public Move(TaskC.Point point, int dx, int dy) {
                this.point = new TaskC.Point(point.x, point.y);
                this.dx = dx;
                this.dy = dy;
            }

            public Move(TaskC.Move other) {
                this.point = new TaskC.Point(other.point.x, other.point.y);
                this.dx = other.dx;
                this.dy = other.dy;
            }


            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof TaskC.Move))
                    return false;
                TaskC.Move move = (TaskC.Move) obj;
                return point.equals(move.point) && move.dx == dx && move.dy == dy;
            }


            public int hashCode() {
                int hash = point.hashCode();
                hash = hash * 31 + dx;
                hash = hash * 31 + dy;
                return hash;
            }

        }

        private static class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }


            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof TaskC.Point))
                    return false;
                TaskC.Point point = (TaskC.Point) obj;
                return x == point.x && y == point.y;
            }


            public int hashCode() {
                int hash = 23;
                hash = hash * 31 + x;
                hash = hash * 31 + y;
                return hash;
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

