
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        int[] order = new int[n];
        int[] stack = new int[n];
        int[] saved = new int[n];

        int top = 0;
        int count = 0;

        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];

            if (u >= 0) {
                int c = s.charAt(u) - 'a';

                saved[u] = lastSeen[c];

                if (u != 0 && lastSeen[c] != -1) {
                    newParent[u] = lastSeen[c];
                }

                lastSeen[c] = u;
                order[count++] = u;

                stack[top++] = ~u;

                List<Integer> children = tree[u];

                for (int i = children.size() - 1; i >= 0; i--) {
                    stack[top++] = children.get(i);
                }
            } else {
                int node = ~u;
                int c = s.charAt(node) - 'a';

                lastSeen[c] = saved[node];
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            ans[newParent[u]] += ans[u];
        }

        return ans;
    }
}
