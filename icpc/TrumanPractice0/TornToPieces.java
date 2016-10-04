import java.util.*;

public class TornToPieces {
  
  public static void main(String[] args) {
    TornToPieces sol = new TornToPieces();
    sol.solve();
  }

  public void solve() {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    input.nextLine();
    int numNodes = 0;
    Map <String, Integer> map = new HashMap<String, Integer>();
    Map <Integer, String> reverseMap = new HashMap<Integer, String>();
    List<Edge> edges = new ArrayList<Edge>();
    for (int i = 0; i < n; i++) {
      String line = input.nextLine();
      if (line.indexOf(" ") == -1) {
        continue;
      }
      String[] list = line.split(" ");
      if (!map.containsKey(list[0])) {
        map.put(list[0], numNodes);
        reverseMap.put(numNodes++, list[0]);
      }
      for (int idx = 1; idx < list.length; idx++) {
        if (!map.containsKey(list[idx])) {
          map.put(list[idx], numNodes);
          reverseMap.put(numNodes++, list[idx]);
        }
        edges.add(new Edge(map.get(list[0]), map.get(list[idx])));
      }
    }

    String ss = input.next();
    String ff = input.next();
    if (!map.containsKey(ss) || !map.containsKey(ff)) {
      System.out.println("no route found");
      return;
    }
    int start = map.get(ss);
    int goal = map.get(ff);

    Node[] nodes = new Node[numNodes];
    initGraph(nodes, edges);
    int[] trace = new int[numNodes];
    Arrays.fill(trace, -1);
    boolean[] visited = new boolean[numNodes];
    
    //System.out.println("start = " + start + " finish = " + goal);
    visit(goal, nodes, visited, trace, start);
    if (!visited[start]) {
      System.out.println("no route found");
    } else {
      printTrace(start, trace, reverseMap);
      System.out.println();
    }
  }

  private void visit(int current, Node[] nodes, boolean[] visited, int[] trace, int start) {
    if (visited[start]) {
      return;
    }
    visited[current] = true;
    if (current == start) {
      return;
    }
    for (int next : nodes[current].adj) {
      if (!visited[next]) {
        trace[next] = current;
        visit(next, nodes, visited, trace, start);
      }
    }
  }

  private void printTrace(int current, int[] trace, Map<Integer, String> map) {
    if (current == -1) {
      return;
    }
    System.out.print(map.get(current) + " ");
    printTrace(trace[current], trace, map);
  }

  private void initGraph(Node[] nodes, List<Edge> edges) {
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new Node();
    }

    for (Edge edge : edges) {
      int u = edge.first;
      int v = edge.second;
      nodes[u].adj.add(v);
      nodes[v].adj.add(u);
    }
  }

  private class Node {
    private Set<Integer> adj;

    private Node() {
      adj = new HashSet<Integer>();
    }
  }

  private class Edge {
    private int first;
    private int second;

    private Edge(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }
}
