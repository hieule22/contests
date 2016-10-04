import java.util.*;
import java.io.*;

public class ChefAndFriends {
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
    int m = in.nextInt();
    boolean[][] friend = new boolean[n][n];

    int a, b;
    for (int i = 0; i < m; ++i) {
      a = in.nextInt();
      b = in.nextInt();
      friend[a - 1][b - 1] = friend[b - 1][a - 1] = true;
    }

    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        for (int k = j + 1; k < n; ++k) {
          if (!friend[i][j] && !friend[j][k] && !friend[k][i]) {
            out.println("NO");
            return;
          }
        }
      }
    }

    out.println("YES");
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
