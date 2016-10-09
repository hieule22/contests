import java.util.*;
import java.io.*;

public class Grille {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Grille solver = new Grille();
    solver.solve(in, out);
    out.close();
  }

  private void solve(Scanner in, PrintWriter out) {
    int n = in.nextInt();
    if (n == 0) {
      out.println("invalid grille");
      return;
    }
    char[][] mask = new char[n][n];
    for (int i = 0; i < n; i++) {
      mask[i] = in.next().toCharArray();
    }
    
    char[] text = in.next().toCharArray();
    char[][] ans = new char[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(ans[i], '*');
    }

    int holes = 0;
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (mask[r][c] == '.') {
          holes++;
        }
      }
    }
    if (holes * 4 != n * n) {
      out.println("invalid grille");
      return;
    }

    int idx = 0;
    for (int i = 0; i < 4; i++) {
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          if (mask[r][c] == '.') {
            if (ans[r][c] != '*' && ans[r][c] != text[idx]) {
              out.println("invalid grille");
              return;
            }
            ans[r][c] = text[idx++];
          }
        }
      }
      rotate(mask);
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (ans[i][j] == '*') {
          out.println("invalid grille");
          return;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        out.print(ans[i][j]);
      }
    }
    out.println();
  }

  private void print(char[][] matrix) {
    for (char[] row : matrix) {
      for (char cell : row) {
        System.out.print(cell);
      }
      System.out.println();
    }
  }

  private void rotate(char[][] matrix) {
    int n = matrix.length;
    for (int layer = 0; layer < n/2; layer++) {
      for (int i = layer; i < n - layer - 1; i++) {
        char tmp = matrix[layer][i];
        matrix[layer][i] = matrix[n - i - 1][layer];
        matrix[n - i - 1][layer] = matrix[n - layer - 1][n - i - 1];
        matrix[n - layer - 1][n - i - 1] = matrix[i][n - layer - 1];
        matrix[i][n - layer - 1] = tmp;
      }
    }
  }
}
