import java.util.*;

public class Bobby {
  
  public static void main(String[] args) {
    Bobby sol = new Bobby();
    sol.solve();
  }

  public void solve() {
    Scanner input = new Scanner(System.in);
    int test = input.nextInt();
    for (int t = 0; t < test; t++) {
      int R = input.nextInt();
      int S = input.nextInt();
      int X = input.nextInt();
      int Y = input.nextInt();
      int W = input.nextInt();
      double expected = 0;
      double p = (1.0) * (S - R + 1) / S;
      for (int i = X; i <= Y; i++) {
        expected += combination(i,Y) * Math.pow(p,i) * Math.pow((1 - p), (Y - i));
      }
      if (W * expected > 1.0) {
        System.out.println("yes");
      } else {
        System.out.println("no");
      }
    }
  }

  private double combination(int k, int n) {
    long top = factorial(n);
    long bottom = factorial(k) * factorial(n - k);
    return (1.0) * top / bottom;
  }

  private int factorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
