import java.util.*;
import java.io.*;

public class XRange {
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

  public Solution(InputReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
  }

  public void solve(int testNumber) {
    int n = in.nextInt();
    int m = in.nextInt();
    int cycle = 2 * n;
    int anchor = 0;
    boolean clockwise = true;

    for (int i = 0; i < m; ++i) {
      int type = in.nextInt();
      int k = in.nextInt();

      if (type == 1) {  // Rotator
        int shift = 2 * k;
        anchor = (anchor + shift) % cycle;
      } else {  // Flipper
        int delta = (k + cycle - anchor) % cycle;
        anchor = (k + delta) % cycle;
        clockwise = !clockwise;
      }
    }

    if (clockwise) {  // Needs rotator.
      int moves = (cycle - anchor) / 2;
      out.println("1 " + moves % n);
    } else {  // Needs flipper
      out.println("2 " + (anchor / 2));
    }
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
