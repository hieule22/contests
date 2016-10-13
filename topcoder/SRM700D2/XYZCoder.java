
public class XYZCoder {
    private static final long MOD = (long) 1e9 + 7;
    private long[][] dp;
    private long[][] cache;

    public int countWays(int room, int size) {
        int total = room * size;
        dp = new long[room + 1][total + 1];
        cache = new long[room + 1][total + 1];
        for (int rank = room; rank >= 1; --rank) {
            for (int i = 1; i <= total; ++i) {
                if (i - 1 < rank - 1) {
                    cache[rank][i] = (cache[rank][i - 1] + dp[rank][i]) % MOD;
                    continue;
                }
                if (total - i + 1 < size * (room - rank + 1)) {
                    cache[rank][i] = (cache[rank][i - 1] + dp[rank][i]) % MOD;
                    continue;
                }
                if (rank == room) {
                    dp[rank][i] = (i <= total + 1 - size) ? 1 : 0;
                    cache[rank][i] = (cache[rank][i - 1] + dp[rank][i]) % MOD;
                    continue;
                }
                long temp = (cache[rank + 1][total] + MOD - cache[rank + 1][i]) % MOD;
                dp[rank][i] = (dp[rank][i] + temp) % MOD;
                cache[rank][i] = (cache[rank][i - 1] + dp[rank][i]) % MOD;
            }
        }

        long result = dp[1][1];
        return (int) ((result * factorial(room)) % MOD);
    }

    private long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; ++i) {
            result = (result * i) % MOD;
        }
        return result;
    }

}
