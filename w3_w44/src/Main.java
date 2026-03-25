import java.util.*;

class Transaction {

    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}


class TransactionSorter {

    // ---------- Bubble Sort ----------
    public static void bubbleSort(List<Transaction> list) {

        int n = list.size();
        int swaps = 0;
        int passes = 0;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {

                if (list.get(j).fee > list.get(j + 1).fee) {

                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("\nBubble Sort Result:");
        printList(list);
        System.out.println("Passes = " + passes + ", Swaps = " + swaps);
    }


    // ---------- Insertion Sort ----------
    public static void insertionSort(List<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {

            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result:");
        printList(list);
    }


    // Compare fee then timestamp (stable)
    private static int compare(Transaction a, Transaction b) {

        if (a.fee != b.fee)
            return Double.compare(a.fee, b.fee);

        return a.timestamp.compareTo(b.timestamp);
    }


    // ---------- Outlier Detection ----------
    public static void findOutliers(List<Transaction> list) {

        System.out.print("\nHigh-fee outliers (>50): ");

        boolean found = false;

        for (Transaction t : list) {

            if (t.fee > 50) {
                System.out.print(t + " ");
                found = true;
            }
        }

        if (!found) System.out.print("none");

        System.out.println();
    }


    // ---------- Utility ----------
    public static void printList(List<Transaction> list) {

        for (Transaction t : list) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}


public class TransactionAuditSystem {

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 60.0, "11:00")); // outlier


        int size = transactions.size();


        // Choose algorithm based on batch size
        if (size <= 100) {

            TransactionSorter.bubbleSort(
                    new ArrayList<>(transactions));

        } else if (size <= 1000) {

            TransactionSorter.insertionSort(
                    new ArrayList<>(transactions));

        }


        TransactionSorter.findOutliers(transactions);
    }
}