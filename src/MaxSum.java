public class MaxSum {
    //动态规划，滚动数组
    public static int maxSum(int[] a) {
        int n = a.length;
        int curSum = a[0];
        int maxSum = a[0];
        for (int i = 1; i < n; i++) {
            curSum = Math.max(curSum + a[i], a[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
    /*
    * public static int maxSum(int[] a) {
    *     int[] dp = new int[a.length];
    *     dp[0] = a[0];
    *     int maxSum = dp[0];
    *     for (int i = 1; i < a.length; i++) {
    *         dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
    *         maxSum = max(maxSum, dp[i]);
    *     }
    *     return maxSum;
    * }
    * */
}
