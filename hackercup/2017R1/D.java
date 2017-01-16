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
public class D {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "beach.*umbrellas.*[.]txt";
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
            outputStream = new FileOutputStream("beachumbrellas.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        BeachUmbrellas solver = new BeachUmbrellas();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class BeachUmbrellas {
        private static final long MOD = (long) 1e9 + 7;
        private static final int MAXN = 2000;
        private long[] factorials;

        public BeachUmbrellas() {
            factorials = new long[MAXN * 2];
            factorials[0] = 1;
            for (int i = 1; i < factorials.length; ++i)
                factorials[i] = (factorials[i - 1] * i) % MOD;
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nGroups = in.nextInt();
            int nSpots = in.nextInt();
            int[] radius = new int[nGroups];
            int sumRadius = 0;
            for (int i = 0; i < nGroups; ++i) {
                radius[i] = in.nextInt();
                sumRadius += radius[i];
            }

            if (nGroups == 1) {
                out.printf("Case #%d: %d\n", testNumber, nSpots);
                return;
            }

            int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
            for (int left = 0; left < nGroups; ++left) {
                for (int right = left + 1; right < nGroups; ++right) {
                    int sum = sumRadius * 2 - (radius[left] + radius[right]);
                    int residue = (nSpots - 1) - sum;
                    if (residue + nGroups >= nGroups) {
                        low = Math.min(low, residue + 1);
                        high = Math.max(high, residue + nGroups);
                    }
                }
            }

            long[] cache = null;
            if (high >= low) {
                cache = new long[high - low + 1];
                cache[0] = low;
                for (int i = 1; i < cache.length; ++i) {
                    cache[i] = (cache[i - 1] * (low + i)) % MOD;
                }
            }

            long result = 0;
            for (int left = 0; left < nGroups; ++left) {
                for (int right = left + 1; right < nGroups; ++right) {
                    int sum = sumRadius * 2 - (radius[left] + radius[right]);
                    int residue = (nSpots - 1) - sum;
                    long temp = choose(residue + nGroups, nGroups, cache, low);
                    result = (result + temp * 2 * factorials[nGroups - 2]) % MOD;
                }
            }
            out.printf("Case #%d: %d\n", testNumber, result);
        }

        private long choose(int n, int k, long[] cache, int offset) {
            if (n < k) return 0;
            int begin = (n - k + 1) - offset;
            int end = n - offset;
            long result = (cache[end] * modInverse(cache[begin], MOD)) % MOD;
            result = (result * (n - k + 1)) % MOD;
            return (result * modInverse(factorials[k], MOD)) % MOD;
        }

        private static long modInverse(long a, long mod) {
            return modPow(a, mod - 2);
        }

        private static long modPow(long a, long p) {
            if (p == 0) return 1;
            long result = modPow(a, p / 2);
            result = (result * result) % MOD;
            if (p % 2 == 1)
                result = (result * a) % MOD;
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
