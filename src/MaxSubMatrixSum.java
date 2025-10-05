public class MaxSubMatrixSum {
    public static int maxSubMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (n == 0 || m == 0) return 0;
        int maxSum = Integer.MIN_VALUE;
        int[] temp = new int[m];
        for (int top = 0; top < n; top++) {  //充当行指针，从上往下
            for (int i = 0; i < m; i++) temp[i] = 0;
            for (int bottom = top; bottom < n; bottom++) {
                for (int j = 0; j < m; j++) temp[j] += matrix[bottom][j]; //走完第一个循环时，每个temp元素都只加了一个元素
                int curSum = temp[0];
                int curMax = temp[0];
                for (int k = 1; k < m; k++) {
                    curSum = Math.max(curSum + temp[k], temp[k]);
                    curMax = Math.max(curMax, curSum);
                }
                maxSum = Math.max(maxSum, curMax);
            }
        }
        return maxSum;
    }
}
