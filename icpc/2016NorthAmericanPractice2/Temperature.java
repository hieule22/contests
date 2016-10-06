import java.util.*;
import java.io.*;

public class Temperature {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int x = in.nextInt();
    int y = in.nextInt();

    if (y == 1) {
      if (x == 0)
        System.out.println("ALL GOOD");
      else
        System.out.println("IMPOSSIBLE");
      return;
    }

    double result = 1.0 * x / (1 - y);
    System.out.println(result);
  }
}
