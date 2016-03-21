package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class CODECRCK {
    private static final double X = Math.sqrt(2);
    private static final double Y = Math.sqrt(3);

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int i = in.nextInt();
        int k = in.nextInt();
        long s = in.nextLong();
        double ai = in.nextInt();
        double bi = in.nextInt();

        int power;
        if ((i - k) % 2 == 0) {
            power = (k - i) * 2;
        } else {
            i ++;
            double a = X * (ai + bi) - X * Y * (ai - bi);
            double b = X * (ai - bi) + X * Y * (ai + bi);
            power = (k - i) * 2;
            ai = a;
            bi = b;
        }

        power -= s;
        double sum = ai + bi;
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
        out.println(sum);
    }
}
