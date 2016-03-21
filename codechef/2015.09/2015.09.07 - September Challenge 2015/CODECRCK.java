package taskdirectory;

import java.util.Scanner;
import java.io.PrintWriter;

public class CODECRCK {
    static final double X = Math.sqrt(2);
    static final double Y = Math.sqrt(3);

    public void solve(int testNumber, Scanner in, PrintWriter out) {
//        int i = in.nextInt();
//        int k = in.nextInt();
        long i = in.nextLong();
        long k = in.nextLong();
        long s = in.nextLong();
        double ai = in.nextDouble();
        double bi = in.nextDouble();
//        int i = 10;
//        int k = 20;
//        long s = 2000;
//        double ai = 0;
//        double bi = 0;

        long power;
        if (((i - k) & 1) == 0)
            power = (k - i) << 1;
        else {
            i++;
            double a = X * (ai + bi) - X * Y * (ai - bi);
            double b = X * (ai - bi) + X * Y * (ai + bi);
            power = (k - i) << 1;
            ai = a;
            bi = b;
        }

        power -= s;
        double sum = ai + bi;
        if (sum != 0) {
            if (power >= 0) {
                sum *= Math.pow(2, power);
            } else {
                power = -power;
                for (int j = 0; j < power; j++) {
                    if (Math.abs(sum) < 0.01) {
                        sum = 0;
                        break;
                    } else {
                        sum /= 2.0;
                    }
                }
            }
        }
        out.println(sum);
    }
}
