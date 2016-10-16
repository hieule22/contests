import java.io.*;
import java.util.*;

public class MazeMakers {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	MazeMakers solver = new MazeMakers();
	solver.solve(in, out);
	out.close();
    }
    
    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {-1, 0, 1, 0};
    private int rows;
    private int cols;
    private Cell[][] cells;
    private Cell[] entrances;
    private boolean[][] visited;
    private boolean multiplePaths;

    private void solve(Scanner in, PrintWriter out) {
	rows = in.nextInt();
	cols = in.nextInt();
	if (rows == 0 && cols == 0) return;
	
	cells = new Cell[rows][cols];
	entrances = new Cell[2];
	for (int i = 0; i < rows; ++i) {
	    char[] line = in.next().toCharArray();
	    for (int j = 0; j < cols; ++j) {
		cells[i][j] = new Cell(line[j], i, j);
		if (isEntrance(cells[i][j])) {
		    if (entrances[0] == null)
			entrances[0] = cells[i][j];
		    else
			entrances[1] = cells[i][j];
		}
	    }
	}

	visited = new boolean[rows][cols];
	multiplePaths = false;
	Cell src = entrances[0];
	Cell dest = entrances[1];
	dfs(src, new Cell('0', -1, -1));

	if (!visited[dest.r][dest.c]) {
	    out.println("NO SOLUTION");
	} else {
	    boolean unreachableCell = false;
	    for (int i = 0; i < rows; ++i) {
		for (int j = 0; j < cols; ++j) {
		    if (!visited[i][j])
			unreachableCell = true;
		}
	    }
	    if (unreachableCell) {
		out.println("UNREACHABLE CELL");
	    } else if (multiplePaths) {
		out.println("MULTIPLE PATHS");
	    } else {
		out.println("MAZE OK");
	    }
	}

	solve(in, out);
    }

    private void dfs(Cell node, Cell pred) {
	if (visited[node.r][node.c]) {
	    multiplePaths = true;
	    return;
	}

	visited[node.r][node.c] = true;
	for (int i = 0; i < dr.length; ++i) {
	    if (!node.adj[i]) continue;
	    int r = node.r + dr[i];
	    int c = node.c + dc[i];
	    if (r >= 0 && r < rows && c >= 0 && c < cols
		&& !(r == pred.r && c == pred.c)) {
		dfs(cells[r][c], node);
	    }
	}
    }

    private boolean isEntrance(Cell cell) {
	return (cell.r == 0 && cell.adj[3]) ||  // Open up
	    (cell.r == rows - 1 && cell.adj[1]) ||  // Open down
	    (cell.c == 0 && cell.adj[0]) ||  // Open left
	    (cell.c == cols - 1 && cell.adj[2]);  // Open right
    }

    private class Cell {
	private boolean[] adj;
	private int r, c;
	
	public Cell(char tag, int r, int c) {
	    this.r = r;
	    this.c = c;
	    adj = new boolean[dr.length];
	    int rep = (isNumeric(tag)) ?
		Character.getNumericValue(tag) : (int)(tag - 'A' + 10);
	    Arrays.fill(adj, true);
	    for (int i = 0; i < dr.length; ++i) {
		if ((rep & (1 << i)) != 0) 
		    adj[i] = false;
	    }
	}
    }

    private static boolean isNumeric(char c) {
	return c >= '0' && c <= '9';
    }
}
