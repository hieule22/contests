import java.util.*;
import java.io.*;

public class DivisorExploration {
  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Solution solver = new Solution(in, out);

    int d = in.nextInt();
    for (int i = 0; i < d; ++i)
      solver.solve(i);
    out.close();
  }
}

class Solution {
  private final InputReader in;
  private final PrintWriter out;
  private long[] factorial;
  private long[] power;

  public static final long MOD = (long)1e9 + 7;

  public Solution(InputReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
    factorial = new long[200005];
    factorial[0] = 1;
    for (int i = 1; i < factorial.length; ++i)
      factorial[i] = factorial[i - 1] * i % MOD;

    power = new long[100005];
    power[0] = 1;
    for (int i = 1; i < power.length; ++i)
      power[i] = (power[i - 1] * 2) % MOD;
  }

  public void solve(int testNumber) {
    int m = in.nextInt();
    int a = in.nextInt();

    long base = 1L * (a + 2) * (a + 3) % MOD;
    base = base * inverse(2) % MOD;
    if (m == 1) {
      out.println(base);
      return;
    }

    base = base * factorial[a + m + 1] % MOD;
    base = base * inverse(factorial[a + 2]) % MOD;
    base = base * factorial[a + m + 2] % MOD;
    base = base * inverse(factorial[a + 3]) % MOD;
    base = base * inverse(power[m - 1]) % MOD;
    out.println(base);
  }

  private static long inverse(long n) {
    return pow(n, MOD - 2);
  }

  private static long pow(long a, long b) {
    if (b == 0)
      return 1;
    long res = pow(a, b >> 1);
    res = res * res % MOD;
    if (b % 2 == 1)
      res = res * a % MOD;
    return res;
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
