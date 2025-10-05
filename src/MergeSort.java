public class MergeSort {
    public void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return; //只有一个元素,这里是核心操作
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    //通过递归，利用栈的特性，从单个元素开始排序并两两合并，使得后续，所要合并的两个区间各自之间都是有序的
    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }
    }
}
