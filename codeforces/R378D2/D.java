import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
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
            Map<Side, Pair<Integer, Integer>> maxDimensions = new HashMap<>();

            int n = in.nextInt();
            int maxRadius = Integer.MIN_VALUE;
            int maxIndex = -1;
            int[] maxIndices = {-1, -1};

            for (int i = 0; i < n; ++i) {
                int[] sides = new int[3];
                int sum = 0;
                int minSide = Integer.MAX_VALUE;
                for (int j = 0; j < 3; ++j) {
                    sides[j] = in.nextInt();
                    sum += sides[j];
                    minSide = Math.min(minSide, sides[j]);
                }

                // Stand alone parallelepiped.
                if (minSide > maxRadius) {
                    maxRadius = minSide;
                    maxIndex = i;
                    maxIndices = new int[]{-1, -1};
                }

                for (int j = 0; j < 3; ++j) {
                    for (int k = j + 1; k < 3; ++k) {
                        Side face = new Side(sides[j], sides[k]);
                        int remainSide = sum - sides[j] - sides[k];

                        if (maxDimensions.containsKey(face)) {
                            Pair<Integer, Integer> maxRemainSide = maxDimensions.get(face);
                            int tempRadius = Math.min(maxRemainSide.second + remainSide,
                                    Math.min(sides[j], sides[k]));
                            if (tempRadius > maxRadius) {
                                maxRadius = tempRadius;
                                maxIndex = -1;
                                maxIndices = new int[]{maxRemainSide.first, i};
                            }

                            if (maxRemainSide.second < remainSide) {
                                maxRemainSide.first = i;
                                maxRemainSide.second = remainSide;
                            }
                        } else {
                            maxDimensions.put(face, new Pair<>(i, remainSide));
                        }
                    }
                }
            }

            if (maxIndex != -1) {
                out.println(1);
                out.println(maxIndex + 1);
            } else {
                out.println(2);
                out.println((maxIndices[0] + 1) + " " + (maxIndices[1] + 1));
            }
        }

        private class Side {
            int first;
            int second;

            public Side(int first, int second) {
                if (first > second) {
                    this.first = second;
                    this.second = first;
                } else {
                    this.first = first;
                    this.second = second;
                }
            }


            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof Side))
                    return false;
                return first == ((Side) obj).first && second == ((Side) obj).second;
            }


            public int hashCode() {
                int result = 23 + first;
                return 31 * result + second;
            }

        }

    }

    static class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
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
