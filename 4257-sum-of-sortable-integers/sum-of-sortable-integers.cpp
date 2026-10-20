class Solution {
public:
    bool check(vector<int>& nums, vector<int>& a, int start, int k) {
        vector<int> pattern(k);

        for (int i = 0; i < k; i++)
            pattern[i] = a[start + i];

        vector<int> lps(k, 0);

        for (int i = 1, len = 0; i < k;) {
            if (pattern[i] == pattern[len]) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }

        int j = 0;

        for (int i = 0; i < 2 * k - 1; i++) {
            int value = nums[start + i % k];

            while (j > 0 && value != pattern[j])
                j = lps[j - 1];

            if (value == pattern[j])
                j++;

            if (j == k)
                return true;
        }

        return false;
    }

    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();

        vector<int> a = nums;
        sort(a.begin(), a.end());

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0)
                continue;

            bool ok = true;

            for (int i = 0; i < n; i += k) {
                if (!check(nums, a, i, k)) {
                    ok = false;
                    break;
                }
            }

            if (ok)
                ans += k;
        }

        return ans;
    }
};