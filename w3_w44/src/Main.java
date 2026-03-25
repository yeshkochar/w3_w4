import java.util.*;

class Trade {

    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}


class TradeSorter {

    // -------- MERGE SORT (ASC) --------
    public static void mergeSort(Trade[] arr, int l, int r) {

        if (l < r) {

            int mid = (l + r) / 2;

            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            merge(arr, l, mid, r);
        }
    }


    private static void merge(Trade[] arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        Trade[] left = new Trade[n1];
        Trade[] right = new Trade[n2];

        for (int i = 0; i < n1; i++)
            left[i] = arr[l + i];

        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];


        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {

            if (left[i].volume <= right[j].volume) {

                arr[k] = left[i];
                i++;

            } else {

                arr[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k++] = left[i++];
        }

        while (j < n2) {
            arr[k++] = right[j++];
        }
    }



    // -------- QUICK SORT DESC --------
    public static void quickSort(Trade[] arr, int low, int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    private static int partition(
            Trade[] arr, int low, int high) {

        int pivot = arr[high].volume;

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j].volume > pivot) { // DESC

                i++;

                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }



    // -------- MERGE TWO SORTED LISTS --------
    public static Trade[] mergeLists(
            Trade[] a, Trade[] b) {

        Trade[] result =
                new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {

            if (a[i].volume <= b[j].volume)
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }

        while (i < a.length)
            result[k++] = a[i++];

        while (j < b.length)
            result[k++] = b[j++];

        return result;
    }



    // -------- TOTAL VOLUME --------
    public static int totalVolume(Trade[] arr) {

        int sum = 0;

        for (Trade t : arr)
            sum += t.volume;

        return sum;
    }



    public static void print(Trade[] arr) {

        for (Trade t : arr)
            System.out.print(t + " ");

        System.out.println();
    }
}



public class TradeVolumeAnalysis {

    public static void main(String[] args) {

        Trade[] trades = {

                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)

        };


        // Merge sort ASC
        Trade[] copy1 = trades.clone();

        TradeSorter.mergeSort(
                copy1, 0, copy1.length - 1);

        System.out.println("MergeSort:");
        TradeSorter.print(copy1);


        // Quick sort DESC
        Trade[] copy2 = trades.clone();

        TradeSorter.quickSort(
                copy2, 0, copy2.length - 1);

        System.out.println("QuickSort DESC:");
        TradeSorter.print(copy2);


        // Merge two lists
        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 300)
        };

        Trade[] afternoon = {
                new Trade("a1", 200),
                new Trade("a2", 400)
        };

        Trade[] merged =
                TradeSorter.mergeLists(
                        morning, afternoon);

        System.out.println("Merged:");
        TradeSorter.print(merged);


        // Total volume
        System.out.println(
                "Total volume = "
                        + TradeSorter.totalVolume(
                        merged));
    }
}