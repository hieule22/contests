import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class SimilarNames2 {
    private Map<Integer, List<Integer>> graph;
    private static final long MOD = (long) 1e9 + 7;
    private long[] cache = null;

    public int count(String[] names, int L) {
        graph = new HashMap<>();
        for (int i = 0; i < names.length; ++i) {
            List<Integer> children = new ArrayList<>();
            for (int j = 0; j < names.length; ++j) {
                if (isProperPrefix(names[i], names[j]))
                    children.add(j);
            }
            graph.put(i, children);
        }

        long[][] dp = new long[graph.size()][L + 1];
        for (int i = 0; i < graph.size(); ++i)
            dp[i][1] = 1;

        for (int length = 2; length <= L; ++length) {
            for (int i = 0; i < graph.size(); ++i) {
                for (int child : graph.get(i)) {
                    dp[i][length] = (dp[i][length] + dp[child][length - 1]) % MOD;
                }
            }
        }

        long result = 0;
        for (int i = 0; i < graph.size(); ++i) {
            long temp = dp[i][L] * factorial(graph.size() - L) % MOD;
            result = (result + temp) % MOD;
        }

        return (int) result;
    }

    private static boolean isProperPrefix(String target, String prefix) {
        if (prefix.length() >= target.length())
            return false;

        for (int i = 0; i < prefix.length(); ++i) {
            if (prefix.charAt(i) != target.charAt(i))
                return false;
        }
        return true;
    }

    private long factorial(int n) {
        if (cache == null) {
            cache = new long[51];
            cache[0] = 1;
            for (int i = 1; i < cache.length; ++i)
                cache[i] = (cache[i - 1] * i) % MOD;
        }
        return cache[n];
    }

}
