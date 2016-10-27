import java.util.Arrays;
import java.util.Comparator;

/**
 * Java construction of a suffix array.
 * @author Hieu Le
 * @version 10/26/16
 */
public class SuffixArray {
  
    public static int[] construct(CharSequence s) {
        final int length = s.length();
        // maxPower computes the smallest power of 2 >= length.
        final int maxPower = (int) Math.ceil(Math.log(length) / Math.log(2));
        Integer[] suffixArray = new Integer[length];
        // ranks[i][] stores the relative orders of all substrings of length 2^i.
        int[][] ranks = new int[maxPower + 1][length];
        for (int i = 0; i < length; ++i) {
            suffixArray[i] = i;
            ranks[0][i] = s.charAt(i);
        }

        for (int i = 1; i <= maxPower; ++i) {
            final int currentPower = i;
            Comparator<Integer> comparator = (o1, o2) -> {
                if (ranks[currentPower - 1][o1] == ranks[currentPower - 1][o2]) {
                    o1 += (1 << (currentPower - 1));
                    o2 += (1 << (currentPower - 1));
                }

                if (o1 >= suffixArray.length)
                    return -1;
                if (o2 >= suffixArray.length)
                    return 1;
                return ranks[currentPower - 1][o1] - ranks[currentPower - 1][o2];
            };
            Arrays.sort(suffixArray, comparator);

            // Update ranks.
            int currentRank = ranks[currentPower][suffixArray[0]] = 0;
            for (int j = 1; j < length; ++j) {
                if (comparator.compare(suffixArray[j], suffixArray[j - 1]) != 0)
                    currentRank++;
                ranks[currentPower][suffixArray[j]] = currentRank;
            }
        }

        int[] result = new int[length];
        for (int i = 0; i < length; ++i)
            result[i] = suffixArray[i];
        return result;
    }
}
