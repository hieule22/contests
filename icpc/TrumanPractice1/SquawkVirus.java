import java.util.*;
import java.io.*;

public class SquawkVirus {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	SquawkVirus solver = new SquawkVirus();
	solver.solve(in, out);
	out.close();
    }

    private int n;
    private int m;
    private int s;
    private int t;

    List<List<Integer>> graph;

    public void solve(Scanner in, PrintWriter out) {
	n = in.nextInt();
	m = in.nextInt();
	s = in.nextInt();
	t = in.nextInt();
	
	graph = new ArrayList<List<Integer>>();
	for (int i = 0; i < n; ++i)
	    graph.add(new ArrayList<Integer>());

	for (int i = 0; i < m; ++i) {
	    int x = in.nextInt();
	    int y = in.nextInt();
	    graph.get(x).add(y);
	    graph.get(y).add(x);
	}

	long[] count = new long[n];
	count[s] = 1;
	for (int i = 0; i < t; ++i) {
	    long[] frontier = new long[n];
	    for (int node = 0; node < n; ++node) {
		if (count[node] == 0) continue;
		for (int neighbor : graph.get(node))
		    frontier[neighbor] += count[node];
	    }
	    count = frontier;
	}

	long result = 0;
	for (long cnt : count)
	    result += cnt;

	out.println(result);
    }
}
