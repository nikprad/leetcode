
import java.util.*;

class Solution {

    private static final long MOD1 = 1000000007L;
    private static final long MOD2 = 1000000009L;
    private static final long BASE = 911382323L;

    private long[] h1, h2, p1, p2;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];

        p1[0] = p2[0] = 1;

        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * BASE + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE + nums[i]) % MOD2;

            p1[i + 1] = p1[i] * BASE % MOD1;
            p2[i + 1] = p2[i] * BASE % MOD2;
        }

        int low = 1, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (check(n, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean check(int n, int len) {

        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            int j = i + len;

            long x = (h1[j] - h1[i] * p1[len] % MOD1
                      + MOD1) % MOD1;

            long y = (h2[j] - h2[i] * p2[len] % MOD2
                      + MOD2) % MOD2;

            long key = (x << 32) | y;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
