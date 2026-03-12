import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int left = 0;
        int right = (int)1e9;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (can(mid, n, edges, k)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int target, int n, int[][] edges, int k) {

        DSU dsu = new DSU(n);
        int used = 0;

        List<int[]> optional = new ArrayList<>();

        for (int[] e : edges) {

            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 1) {

                if (s < target) return false;

                if (!dsu.union(u, v)) return false; // cycle in must edges

                used++;

            } else {
                optional.add(e);
            }
        }

        List<int[]> usable = new ArrayList<>();

        for (int[] e : optional) {

            int s = e[2];

            if (s >= target)
                usable.add(new int[]{e[0], e[1], 0});
            else if (s * 2 >= target)
                usable.add(new int[]{e[0], e[1], 1});
        }

        usable.sort((a, b) -> a[2] - b[2]);

        int upgrades = 0;

        for (int[] e : usable) {

            if (dsu.union(e[0], e[1])) {

                upgrades += e[2];
                used++;

                if (upgrades > k) return false;

                if (used == n - 1) return true;
            }
        }

        return used == n - 1 && upgrades <= k;
    }
}