package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class PureAlphabet {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] s = in.next().toCharArray();
        System.out.println(Arrays.toString(s));
        for (int i = 0; i < s.length; i++) {
            char res;
            if (!Character.isAlphabetic(s[i])) res = ' ';
            else if (Character.isUpperCase(s[i])) {
                if (s[i] == 'Z') res = 'A';
                else res = (char)(s[i] + 1);
            } else {
                if (s[i] == 'z') res = 'a';
                else res = (char)(s[i] + 1);
            }
            out.print(res);
        }
        out.println();
    }
}
