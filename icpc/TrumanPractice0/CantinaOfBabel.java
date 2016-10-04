import java.util.*;

public class CantinaOfBabel {
  
    public static void main(String[] args) {
	CantinaOfBabel sol = new CantinaOfBabel();
	sol.solve();
    }

    private Map<String, List<String>> langClass;
    private Map<Integer, String> indexToLang;
    private Map<String, Integer> langToIndex;

    private int langCount;
    private boolean[][] graph;
    private boolean[] visited;
	
    public void solve() {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	in.nextLine();
	langClass = new HashMap<>();
	indexToLang = new HashMap<>();
	langToIndex = new HashMap<>();

	langCount = 0;
	graph = new boolean[100][100];
	for (int i = 0; i < n; ++i) {
	    String[] tokens = in.nextLine().split(" ");
	    String name = tokens[0];
	    String mainLang = tokens[1];
	    // System.err.println("Name: " + name + " Main lang: " + mainLang);
	    int mainIndex = getIndex(mainLang);
	    langClass.get(mainLang).add(name);
	    
	    for (int j = 2; j < tokens.length; ++j) {
		String otherLang = tokens[j];
		int otherIndex = getIndex(otherLang);
		graph[mainIndex][otherIndex] = true;
	    }
	}
	
	for (int k = 0; k < langCount; ++k)
	    for (int i = 0; i < langCount; ++i)
		for (int j = 0; j < langCount; ++j)
		    graph[i][j] = (graph[i][j] || (graph[i][k] && graph[k][j]));

	visited = new boolean[100];
	int size = Integer.MIN_VALUE;
	for (int i = 0; i < langCount; ++i) {
	    if (!visited[i]) {
		size = Math.max(explore(i), size);
	    }
	}
	
	System.out.println(n - size);
    }

    private int explore(int node) {
	if (visited[node])
	    return 0;

	visited[node] = true;
	String lang = indexToLang.get(node);
	int result = langClass.get(lang).size();
	for (int i = 0; i < langCount; ++i) {
	    if (graph[node][i] && graph[i][node])
		result += explore(i);
	}
	return result;
    }

    private int getIndex(String lang) {
	if (langToIndex.containsKey(lang))
	    return langToIndex.get(lang);

	langClass.put(lang, new ArrayList<String>());
	indexToLang.put(langCount, lang);
	langToIndex.put(lang, langCount);
	++langCount;
	return langCount - 1;
    }	   
}
