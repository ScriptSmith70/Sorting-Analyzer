import java.util.Arrays;
import java.util.Random;

class SortResult {
    String algorithm;
    long timeTaken;
    int comparisons;
    int swaps;

    SortResult(String algorithm, long timeTaken, int comparisons, int swaps) {
        this.algorithm = algorithm;
        this.timeTaken = timeTaken;
        this.comparisons = comparisons;
        this.swaps = swaps;
    }
}

public class SortingAnalyzer {

    static class Counter {
        int comparisons = 0;
        int swaps = 0;
    }

    // ---------- Sorting Algorithms ----------

    // Bubble Sort
    public static void bubbleSort(int[] arr, Counter c) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                c.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    c.swaps++;
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr, Counter c) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                c.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    c.swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort
public static void selectionSort(int[] arr, Counter c) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            c.comparisons++;
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        if (minIndex != i) {
            swap(arr, i, minIndex);
            c.swaps++;
        }
    }
}

    // Merge Sort
    public static void mergeSort(int[] arr, Counter c) {
        mergeSortHelper(arr, 0, arr.length - 1, c);
    }

    private static void mergeSortHelper(int[] arr, int l, int r, Counter c) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortHelper(arr, l, m, c);
            mergeSortHelper(arr, m + 1, r, c);
            merge(arr, l, m, r, c);
        }
    }

    private static void merge(int[] arr, int l, int m, int r, Counter c) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            c.comparisons++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            c.swaps++;
        }

        while (i < n1) {
            arr[k++] = L[i++];
            c.swaps++;
        }
        while (j < n2) {
            arr[k++] = R[j++];
            c.swaps++;
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, Counter c) {
        quickSortHelper(arr, 0, arr.length - 1, c);
    }

    private static void quickSortHelper(int[] arr, int low, int high, Counter c) {
        if (low < high) {
            int pi = partition(arr, low, high, c);
            quickSortHelper(arr, low, pi - 1, c);
            quickSortHelper(arr, pi + 1, high, c);
        }
    }

    private static int partition(int[] arr, int low, int high, Counter c) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            c.comparisons++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                c.swaps++;
            }
        }
        swap(arr, i + 1, high);
        c.swaps++;
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ---------- Utility Methods ----------

    private static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    private static SortResult analyze(String name, int[] arr, java.util.function.BiConsumer<int[], Counter> sorter) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Counter c = new Counter();
        long start = System.nanoTime();
        sorter.accept(copy, c);
        long end = System.nanoTime();
        return new SortResult(name, end - start, c.comparisons, c.swaps);
    }

    // ---------- Main ----------

    public static void main(String[] args) {
        int size = 10000; // change size for quick tests
        int[] arr = generateArray(size);

        SortResult[] results = new SortResult[5];
        results[0] = analyze("Bubble Sort", arr, SortingAnalyzer::bubbleSort);
        results[1] = analyze("Insertion Sort", arr, SortingAnalyzer::insertionSort);
        results[2] = analyze("Selection Sort", arr, SortingAnalyzer::selectionSort);
        results[3] = analyze("Merge Sort", arr, SortingAnalyzer::mergeSort);
        results[4] = analyze("Quick Sort", arr, SortingAnalyzer::quickSort);


        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-12s | %-12s%n", "Algorithm", "Time (ns)", "Comparisons", "Swaps");
        System.out.println("-------------------------------------------------------------");
        for (SortResult r : results) {
            System.out.printf("%-15s | %-12d | %-12d | %-12d%n", r.algorithm, r.timeTaken, r.comparisons, r.swaps);
        }
        System.out.println("-------------------------------------------------------------");
    }
}
