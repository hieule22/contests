package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class REBXOR {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            max = Math.max(max, a[i]);
        }

        int[] prefix = new int[n];
        prefix[0] = a[0];
        for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] ^ a[i];

        int height = Integer.toBinaryString(max).length();
        int[] before = new int[n];
        Trie t1 = new Trie(height);
        t1.insert(0);
        before[0] = prefix[0];
        t1.insert(prefix[0]);
        for (int i = 1; i < n; i++) {
            before[i] = Math.max(before[i - 1], t1.getMax(prefix[i]));
            t1.insert(prefix[i]);
        }

        int[] after = new int[n];
        Trie t2 = new Trie(height);
        t2.insert(0);
        after[n - 1] = a[n - 1];
        t2.insert(a[n - 1]);
        for (int i = n - 2; i >= 1; i--) {
            int suffix = prefix[n - 1] ^ prefix[i - 1];
            after[i] = Math.max(after[i + 1], t2.getMax(suffix));
            t2.insert(suffix);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++)
            res = Math.max(res, before[i] + after[i + 1]);
        out.print(res);
    }
}

class Trie {

    Node root;

    public Trie(int height) {
        root = new Node(height);
    }

    void insert(int x) {
        root.insert(x);
    }

    int getMax(int x) {
        return root.getMax(x);
    }

    class Node {
        int height;
        Node[] child;

        public Node(int height) {
            this.height = height;
            child = new Node[2];
        }

        void insert(int x) {
            if (height == 0) return;
            if ((x & (1 << (height - 1))) > 0) {    // The height-th bit is setPos
                if (child[1] == null) child[1] = new Node(height - 1);
                child[1].insert(x);
            } else {
                if (child[0] == null) child[0] = new Node(height - 1);
                child[0].insert(x);
            }
        }

        int getMax(int x) {
            if (height == 0) return 0;
            if ((x & (1 << (height - 1))) > 0) {
                if (child[0] != null) return (1 << (height - 1)) + child[0].getMax(x);
                else return child[1].getMax(x);
            } else {
                if (child[1] != null) return (1 << (height - 1)) + child[1].getMax(x);
                else return child[0].getMax(x);
            }
        }
    }
}
