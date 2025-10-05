import java.util.Arrays;

public class RoundRobin {
    //生成循环赛日程表
    public static int[][] schedule(int n) {
        if (n % 2 != 0) throw new IllegalArgumentException("n must be even");
        int[][] table = new int[n][n - 1];
        int[] players = new int[n];
        for (int i = 0; i < n; i++) players[i] = i + 1;

        for (int day = 0; day < n - 1; day++) {
            //当天对战情况
            for (int i = 0; i < n / 2; i++) {
                int p1 = players[i];
                int p2 = players[n - 1 - i];
                table[p1 - 1][day] = p2;
                table[p2 - 1][day] = p1;
            }
            //除了第一个人，其他人顺时针旋转一格
            int last = players[n - 1];
            for (int i = n - 1; i > 1; i--) players[i] = players[i - 1];
            players[1] = last;
        }
        return table;
    }
}
