import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Queue;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class E {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            int[] computers = new int[n];
            Map<Integer, Integer> computerFrequency = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                int power = in.nextInt();
                computers[i] = power;
                if (!computerFrequency.containsKey(power))
                    computerFrequency.put(power, 0);
                computerFrequency.put(power, computerFrequency.get(power) + 1);
            }

            Machine[] sockets = new Machine[m];
            for (int i = 0; i < m; ++i) {
                int power = in.nextInt();
                sockets[i] = new Machine(i, power);
            }

            Arrays.sort(sockets, (o1, o2) -> o1.power - o2.power);

            int[] adapterCounts = new int[sockets.length];
            Map<Integer, Queue<Integer>> socketForPower = new HashMap<>();
            int u = 0;
            for (Machine socket : sockets) {
                int adapterCount = 0;
                int power = socket.power;
                while (power > 0) {
                    if (computerFrequency.containsKey(power) && computerFrequency.get(power) > 0) {
                        computerFrequency.put(power, computerFrequency.get(power) - 1);
                        adapterCounts[socket.index] = adapterCount;
                        u += adapterCount;
                        if (!socketForPower.containsKey(power))
                            socketForPower.put(power, new LinkedList<>());
                        socketForPower.get(power).add(socket.index + 1);
                        break;
                    }
                    if (power == 1) {
                        break;
                    }
                    adapterCount++;
                    power = (power + 1) / 2;
                }
            }

            int[] socketForComputer = new int[computers.length];
            int c = 0;
            for (int i = 0; i < computers.length; ++i) {
                int power = computers[i];
                if (socketForPower.containsKey(power) && !socketForPower.get(power).isEmpty()) {
                    c++;
                    int socket = socketForPower.get(power).poll();
                    socketForComputer[i] = socket;
                }
            }

            out.println(c + " " + u);
            for (int a : adapterCounts)
                out.print(a + " ");
            out.println();
            for (int socket : socketForComputer)
                out.print(socket + " ");
            out.println();
        }

        private class Machine {
            private int index;
            private int power;

            public Machine(int index, int power) {
                this.index = index;
                this.power = power;
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

    }
}
