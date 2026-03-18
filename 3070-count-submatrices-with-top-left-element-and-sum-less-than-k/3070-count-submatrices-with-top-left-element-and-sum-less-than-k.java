class Solution {
    public int countSubmatrices(int[][] grid, int k) {

        int m = grid.length, n = grid[0].length;

        // prefix sum in-place
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i > 0) grid[i][j] += grid[i - 1][j];
                if (j > 0) grid[i][j] += grid[i][j - 1];
                if (i > 0 && j > 0) grid[i][j] -= grid[i - 1][j - 1];
            }
        }

        int count = 0;

        // count valid submatrices
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] <= k) count++;
            }
        }

        return count;
    }
}