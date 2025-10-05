public class MinTriangulationPerimeter {
    public static double minTriangulationPerimeter(double[][] points) {
        int n = points.length;
        double[][] t = new double[n][n];
        for (int i = 1; i <= n; i++) { //两个点，t[i][i]表示点{i-1, i}
            t[i][i] = 0;
        }
        for (int p = 2; p < n; p++) { //p表示长度，但实际点数为p+1
            for (int i = 1; i <= n - p + 1; i++) { //i表示起始位置,实际是i-1
                int j = i + p - 1; //i-1+(p+1)-1， j<=n
                t[i][j] = t[i + 1][j] + perimeter(points[i - 1], points[i], points[j]);

                for (int k = i + 1; k < j; k++) {
                    double u = t[i][k] + t[k + 1][j] + perimeter(points[i - 1], points[k], points[j]);
                    if (u < t[i][j]) {
                        t[i][j] = u;
                    }
                }
            }
        }
        return t[1][n - 1];
    }

    private static double perimeter(double[] a, double[] b, double[] c) {
        return distance(a, b) + distance(c, b) + distance(a, c);
    }

    private static double distance(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
