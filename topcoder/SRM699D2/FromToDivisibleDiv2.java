import java.util.List;
import java.util.ArrayList;

public class FromToDivisibleDiv2
{
    public int shortest(int N, int S, int T, int[] a, int[] b) {
        int length = a.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < length; ++j) {
                if (lcm(b[i], a[j]) <= N)
                    neighbors.add(j);
            }
            graph.add(neighbors);
        }

        List<Integer> frontier = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            if (S % a[i] == 0)
                frontier.add(i);
        }

        boolean[] dest = new boolean[length];
        for (int i = 0; i < length; ++i) {
            if (T % b[i] == 0)
                dest[i] = true;
        }

        int result = 0;
        boolean[] visited = new boolean[length];
        while (!frontier.isEmpty()) {
            ++result;
            List<Integer> next = new ArrayList<>();
            for (int node : frontier) {
                if (dest[node])
                    return result;
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        next.add(neighbor);
                    }
                }
            }
            frontier = next;
        }
        return -1;
    }

    private static long lcm(int a, int b) {
        int gcd = gcd(a, b);
        return (long) a * b / gcd;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
