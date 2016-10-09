import java.util.*;
import java.io.*;

public class Rings {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	Rings solver = new Rings();
	solver.solve(in, out);
	out.close();
    }

    public static final char EMPTY = '.';

    private int rows;
    private int cols;
    private char[][] grid;
    private int[][] res;
    List<Cell> frontier;

    private void solve(Scanner in, PrintWriter out) {
	rows = in.nextInt();
	cols = in.nextInt();
	
	grid = new char[rows][cols];
	res = new int[rows][cols];
	for (int i = 0; i < rows; ++i)
	    grid[i] = in.next().toCharArray();
	
	frontier = new ArrayList<>();
	for (int i = 0; i < rows; ++i) {
	    for (int j = 0; j < cols; ++j) {
		if (grid[i][j] == EMPTY)
		    frontier.add(new Cell(i, j));
	    }
	}

	for (int i = -1; i < cols + 1; ++i) {
	    frontier.add(new Cell(-1, i));
	    frontier.add(new Cell(rows, i));
	}

	for (int i = -1; i < rows + 1; ++i) {
	    frontier.add(new Cell(i, -1));
	    frontier.add(new Cell(i, cols));
	}

	int ring = 0;
	while (!frontier.isEmpty()) {
	    ring++;
	    search(ring);
	}
	
	int width = (ring < 10) ? 2 : 3;
	for (int i = 0; i < rows; ++i) {
	    for (int j = 0; j < cols; ++j) {
		if (res[i][j] == 0) {
		    for (int k = 0; k < width; ++k)
			out.print(EMPTY);
		    continue;
		}
		String output = Integer.toString(res[i][j]);
		while (output.length() < width)
		    output = "." + output;

		out.print(output);
	    }
	    out.println();
	}
    }

    private int[] dr = {-1, 0, 1, 0};
    private int[] dc = {0, -1, 0, 1};

    private void search(int ring) {
	List<Cell> next = new ArrayList<>();
	for (Cell cell : frontier) {
	    for (int i = 0; i < dr.length; ++i) {
		int r = cell.r + dr[i];
		int c = cell.c + dc[i];
		if (validate(r, c) && grid[r][c] != EMPTY && res[r][c] == 0) {
		    res[r][c] = ring;
		    next.add(new Cell(r, c));
		}
	    }
	}
	frontier = next;
    }

    private boolean validate(int r, int c) {
	return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private class Cell {
	private int r, c;

	public Cell(int r, int c) {
	    this.r = r;
	    this.c = c;
	}
    }
}
