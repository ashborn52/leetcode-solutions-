class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1));
        if (k > total) return "";

        char[] chars = {'a','b','c'};
        StringBuilder ans = new StringBuilder();

        int block = 1 << (n - 1);
        int idx = (k - 1) / block;
        ans.append(chars[idx]);

        k = (k - 1) % block + 1;

        for (int i = 1; i < n; i++) {
            char prev = ans.charAt(i - 1);
            block >>= 1;

            for (char c : chars) {
                if (c == prev) continue;

                if (k > block) {
                    k -= block;
                } else {
                    ans.append(c);
                    break;
                }
            }
        }

        return ans.toString();
    }
}