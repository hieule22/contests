import java.io.*;
import java.util.*;

public class Multiplication {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	Multiplication solver = new Multiplication();
	solver.solve(in, out);
	out.close();
    }
    
    private static final String TERMINAL = "0";
    private String A, B, C;
    private char[][][] partial;
    private char[][] res;
    private int rows, cols;

    private void solve(Scanner in, PrintWriter out) {
	A = in.next();
	B = in.next();
	if (A.equals(TERMINAL) && B.equals(TERMINAL))
	    return;

	C = multiply(A, B);
	partial = computePartial(A, B);

	rows = 5 + 4 * B.length();
	cols = 5 + 4 * A.length();

	res = new char[rows][cols];
	for (char[] row : res)
	    Arrays.fill(row, ' ');

	int r, c;

	// Print borders
	for (c = 1; c < cols - 1; ++c)
	    res[0][c] = res[rows - 1][c] = '-';
	for (r = 1; r < rows - 1; ++r)
	    res[r][0] = res[r][cols - 1] = '|';
	res[0][0] = res[rows - 1][0]
            = res[0][cols - 1] = res[rows - 1][cols - 1] = '+';
	
	// Print A.
	r = 1;
	for (int i = 0; i < A.length(); ++i) {
	    c = 4 * (i + 1);
	    res[r][c] = A.charAt(i);
	}

	// Print B.
	c = cols - 2;
	for (int i = 0; i < B.length(); ++i) {
	    r = 4 * (i + 1);
	    res[r][c] = B.charAt(i);
	}

	// Print partials.
	for (int i = 0; i < A.length(); ++i) {
	    int c0 = 2 + 4 * i, c1 = c0 + 4; 
	    for (int j = 0; j < B.length(); ++j) {
		int r0 = 2 + 4 * j, r1 = r0 + 4;
		// Print cell borders.
		for (c = c0; c <= c1; ++c)
		    res[r0][c] = res[r1][c] = '-';
		for (r = r0; r <= r1; ++r)
		    res[r][c0] = res[r][c1] = '|';
		res[r0][c0] = res[r0][c1] = res[r1][c0] = res[r1][c1] = '+';
		// Print partial digits.
		res[r0 + 1][c0 + 1] = partial[i][j][0];
		res[r1 - 1][c1 - 1] = partial[i][j][1];
		// Print diagonal
		for (r = r0 + 1, c = c1 - 1; r < r1; ++r, --c)
		    res[r][c] = '/';
	    }
	}
		
	// Print C.
	for (int i = 0; i < A.length(); ++i) {
	    char digit = C.charAt(C.length() - i - 1);
	    c = 3 + 4 * (A.length() - 1 - i);
            res[rows - 2][c] = digit;
	    res[rows - 2][c - 2] = '/';
	}
	
	if (C.length() - A.length() - 1 < 0)
	    res[rows - 2][1] = ' ';

	for (int i = C.length() - A.length() - 1; i >= 0; --i) {
	    char digit = C.charAt(i);
	    r = 5 + 4 * (B.length() - 1 - (C.length() - A.length() - 1 - i));
	    res[r][1] = digit;
	    res[r + 2][1] = '/';
	}

	for (int i = 0; i < res.length; ++i)
	    out.println(res[i]);

	solve(in, out);
    }

    private String multiply(String s, String t) {
	long ss = Long.parseLong(s);
	long tt = Long.parseLong(t);
	return Long.toString(ss * tt);
    }

    private char[][][] computePartial(String s, String t) {
	char[][][] res = new char[s.length()][t.length()][2];
	for (int i = 0; i < s.length(); ++i) {
	    for (int j = 0; j < t.length(); ++j) {
		int product = Character.getNumericValue(s.charAt(i)) *
		    Character.getNumericValue(t.charAt(j));
		String rep = Integer.toString(product);
		res[i][j][1] = rep.charAt(rep.length() - 1);
		if (rep.length() == 2)
		    res[i][j][0] = rep.charAt(0);
		else
		    res[i][j][0] = '0';
	    }
	}
	return res;
    }
}
