package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BudgetFriendly {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int c = in.nextInt();
        int b = in.nextInt();
        List<List<Hotel>> cities = new ArrayList<List<Hotel>>(c);
        for (int i = 0; i < c; i++) {
            List<Hotel> city = new ArrayList<Hotel>();
            int n = in.nextInt();
            for (int j = 0; j < n; j++)
                city.add(new Hotel(in.nextInt(), in.nextDouble()));
            cities.add(city);
        }

        double[][] dp = new double[c][b + 1];
        for (int i = 0; i < c; i++) Arrays.fill(dp[i], -1);
        for (Hotel hotel : cities.get(0))
            if (b >= hotel.price) dp[0][b - hotel.price] = Math.max(hotel.score, dp[0][b - hotel.price]);

        for (int i = 1; i < c; i++) {
            for (Hotel hotel : cities.get(i)) {
                for (int j = 0; j <= b; j++) {
                    if (dp[i - 1][j] == -1) continue;
                    if (j >= hotel.price)
                        dp[i][j - hotel.price] = Math.max(dp[i - 1][j] + hotel.score, dp[i][j - hotel.price]);
                }
            }
        }

        double score = -1;
        for (int i = 0; i <= b; i++)
            score = Math.max(score, dp[c - 1][i]);
        if (score == -1)
            out.println(-1);
        else {
            score = Math.round(score * 100.0) / 100.0;
            String s = Double.toString(score);
            boolean isDecimal = false;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == '.') {
                    isDecimal = true;
                    int len = s.length();
                    for (int j = len; j < i + 3; j++) s += '0';
                }
            if (!isDecimal) s += ".00";
            out.println(s);
        }
    }
}

class Hotel {
    int price;
    double score;

    public Hotel(int price, double score) {
        this.price = price;
        this.score = score;
    }
}
