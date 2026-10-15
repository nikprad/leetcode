class Solution {
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(freq.values());
        counts.sort(Collections.reverseOrder());

        int removed = 0;
        int ans = 0;
        int half = arr.length / 2;

        for (int count : counts) {
            removed += count;
            ans++;

            if (removed >= half) {
                break;
            }
        }

        return ans;
    }
}