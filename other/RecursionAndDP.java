public static int findMagicIndex(int[] a) {
  return findMagicIndex(a, 0, a.length - 1);
}

public static int findMagicIndex(int[] a, int low, int high) {
  if (low > high) {
    return -1;
  }
  if (a[low] == low) {
    return low;
  } else if (a[low] > low) {
    low = a[low];
  } else {
    low++;
  }

  if (a[high] == high) {
    return high;
  } else if (a[high] > high) {
    high--;
  } else {
    high = a[high];
  }

  return findMagicIndex(low, high);
}

public static List<String> permute(String input) {
  Map<Character, Integer> frequency = new HashMap<>();
  for (char c : input.toCharArray()) {
    if (!frequency.containsKey(c))
      frequency.put(c, 1);
    else
      frequency.put(c, frequency.get(c) + 1);
  }

  List<String> result = new ArrayList<>();
  char[] buffer = new char[input.length()];
  fillBuffer(0, frequency, buffer, result);
}

private static void fillBuffer(int index, Map<Character, Integer> frequency,
                               char[] buffer, List<String> result) {
  if (index == buffer.length) {
    result.add(new String(buffer));
    return;
  }

  for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
    if (entry.second > 0) {
      buffer[index] = entry.first;
      entry.second--;
      fillBuffer(index + 1, frequency, buffer, result);
      entry.second++;  // Backtrack
    }
  }
}

public static void permuteParentheses(int n) {
  char[] buffer = new char[2 * n];
  fillBuffer(0, n, n, buffer);
}

private static void fillBuffer(int index, int open, int close, char[] buffer) {
  if (index == buffer.length) {
    print(buffer);
    return;
  }

  if (open < buffer.length / 2) {
    buffer[index] = '(';
    fillBuffer(index + 1, open + 1, close, buffer);
  }

  if (open > close) {
    buffer[index] = ')';
    fillBuffer(index + 1, open, close + 1, buffer);
  }
}

public static int countWays(int n) {
  static int[] values = {1, 5, 10, 25};
  int[][] ways = new int[n + 1][4];
  for (int i = 0; i <= n; ++i)
    ways[i][0] = 1;
  for (int i = 1; i < 4; ++i) {
    for (int j = 0; j <= n; ++j) {
      ways[j][i] = ways[j][i - 1];
      if (j >= a[i])
        ways[j][i] += ways[j - a[i]][i];
    }
  }

  return ways[n][3];
}

class Box implements Comparable<Box> {
  int heigh, width, depth;

  @Override
  public int compareTo(Box other) {
    if (height != other.height)
      return height - other.height;
    if (width != other.width)
      return width - other.width;
    return depth - other.depth;
  }

  public boolean isLessThan(Box other) {
    return height < other.height && width < other.width && depth < other.depth;
  }
}

public static int findMaxStackHeight(List<Box> boxes) {
  Collections.sort(boxes);
  int[] maxHeight = new int[boxes.size()];
  int result = 0;
  for (int i = 0; i < maxHeight.length; ++i) {
    maxHeight[i] = 1;
    for (int j = 0; i < j; ++i) {
      if (boxes.get(i).isLessThan(boxes.get(j)))
        maxHeight[i] = Math.max(maxHeight[j] + 1, maxHeight[i]);
    }
    result = Math.max(result, maxHeight[i]);
  }
  return result;
}
