import java.util.*;

public class SecretMessage {
  
  public static void main(String[] args) {
    SecretMessage sol = new SecretMessage();
    sol.solve();
  }

  public void solve() {
    Scanner input = new Scanner(System.in);
    int test = input.nextInt();
    for (int t = 0; t < test; t++) {
      String text = input.next();
      process(text);
    }
  }

  private void process(String text) {
    int l = text.length();
    int k = findK(l);
    char[][] matrix = new char[k][k];
    int idx = 0;
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < k; j++) {
        if (idx >= l) {
          matrix[i][j] = '*';
        } else {
          matrix[i][j] = text.charAt(idx++);
        }
      }
    }
    rotate(matrix);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < k; j++) {
        if (matrix[i][j] != '*') {
          sb.append(matrix[i][j]);
        }
      }
    }
    System.out.println(sb.toString());
  }

  private int findK(int l) {
    for (int k = 1; k <= l; k++) {
      if (k * k >= l) {
        return k;
      }
    }
    return -1;
  }

  private void rotate(char[][] matrix) {
    int k = matrix.length;
    for (int layer = 0; layer < k/2; layer++) {
      for (int idx = layer; idx < k - layer - 1; idx++) {
        char tmp = matrix[layer][idx];
        matrix[layer][idx] = matrix[k - idx - 1][layer];
        matrix[k - idx - 1][layer] = matrix[k - layer - 1][k - idx - 1];
        matrix[k - layer - 1][k - idx - 1] = matrix[idx][k - layer - 1];
        matrix[idx][k - layer - 1] = tmp;
      }
    }
  }

  private void print(char[][] matrix) {
    for (char[] row : matrix) {
      for (char cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }
  }
}
