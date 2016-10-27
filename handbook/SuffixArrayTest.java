import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * Unit tests for suffix array construction.
 * @author Hieu Le
 * @version 10/26/16
 */
public class SuffixArrayTest {

    @org.junit.Test
    public void testConstructBasic() throws Exception {
        assertArrayEquals(SuffixArray.construct("a"), new int[] {0});
        assertArrayEquals(SuffixArray.construct("ab"), new int[] {0, 1});
        assertArrayEquals(SuffixArray.construct("aa"), new int[] {1, 0});
        assertArrayEquals(SuffixArray.construct("dcba"), new int[] {3, 2, 1, 0});
        assertArrayEquals(SuffixArray.construct("aabaab"), new int[] {3, 0, 4, 1, 5, 2});
        assertArrayEquals(SuffixArray.construct("bbaa"), new int[] {3, 2, 1, 0});
    }

    @org.junit.Test
    public void testConstructLarge() throws Exception {
        Random rnd = new Random(System.currentTimeMillis());
        final int ALPHABET = 256;
        final int length = 10000;
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            char c = (char) (rnd.nextInt() % ALPHABET);
            s.append(c);
        }
        assertArrayEquals(SuffixArray.construct(s), constructNaive(s));
    }

    private int[] constructNaive(CharSequence s) {
        final int length = s.length();
        Integer[] orders = new Integer[length];
        for (int i = 0; i < length; ++i)
            orders[i] = i;
        Arrays.sort(orders, (o1, o2) -> {
            while (o1 < s.length() && o2 < s.length()) {
                if (s.charAt(o1) != s.charAt(o2))
                    return s.charAt(o1) - s.charAt(o2);
                o1++;
                o2++;
            }
            return o2 - o1;
        });

        int[] result = new int[length];
        for (int i = 0; i < length; ++i)
            result[i] = orders[i];
        return result;
    }
}
