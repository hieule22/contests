import java.io.*;
import java.util.*;

public class FunHouse {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	FunHouse solver = new FunHouse();
	solver.solve(in, out);
	out.close();
    }
    
    private int rows;
    private int cols;
    private char[][] grid;
    private int r, c, dr, dc;
    private int testnum = 0;

    private void solve(Scanner in, PrintWriter out) {
	cols = in.nextInt();
	rows = in.nextInt();
	testnum++;
	if (cols == 0 && rows == 0)
          return;
	
	grid = new char[rows][cols];
	for (int i = 0; i < rows; ++i) {
	    grid[i] = in.next().toCharArray();
	    for (int j = 0; j < cols; ++j) {
		if (grid[i][j] == '*') {
		    r = i;
		    c = j;
		}
	    }
	}
	    
	if (r == 0) {
	    dr = 1;
	    dc = 0;
	} else if (r == rows - 1) {
	    dr = -1;
	    dc = 0;
	} else if (c == 0) {
	    dr = 0;
	    dc = 1;
	} else if (c == cols - 1) {
	    dr = 0;
	    dc = -1;
	}

	do {
	    if (isMirror(r, c)) {
		update(grid[r][c]);
	    }
	    r += dr;
	    c += dc;
	} while (!isWall(r, c));
	
	out.println("HOUSE " + testnum);
	for (int i = 0; i < rows; ++i) {
	    for (int j = 0; j < cols; ++j) {
		if (i == r && j == c) {
		    out.print("&");
		} else {
		    out.print(grid[i][j]);
		}
	    }
	    out.println();
	}

	solve(in, out);
    }

    private boolean isWall(int rr, int cc) {
	return rr == 0 || rr == rows - 1 || cc == 0 || cc == cols - 1;
    }

    private boolean isMirror(int rr, int cc) {
	return grid[rr][cc] == '\\' || grid[rr][cc] == '/';
    }

    private void update(char mirror) {
	if (dr == 1 && dc == 0) {
	    dr = 0;
	    dc = (mirror == '/') ? -1 : 1;
	} else if (dr == -1 && dc == 0) {
	    dr = 0;
	    dc = (mirror == '/') ? 1 : -1;
	} else if (dr == 0 && dc == 1) {
	    dc = 0;
	    dr = (mirror == '/') ? -1 : 1;
	} else {
	    dc = 0;
	    dr = (mirror == '/') ? 1 : -1;
	}
    }
}
