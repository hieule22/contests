import java.util.*;
import java.io.*;

public class MaxBipartiteMatch {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    MaxBipartiteMatch problem = new MaxBipartiteMatch();
    problem.solve(in, out);
    out.close();
  }

  // Number of cards.
  private int n;
  // Number of possible values.
  private int m;
  // Adjacency list.
  private List<List<Integer>> graph;
  // matched[i] denotes which card the value i has been matched to.
  // Initially set to -1.
  private int[] matched;
  // visited[i] signals whether value i has been visited.
  // Reset to false before matching each card.
  private boolean[] visited;

  private void solve(Scanner in, PrintWriter out) {
    int testCount = in.nextInt();
    for (int test = 0; test < testCount; ++test) {
      n = in.nextInt();
      
      graph = new ArrayList<>();
      m = -1;
      for (int i = 0; i < n; ++i) {
        int p = in.nextInt();
        int q = in.nextInt();
        graph.add(new ArrayList<>());
        graph.get(i).add(p);
        graph.get(i).add(q);
        m = Math.max(m, Math.max(p, q));
      }

      matched = new int[m + 1];
      Arrays.fill(matched, -1);
      visited = new boolean[m + 1];

      int i;
      for (i = 0; i < n; ++i) {
        Arrays.fill(visited, false);
        if (!bipartiteMatch(i)) {
          out.println("impossible");
          break;
        }
      }

      if (i == n) {
        out.println("possible");
      }
    }
  }

  private boolean bipartiteMatch(int u) {
    for (int v : graph.get(u)) {
      if (!visited[v]) {
        visited[v] = true;
        if (matched[v] < 0 || bipartiteMatch(matched[v])) {
          matched[v] = u;
          return true;
        }
      }
    }
    return false;
  }
}
