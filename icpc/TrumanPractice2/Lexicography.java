import java.io.*;
import java.util.*;

public class Lexicography {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Lexicography solver = new Lexicography();
    solver.solve(in, out);
    out.close();
  }
  
  private long[] factorial = new long[17];

  private void solve(Scanner in, PrintWriter out) {
    factorial[0] = factorial[1] = 1;
    for (int i = 2; i < factorial.length; i++) {
      factorial[i] = factorial[i - 1] * i;
    }
    String word = in.next();
    while (!word.equals("#")) {
      long rank = in.nextLong();
      process(word, rank, out);

      word = in.next();
    }
  }

  private void process(String word, long rank, PrintWriter output) {
    int[] count = new int[26];
    for (int i = 0; i < word.length(); i++) {
      count[word.charAt(i) - 'A']++;
    }

    List<Character> list = new ArrayList<Character>();
    for (int i = 0; i < 26; i++) {
      if (count[i] > 0) {
        list.add((char)(i + 'A'));
      }
    }

    char[] result = new char[word.length()];
    generate(0, list, rank, count, result);
    output.println(new String(result));
  }

  private void generate(int idx, List<Character> list, long rank,
                        int[] count, char[] result) {
    if (rank <= 1) {
      int let = 0;
      for (int i = idx; i < result.length; i++) {
        while (let < 26 && count[let] == 0) let++;
        if (let == 26) {
          result[i] = '$';
          return;
        }
        result[i] = (char)(let + 'A');
        count[let]--;
      }
      return;
    }

    Pair bs = binarySearch(list, count, rank, result.length - idx - 1);
    result[idx] = bs.letter;
    count[bs.letter - 'A']--;
    List<Character> newlist = new ArrayList<Character>();
    for (int i = 0; i < 26; i++) {
      if (count[i] > 0) {
        newlist.add((char)(i + 'A'));
      }
    }
    generate(idx + 1, newlist, rank - bs.count, count, result);
  }

  private Pair binarySearch(List<Character> list, int[] count,
                            long rank, int rem) {
    int left = 0;
    int right = list.size() - 1;
    Pair result = new Pair('$', 0);
    while (left <= right) {
      int mid = (left + right) / 2;
      long cc = getCount(mid, list, count, rem);
      if (cc < rank) {
        result = new Pair(list.get(mid), cc);
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return result;
  }

  private long getCount(int idx, List<Character> list, int[] count, int rem) {
    long result = factorial[rem];
    int lessthan = 0;
    for (char letter : list) {
      if (letter < list.get(idx)){
        lessthan += count[letter - 'A'];
      } else {
        break;
      }
    }
    result *= lessthan;

    for (int i = 0; i < 26; i++) {
      result /= factorial[count[i]];
    }
    return result;
  }

  private class Pair {
    private char letter;
    private long count;

    private Pair(char letter, long count) {
      this.letter = letter;
      this.count = count;
    }
  }
}
