package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int e = in.nextInt();
        int s = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();

        final Station[] stn = new Station[n + 1];
        for (int i = 0; i < n; i++)
            stn[i] = new Station(in.nextInt(), in.nextInt());
        stn[n] = new Station(3, e);
        stn[n].premium = stn[n].regular = stn[n].fuel = 0;

        Arrays.sort(stn, 0, n, new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o1.pos - o2.pos;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (stn[o1].regular != stn[o2].regular)
                    return stn[o1].regular - stn[o2].regular;
                return stn[o1].premium - stn[o2].premium;
            }
        });
        queue.add(n);

        boolean[] done = new boolean[n + 1];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            done[cur] = true;
            int low = getLowerBound(stn, n, stn[cur].pos - s);
            int high = getUpperBound(stn, n, stn[cur].pos + s);
            for (int i = low; i <= high; i++) {
                if (!done[i]) {
                    int regular = stn[cur].regular;
                    int premium = stn[cur].premium;
                    int fuel = Math.abs(stn[cur].pos - stn[i].pos);
                    if (stn[i].type == 1) regular += fuel;
                    if (stn[i].type == 2) premium += fuel;
                    if (regular < stn[i].regular || (regular == stn[i].regular && premium < stn[i].premium)) {
                        stn[i].regular = regular;
                        stn[i].premium = premium;
                        stn[i].fuel = fuel;
                    }
                    queue.add(i);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            if (Math.abs(start - e) <= s) {
                out.println("0 0");
                continue;
            }

            int low = getLowerBound(stn, n, start - s);
            int high = getUpperBound(stn, n, start + s);
            int regular = Integer.MAX_VALUE;
            int premium = Integer.MAX_VALUE;
            for (int j = low; j <= high; j++) {
                if (!done[j]) continue;
                int remain = s - Math.abs(start - stn[j].pos);
                int r = stn[j].regular;
                int p = stn[j].premium;
                if (stn[j].type == 1) {
                    r -= remain;
                    if (r < 0) r = 0;
                }
                if (stn[j].type == 2) {
                    p -= remain;
                    if (p < 0) p = 0;
                }
                if (regular > r || (regular == r && premium > p)) {
                    regular = r;
                    premium = p;
                }
            }
            if (regular != Integer.MAX_VALUE) {
                out.println(regular + " " + premium);
            } else {
                out.println("-1 -1");
            }
        }
    }

    private int getUpperBound(Station[] stn, int n, int limit) {
        if (stn[0].pos > limit) return -1;
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (stn[mid].pos > limit) high = mid - 1;
            else low = mid;
        }
        return low;
    }

    private int getLowerBound(Station[] stn, int n, int limit) {
        if (limit > stn[n - 1].pos) return n;
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (stn[mid].pos < limit) low = mid + 1;
            else high = mid;
        }
        return low;
    }
    
    
}

class Station {
    int type, pos, regular, premium, fuel;

    public Station(int type, int pos) {
        this.type = type;
        this.pos = pos;
        regular = Integer.MAX_VALUE;
        premium = Integer.MAX_VALUE;
    }
}