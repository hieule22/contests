import java.io.OutputStream;
import java.io.FilenameFilter;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Collections;
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
            final String regex = "laundro.*.*matt.*[.]txt";
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
            outputStream = new FileOutputStream("laundro,matt.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        LaundroMatt solver = new LaundroMatt();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class LaundroMatt {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nLoads = in.nextInt();
            int nWashers = in.nextInt();
            int nDryers = in.nextInt();
            int dryTime = in.nextInt();
            int[] washTimes = new int[nWashers];

            int minWashTime = Integer.MAX_VALUE;
            for (int i = 0; i < nWashers; ++i) {
                washTimes[i] = in.nextInt();
                minWashTime = Math.min(minWashTime, washTimes[i]);
            }

            // Find earliest time to complete washing all loads.
            long low = 0, high = (long) minWashTime * nLoads;
            while (low < high) {
                long mid = low + (high - low) / 2;
                if (checkWashers(mid, washTimes, nLoads))
                    high = mid;
                else
                    low = mid + 1;
            }

            List<Long> washEndtimes = new ArrayList<>();
            int residue = nLoads;
            for (int i = 0; i < nWashers && residue > 0; ++i) {
                long nWashes = Math.min(low / washTimes[i], residue);
                long endtime = washTimes[i];
                for (int j = 0; j < nWashes; ++j) {
                    washEndtimes.add(endtime);
                    endtime += washTimes[i];
                }
                residue -= nWashes;
            }
            Collections.sort(washEndtimes);

            if (nDryers >= nLoads) {
                long result = washEndtimes.get(washEndtimes.size() - 1) + dryTime;
                out.printf("Case #%d: %d\n", testNumber, result);
                return;
            }

            Queue<Long> nextDryers = new LinkedList<>();
            for (int i = 0; i < nDryers; ++i)
                nextDryers.add((long) 0);

            long result = 0;
            for (long endtime : washEndtimes) {
                long nextDryer = Math.max(nextDryers.poll(), endtime);
                result = nextDryer + dryTime;
                nextDryers.add(result);
            }
            out.printf("Case #%d: %d\n", testNumber, result);
        }

        private static boolean checkWashers(long upperbound, int[] washTimes, int nLoads) {
            long maxLoads = 0;
            for (int washTime : washTimes)
                maxLoads += upperbound / washTime;
            return maxLoads >= nLoads;
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
