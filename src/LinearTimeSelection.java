import java.util.Arrays;
public class LinearTimeSelection {
    //主函数：返回数组arr中第k小的数,k从1开始
    public static int select(int[] arr, int left, int right, int k) {
        //如果数组较小，直接排序返回
        if (right - left + 1 <= 5) {
            Arrays.sort(arr, left, right + 1); //排序左闭右开，取不到right + 1
            return arr[left + k - 1];
        }
        //将数组分为每组5个元素，取出各组中位数
        int numMedians = (int) Math.ceil((right - left + 1) / 5.0); //向上取整，确保所有元素都被分组
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(right, subLeft + 4);

            Arrays.sort(arr, subLeft, subRight + 1);
            int median = arr[subLeft + (subRight - subLeft) / 2];
            medians[i] = median;
        }
        //递归求中位数的中位数
        int pivot = select(medians, 0, numMedians - 1, (numMedians + 1) / 2);
        //分区：根据pivot将数组分成两部分
        int pivotIndex = partition(arr, left, right, pivot);
        //判断pivotIndex与k的大小关系
        int rank = pivotIndex - left + 1;
        if (rank == k) {
            return pivot;
        } else if (rank > k) {
            return select(arr, left, pivotIndex - 1, k);
        } else {
            return select(arr, pivotIndex + 1, right, k - rank);
        }
    }
    //分区函数
    private static int partition(int[] arr, int left, int right, int pivot) {
        //找到pivot的位置，将其换到最后
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);
        //将小于pivot的元素放到左边，大于pivot的元素放到右边
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
