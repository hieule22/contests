import java.util.*;

public class QuickBrownFox {
  
  public static void main(String[] args) {
    QuickBrownFox sol = new QuickBrownFox();
    sol.solve();
  }

  public void solve() {
    Scanner input = new Scanner(System.in);
    int test = input.nextInt();
    input.nextLine();
    for (int t = 0; t < test; t++) {
      String line = input.nextLine();
      process(line.toLowerCase());
    }
  }

  private void process(String line) {
    boolean[] has = new boolean[26];

    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      //System.out.println(c + " " + (c - 'a'));
      if ('a' <= c && c <= 'z') {
        has[c - 'a'] = true;
      }
    }

    boolean check = true;
    String notFound = "missing ";
    for (int i = 0; i < 26; i++) {
      if (!has[i]) {
        notFound += (char)(i + 'a');
        check = false;
      }
    }
    if (check) {
      System.out.println("pangram");
    } else {
      System.out.println(notFound);
    }
  }
}
