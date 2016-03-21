package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MaxConnectedComponent {

    private Node[] node;
    private TreeMap<Integer, Integer> minMap;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        node = new Node[n];
        minMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            node[i] = new Node(i, 0, in.nextInt());
            if (!minMap.containsKey(node[i].weight)) minMap.put(node[i].weight, 1);
            else minMap.put(node[i].weight, minMap.get(node[i].weight) + 1);
        }

        for (int i = 0; i < q; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            union(x, y);
            out.println(minMap.firstKey());
        }
    }

    private void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return;

        if (minMap.get(node[xRoot].weight) == 1) minMap.remove(node[xRoot].weight);
        else minMap.put(node[xRoot].weight, minMap.get(node[xRoot].weight) - 1);

        if (minMap.get(node[yRoot].weight) == 1) minMap.remove(node[yRoot].weight);
        else minMap.put(node[yRoot].weight, minMap.get(node[yRoot].weight) - 1);

        int total;
        if (node[xRoot].rank < node[yRoot].rank) {
            node[xRoot].parent = yRoot;
            node[yRoot].weight += node[xRoot].weight;
            total = node[yRoot].weight;
        } else if (node[xRoot].rank > node[yRoot].rank) {
            node[yRoot].parent = xRoot;
            node[xRoot].weight += node[yRoot].weight;
            total = node[xRoot].weight;
        } else {
            node[yRoot].parent = xRoot;
            node[xRoot].rank++;
            node[xRoot].weight += node[yRoot].weight;
            total = node[xRoot].weight;
        }
        if (!minMap.containsKey(total)) minMap.put(total, 1);
        else minMap.put(total, minMap.get(total) + 1);
    }

    private int find(int x) {
        if (node[x].parent != x)
            node[x].parent = find(node[x].parent);
        return node[x].parent;
    }

    class Node {
        int parent;
        int rank;
        int weight;

        public Node(int parent, int rank, int weight) {
            this.parent = parent;
            this.rank = rank;
            this.weight = weight;
        }
    }
}

