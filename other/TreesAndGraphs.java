public static class Project {
  public String name;
  public List<Project> dependencies;
}

public static List<Project> buildOrder(List<Project> projects) {
  
}

public static int countPaths(TreeNode root, int sum) {
  Map<Integer, Integer> leftPaths = countPaths(root.left);
  Map<Integer, Integer> rightPaths = countPaths(root.right);

  int result = 0;
  if (leftPaths.containsKey(sum))
    result += leftPaths.get(sum);
  if (rightPaths.containsKey(sum))
    result += rightPaths.get(sum);
  if (root.data != 0) {
    if (leftPaths.containsKey(sum - root.data))
      result += leftPaths.get(sum - root.data);
    if (rightPaths.containsKey(sum - root.data))
      result += rightPaths.get(sum - root.data);
  }
}

private static Map<Integer, Integer> countPaths(TreeNode node) {
  if (node == null) {
    return new HashMap<>();  // Returns an empty map
  }
  Map<Integer, Integer> leftPaths = countPaths(root.left);
  Map<Integer, Integer> rightPaths = countPaths(root.right);
  Map<Integer, Integer> result = new HashMap<Integer>();
  result.put(node.data, 1);

  for (Map.Entry<Integer, Integer> entry : leftPaths.entrySet()) {
    // Paths on left child without root
    if (!result.contains(entry.getKey()))
      result.put(entry.getKey(), entry.getValue());
    else
      result.put(entry.getKey(), result.get(entry.getKey()) + entry.getValue());
    // Paths on left child with root
    if (!result.contains(entry.getKe))
}
