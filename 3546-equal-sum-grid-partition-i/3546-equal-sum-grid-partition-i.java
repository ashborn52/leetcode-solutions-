class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        // Calculate total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        // If total is odd → impossible
        if (total % 2 != 0) return false;

        long target = total / 2;

        // Check horizontal cuts
        long prefix = 0;
        for (int i = 0; i < m - 1; i++) {
            long rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            prefix += rowSum;

            if (prefix == target) return true;
        }

        // Check vertical cuts
        prefix = 0;
        for (int j = 0; j < n - 1; j++) {
            long colSum = 0;
            for (int i = 0; i < m; i++) {
                colSum += grid[i][j];
            }
            prefix += colSum;

            if (prefix == target) return true;
        }

        return false;
    }
}