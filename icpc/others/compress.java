import java.util.*;
import java.io.*;

public class compress {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    PrintWriter output = new PrintWriter(System.out);
    compress sol = new compress();
    sol.solve(input, output);
    output.close();
  }

  public void solve(Scanner input, PrintWriter output) {
    int w = input.nextInt();
    int test = 0;
    while (w != 0) {
      int t = input.nextInt();
      char[][] matrix = new char[w][w];
      for (int i = 0; i < w; i++) {
        matrix[i] = input.next().toCharArray();
      }
      compute(matrix, 0, 0, w, t);
      
      output.printf("Image %d:\n", ++test);
      for (char[] row : matrix) {
        for (char cell : row) {
 	  output.print(cell);
        }
        output.println();
      }    
      w = input.nextInt();
    }
  }

  private void compute(char[][] matrix, int r, int c, int w, int t) {
    if (w == 1) {
      return;
    }
    int count = 0;
    for (int i = r; i < r + w; i++) {
      for (int j = c; j < c + w; j++) {
        if (matrix[i][j] == '1') {
          count++;
	}
      }
   }
   //System.out.println(r + " " + c + " " + w + " -> " + count + " " + w * w); 
   if (count * 100 >= w * w * t) {
      change(matrix, r, c, w, '1');
      return;
    }
    if ((w * w - count) * 100 >= w * w * t) { 
      change(matrix, r, c, w, '0');
      return;
    }
    compute(matrix, r, c, w/2, t);
    compute(matrix, r + w/2, c, w/2, t);
    compute(matrix, r, c + w/2, w/2, t);
    compute(matrix, r + w/2, c + w/2, w/2, t); 
  }

  private void change(char[][] matrix, int r, int c, int w, char target) {
    for (int i = r; i < r + w; i++) {
      for (int j = c; j < c + w; j++) {
        matrix[i][j] = target;
      }
    }
  }
}
