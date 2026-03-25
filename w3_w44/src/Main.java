import java.util.*;

class Client {

    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double balance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = balance;
    }

    public String toString() {
        return name + ":" + riskScore + "($" + accountBalance + ")";
    }
}


class ClientSorter {

    // -------- Bubble Sort ASC (riskScore) --------
    public static void bubbleSort(Client[] arr) {

        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("\nBubble Sort (ASC):");
        print(arr);
        System.out.println("Swaps = " + swaps);
    }


    // -------- Insertion Sort DESC + balance --------
    public static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {

            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    compare(arr[j], key) < 0) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (DESC risk + balance):");
        print(arr);
    }


    // compare for DESC risk, then balance
    private static int compare(Client a, Client b) {

        if (a.riskScore != b.riskScore)
            return Integer.compare(a.riskScore, b.riskScore);

        return Double.compare(a.accountBalance, b.accountBalance);
    }


    // -------- Top 10 risks --------
    public static void topRisk(Client[] arr, int k) {

        System.out.println("\nTop " + k + " highest risk:");

        for (int i = 0; i < k && i < arr.length; i++) {

            System.out.println(
                    arr[i].name +
                            " (" + arr[i].riskScore + ")"
            );
        }
    }


    // -------- Print --------
    public static void print(Client[] arr) {

        for (Client c : arr) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}


public class ClientRiskRanking {

    public static void main(String[] args) {

        Client[] clients = {

                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 9000),
                new Client("clientB", 50, 2000),
                new Client("clientD", 95, 1000),
                new Client("clientE", 70, 3000)

        };


        // Bubble sort ASC
        ClientSorter.bubbleSort(clients.clone());


        // Insertion sort DESC
        Client[] copy = clients.clone();
        ClientSorter.insertionSort(copy);


        // Top risks
        ClientSorter.topRisk(copy, 10);
    }
}