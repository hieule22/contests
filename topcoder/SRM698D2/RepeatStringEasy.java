import java.util.Arrays;

public class RepeatStringEasy
{
    private static final int INF = (int) 1e9;

    public int maximalLength(String s) {
        int deletions = s.length();
        for (int middle = 1; middle < s.length() - 1; ++middle) {
            String left = s.substring(0, middle);
            String right = s.substring(middle);
            deletions = Math.min(deletions, transform(left, right));
        }
        return s.length() - deletions;
    }

    private int transform(String a, String b) {
        int[][] memo = new int[a.length() + 1][b.length() + 1];
        for (int[] layer : memo)
            Arrays.fill(layer, INF);
        memo[0][0] = 0;

        for (int i = 0; i <= a.length(); ++i) {
            for (int j = 0; j <= b.length(); ++j) {
                if (i - 1 >= 0)
                    memo[i][j] = Math.min(memo[i][j], memo[i - 1][j] + 1);
                if (j - 1 >= 0)
                    memo[i][j] = Math.min(memo[i][j], memo[i][j - 1] + 1);
                if (i - 1 >= 0 && j - 1 >= 0) {
                  int deletions = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 2;
                  memo[i][j] = Math.min(memo[i][j], memo[i - 1][j - 1] + deletions);
                }
            }
        }

        return memo[a.length()][b.length()];
    }
}
