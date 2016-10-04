public static int countBits(int n) {
  int result = 0;
  while (n != 0) {
    if (n & 1)
      result++;
    n >>= 1;
  }
  return result;
}

public static int maximizeExpression(String[] tokens) {
  
}

private static maximizeExpression(String[] tokens, int begin, int end) {
  if (begin == end) {
    return Integer.parseInt(tokens[i]);
  }
  int result = Integer.MIN_VALUE;
  for (int i = begin + 1; i < end; i += 2) {
    int temp;
    char operator = tokens[i].charAt(0);
    if (operator == '+') {
      temp = maximizeExpression(tokens, begin, i - 1) +
          maximizeExpression(tokens, i + 1, a.length - 1);
    } else if (operator == '-') {
      temp = maximizeExpression(tokens, 0, i - 1) -
          minimizeExpression(tokens, i + 1, a.length - 1);
    }
    result = Math.max(result, temp);
  }
  return result; 
}
