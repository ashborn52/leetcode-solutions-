import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> positions[a] - positions[b]);

        Stack<Integer> stack = new Stack<>();
        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);

        for (int id : idx) {

            if (directions.charAt(id) == 'R') {
                stack.push(id);
            } else {

                while (!stack.isEmpty() && alive[id]) {

                    int top = stack.peek();

                    if (healths[top] < healths[id]) {
                        alive[top] = false;
                        stack.pop();
                        healths[id]--;

                    } else if (healths[top] > healths[id]) {
                        healths[top]--;
                        alive[id] = false;

                    } else {
                        alive[top] = false;
                        alive[id] = false;
                        stack.pop();
                    }
                }
            }
        }

        // ✅ Collect in ORIGINAL order
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (alive[i]) {
                res.add(healths[i]);
            }
        }

        return res;
    }
}