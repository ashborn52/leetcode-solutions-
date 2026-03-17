class Solution {
    public int largestSubmatrix(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;

        // Step 1: build heights
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        int maxArea = 0;

        // Step 2: process each row
        for (int i = 0; i < m; i++) {

            int[] row = matrix[i].clone();
            java.util.Arrays.sort(row);

            // Step 3: try all widths
            for (int j = 0; j < n; j++) {
                int height = row[j];
                int width = n - j;
                maxArea = Math.max(maxArea, height * width);
            }
        }

        return maxArea;
    }
}