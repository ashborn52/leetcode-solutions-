class Solution {

    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) parent[pa] = pb;
        }
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        DSU dsu = new DSU(n);

        // Step 1: group equal positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] > 0) {
                    dsu.union(i, j);
                }
            }
        }

        // Step 2: assign characters
        char[] res = new char[n];
        int[] groupChar = new int[n];
        for (int i = 0; i < n; i++) groupChar[i] = -1;

        int curr = 0;

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            if (groupChar[root] == -1) {
                if (curr >= 26) return "";
                groupChar[root] = curr++;
            }
            res[i] = (char) ('a' + groupChar[root]);
        }

        String s = new String(res);

        // Step 3: validate LCP
        int[][] check = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 < n && j + 1 < n)
                        check[i][j] = 1 + check[i + 1][j + 1];
                    else
                        check[i][j] = 1;
                } else {
                    check[i][j] = 0;
                }
            }
        }

        // Step 4: compare
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return s;
    }
}