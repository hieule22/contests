import java.util.*;

public class SimonSays {
  
  public static void main(String[] args) {
    SimonSays sol = new SimonSays();
    sol.solve();
  }

  public void solve() {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    in.nextLine();
    final String prefix = "Simon says ";
    for (int i = 0; i < n; ++i) {
	String line = in.nextLine();
	if (!line.startsWith(prefix))
	    continue;
	String suffix = line.substring(prefix.length());
	System.out.println(suffix);
    }
  }
}
