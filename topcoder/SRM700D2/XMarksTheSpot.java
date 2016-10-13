import java.util.List;
import java.util.ArrayList;

public class XMarksTheSpot {
    public static final char LANDMARK = 'O';
    public static final char UNKNOWN = '?';
    private char[][] grid;
    private int minRow;
    private int maxRow;
    private int minCol;
    private int maxCol;
    private int height;
    private int width;
    private List<Cell> unknownCells;

    public int countArea(String[] board) {
        grid = new char[board.length][board[0].length()];
        for (int i = 0; i < grid.length; ++i)
            grid[i] = board[i].toCharArray();
        height = grid.length;
        width = grid[0].length;
        minRow = minCol = Integer.MAX_VALUE;
        maxRow = maxCol = Integer.MIN_VALUE;
        unknownCells = new ArrayList<>();

        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                if (grid[r][c] == UNKNOWN) {
                    unknownCells.add(new Cell(r, c));
                }
                if (grid[r][c] == LANDMARK) {
                    minRow = Math.min(r, minRow);
                    maxRow = Math.max(r, maxRow);
                    minCol = Math.min(c, minCol);
                    maxCol = Math.max(c, maxCol);
                }
            }
        }

        int result = 0;
        int limit = (1 << unknownCells.size());
        for (int mask = 0; mask < limit; ++mask) {
            int topRow = minRow;
            int botRow = maxRow;
            int leftCol = minCol;
            int rightCol = maxCol;
            for (int i = 0; i < unknownCells.size(); ++i) {
                if ((mask & (1 << i)) > 0) {
                    Cell cell = unknownCells.get(i);
                    topRow = Math.min(topRow, cell.row);
                    botRow = Math.max(botRow, cell.row);
                    leftCol = Math.min(leftCol, cell.col);
                    rightCol = Math.max(rightCol, cell.col);
                }
            }

            if (topRow == Integer.MAX_VALUE)
                continue;

            result += (botRow - topRow + 1) * (rightCol - leftCol + 1);
        }
        return result;
    }

    private class Cell {
        int row;
        int col;

        public Cell(int r, int c) {
            row = r;
            col = c;
        }

    }

}
