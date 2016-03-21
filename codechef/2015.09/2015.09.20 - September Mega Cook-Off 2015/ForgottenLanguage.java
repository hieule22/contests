package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForgottenLanguage {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<String> words = new ArrayList<String>();
        Map<String, Boolean> dict = new HashMap<String, Boolean>();
        for (int i = 0; i < n; i++) {
            String word = in.next();
            words.add(word);
            dict.put(word, false);
        }
        for (int i = 0; i < k; i++) {
            int l = in.nextInt();
            for (int j = 0; j < l; j++) {
                String word = in.next();
                if (dict.containsKey(word)) dict.put(word, true);
            }
        }
        for (String word : words) {
            if (dict.get(word)) out.print("YES ");
            else out.print("NO ");
        }
        out.println();
    }
}
