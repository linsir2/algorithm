public class ChessBoardCover {
    //给骨牌编号
    static int tile = 1;
    //用二维数组表示棋盘
    static int[][] board;

    public static void cover(int topX, int topY, int specialX, int specialY, int size) {
        //递归结束条件
        if (size == 1) return;
        //给本次递归使用的骨牌编号
        int t = tile ++;
        //将棋盘分成4个部分
        int half = size / 2;
        int midX = topX + half;
        int midY = topY + half;
        //midX,midY归属于哪个象限，自己决定，通过条件中的<=判断
        //左上象限
        if (specialX < midX && specialY < midY) {
            cover(topX, topY, specialX, specialY, half);
        } else {
            board[midX - 1][midY - 1] = t;
            cover(topX, topY, midX - 1, midY - 1, half);
        }
        //右上象限
        if (specialX < midX && specialY >= midY) {
            cover(topX, midY, specialX, specialY, half);
        } else {
            board[midX - 1][midY] = t;
            cover(topX, midY, midX - 1, midY, half);
        }
        //左下象限
        if (specialX >= midX && specialY < midY) {
            cover(midX, topY, specialX, specialY, half);
        } else {
            board[midX][midY - 1] = t;
            cover(midX, topY, midX, midY - 1, half);
        }
        //右下象限
        if (specialX >= midX && specialY >= midY) {
            cover(midX, midY, specialX, specialY, half);
        } else {
            board[midX][midY] = t;
            cover(midX, midY, midX, midY, half);
        }
    }

}
