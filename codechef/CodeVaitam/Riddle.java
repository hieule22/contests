import java.util.Scanner;
import java.math.BigInteger;

public class Riddle
{
	private static char[] operands = new char[] {'/', '*', '+', '-'};

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int i = 0; i < t; i++)
		{
			int n = sc.nextInt();

			BigInteger res = BigInteger.valueOf(sc.nextInt());
			BigInteger num;

			int cnt = 0, index = 0;
			for (int j = 1; j < n; j++) {
				char cur_op = operands[index];
				cnt++;
				if (cnt == 4) {
					cnt = 0;
					index = (index + 2) % 4;
				} else {
					index = (index + 1) % 4;
				}

				num = BigInteger.valueOf(sc.nextInt());

				if (cur_op == '+') 
					res = res.add(num);
				else if (cur_op == '-')
					res = res.subtract(num);
				else if (cur_op == '*')
					res = res.multiply(num);
				else 
					res = res.divide(num);
			}

			System.out.println(res.toString());
		}

	}
}