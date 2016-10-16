import java.io.*;
import java.util.*;

public class WordCloud {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    WordCloud solver = new WordCloud();
    solver.solve(in, out);
    out.close();
  }

  private void solve(Scanner in, PrintWriter out) {
    int W = in.nextInt();
    int test = 0;
    while (W != 0) {
      int n = in.nextInt();
      String[] words = new String[n];
      int[] count = new int[n];
      for (int i = 0; i < n; i++) {
        words[i] = in.next();
        count[i] = in.nextInt();
      }
      process(words, count, n, W, ++test, out);
      W = in.nextInt();
    }
  }

  private void process(String[] words, int[] count, int n, int W,
                       int test, PrintWriter output) {
    int[] fontSize = new int[n];
    int[] width = new int[n];

    init(words, count, fontSize, width);

    int total = 0;
    int currentWidth = width[0];
    int currentHeight = fontSize[0];

    for (int i = 1; i < words.length; i++) {
      if (currentWidth + 10 + width[i] > W) {
        total += currentHeight;
        currentWidth = width[i];
        currentHeight = fontSize[i];
      } else {
        currentWidth += 10 + width[i];
        currentHeight = Math.max(currentHeight, fontSize[i]);
      }
    }
    total += currentHeight;
    output.printf("CLOUD %d: %d\n", test, total);
  }

  private void init(String[] words, int[] count, int[] fontSize, int[] width) {
    int max = 0;
    for (int val : count)
      max = Math.max(max, val);

    for (int i = 0; i < words.length; i++) {
      fontSize[i] = 8 + (int)(Math.ceil(40.0 * (count[i] - 4) / (max - 4)));
      width[i] = (int)Math.ceil(9.0/16 * words[i].length() * fontSize[i]);
    }
  }
}
