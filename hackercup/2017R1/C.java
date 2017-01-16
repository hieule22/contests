import java.io.OutputStream;
import java.io.FilenameFilter;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
public class C {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "manic.*moving.*[.]txt";
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
            outputStream = new FileOutputStream("manicmoving.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        ManicMoving solver = new ManicMoving();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class ManicMoving {
        private static final int INF = (int) 1e9;
        private static final int START = 0;
        private static final int PICKUP = 0;
        private static final int DROPOFF = 1;
        private int[][] dist;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nTowns = in.nextInt();
            int nRoads = in.nextInt();
            int nFamilies = in.nextInt();

            dist = new int[nTowns][nTowns];
            for (int[] row : dist)
                Arrays.fill(row, INF);
            for (int i = 0; i < nRoads; ++i) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                int distance = in.nextInt();
                dist[a][b] = dist[b][a] = Math.min(dist[a][b], distance);
            }
            for (int i = 0; i < nTowns; ++i)
                dist[i][i] = 0;

            // F-W to find all-pair shortest paths.
            for (int k = 0; k < nTowns; ++k) {
                for (int i = 0; i < nTowns; ++i) {
                    for (int j = 0; j < nTowns; ++j)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }

            int[] starts = new int[nFamilies], ends = new int[nFamilies];
            int before = START;
            boolean valid = true;
            for (int i = 0; i < nFamilies; ++i) {
                starts[i] = in.nextInt() - 1;
                ends[i] = in.nextInt() - 1;
                // Checks if target towns are connected.
                if (findDistance(before, starts[i]) >= INF || findDistance(starts[i], ends[i]) >= INF) {
                    valid = false;
                }
                before = starts[i];
            }

            if (!valid) {
                out.printf("Case #%d: -1\n", testNumber);
                return;
            }

            // memo[i][j][0] gives minimum cost of picking up i to end up with j loads (1 <= j <= 2).
            // memo[i][j][1] gives minimum cost of dropping off i to end up with j loads (0 <= j <= 1).
            int[][][] memo = new int[nFamilies][3][2];
            for (int[][] r : memo) for (int[] rr : r) Arrays.fill(rr, INF);
            // Base cases. NOTE: Pick up 0 with 2 loads is impossible.
            // Pick up 0 with 1 load.
            memo[0][1][PICKUP] = findDistance(START, starts[0]);
            // Drop off 0 with 0 load.
            memo[0][0][DROPOFF] = findDistance(START, starts[0], ends[0]);
            // Drop off 0 with 1 load: Pick up 0, pick up 1 then drop off 0.
            if (nFamilies > 1) {
                memo[0][1][DROPOFF] = findDistance(START, starts[0], starts[1], ends[0]);
            }

            for (int i = 1; i < nFamilies; ++i) {
                // Pick up i with 1 load: Must drop off i - 1 with 0 load before pick up i.
                memo[i][1][PICKUP] = memo[i - 1][0][DROPOFF] + findDistance(ends[i - 1], starts[i]);

                // Pick up i with 2 loads: Must pick up i before drop off i - 1.
                // 1) Pick up i - 1 with 1 load, then pick up i.
                // 2) Pick up i - 1 with 2 loads, drop off i - 2, then pick up i.
                memo[i][2][PICKUP] = memo[i - 1][1][PICKUP] + findDistance(starts[i - 1], starts[i]);
                if (i > 1) {
                    memo[i][2][PICKUP] = Math.min(memo[i][2][PICKUP],
                            memo[i - 1][2][PICKUP] + findDistance(starts[i - 1], ends[i - 2], starts[i]));
                }

                // Drop off i with 0 load: Must drop off i before pick up i + 1.
                // 1) Pick up i with 1 load, drop off i.
                // 2) Pick up i with 2 loads, drop off i - 1, drop off i.
                memo[i][0][DROPOFF] = Math.min(memo[i][1][PICKUP] + findDistance(starts[i], ends[i]),
                        memo[i][2][PICKUP] + findDistance(starts[i], ends[i - 1], ends[i]));

                // Drop off i with 1 load: Must pick up i + 1 before drop off i.
                // 1) Pick up i with 1 load, pick up i + 1 then drop off i.
                // 2) Pick up i with 2 loads, drop off i - 1, pick up i + 1 then drop off i.
                if (i < nFamilies - 1) {
                    memo[i][1][DROPOFF] = Math.min(memo[i][1][PICKUP] + findDistance(starts[i], starts[i + 1], ends[i]),
                            memo[i][2][PICKUP] + findDistance(starts[i], ends[i - 1], starts[i + 1], ends[i]));
                }
            }

            // Final result equals drop off (n - 1) with 0 load.
            out.printf("Case #%d: %d\n", testNumber, memo[nFamilies - 1][0][DROPOFF]);
        }

        private int findDistance(int... stops) {
            int result = 0;
            for (int i = 1; i < stops.length; ++i)
                result += dist[stops[i - 1]][stops[i]];
            return result;
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

    }
}
