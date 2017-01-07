import java.io.OutputStream;
import java.io.FilenameFilter;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
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
public class ProgressPie {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "progress.*pie.*[.]txt";
            File directory = new File(".");
            File[] candidates = directory.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.matches(regex);
                }
            });
            File toRun = null;
            for (File candidate : candidates) {
                if (toRun == null || candidate.lastModified() > toRun.lastModified())
                    toRun = candidate;
            }
            inputStream = new FileInputStream(toRun);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("progresspie.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        ProgressPie solver = new ProgressPie();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class ProgressPie {
        private static final ProgressPie.Point CENTER = new ProgressPie.Point(50, 50);
        private static final ProgressPie.Point TOP = new ProgressPie.Point(50, 100);
        private static final int RADIUS = 50;
        private static final double EPSILON = 1e-6;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            double percentage = in.nextDouble();
            ProgressPie.Point point = new ProgressPie.Point(in.nextInt(), in.nextInt());

            // Reject if point lies outside circle.
            if (getDistance(CENTER, point) - RADIUS > EPSILON) {
                out.printf("Case #%d: white\n", testNumber);
                return;
            }

            // Accept if point coincides with center.
            if (getDistance(CENTER, point) < EPSILON) {
                out.printf("Case #%d: black\n", testNumber);
                return;
            }

            // Compute angle formed by TOP, CENTER and point.
            double angle = getAngleInRadians(TOP, CENTER, point);
            // Take the complement if angle is obtuse.
            if (point.x < 50) {
                angle = 2 * Math.PI - angle;
            }

            double upperBound = percentage * 2 * Math.PI / 100;
            // Accept if angle does not exceed upperBound.
            if (upperBound - angle >= -EPSILON) {
                out.printf("Case #%d: black\n", testNumber);
            } else {
                out.printf("Case #%d: white\n", testNumber);
            }
        }

        private static double getAngleInRadians(ProgressPie.Point a, ProgressPie.Point o, ProgressPie.Point b) {
            ProgressPie.Vector oa = new ProgressPie.Vector(o, a);
            ProgressPie.Vector ob = new ProgressPie.Vector(o, b);
            return Math.acos(dotProduct(oa, ob) /
                    Math.sqrt(normSquare(oa) * normSquare(ob)));
        }

        private static double normSquare(ProgressPie.Vector v) {
            return v.x * v.x + v.y * v.y;
        }

        private static double dotProduct(ProgressPie.Vector a, ProgressPie.Vector b) {
            return a.x * b.x + a.y * b.y;
        }

        private static double getDistance(ProgressPie.Point a, ProgressPie.Point b) {
            return Math.sqrt(normSquare(new ProgressPie.Vector(a, b)));
        }

        private static class Vector {
            private double x;
            private double y;

            private Vector(ProgressPie.Point a, ProgressPie.Point b) {
                x = b.x - a.x;
                y = b.y - a.y;
            }

        }

        private static class Point {
            private double x;
            private double y;

            private Point(double x, double y) {
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
            reader = new BufferedReader(
                    new InputStreamReader(stream), BUFFER_SIZE);
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}
