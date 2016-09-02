import java.util.*;
import java.io.*;

public class LongSeq {
  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Solution solver = new Solution(in, out);
    
    int t = in.nextInt();
    for (int i = 0; i < t; ++i) {
      solver.solve(t);
    }
    out.flush();
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
    char[] digits = in.next().toCharArray();
    int ones = 0;
    int zeros = 0;
    for (char digit : digits) {
      if (digit == '0')
        ++zeros;
      else
        ++ones;
    }

    if (zeros == 1 || ones == 1) {
      out.println("Yes");
    } else {
      out.println("No");
    }
  }
}

class InputReader {
  private final BufferedReader reader;
  private StringTokenizer tokenizer;
  
  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream));
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
