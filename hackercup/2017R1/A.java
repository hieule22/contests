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
public class A {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "pie.*progress.*[.]txt";
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
            outputStream = new FileOutputStream("pieprogress.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        PieProgress solver = new PieProgress();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class PieProgress {
        private static final int INF = (int) 1e9;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nDays = in.nextInt();
            int nPiesPerDay = in.nextInt();

            // costs[i][j] gives minimum cost of purchasing j pies on ith day (all 1-indexed).
            int[][] costs = new int[nDays + 1][nPiesPerDay + 1];
            for (int i = 1; i <= nDays; ++i) {
                int[] temp = new int[nPiesPerDay];
                for (int j = 0; j < nPiesPerDay; ++j)
                    temp[j] = in.nextInt();
                Arrays.sort(temp);
                int runningTotal = 0;
                for (int j = 1; j <= nPiesPerDay; ++j) {
                    runningTotal += temp[j - 1];
                    costs[i][j] = runningTotal + j * j;
                }
            }

            // memo[i][j] gives minimum cost of purchasing j pies up to ith day (all 1-indexed).
            int[][] memo = new int[nDays + 1][nDays + 1];
            for (int[] row : memo)
                Arrays.fill(row, INF);
            // Base case.
            int nPiesMax = Math.min(nDays, nPiesPerDay);
            for (int i = 1; i <= nPiesMax; ++i)
                memo[1][i] = costs[1][i];

            int result = memo[1][nDays];
            for (int i = 2; i <= nDays; ++i) {
                for (int j = i; j <= nDays; ++j) {
                    for (int k = i - 1; k <= j; ++k) {
                        if (j - k > nPiesPerDay) continue;
                        int currentCost = costs[i][j - k];
                        memo[i][j] = Math.min(memo[i][j], memo[i - 1][k] + currentCost);
                    }
                }
                result = Math.min(result, memo[i][nDays]);
            }

            out.printf("Case #%d: %d\n", testNumber, result);
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
