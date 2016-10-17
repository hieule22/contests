import java.io.OutputStream;
import java.io.IOException;
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
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        private int n;
        private int m;
        private int[] exams;
        private int[] preps;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt(); // Number of days
            m = in.nextInt(); // Number of subjects

            exams = new int[n];
            for (int i = 0; i < exams.length; ++i)
                exams[i] = in.nextInt();

            preps = new int[m + 1];
            for (int i = 1; i < preps.length; ++i)
                preps[i] = in.nextInt();

            if (!check(exams.length - 1)) {
                out.println(-1);
                return;
            }

            int low = 0, high = exams.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (check(mid))
                    high = mid;
                else
                    low = mid + 1;
            }

            out.println(low + 1);
        }

        private boolean check(int duration) {
            boolean[] chosenDays = new boolean[n]; // What day is chosen for an exam.
            boolean[] takenExams = new boolean[m + 1];  // What exam has been taken.
            int last = Integer.MIN_VALUE;

            for (int i = duration; i >= 0; --i) {
                int exam = exams[i];
                if (exam != 0 && !takenExams[exam]) {
                    takenExams[exam] = true;
                    chosenDays[i] = true;
                    last = Math.max(last, i);
                }
            }

            for (int i = 1; i < takenExams.length; ++i) {
                if (!takenExams[i]) return false;
            }

            long budget = 0;
            for (int i = 0; i <= last; ++i) {
                int exam = exams[i];
                if (chosenDays[i]) {
                    if (budget < preps[exam])
                        return false;
                    budget -= preps[exam];
                } else {
                    budget++;
                }
            }

            return true;
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
