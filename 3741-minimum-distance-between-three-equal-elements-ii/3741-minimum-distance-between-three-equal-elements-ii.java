import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];

            map.putIfAbsent(val, new ArrayDeque<>());
            Deque<Integer> dq = map.get(val);

            dq.addLast(i);

            // keep only last 3 indices
            if (dq.size() > 3) {
                dq.pollFirst();
            }

            if (dq.size() == 3) {
                int first = dq.peekFirst();
                int last = dq.peekLast();
                ans = Math.min(ans, 2 * (last - first));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}