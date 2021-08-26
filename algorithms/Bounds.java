package algorithms;

public class Bounds {
    /**
     * Find last occurrence of element {@code key}
     *
     * @param arr is the array in which {@code key} needs to be found
     * @param key element needs to be searched
     * @return -1 if {@code key} not found
     */
    public int upper_bound(int[] arr, int key) {
        int l = 0, r = arr.length - 1, mid, result = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid] == key) {
                result = mid;
                l = mid + 1;        // move forward
            } else if (arr[mid] > key)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return result;
    }

    /**
     * Find 1st occurrence of element {@code key}
     *
     * @param arr is the array in which {@code key} needs to be found
     * @param key element needs to be searched
     * @return -1 if {@code key} not found
     */
    private int lower_bound(int[] arr, int key) {
        int l = 0, r = arr.length - 1, mid, result = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid] == key) {
                result = mid;
                r = mid - 1;        // move backward
            } else if (arr[mid] > key)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5};
        System.out.println(new Bounds().lower_bound(arr, 3));
        System.out.println(new Bounds().upper_bound(arr, 3));
    }
}
