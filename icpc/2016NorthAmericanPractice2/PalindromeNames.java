import java.util.*;
import java.io.*;

public class PalindromeNames {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    PrintWriter output = new PrintWriter(System.out);
    PalindromeNames mn = new PalindromeNames();
    mn.solve(input, output);
    output.close();
  }
  //abcde--
  //--
  public void solve(Scanner input, PrintWriter output) {
    String text = input.next();
    int len = text.length();
    text += text;
    int result = Integer.MAX_VALUE;
    for (int l = len; l <= text.length(); l++) {
      int count = 0;
      for (int i = 0; i < l/2; i++) {
        if ((l - i - 1) >= len || text.charAt(i) != text.charAt(l - i - 1)) {
          count++;
        }
      }
      result = Math.min(result, count);
    }
    output.println(result);
  }
}
