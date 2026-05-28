class Solution {
    public int[] findDiagonalOrder(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        int[] ans = new int[m * n];
        int index = 0;

        // Total diagonals
        for (int d = 0; d < m + n - 1; d++) {

            int r, c;

            // Starting point
            if (d < n) {
                r = 0;
                c = d;
            } else {
                r = d - n + 1;
                c = n - 1;
            }

            ArrayList<Integer> temp = new ArrayList<>();

            // Collect diagonal
            while (r < m && c >= 0) {
                temp.add(mat[r][c]);
                r++;
                c--;
            }

            // Reverse even diagonals
            if (d % 2 == 0) {
                for (int i = temp.size() - 1; i >= 0; i--) {
                    ans[index++] = temp.get(i);
                }
            } else {
                for (int num : temp) {
                    ans[index++] = num;
                }
            }
        }

        return ans;
    }
}