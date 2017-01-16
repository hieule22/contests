import java.io.OutputStream;
import java.io.FilenameFilter;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class B {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "fighting.*the.*zombies.*[.]txt";
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
            outputStream = new FileOutputStream("fightingthezombies.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        FightingTheZombies solver = new FightingTheZombies();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class FightingTheZombies {
        private static final long[] dx = {1, 1, -1, -1};
        private static final long[] dy = {1, -1, -1, 1};
        private long[] x;
        private long[] y;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nZombies = in.nextInt();
            long range = in.nextLong();
            x = new long[nZombies];
            y = new long[nZombies];
            for (int i = 0; i < nZombies; ++i) {
                x[i] = in.nextLong();
                y[i] = in.nextLong();
            }

            List<Set<Integer>> groups = new ArrayList<>();
            for (int pivot = 0; pivot < nZombies; ++pivot) {
                for (int dir = 0; dir < dx.length; ++dir) {
                    Set<Integer> group = new HashSet<>();
                    for (int i = 0; i < nZombies; ++i) {
                        if (isReachable(pivot, i, dir, range))
                            group.add(i);
                    }
                    groups.add(group);
                }
            }

            int result = 0;
            for (Set<Integer> first : groups) {
                for (Set<Integer> second : groups) {
                    result = Math.max(result, union(first, second).size());
                }
            }

            out.printf("Case #%d: %d\n", testNumber, result);
        }

        private static Set<Integer> union(Set<Integer> first, Set<Integer> second) {
            Set<Integer> result = new HashSet<>(first);
            result.addAll(second);
            return result;
        }

        private boolean isReachable(int pivot, int target, int dir, long range) {
            return isBetween(x[pivot], x[pivot] + dx[dir] * range, x[target]) &&
                    isBetween(y[pivot], y[pivot] + dy[dir] * range, y[target]);
        }

        private static boolean isBetween(long low, long high, long target) {
            if (low > high) {
                long temp = low;
                low = high;
                high = temp;
            }

            return low <= target && target <= high;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
