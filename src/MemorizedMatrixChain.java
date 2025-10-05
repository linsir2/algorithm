public class MemorizedMatrixChain {
    public static void solveMatrixChain(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        memorizedMatrixChain(n, m, s, p);
        System.out.println("最少连乘次数：" + m[1][n]);
    }
    private static int memorizedMatrixChain(int n , int[][] m, int[][] s, int[] p) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                m[i][j] = 0;
            }
        }
        return lookUpChain(1, n, p, s, m);
    }
    private static int lookUpChain(int i, int j, int[] p, int[][] s, int[][] m) {
        if (m[i][j] > 0) return m[i][j];
        if (i == j) return 0;

        int u = lookUpChain(i, i, p, s, m) + lookUpChain(i + 1, j, p, s, m) + p[i - 1] * p[i] * p[j];
        s[i][j] = u;
        for (int k = i + 1; k < j; k++) {
            int t = lookUpChain(i, k, p, s, m) +  lookUpChain(k + 1, j, p, s, m) +  p[i - 1] * p[k] * p[j];
            if (t < u) {
                u = t;
                s[i][j] = t;
            }
        }
        m[i][j] = u;
        return u;
    }
}
