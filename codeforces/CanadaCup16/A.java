import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class A
{
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        private int n;
        private char[] bumpers;
        private Map<Integer, List<Integer>> nextMoves;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            bumpers = in.next().toCharArray();
            nextMoves = new HashMap<>();
            for (int i = 0; i < bumpers.length; ++i) {
                int nextMove = i;
                if (bumpers[i] == '<')
                    nextMove--;
                else
                    nextMove++;
                if (!nextMoves.containsKey(nextMove))
                    nextMoves.put(nextMove, new ArrayList<>());
                nextMoves.get(nextMove).add(i);
            }

            List<Integer> forbidden = new ArrayList<>();
            forbidden.add(-1);
            forbidden.add(n);
            boolean[] dangers = new boolean[n];
            while (!forbidden.isEmpty()) {
                List<Integer> frontier = new ArrayList<>();
                for (int pos : forbidden) {
                    if (!nextMoves.containsKey(pos))
                        continue;
                    for (int move : nextMoves.get(pos)) {
                        if (!dangers[move]) {
                            dangers[move] = true;
                            frontier.add(move);
                        }
                    }
                }
                forbidden = frontier;
            }

            int res = 0;
            for (boolean danger : dangers) {
                if (danger) res++;
            }

            out.println(res);
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

    }
}
