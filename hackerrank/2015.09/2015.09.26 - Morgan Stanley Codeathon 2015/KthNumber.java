package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KthNumber {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        Query[] queries = new Query[q];
        for (int i = 0; i < q; i++) queries[i] = new Query(in.nextInt(), in.nextInt(), i);
        Arrays.sort(queries, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                return (o1.l != o2.l) ? (o1.l - o2.l) : (o1.k - o2.k);
            }
        });

        List<List<Query>> query = new ArrayList<>(q);
        int threshold = 0;
        for (int i = 0; i < q; i++) {
            if (queries[i].l == threshold)
                query.get(query.size() - 1).add(queries[i]);
            else {
                query.add(new ArrayList<Query>());
                query.get(query.size() - 1).add(queries[i]);
                threshold = queries[i].l;
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i] < query.get(0).get(0).l) continue;
            int low = 0;
            int high = query.size() - 1;
            while (low < high) {
                int mid = low + ((high - low + 1) >> 1);
                if (query.get(mid).get(0).l > a[i]) high = mid - 1;
                else low = mid;
            }
            assert (query.get(low).get(0).l <= a[i]);
            if (low < query.size() - 1) assert query.get(low + 1).get(0).l > a[i];

            for (int j = low; j >= 0; j--) {
                List<Query> cur = query.get(j);
                if (cur.get(cur.size() - 1).k <= 0) continue;
                int lo = 0;
                int hi = cur.size() - 1;
                while (lo < hi) {
                    int mid = lo + ((hi - lo) >> 1);
                    if (cur.get(mid).k <= 0) lo = mid + 1;
                    else hi = mid;
                }
                assert cur.get(lo).k > 0;
                if (lo > 0) assert cur.get(lo - 1).k <= 0;

                for (int k = lo; k < cur.size(); k++) {
                    cur.get(k).k--;
                    if (cur.get(k).k == 0) {
                        res[cur.get(k).i] = a[i];
                    }
                }
            }
        }

        for (int i = 0; i < q; i++)
            out.println(res[i]);
    }

    class Query {
        int l, k, i;

        public Query(int l, int k, int i) {
            this.l = l;
            this.k = k;
            this.i = i;
        }
    }
}
