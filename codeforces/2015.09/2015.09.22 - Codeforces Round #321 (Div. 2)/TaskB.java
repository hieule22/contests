package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int d = in.nextInt();
        Friend[] friends = new Friend[n + 1];
        for (int i = 1; i <= n; i++) friends[i] = new Friend(in.nextInt(), in.nextInt());
        Arrays.sort(friends, 1, n + 1);

        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) prefix[i] = prefix[i - 1] + friends[i].factor;

        long res = 0;
        for (int i = 1; i <= n; i++) {
            int max = friends[i].money + d - 1;
            if (max > friends[n].money) {
                res = Math.max(res, prefix[n] - prefix[i - 1]);
                continue;
            }
            int low = i;
            int high = n;
            while (low != high) {
                int mid = low + ((high - low + 1) >> 1);
                if (friends[mid].money > max) {
                    high = mid - 1;
                } else {
                    low = mid;
                }
            }
            res = Math.max(res, prefix[low] - prefix[i - 1]);
        }
        out.println(res);
    }

    class Friend implements Comparable<Friend> {
        int money;
        int factor;

        public Friend(int money, int factor) {
            this.money = money;
            this.factor = factor;
        }

        public int compareTo(Friend o) {
            return money - o.money;
        }
    }
}
