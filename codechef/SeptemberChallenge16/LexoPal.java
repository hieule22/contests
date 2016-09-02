import java.util.*;
import java.io.*;

public class LexoPal {
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
    char[] word = in.next().toCharArray();
    int forward = 0, backward = word.length - 1;
    while (forward <= backward) {
      if (word[forward] == '.' && word[backward] == '.') {
        word[forward] = 'a';
        word[backward] = 'a';
      } else if (word[forward] == '.') {
        word[forward] = word[backward];
      } else if (word[backward] == '.') {
        word[backward] = word[forward];
      } else if (word[forward] != word[backward]) {
        out.println(-1);
        return;
      }
      ++forward;
      --backward;
    }
    out.println(word);
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
