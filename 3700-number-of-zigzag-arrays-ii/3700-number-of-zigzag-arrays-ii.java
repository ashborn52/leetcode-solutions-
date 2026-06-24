class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[] vec = new long[size];

        // length = 2 initial states
        for (int v = 0; v < m; v++) {
            vec[v] = v;             // last move is up: previous value < v
            vec[m + v] = m - 1 - v; // last move is down: previous value > v
        }

        long[][] trans = new long[size][size];

        for (int prev = 0; prev < m; prev++) {
            for (int cur = prev + 1; cur < m; cur++) {
                trans[cur][m + prev] = 1;       // down -> up
                trans[m + prev][cur] = 1;       // up -> down
            }
        }

        long[] ansVec = matPowVec(trans, vec, n - 2);

        long ans = 0;
        for (long x : ansVec) ans = (ans + x) % MOD;
        return (int) ans;
    }

    private long[] matPowVec(long[][] mat, long[] vec, long exp) {
        while (exp > 0) {
            if ((exp & 1) == 1) vec = mul(mat, vec);
            mat = mul(mat, mat);
            exp >>= 1;
        }
        return vec;
    }

    private long[] mul(long[][] a, long[] v) {
        int n = v.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if (a[i][j] != 0 && v[j] != 0) {
                    sum = (sum + a[i][j] * v[j]) % MOD;
                }
            }
            res[i] = sum;
        }
        return res;
    }

    private long[][] mul(long[][] a, long[][] b) {
        int n = a.length;
        long[][] c = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
}