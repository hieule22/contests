import java.io.*;
import java.util.*;

public class ShrineMaintenance {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    ShrineMaintenance solver = new ShrineMaintenance();
    solver.solve(in, out);
    out.close();
  }

  private static final int R = 1000;

  private boolean[] isShrine;
  private List<Integer> points;
  private int w, n, d;
  private List<Double> lengths;

  private void solve(Scanner in, PrintWriter out) {
    w = in.nextInt();
    while (w != 0) {
      n = in.nextInt();
      d = in.nextInt();
      isShrine = new boolean[n + 1];
      for (int i = 0; i < d; i++) {
        int shrine = in.nextInt();
        isShrine[shrine] = true;
      }
      for (int i = 0; i <= n; i++) {
        if (isShrine[i]) {
          for (int j = i + i; j <= n; j += i) {
            isShrine[j] = true;
          }
        }
      }

      points = new ArrayList<Integer>();
      for (int i = 0; i <= n; i++) {
        if (isShrine[i]) points.add(i);
      }

      process(out);

      w = in.nextInt();
    }
  }

  private void process(PrintWriter out) {
    calcuateLengths();
    double result = binarySearch();
    out.println(round(result + 2 * R, 1));
  }

  private void calcuateLengths() {
    lengths = new ArrayList<Double>();
    for (int i = 0; i < points.size() - 1; i++) {
      Double angle = 180.0 * (points.get(i + 1) - points.get(i)) / n;
      Double l = 2.0 * R * Math.sin(Math.toRadians(angle));
      lengths.add(l);
    }

    Double angle = 180.0 * (n + points.get(0) - points.get(points.size() - 1)) / n;
    Double l = 2.0 * R * Math.sin(Math.toRadians(angle));
    lengths.add(l);
  }
  
  private double binarySearch() {
    double left = 0;
    double right = Math.PI * R * 2;
    double result = -1;
    for (int i = 0; i <= 20; i++) {
      double mid = (left + right) / 2;
      if (check(mid)) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return left;
  }

  private boolean check(double range) {
    for (int pivot = 0; pivot < lengths.size(); pivot++) {
      double current = 0;
      int count = 0;
      for (int i = 0; i < lengths.size() - 1; i++) {
        double segment = lengths.get((i + pivot) % lengths.size());
        if (current + segment > range) {
          count++;
          if (count > w) break;
          current = 0;
        } else {
          current += segment;
        }
      }
      if (count < w) return true;
    }
    return false;
  }

  private static double round (double value, int precision) {
    int scale = (int) Math.pow(10, precision);
    return (double) Math.round(value * scale) / scale;
  }
}











