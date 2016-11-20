import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
            int[] radii = new int[n];
            int[] heights = new int[n];
            long[] volumes = new long[n];
            for (int i = 0; i < n; ++i) {
                radii[i] = in.nextInt();
                heights[i] = in.nextInt();
                volumes[i] = (long) radii[i] * radii[i] * heights[i];
            }

            int[] ranks = new int[n];
            compress(ranks, volumes, radii, heights);

            SegmentTree tree = new SegmentTree(volumes.length);
            long result = Long.MIN_VALUE;
            for (int rank : ranks) {
                long maxArea = tree.query(0, rank - 1) + volumes[rank];
                tree.update(rank, maxArea);
                result = Math.max(result, maxArea);
            }

            out.println(Math.PI * result);
        }

        private void compress(int[] ranks, long[] volumes, int[] radii, int[] heights) {
            Arrays.sort(volumes);
            for (int i = 0; i < ranks.length; ++i) {
                long area = (long) radii[i] * radii[i] * heights[i];
                ranks[i] = Arrays.binarySearch(volumes, area);
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

    static class SegmentTree {
        private static final int SPAN_FACTOR = 4;
        private long[] tree;
        private int range;

        public SegmentTree(int range) {
            this.range = range;
            tree = new long[range * SPAN_FACTOR];
        }

        public void update(int position, long value) {
            updateImpl(0, 0, range - 1, position, value);
        }

        public long query(int left, int right) {
            return queryImpl(0, 0, range - 1, left, right);
        }

        private void updateImpl(int index, int begin, int end, int position, long value) {
            if (position < begin || position > end) {
                return;
            }
            if (begin <= position && position <= end) {
                tree[index] = Math.max(tree[index], value);
                if (begin == end) {
                    return;
                }
            }

            int mid = begin + (end - begin) / 2;
            updateImpl(2 * index + 1, begin, mid, position, value);
            updateImpl(2 * index + 2, mid + 1, end, position, value);
        }

        private long queryImpl(int index, int begin, int end, int left, int right) {
            if (end < left || right < begin)
                return 0;  // All values are guaranteed to be positive.
            if (left <= begin && end <= right)
                return tree[index];
            int mid = begin + (end - begin) / 2;
            long leftHalf = queryImpl(2 * index + 1, begin, mid, left, right);
            long rightHalf = queryImpl(2 * index + 2, mid + 1, end, left, right);
            return Math.max(leftHalf, rightHalf);
        }
    }
}
