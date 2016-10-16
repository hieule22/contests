import java.io.*;
import java.util.*;

public class WetTiles {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	WetTiles solver = new WetTiles();
	solver.solve(in, out);
	out.close();
    }
    
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {1, 0, -1, 0};

    private int width, height, t, l, w;
    private boolean[][] visited;

    private void solve(Scanner in, PrintWriter out) {
	width = in.nextInt();
	if (width == -1) return;
	height = in.nextInt();
	t = in.nextInt(); // Number of minutes
	l = in.nextInt(); // Number of leaks
	w = in.nextInt(); // Number of walls

	boolean[][] visited = new boolean[width + 1][height + 1];
	Queue<Point> frontier = new LinkedList<>();
	for (int i = 0; i < l; ++i) {
	    frontier.add(new Point(in.nextInt(), in.nextInt()));
	}

	for (int i = 0; i < w; ++i) {
	    int x0 = in.nextInt();
	    int y0 = in.nextInt();
	    int x1 = in.nextInt();
	    int y1 = in.nextInt();

	    int dx = (x1 == x0) ? 0 : (x1 > x0 ? 1 : -1);
	    int dy = (y1 == y0) ? 0 : (y1 > y0 ? 1 : -1);
	    for (int x = x0, y = y0; x != x1 || y != y1; x += dx, y +=dy) {
		visited[x][y] = true;
	    }
	    visited[x1][y1] = true;
	}

	int result = 0;
	int time = 0;
	while (!frontier.isEmpty() && time < t) {
	    result += frontier.size();
	    ++time;
	    Queue<Point> next = new LinkedList<>();
	    for (Point point : frontier) {
		for (int i = 0; i < dx.length; ++i) {
		    int x = point.x + dx[i];
		    int y = point.y + dy[i];
		    if (x > 0 && x <= width && y > 0 &&
                        y <= height && !visited[x][y]) {
			visited[x][y] = true;
			next.add(new Point(x, y));
		    }
		}
	    }
	    frontier = next;
	}

	out.println(result - l);
	solve(in, out);
    }

    private class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
    }
}
