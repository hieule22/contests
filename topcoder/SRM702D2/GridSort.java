import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridSort {
    public String sort(int n, int m, int[] grid) {
        if (n == 1 || m == 1)
            return "Possible";

        int rows = n, cols = m;
        List<Integer> differences = null;
        for (int i = 0; i < rows; ++i) {
            int[] row = new int[cols];
            for (int j = 0; j < cols; ++j) {
                row[j] = getElement(grid, i, j, cols);
            }
            Arrays.sort(row);
            if (row[0] % cols != 1)
                return "Impossible";
            // Check all numbers in the same row are consecutive.
            if (row[cols - 1] - row[0] != cols - 1)
                return "Impossible";

            if (differences == null) {
                differences = new ArrayList<>();
                for (int j = 1; j < cols; ++j)
                    differences.add(getElement(grid, i, j, cols)
                                    - getElement(grid, i, j - 1, cols));
            } else {
                for (int j = 1; j < cols; ++j) {
                    int difference = getElement(grid, i, j, cols)
                        - getElement(grid, i, j - 1, cols);
                    if (difference != differences.get(j - 1))
                        return "Impossible";
                }
            }
        }
        return "Possible";
    }

    private int getElement(int[] grid, int row, int col, int cols) {
        int index = row * cols + col;
        return grid[index];
    }
}
