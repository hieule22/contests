package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class TimeMeasure {

    private static double EPSILON = 1.0 / 120;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        double angle = in.nextDouble();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (isValid(h, m, angle)) out.println(getTime(h, m));
            }
        }
    }

    private boolean isValid(int h, int m, double angle) {
        double a = Math.abs(11 * m - 60 * h) / 2.0;
        if (a >= 180) a = 360 - a;
        return Math.abs(a - angle) < EPSILON;
    }

    private String getTime(int h, int m) {
        String hour = (h >= 10) ? ("" + h) : ("0" + h);
        String minute = (m >= 10) ? ("" + m) : ("0" + m);
        return hour + ":" + minute;
    }
}
