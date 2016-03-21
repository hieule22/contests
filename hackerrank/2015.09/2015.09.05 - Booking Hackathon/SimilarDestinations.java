package taskdirectory;

import java.util.*;
import java.io.PrintWriter;

public class SimilarDestinations {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        in.nextLine();
        List<Dest> dest = new ArrayList<Dest>();
        Map<String, Integer> set = new HashMap<String, Integer>();
        int cnt = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String name = line.split(":")[0];
            line = line.substring(name.length() + 1);
            Dest newDest = new Dest(name);
            for (String tag : line.split(",")) {
                newDest.tags.add(tag);
                if (!set.containsKey(tag)) {
                    set.put(tag, cnt);
                    cnt ++;
                }
            }
            dest.add(newDest);
        }
        Collections.sort(dest);
        int m = dest.size();

        Set<String> idSet = new HashSet<String>();
        List<List<String>> tagList = new ArrayList<List<String>>();
        for (int i = 0; i < m; i++) {
            Dest root = dest.get(i);
            for (int j = i + 1; j < m; j++) {
                Dest target = dest.get(j);
                int common = 0;
                int[] id = new int[set.size()];
                List<String> tags = new ArrayList<String>();
                for (String tag : root.tags)
                    if (target.tags.contains(tag)) {
                        common ++;
                        id[set.get(tag)] = 1;
                        tags.add(tag);
                    }
                if (common < n) continue;
                String s = "";
                for (int x : id) s += x;
                if (idSet.contains(s)) continue;
                idSet.add(s);
                tagList.add(tags);
            }
        }

        List<Group> groups = new ArrayList<Group>();
        for (List<String> tags : tagList) {
            Group group = new Group(tags.size());
            for (int i = 0; i < m; i++) {
                boolean isOk = true;
                for (String tag : tags)
                    if (!dest.get(i).tags.contains(tag)) {
                        isOk = false;
                        break;
                    }
                if (isOk) group.names.add(dest.get(i).name);
            }
            group.tags = tags;
            Collections.sort(group.tags);
            groups.add(group);
        }
        
        Collections.sort(groups);
        for (Group group : groups) {
            for (int i = 0; i < group.names.size() - 1; i++) out.print(group.names.get(i) + ",");
            out.print(group.names.get(group.names.size() - 1) + ":");
            for (int i = 0; i < group.tags.size() - 1; i++) out.print(group.tags.get(i) + ",");
            out.println(group.tags.get(group.tags.size() - 1));
        }
    }
}

class Dest implements Comparable<Dest> {
    String name;
    Set<String> tags;

    public Dest(String name) {
        this.name = name;
        tags = new HashSet<String>();
    }

    @Override
    public int compareTo(Dest dest) {
        return name.compareTo(dest.name);
    }
}

class Group implements Comparable<Group>{
    int length;
    List<String> names;
    List<String> tags;

    public Group(int length) {
        this.length = length;
        names = new LinkedList<String>();
        tags = new LinkedList<String>();
    }

    @Override
    public int compareTo(Group group) {
        if (length != group.length) return group.length - length;
        for (int i = 0; i < Math.min(names.size(), group.names.size()); i++)
            if (!names.get(i).equals(group.names.get(i)))
                return names.get(i).compareTo(group.names.get(i));
        return 0;
    }
}
