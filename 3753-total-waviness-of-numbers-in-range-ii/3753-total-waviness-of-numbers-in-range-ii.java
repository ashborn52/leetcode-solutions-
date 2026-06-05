class Solution {

    String s;
    long[][][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x < 0) return 0;

        s = String.valueOf(x);

        memo = new long[s.length()][2][2][11][11][2];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int p1 = 0; p1 < 11; p1++) {
                        for (int p2 = 0; p2 < 11; p2++) {
                            Arrays.fill(memo[i][j][k][p1][p2], -1);
                        }
                    }
                }
            }
        }

        return dfs(0, 1, 0, 10, 10, 1)[1];
    }

    private long[] dfs(int pos, int tight, int started,
                       int prev1, int prev2, int dummy) {

        if (pos == s.length()) {
            return new long[]{1, 0};
        }

        if (memo[pos][tight][started][prev1][prev2][0] != -1) {
            return new long[]{
                    memo[pos][tight][started][prev1][prev2][0],
                    memo[pos][tight][started][prev1][prev2][1]
            };
        }

        long totalCount = 0;
        long totalWave = 0;

        int limit = tight == 1 ? s.charAt(pos) - '0' : 9;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                long[] nxt = dfs(pos + 1, ntight, 0, 10, 10, 1);

                totalCount += nxt[0];
                totalWave += nxt[1];

            } else {

                int nstarted = 1;

                long add = 0;

                if (prev2 != 10) {

                    boolean peak =
                            prev1 > prev2 && prev1 > d;

                    boolean valley =
                            prev1 < prev2 && prev1 < d;

                    if (peak || valley) add = 1;
                }

                int nprev2 = prev1;
                int nprev1 = d;

                long[] nxt = dfs(
                        pos + 1,
                        ntight,
                        nstarted,
                        nprev1,
                        nprev2,
                        1
                );

                totalCount += nxt[0];
                totalWave += nxt[1] + add * nxt[0];
            }
        }

        memo[pos][tight][started][prev1][prev2][0] = totalCount;
        memo[pos][tight][started][prev1][prev2][1] = totalWave;

        return new long[]{totalCount, totalWave};
    }
}