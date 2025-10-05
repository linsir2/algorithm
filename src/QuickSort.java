public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[i] <= pivot) i++;
            while (i < j && arr[j] >= pivot) j--;
            swap(arr, i, j);
        }
        swap(arr, j, right);
        return j;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
