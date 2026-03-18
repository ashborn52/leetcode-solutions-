import java.util.*;

class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int index, int[] nums, List<Integer> curr, List<List<Integer>> res) {
        
        res.add(new ArrayList<>(curr)); // add current subset

        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);              // choose
            backtrack(i + 1, nums, curr, res);
            curr.remove(curr.size() - 1);  // backtrack
        }
    }
}