import java.util.*;

class AccountSearch {

    // ---------- LINEAR SEARCH ----------
    public static void linearSearch(
            String[] arr,
            String target) {

        int first = -1;
        int last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {

            comparisons++;

            if (arr[i].equals(target)) {

                if (first == -1)
                    first = i;

                last = i;
            }
        }

        System.out.println("\nLinear Search:");
        System.out.println(
                "First index = " + first);
        System.out.println(
                "Last index = " + last);
        System.out.println(
                "Comparisons = " + comparisons);
        System.out.println(
                "Time Complexity = O(n)");
    }



    // ---------- BINARY SEARCH ----------
    public static void binarySearch(
            String[] arr,
            String target) {

        Arrays.sort(arr); // required

        int low = 0;
        int high = arr.length - 1;

        int index = -1;
        int comparisons = 0;

        while (low <= high) {

            comparisons++;

            int mid =
                    (low + high) / 2;

            int cmp =
                    arr[mid]
                            .compareTo(target);

            if (cmp == 0) {

                index = mid;
                break;

            } else if (cmp < 0) {

                low = mid + 1;

            } else {

                high = mid - 1;
            }
        }

        int count = 0;

        if (index != -1) {

            // count duplicates
            for (String s : arr) {

                if (s.equals(target))
                    count++;
            }
        }

        System.out.println("\nBinary Search:");
        System.out.println(
                "Sorted logs = "
                        + Arrays.toString(arr));

        System.out.println(
                "Index = " + index);

        System.out.println(
                "Count = " + count);

        System.out.println(
                "Comparisons = "
                        + comparisons);

        System.out.println(
                "Time Complexity = O(log n)");
    }
}



public class AccountIdLookup {

    public static void main(String[] args) {

        String[] logs = {

                "accB",
                "accA",
                "accB",
                "accC",
                "accD",
                "accB"

        };


        AccountSearch.linearSearch(
                logs,
                "accB");

        AccountSearch.binarySearch(
                logs,
                "accB");
    }
}