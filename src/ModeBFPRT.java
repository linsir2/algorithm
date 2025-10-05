import java.util.Arrays;

public class ModeBFPRT {
    static class ModeCount {
        int value;
        int count;
        ModeCount(int v, int c) {
            value = v;
            count = c;
        }
    }
    //对外接口：返回数组的众数
    public static int findMode(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("数组不能为空");
        ModeCount mc = findModeHelper(arr, 0, arr.length - 1);
        return mc.value;
    }
    //递归求众数
    private static ModeCount findModeHelper(int[] arr, int left, int right) {
        if (left > right) return new ModeCount(0, 0);
        if (left == right) return new ModeCount(arr[left], 1);

        //用线性时间选择(BFPRT)找一个稳定的pivot
        int n = right - left + 1;
        int medianPos = (n + 1) / 2;//中位数索引，第(n + 1) / 2个数
        int pivot = selectKth(arr, left, right, medianPos);

        int lt = left, i = left, gt = right;
        while (i <= gt) {
            if (arr[i] < pivot) swap(arr, lt++, i++);
            else if (arr[i] > pivot) swap(arr, i, gt--);
            else i++;
        }

        int countPivot = gt - lt + 1;
        ModeCount leftMode = findModeHelper(arr, left, lt - 1);
        ModeCount rightMode = findModeHelper(arr, gt + 1, right);

        ModeCount best = new ModeCount(pivot, countPivot);
        if (leftMode.count > best.count) best = leftMode;
        if (rightMode.count > best.count) best = rightMode;
        return best;
    }
    //线性时间选择(BFPRT)找第k小的数
    private static int selectKth(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int n = right - left + 1;
        int numMedians = (int)Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];

        //将数组分成n/5组，每组5个元素，找到每组的中位数
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        int pivot;
        if (numMedians == 1) pivot = medians[0];
        else pivot = selectKth(medians, 0, numMedians - 1, (numMedians + 1) / 2);

        //用pivot将数组划分为三部分：小于pivot，等于pivot，大于pivot
        int lt = left, i = left, gt = right;
        while (i <= gt) {
            if (arr[i] < pivot) swap(arr, lt++, i++);
            else if (arr[i] > pivot) swap(arr, i, gt--);
            else i++;
        }
        int numLess = lt - left; //小于pivot部分的元素个数
        int numEqual = gt - lt + 1; //从lt到gt部分为等于pivot部分的元素个数
        if (k <= numLess) return selectKth(arr, left, lt - 1, k);
        else if (k <= numLess + numEqual) return pivot;
        else return selectKth(arr, gt + 1, right, k - numLess - numEqual);
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
