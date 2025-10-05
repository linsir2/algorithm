import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] arr = {-1, 5, -8, 9, 5, 7, 8, -9};
        long i = MaxMSubarraySumDP.maxMSubarraySum(arr, 1);
        long j = MaxMSubarraySum.maxMSubarraySum(arr, 1);
        System.out.println(i);
        System.out.println(j);
    }
}
