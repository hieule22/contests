import java.util.*;
import java.io.*;

public class Hyperspace {
  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Solution solver = new Solution(in, out);
    solver.solve(0);
    out.close();
  }
}

class Solution {
  private final InputReader in;
  private final PrintWriter out;
  private long[][] vector;
  private int n, m;

  public static final long MIN = (long)-1e9;
  public static final long MAX = (long)1e9;

  public Solution(InputReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
  }

  public void solve(int testNumber) {
    n = in.nextInt();
    m = in.nextInt();
    vector = new long[n][m];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j)
        vector[i][j] = in.nextInt();
    }
    
    for (int i = 0; i < m; ++i) {
      long result = findMinimum(i);
      out.print(result + " ");
    }
    out.println();
  }

  private long findMinimum(int index) {
    long begin = MIN, end = MAX;
    // Preprocess.
    long[] elements = new long[n];
    for (int i = 0; i < n; ++i)
      elements[i] = vector[i][index];
    Arrays.sort(elements);
    // Prefix sums.
    long[] prefix = new long[n];
    prefix[0] = elements[0];
    for (int i = 1; i < n; ++i)
      prefix[i] = prefix[i - 1] + elements[i];
    
    while (begin + 1 < end) {
      long low = (2 * begin + end) / 3;
      long high = (begin + 2 * end) / 3;
      long ll = calculate(low, elements, prefix);
      long hh = calculate(high, elements, prefix);
      if (ll <= hh)
        end = high - 1;
      else
        begin = low + 1;
    }
    return (calculate(end) < calculate(begin)) ? end : begin;
  }

  private long calculate(long value, long[] elements, long[] prefix) {
    if (value < elements[0])
      return prefix[n - 1] - value * n;
    if (value > elements[n - 1])
      return value * n - prefix[n - 1];
    int low = 0, high = n - 1;
    while (low < high) {
      int mid = low + ((high - low + 1) / 2);
      if (elements[mid] < value)
        low = mid;
      else  // elements[mid] >= value
        high = mid - 1;
    }
    return (value * (low + 1) - prefix[low]) +
        (prefix[n - 1] - prefix[low] - value * (n - low - 1));
  }
}

class InputReader {
  public static final int STREAM_SIZE = 32768;
  private final BufferedReader reader;
  private StringTokenizer tokenizer;
  
  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream), STREAM_SIZE);
  }

  public String next() {
    if (tokenizer == null || !tokenizer.hasMoreTokens()) {
      String nextLine = null;
      try {
        nextLine = reader.readLine();
      } catch (IOException e) {
        throw new RuntimeException(e.toString());
      }
      tokenizer = new StringTokenizer(nextLine);
    }
    return tokenizer.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }
}
