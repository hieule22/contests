import java.io.*;
import java.util.*;

public class ReverseRot {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    ReverseRot solver = new ReverseRot();
    solver.solve(in, out);
    out.close();
  }

  char[] letters = new char[28];

  private void solve(Scanner in, PrintWriter out) {
    for (char c = 'A'; c <= 'Z'; c++) {
      letters[c - 'A'] = c;
    }
    letters[26] = '_';
    letters[27] = '.';

    int n = in.nextInt();
    while (n != 0) {
      String text = in.next();
      process(text, n, out);

      n = in.nextInt();
    }
  }

  private void process(String text, int n, PrintWriter output) {
    Map<Character, Character> map = new HashMap<Character, Character>();
    for (int i = 0; i < letters.length; i++) {
      char letter = letters[i];
      char next = letters[(i + n) % letters.length];
      map.put(letter, next);
    }

    char[] result = new char[text.length()];
    for (int i = 0; i < result.length; i++) {
      result[i] = text.charAt(result.length - i - 1);
      result[i] = map.get(result[i]);
    }
    output.println(new String(result));
  }
}
