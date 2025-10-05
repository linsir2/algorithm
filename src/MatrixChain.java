public class MatrixChain {
    //外部方法：只需要传入维度数组p
    public static void solveMatrixChain(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        //调用内部方法
        matrixChain(p, n, m, s);

        System.out.println("最少乘法次数：" + m[1][n]);
        System.out.print("最优括号化方案：");
        printOptimalParens(s, 1, n);
        System.out.println();
    }
    //内部方法：动态规划计算最优值，自底向上
    private static void matrixChain(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; i++) m[i][i] = 0;

        for (int len = 2; len <= n; len++) { //矩阵链长度
            for (int i = 1; i <= n - len + 1; i++) { //矩阵链起始位置
                int j = i + len - 1; //矩阵链终止位置
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) { //k表示断点位置
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
    //打印最优括号化方案 ,自顶向下
    private static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) System.out.print("A" + i);
        else { //递归，栈
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
