import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class E {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        private String[] tokens;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            tokens = in.next().split(",");

            int pointer = 0;
            List<Node> trees = new ArrayList<>();
            while (pointer < tokens.length) {
                Node root = new Node();
                pointer = buildTree(pointer, root);
                trees.add(root);
            }

            List<List<String>> levels = new ArrayList<>();
            for (Node root : trees) {
                traverse(root, levels, 0);
            }

            out.println(levels.size());
            for (List<String> level : levels) {
                for (String node : level)
                    out.print(node + " ");
                out.println();
            }
        }

        private void traverse(Node node, List<List<String>> levels, int depth) {
            if (levels.size() == depth) {
                levels.add(new ArrayList<>());
            }
            levels.get(depth).add(node.rep);
            for (Node child : node.children) {
                traverse(child, levels, depth + 1);
            }
        }

        private int buildTree(int pointer, Node node) {
            node.rep = tokens[pointer];
            int childrenCount = Integer.parseInt(tokens[pointer + 1]);
            node.children = new ArrayList<>(childrenCount);
            pointer += 2;
            for (int i = 0; i < childrenCount; ++i) {
                Node child = new Node();
                pointer = buildTree(pointer, child);
                node.children.add(child);
            }
            return pointer;
        }

        private class Node {
            private String rep;
            private List<Node> children;

        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private static final int BUFFER_SIZE = 32768;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(
                    new InputStreamReader(stream), BUFFER_SIZE);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

    }
}
