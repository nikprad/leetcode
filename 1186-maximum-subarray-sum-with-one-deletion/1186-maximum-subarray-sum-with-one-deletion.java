class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int noDelete = arr[0];
        int oneDelete = arr[0]; 
        int maxSum = arr[0];

        for (int i = 1; i < n; i++) {
            int element = arr[i];
            int nextNoDelete = Math.max(element, noDelete + element);
            
            oneDelete = Math.max(oneDelete + element, noDelete);
            noDelete = nextNoDelete;
            
            maxSum = Math.max(maxSum, Math.max(oneDelete, noDelete));
        }
        return maxSum;
    }   
}
