package taskdirectory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacilityExtraction {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        in.nextLine();
        String[] facility = new String[n];
        for (int i = 0; i < n; i++) facility[i] = in.nextLine();
        String description = in.nextLine().toLowerCase();
        List<String> extracted = new ArrayList<String>(n);
        for (String f : facility) {
            Pattern p = Pattern.compile(f.toLowerCase());
            Matcher m = p.matcher(description);
            if (m.find()) extracted.add(f);
//            if (match) extracted.add(f);
        }
        Collections.sort(extracted);
        for (String f : extracted) out.println(f);

    }
}
