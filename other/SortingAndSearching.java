public static int find(int[] a, int target) {
  int pivot = findPivot(a);
  int leftIndex = find(target, a, 0, pivot - 1);
  if (leftIndex != -1)
    return leftIndex;
  return find(target, a, pivot, a.length - 1);
}

public static int find(int target, int[] a, int begin, int end) {
  if (begin > end)
    return -1;
  int mid = (begin + end) / 2;
  if (a[mid] == target)
    return mid;
  else if (a[mid] > target)
    return find(target, a, begin, mid - 1);
  else
    return find(target, a, mid + 1, end);
}

private static int findPivot(int[] a) {
  if (a[0] < a[a.length - 1])
    return 0;  // The array is not shifted
  int low = 0;
  int high = a.length - 1;
  while (low < high) {
    int mid = (low + high) / 2;
    if (a[mid] > a[0])
      low = mid + 1;
    else  // a[mid] <= a[0]
      high = mid;
  }
  return low;
}

public static int findFromListy(Listy list, int target) {
  int size = findSize(list);
  return binarySearch(list, target, 0, size - 1);
}

private static int findSize(Listy list) {
  int low = 0;
  int high = Integer.MAX_VALUE;
  // Finds first element a such that list.elementAt(i) == -1
  while (low < high) {
    int mid = (low + high) / 2;
    if (list.elementAt(mid) == -1)
      high = mid;
    else
      low = mid + 1;
  }
  return low;
}

class Position {
  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }
  
  int row;
  int col;
}

public static int find(int[][] mat, int target) {
  return findImpl(mat, 0, mat.length - 1, 0, mat[0].length - 1, target);
}

private static int findImpl(int[][] mat, int startRow, int endRow,
                            int startCol, int endCol, int target) {
  if (startRow > endRow || startCol > endCol)
    return new Position(-1, -1);
  int midRow = (startRow + endRow) / 2;
  int midCol = (startCol + endCol) / 2;
  if (mat[midRow][midCol] == target)
    return return new Position(midRow, midCol);
  if (mat[midRow][midCol] < target) {
    Position bottomLeft =
        findImpl(mat, midRow + 1, endRow, midCol + 1, endCol, target);
    if (bottomLeft.row != -1)
      return bottomLeft;
    Position topLeft =
        findImpl(mat, startRow, midRow, midCol + 1, endCol, target);
    if (topLeft.row != -1)
      return topLeft;
    return findImpl(mat, midRow + 1, endRow, startCol, midCol, target);
  }
}
