import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.InputMismatchException;
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
public class D {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            Piece king = new Piece('K', in.nextInt(), in.nextInt());
            Piece[] pieces = new Piece[n];
            for (int i = 0; i < n; ++i)
                pieces[i] = new Piece(in.nextChar(), in.nextInt(), in.nextInt());

            int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
            int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

            for (int i = 0; i < dx.length; ++i) {
                Piece nearest = null;
                long dist = Long.MAX_VALUE;
                for (Piece piece : pieces) {
                    if (coincide(king, piece, dx[i], dy[i])) {
                        long temp = (long) Math.abs(piece.x - king.x) + Math.abs(piece.y - king.y);
                        if (temp < dist) {
                            nearest = piece;
                            dist = temp;
                        }
                    }
                }
                if (nearest != null) {
                    if (canAttack(dx[i], dy[i], nearest.type)) {
                        out.println("YES");
                        return;
                    }
                }
            }
            out.println("NO");
        }

        private boolean coincide(Piece a, Piece b, int dx, int dy) {
            if (dx == 0) {
                if (a.x != b.x || a.y == b.y) return false;
                if (dy > 0) return b.y > a.y;
                return b.y < a.y;
            } else if (dx == 1) {
                if (a.x >= b.x) return false;
                if (dy == 0) return a.y == b.y;
                if (dy == 1) {
                    return b.x - a.x == b.y - a.y;
                } else {
                    return b.x - a.x == a.y - b.y;
                }
            } else if (dx == -1) {
                if (a.x <= b.x) return false;
                if (dy == 0) return a.y == b.y;
                if (dy == 1)
                    return a.x - b.x == b.y - a.y;
                else
                    return a.x - b.x == a.y - b.y;
            }
            return false;
        }

        private boolean canAttack(int dx, int dy, char type) {
            if (type == 'B') {
                return dx != 0 && dy != 0;
            }
            if (type == 'R') {
                return dx == 0 || dy == 0;
            }
            return true;
        }

        private class Piece {
            private int x;
            private int y;
            private char type;

            public Piece(char type, int x, int y) {
                this.type = type;
                this.x = x;
                this.y = y;
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

        public char nextChar() {
            String s = next();
            if (s.length() != 1) throw new InputMismatchException();
            return s.charAt(0);
        }

    }
}
