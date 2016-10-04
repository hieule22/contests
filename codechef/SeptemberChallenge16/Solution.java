import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Solution solver = new Solution(in, out);

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
