import java.util.*;

public class CircuitCounting {
  
  public static void main(String[] args) {
    CircuitCounting sol = new CircuitCounting();
    sol.solve();
  }

  public void solve() {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    Pair[] pairs = new Pair[n + 1];
    for (int i = 1; i <= n; i++) {
      pairs[i] = new Pair(input.nextInt(), input.nextInt());
    }

    long[][][] f = new long[801][801][41];
    f[400][400][0] = 1L;
    for (int k = 1; k <= n; k++) {
      for (int x = 0; x <= 800; x++) {
        for (int y = 0; y <= 800; y++) {
          f[x][y][k] = f[x][y][k - 1];
          if (0 <= x - pairs[k].first && x - pairs[k].first <= 800 && 0 <= y - pairs[k].second && y - pairs[k].second <= 800) {
            f[x][y][k] += f[x - pairs[k].first][y - pairs[k].second][k - 1];
          }
        }
      }
    }
    System.out.println(f[400][400][n] - 1);
  }

  private class Pair {
    private int first;
    private int second;

    private Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }
}
