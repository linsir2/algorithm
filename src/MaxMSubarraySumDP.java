import java.util.Arrays;
public class MaxMSubarraySumDP {
    static final long NEG = (long) -4e18;

    public static long maxMSubarraySum(int[] arr, int m) {
        int n = arr.length;
        if (m <= 0) return 0;

        long[][] dp = new long[n + 1][m + 1];
        long[][] local = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], NEG);
            Arrays.fill(local[i], NEG);
        }
        // 关键：前 i 个元素选 0 段的和都是 0
        for (int i = 0; i <= n; i++) dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            int val = arr[i - 1];
            for (int k = 1; k <= m; k++) {
                if (i >= k) {
                    local[i][k] = Math.max(dp[i - 1][k - 1] + val, local[i - 1][k] + val);
                    dp[i][k] = Math.max(dp[i - 1][k], local[i][k]);
                }
            }
        }
        return dp[n][m];
    }
}
