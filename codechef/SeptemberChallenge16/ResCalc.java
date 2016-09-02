import java.util.*;
import java.io.*;

public class ResCalc {
  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Solution solver = new Solution(in, out);

    int t = in.nextInt();
    for (int i = 0; i < t; ++i) {
      solver.solve(i);
    }
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
    int result = 0;
    int winner = -1;
    boolean tie = false;

    for (int i = 0; i < n; ++i) {
      int c = in.nextInt();
      int[] cookies = new int[7];
      for (int j = 0; j < c; ++j)
        ++cookies[in.nextInt()];
      int score = c + getScore(cookies);
      if (score > result) {
        result = score;
        winner = i;
        tie = false;
      } else if (score == result) {
        tie = true;
      }
    }

    // out.println(result);
    if (tie) {
      out.println("tie");
    } else {
      if (winner == 0)
        out.println("chef");
      else
        out.println(winner + 1);
    }
  }

  private static int getScore(int[] cookies) {
    Arrays.sort(cookies);
    int result = 4 * cookies[1] + 2 * (cookies[2] - cookies[1]) +
        (cookies[3] - cookies[2]);
    return result;
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
