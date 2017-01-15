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
public class C {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "yachtzee.*[.]txt";
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
            outputStream = new FileOutputStream("yachtzee.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Yachtzee solver = new Yachtzee();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class Yachtzee {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nSteps = in.nextInt();
            int lowerBound = in.nextInt(), upperBound = in.nextInt(), range = upperBound - lowerBound;
            long[] prefixCosts = new long[nSteps];
            prefixCosts[0] = in.nextInt();
            for (int i = 1; i < nSteps; ++i)
                prefixCosts[i] = prefixCosts[i - 1] + in.nextInt();
            long totalCost = prefixCosts[nSteps - 1];

            double result = 0;
            long low = lowerBound / totalCost, high = upperBound / totalCost;
            for (int i = 0; i < nSteps; ++i) {
                long previous = (i == 0) ? 0 : prefixCosts[i - 1];
                // First segment.
                {
                    long floor = normalize(low * totalCost + previous, lowerBound, upperBound);
                    long ceiling = normalize(low * totalCost + prefixCosts[i], lowerBound, upperBound);
                    long from = floor - (low * totalCost + previous);
                    long to = ceiling - (low * totalCost + previous);
                    result += (from + to) / 2.0 * (to - from) / range;
                }
                // Last segment.
                if (high > low) {
                    long floor = normalize(high * totalCost + previous, lowerBound, upperBound);
                    long ceiling = normalize(high * totalCost + prefixCosts[i], lowerBound, upperBound);
                    long from = floor - (high * totalCost + previous);
                    long to = ceiling - (high * totalCost + previous);
                    result += (from + to) / 2.0 * (to - from) / range;
                }
                // Other intervening segments.
                if (high - low - 1 > 0) {
                    long from = 0, to = prefixCosts[i] - previous;
                    if (high - low - 1 > 0) {
                        result += (high - low - 1) * (from + to) / 2.0 * (to - from) / range;
                    }
                }
            }

            out.printf("Case #%d: %.9f\n", testNumber, result);
        }

        private static long normalize(long amount, int lowerBound, int upperBound) {
            if (amount > upperBound)
                return upperBound;
            if (amount < lowerBound)
                return lowerBound;
            return amount;
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
