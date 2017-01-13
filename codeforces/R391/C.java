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
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class C {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        private static final long MOD = (long) 1e9 + 7;
        private static final int MAX_TYPES = (int) 1e6;
        private static long[] factorials;

        public TaskC() {
            factorials = new long[MAX_TYPES + 1];
            factorials[0] = 1;
            for (int i = 1; i < factorials.length; ++i) {
                factorials[i] = (factorials[i - 1] * i) % MOD;
            }
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();  // Number of gyms.
            int m = in.nextInt();  // Number of pokemon types.

            Map<Integer, Map<Integer, Integer>> typeMap = new HashMap<>();

            for (int i = 0; i < n; ++i) {
                int g = in.nextInt();  // Number of pokemons in this gym.
                Map<Integer, Integer> pokemonCounts = new HashMap<>();
                for (int j = 0; j < g; ++j) {
                    int type = in.nextInt();
                    if (!pokemonCounts.containsKey(type))
                        pokemonCounts.put(type, 1);
                    else
                        pokemonCounts.put(type, pokemonCounts.get(type) + 1);
                }

                for (Map.Entry<Integer, Integer> entry : pokemonCounts.entrySet()) {
                    int type = entry.getKey();
                    int count = entry.getValue();
                    if (!typeMap.containsKey(type))
                        typeMap.put(type, new HashMap<>());
                    typeMap.get(type).put(i, count);
                }
            }

            Map<Map<Integer, Integer>, List<Integer>> orbits = new HashMap<>();
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : typeMap.entrySet()) {
                int type = entry.getKey();
                Map<Integer, Integer> count = entry.getValue();
                if (!orbits.containsKey(count))
                    orbits.put(count, new ArrayList<>());
                orbits.get(count).add(type);
            }

            long result = 1;
            int residue = m;
            for (Map.Entry<Map<Integer, Integer>, List<Integer>> entry : orbits.entrySet()) {
                List<Integer> orbit = entry.getValue();
                result = (result * factorials[orbit.size()]) % MOD;
                residue -= orbit.size();
            }
            result = (result * factorials[residue]) % MOD;
            out.println(result);
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
