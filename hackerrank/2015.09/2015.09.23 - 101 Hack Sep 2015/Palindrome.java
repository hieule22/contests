package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Palindrome {

    private Map<Long, Integer> prefix;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] s = in.next().toCharArray();
        int[] freq = new int[26];
        prefix = new HashMap<Long, Integer>();
        prefix.put((long)0, 1);

        long cnt = 0;
        for (int i = 0; i < s.length; i++) {
            int pos = s[i] - 'a';
            freq[pos] ^= 1;
            long mask = getMask(freq);
            cnt += count(mask);
            if (!prefix.containsKey(mask)) prefix.put(mask, 1);
            else prefix.put(mask, prefix.get(mask) + 1);
        }

        long total = (long)s.length * (s.length + 1) / 2;
        long gcd = findGcd(cnt, total);
        while (gcd > 1) {
            cnt /= gcd;
            total /= gcd;
            gcd = findGcd(cnt, total);
        }
        out.println(cnt + "/" + total);
    }

    private long getMask(int[] freq) {
        long res = 0;
        for (int i = 0; i < 26; i++)
            if (freq[i] == 1) res += (1 << i);
        return res;
    }

    private int count(long mask) {
        int res = 0;
        if (prefix.containsKey(mask)) res += prefix.get(mask);
        for (int i = 0; i < 26; i++) {
            long alternate;
            if ((mask & (1 << i)) == 0) alternate = mask + (1 << i);
            else alternate = mask - (1 << i);
            if (prefix.containsKey(alternate)) res += prefix.get(alternate);
        }
//        System.out.println(res);
        return res;
    }

    private long findGcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
