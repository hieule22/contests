import java.util.*;

public class AllAboutThatBase {
  
    private static final long MAX = (1L << 32) - 1;

    public static void main(String[] args) {
	AllAboutThatBase sol = new AllAboutThatBase();
	sol.solve();
    }

    private char[] bases;

    public void solve() {
	Scanner in = new Scanner(System.in);
	bases = new char[37];
	for (int i = 1; i <= 9; ++i)
	    bases[i] = (char)('0' + i);
	for (int i = 10; i <= 35; ++i)
	    bases[i] = (char)('a' + i - 10);
	bases[36] = '0';

	int n = in.nextInt();
	in.nextLine();
	for (int i = 0; i < n; ++i) {
	    analyze(in);
	}
    }

    public void analyze(Scanner in) {
	String[] tokens = in.nextLine().split(" ");
	String first = tokens[0];
	String operator = tokens[1];
	String second = tokens[2];
	String result = tokens[4];

	// System.out.println(first + " " + operator + " " + second);

	String output = "";
	for (int i = 1; i <= 36; ++i) {
	    long op1 = toBase(first, i);
	    if (op1 == -1) continue;
	    long op2 = toBase(second, i);
	    if (op2 == -1) continue;
	    long res = toBase(result, i);
	    if (res == -1) continue;
	    
	    // System.out.println("Base: " + i + " " + op1 + " " + op2 + " " + res);
	    boolean valid;
	    if (operator.equals("+")) 
		valid = (op1 + op2 == res);
	    else if (operator.equals("*"))
		valid = (op1 * op2 == res);
	    else if (operator.equals("-"))
		valid = (op1 - op2 == res);
	    else
		valid = (res * op2 == op1);

	    if (valid)
		output += bases[i];
	}

	if (output.length() == 0)
	    System.out.println("invalid");
	else
	    System.out.println(output);
    }

    private static long toBase(String rep, int base) {
	long result = 0;
	// System.out.println("Rep: " + rep + " Base :" + base);
	for (char c : rep.toCharArray()) {
	    int digit;
	    // System.out.println("c: " + c);
	    if (Character.isDigit(c))
		digit = (c - '0');
	    else
		digit = (c - 'a' + 10);

	    // System.out.println("Digit: " + digit);
	    if (base > 1 && digit + 1 > base)
		return -1;
	    if (base == 1 && digit != 1)
		return -1;
	    
	    // System.out.println("Digit: " + digit);

	    result = result * base + digit;
	    if (result > MAX)
		return -1;

	    // System.out.println("Res: " + result);
	}

	return result;
    }	    
}
