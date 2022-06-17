public class Percolation {
    private final Boolean[][] used;
    private final Boolean[][] grid;
    private boolean percolated;
    private final int size;
    private int opened;
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        used = new Boolean[n][n];
        grid = new Boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                used[i][j] = false;
                grid[i][j] = false;
            }
        }
        percolated = false;
        size = n;
        opened = 0;
    }
    public void open(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= size || col >= size) throw new IllegalArgumentException();
        if (grid[row][col]) return;
        opened++;
        grid[row][col] = true;
        if (check(row - 1, col) || check(row + 1, col) || check(row, col - 1) || check(row, col + 1) || row == 0)dfs(row, col);
    }

    private boolean check(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) return false;
        return used[row][col];
    }

    private void dfs(int row, int col) {
        used[row][col] = true;
        if (row < size - 1 && grid[row + 1][col] && !used[row + 1][col]) dfs(row + 1, col);
        if (row > 0 && grid[row - 1][col] && !used[row - 1][col]) dfs(row - 1, col);
        if (col < size - 1 && grid[row][col + 1] && !used[row][col + 1]) dfs(row, col + 1);
        if (col > 0 && grid[row][col - 1] && !used[row][col - 1]) dfs(row, col - 1);
        if (row == size - 1) percolated = true;
    }

    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= size || col >= size) throw new IllegalArgumentException();
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= size || col >= size) throw new IllegalArgumentException();
        return used[row][col];
    }
    public int numberOfOpenSites() {
        return opened;
    }
    public boolean percolates(){
        return percolated;
    }
}
