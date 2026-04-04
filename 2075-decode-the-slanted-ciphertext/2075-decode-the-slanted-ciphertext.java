class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0 || rows == 0) return "";

        int cols = n / rows;

        char[][] matrix = new char[rows][cols];

        // Fill the matrix row-wise
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = encodedText.charAt(idx++);
            }
        }

        StringBuilder ans = new StringBuilder();

        // Traverse diagonals
        for (int col = 0; col < cols; col++) {
            int i = 0, j = col;
            while (i < rows && j < cols) {
                ans.append(matrix[i][j]);
                i++;
                j++;
            }
        }

        // Remove trailing spaces
        int end = ans.length() - 1;
        while (end >= 0 && ans.charAt(end) == ' ') {
            end--;
        }

        return ans.substring(0, end + 1);
    }
}