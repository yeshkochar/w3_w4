import java.util.*;

class Asset {

    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double r, double v) {
        this.name = name;
        this.returnRate = r;
        this.volatility = v;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}


class AssetSorter {

    // ---------- MERGE SORT (stable ASC return) ----------
    public static void mergeSort(
            Asset[] arr, int l, int r) {

        if (l < r) {

            int mid = (l + r) / 2;

            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            merge(arr, l, mid, r);
        }
    }


    private static void merge(
            Asset[] arr, int l, int m, int r) {

        Asset[] temp =
                new Asset[r - l + 1];

        int i = l;
        int j = m + 1;
        int k = 0;

        while (i <= m && j <= r) {

            if (arr[i].returnRate
                    <= arr[j].returnRate) {

                temp[k++] = arr[i++];

            } else {

                temp[k++] = arr[j++];
            }
        }

        while (i <= m)
            temp[k++] = arr[i++];

        while (j <= r)
            temp[k++] = arr[j++];


        for (int x = 0;
             x < temp.length;
             x++) {

            arr[l + x] = temp[x];
        }
    }



    // ---------- QUICK SORT DESC return + ASC volatility ----------
    public static void quickSort(
            Asset[] arr, int low, int high) {

        if (high - low < 10) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {

            int pivot =
                    medianOf3(arr, low, high);

            int pi =
                    partition(arr,
                            low,
                            high,
                            pivot);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    private static int partition(
            Asset[] arr,
            int low,
            int high,
            int pivotIndex) {

        Asset pivot = arr[pivotIndex];

        swap(arr, pivotIndex, high);

        int i = low;

        for (int j = low;
             j < high;
             j++) {

            if (compare(arr[j], pivot) > 0) {

                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, high);

        return i;
    }


    // DESC return, ASC volatility
    private static int compare(
            Asset a,
            Asset b) {

        if (a.returnRate != b.returnRate)
            return Double.compare(
                    a.returnRate,
                    b.returnRate);

        return -Double.compare(
                a.volatility,
                b.volatility);
    }



    // ---------- MEDIAN OF 3 ----------
    private static int medianOf3(
            Asset[] arr,
            int low,
            int high) {

        int mid =
                (low + high) / 2;

        if (arr[low].returnRate >
                arr[mid].returnRate)
            swap(arr, low, mid);

        if (arr[low].returnRate >
                arr[high].returnRate)
            swap(arr, low, high);

        if (arr[mid].returnRate >
                arr[high].returnRate)
            swap(arr, mid, high);

        return mid;
    }



    // ---------- INSERTION (for hybrid) ----------
    private static void insertionSort(
            Asset[] arr,
            int low,
            int high) {

        for (int i = low + 1;
             i <= high;
             i++) {

            Asset key = arr[i];
            int j = i - 1;

            while (j >= low &&
                    compare(arr[j], key) < 0) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }



    private static void swap(
            Asset[] arr,
            int i,
            int j) {

        Asset t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void print(
            Asset[] arr) {

        for (Asset a : arr)
            System.out.print(a + " ");

        System.out.println();
    }
}



public class PortfolioReturnSorting {

    public static void main(String[] args) {

        Asset[] assets = {

                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4),
                new Asset("MSFT", 12, 3)

        };


        // Merge sort ASC
        Asset[] copy1 = assets.clone();

        AssetSorter.mergeSort(
                copy1, 0, copy1.length - 1);

        System.out.println("Merge:");
        AssetSorter.print(copy1);


        // Quick sort DESC
        Asset[] copy2 = assets.clone();

        AssetSorter.quickSort(
                copy2, 0, copy2.length - 1);

        System.out.println("Quick:");
        AssetSorter.print(copy2);
    }
}