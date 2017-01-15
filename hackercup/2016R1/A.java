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
public class A {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "coding.*contest.*creation.*[.]txt";
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
            outputStream = new FileOutputStream("codingcontestcreation.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        CodingContestCreation solver = new CodingContestCreation();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class CodingContestCreation {
        private static final int GAP = 10;
        private static final int MIN_RATING = 1;
        private static final int MAX_RATING = 100;
        private static final int SEQLENGTH = 4;
        private static final int INF = (int) 1e9;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] problems = new int[n];
            for (int i = 0; i < n; ++i)
                problems[i] = in.nextInt();

            // memo[i][j] gives minimum additions such that problem i has rank j in its contest.
            int[][] memo = new int[n][SEQLENGTH + 1];
            // Base case.
            for (int i = 1; i <= SEQLENGTH; ++i) {
                if (problems[0] - i + 1 >= MIN_RATING
                        && problems[0] + SEQLENGTH - i <= MAX_RATING)
                    memo[0][i] = i - 1;
                else
                    memo[0][i] = INF;
            }

            for (int i = 1; i < n; ++i) {
                for (int j = 1; j <= SEQLENGTH; ++j) {
                    memo[i][j] = INF;
                    if (problems[i] - j + 1 < MIN_RATING
                            || problems[i] + SEQLENGTH - j > MAX_RATING)
                        continue;

                    for (int k = 1; k <= SEQLENGTH; ++k)
                        memo[i][j] = Math.min(memo[i][j],
                                memo[i - 1][k] + SEQLENGTH - k + j - 1);
                    int delta = problems[i] - problems[i - 1];
                    for (int k = 1; k < j; ++k) {
                        if (delta >= j - k && delta <= GAP * (j - k))
                            memo[i][j] = Math.min(memo[i][j],
                                    memo[i - 1][k] + j - k - 1);
                    }
                }
            }

            int result = INF;
            for (int i = 1; i <= SEQLENGTH; ++i)
                result = Math.min(result, memo[n - 1][i] + SEQLENGTH - i);
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
