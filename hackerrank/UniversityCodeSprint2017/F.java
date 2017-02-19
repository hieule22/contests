import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class F {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        QueryingSumsOnStrings solver = new QueryingSumsOnStrings();
        solver.solve(1, in, out);
        out.close();
    }

    static class QueryingSumsOnStrings {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int sLength = in.nextInt();
            int nPairs = in.nextInt();
            int nQueries = in.nextInt();
            in.nextInt();

            String s = in.next();
            int[] suffixArr = new SuffixArray(s).getSuffixArray();

            int[][] bounds = new int[nPairs][2];
            for (int i = 0; i < nPairs; ++i) {
                bounds[i][0] = in.nextInt();
                bounds[i][1] = in.nextInt();
            }

            for (int i = 0; i < nQueries; ++i) {
                String w = in.next();
                int a = in.nextInt(), b = in.nextInt();
                int result = 0;
                for (int j = a; j <= b; ++j) {
                    int start = bounds[j][0], end = bounds[j][1];
                    StringPiece substring = new StringPiece(w, start, end);

                    // Find the smallest suffix of s that contains w[start..end].
                    int minIndex = Integer.MAX_VALUE;
                    {
                        int low = 0, high = suffixArr.length - 1;
                        while (low < high) {
                            int mid = low + (high - low) / 2;
                            StringPiece suffix = new StringPiece(s, suffixArr[mid], sLength - 1);
                            if (suffix.compareTo(substring) >= 0) {
                                high = mid;
                            } else {
                                low = mid + 1;
                            }
                        }
                        if (new StringPiece(s, suffixArr[low], sLength - 1).contains(substring)) {
                            minIndex = low;
                        }
                    }

                    // Find the greatest suffix of s that contains w[start..end].
                    int maxIndex = Integer.MIN_VALUE;
                    {
                        int low = 0, high = suffixArr.length - 1;
                        while (low < high) {
                            int mid = low + (high - low + 1) / 2;
                            StringPiece suffix = new StringPiece(s, suffixArr[mid], sLength - 1);
                            if (suffix.contains(substring) || suffix.compareTo(substring) < 0) {
                                low = mid;
                            } else {
                                high = mid - 1;
                            }
                        }
                        if (new StringPiece(s, suffixArr[low], sLength - 1).contains(substring)) {
                            maxIndex = low;
                        }
                    }

                    if (minIndex <= maxIndex) {
                        result += maxIndex - minIndex + 1;
                    }
                }

                out.println(result);
            }
        }

        private class SuffixArray {
            private Integer[] suffixArr;
            private int[][] ranks;

            private SuffixArray(CharSequence str) {
                final int length = str.length();
                // maxPower gives the smallest power of 2 greater than or equal to length.
                final int maxPower = (int) Math.ceil(Math.log(length) / Math.log(2));
                suffixArr = new Integer[length];
                ranks = new int[maxPower + 1][length];
                for (int i = 0; i < length; ++i) {
                    suffixArr[i] = i;
                    ranks[0][i] = str.charAt(i);
                }

                for (int i = 1; i <= maxPower; ++i) {
                    final int currentPower = i;
                    // Custom comparator to sort suffix arrays.
                    Comparator<Integer> comparator = (a, b) -> {
                        if (ranks[currentPower - 1][a] == ranks[currentPower - 1][b]) {
                            a += (1 << (currentPower - 1));
                            b += (1 << (currentPower - 1));
                        }

                        if (a >= length)
                            return -1;
                        if (b >= length)
                            return 1;
                        return ranks[currentPower - 1][a] - ranks[currentPower - 1][b];
                    };
                    Arrays.sort(suffixArr, comparator);

                    // Update ranks.
                    int currentRank = ranks[currentPower][suffixArr[0]] = 0;
                    for (int j = 1; j < length; ++j) {
                        if (comparator.compare(suffixArr[j], suffixArr[j - 1]) != 0)
                            ++currentRank;
                        ranks[currentPower][suffixArr[j]] = currentRank;
                    }
                }
            }

            private int[] getSuffixArray() {
                return toPrimitive(suffixArr);
            }

            private int[] toPrimitive(Integer[] arr) {
                int[] result = new int[arr.length];
                for (int i = 0; i < arr.length; ++i)
                    result[i] = arr[i];
                return result;
            }

        }

        private class StringPiece implements Comparable<StringPiece> {
            private String context;
            private int low;
            private int high;

            private StringPiece(String context, int low, int high) {
                this.context = context;
                this.low = low;
                this.high = high;
            }

            private char charAt(int index) {
                return context.charAt(low + index);
            }

            private int length() {
                return high - low + 1;
            }


            public int compareTo(StringPiece other) {
                int range = Math.min(this.length(), other.length());
                for (int i = 0; i < range; ++i) {
                    if (this.charAt(i) != other.charAt(i)) {
                        return Character.compare(this.charAt(i), other.charAt(i));
                    }
                }
                return Integer.compare(this.length(), other.length());
            }

            private boolean contains(StringPiece other) {
                if (other.length() > this.length()) {
                    return false;
                }

                for (int i = 0; i < other.length(); ++i) {
                    if (this.charAt(i) != other.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }

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
