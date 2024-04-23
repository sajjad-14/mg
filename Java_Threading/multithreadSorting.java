import java.util.Arrays;

class MultithreadedMergeSort extends Thread {
    private int[] arr;

    public MultithreadedMergeSort(int[] arr) {
        this.arr = arr;
    }

    public void run() {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            // Create threads for each half
            Thread leftSorter = new MultithreadedMergeSort(left);
            Thread rightSorter = new MultithreadedMergeSort(right);
            
            leftSorter.start();
            rightSorter.start();

            try {
                leftSorter.join();
                rightSorter.join();
                merge(left, right, arr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        System.out.println("Given Array: " + Arrays.toString(arr));
        Thread sorter = new MultithreadedMergeSort(arr);
        sorter.start();
        try {
            sorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
