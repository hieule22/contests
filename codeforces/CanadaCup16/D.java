import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractCollection;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;
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
            Team first = new Team(in.nextLong(), in.nextLong());
            Team[] others = new Team[n - 1];
            for (int i = 0; i < n - 1; ++i)
                others[i] = new Team(in.nextLong(), in.nextLong());

            // Sort from high to low according to balloon count.
            Arrays.sort(others, (o1, o2) -> {
                if (o2.balloonCount > o1.balloonCount)
                    return 1;
                else if (o2.balloonCount < o1.balloonCount)
                    return -1;
                return 0;
            });

            int rank = findRank(others, first); // How many teams are better than first initially.
            int current = 0;
            int result = rank + 1;
            PriorityQueue<Team> queue = new PriorityQueue<>((Comparator<Team>) (o1, o2) -> {
                long d1 = o1.weight - o1.balloonCount;
                long d2 = o2.weight - o2.balloonCount;
                if (d1 < d2)
                    return -1;
                if (d1 > d2)
                    return 1;
                return 0;
            });

            int eliminations = 0;
            while (first.balloonCount >= 0) {
                for (int i = current; i < rank; ++i) {
                    queue.add(others[i]);
                }

                if (queue.isEmpty()) break;
                Team loser = queue.poll();
                long offset = loser.weight - loser.balloonCount + 1;
                if (first.balloonCount < offset) break;

                first.balloonCount -= offset;
                eliminations++;
                current = rank;
                rank = findRank(others, first);
                result = Math.min(result, rank + 1 - eliminations);
            }

            out.println(result);
        }

        private int findRank(Team[] teams, Team target) {
            if (target.balloonCount >= teams[0].balloonCount)
                return 0;
            int low = 0, high = teams.length - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (teams[mid].balloonCount <= target.balloonCount)
                    high = mid - 1;
                else if (teams[mid].balloonCount > target.balloonCount)
                    low = mid;
            }
            return low + 1;
        }

        private class Team {
            private long weight;
            private long balloonCount;

            public Team(long b, long w) {
                this.balloonCount = b;
                this.weight = w;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
