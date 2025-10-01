package cli;

import algorithms.BoyerMooreMajorityVote;
import metrics.MetricsTracker;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i % 100;
        }

        MetricsTracker tracker = new MetricsTracker();

        long startTime = System.nanoTime();
        int majorityElement = BoyerMooreMajorityVote.findMajorityElement(array);
        long endTime = System.nanoTime();

        System.out.println("Majority Element: " + majorityElement);
        System.out.println("Execution Time: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Array Accesses: " + tracker.getArrayAccesses());
    }
}
