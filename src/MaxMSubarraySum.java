import java.util.Arrays;

public class MaxMSubarraySum {
    static final long NEG = (long) -4e18;

    public static long maxMSubarraySum(int[] numbs, int m) {
        long[] dp = new long[m + 1];
        long[] local = new long[m + 1];
        Arrays.fill(dp, NEG);
        Arrays.fill(local, NEG);
        dp[0] = 0;

        for (int val : numbs) {
            for (int k = m; k >= 1; k--) { //k要倒序循环，否则会覆盖前一轮的值
                local[k] = Math.max(dp[k - 1] + val, local[k] + val);//dp[k - 1]相当于dp[i - 1][k - 1]
                dp[k] = Math.max(local[k], dp[k]);
            }
        }
        return dp[m];
    }
}
