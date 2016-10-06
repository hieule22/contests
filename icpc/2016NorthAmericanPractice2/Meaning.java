import java.util.*;
import java.io.*;

public class Meaning {

  private static final long MOD = (long)1e9 + 7;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PrintWriter printer = new PrintWriter(System.out);
    Meaning solver = new Meaning();
    solver.solve(scanner, printer);
    printer.close();
  }

  private class Word {
    public String first;
    public long second;

    public Word(String first, long second) {
      this.first = first;
      this.second = second;
    }
  }

  public void solve(Scanner in, PrintWriter out) {
    int count = in.nextInt();
    String target = in.next();
    Word[] words = new Word[count];
    for (int i = 0; i < count; ++i) {
      words[i] = new Word(in.next(), in.nextLong());
    }
    
    long[] memo = new long[target.length()];
    for (int i = 0; i < memo.length; ++i) {
      for (Word word : words) {
        int begin = match(word.first, target, i);
        if (begin == 0) {
          memo[i] = (memo[i] + word.second) % MOD;
        } else if (begin > 0) {
          long temp = (memo[begin - 1] * word.second) % MOD;
          memo[i] = (memo[i] + temp) % MOD;
        }
      }
    }

    out.println(memo[memo.length - 1]);
  }

  private static int match(String word, String target, int index) {
    int start = index - word.length() + 1;
    if (start < 0) return -1;
    for (int i = 0; i < word.length(); ++i) {
      if (word.charAt(i) != target.charAt(start + i))
        return -1;
    }
    return start;
  }
}
