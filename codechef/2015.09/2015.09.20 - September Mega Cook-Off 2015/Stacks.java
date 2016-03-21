package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Stacks {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        TreeMap<Integer, Integer> tops = new TreeMap<Integer, Integer>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int next = in.nextInt();
            Integer higher = tops.higherKey(next);
            if (higher == null) {
                if (!tops.containsKey(next)) tops.put(next, 1);
                else tops.put(next, tops.get(next) + 1);
                cnt ++;
            } else {
                if (tops.get(higher) == 1) {
                    tops.remove(higher);
                } else {
                    tops.put(higher, tops.get(higher) - 1);
                }
                if (!tops.containsKey(next)) tops.put(next, 1);
                else tops.put(next, tops.get(next) + 1);
            }
        }
        out.print(cnt + " ");
        for (Map.Entry<Integer, Integer> entry : tops.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++)
                out.print(entry.getKey() + " ");
        }
        out.println();
    }
}
