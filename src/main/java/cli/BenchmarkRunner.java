package cli;

import algorithms.BoyerMooreMajorityVote;
import metrics.MetricsTracker;

import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class BenchmarkRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер ввода (например, 100): ");
        int size = scanner.nextInt();
        System.out.println("Введите тип: random, sorted, reverse, nearly (по умолчанию random): ");
        scanner.nextLine(); // Очистка
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) type = "random";

        int[] nums = generateArray(size, type);

        MetricsTracker tracker = new MetricsTracker();
        long startTime = System.nanoTime();
        int result = BoyerMooreMajorityVote.findMajorityElement(nums, tracker);
        long endTime = System.nanoTime();

        System.out.println("Элемент большинства: " + result);
        System.out.println("Сравнения: " + tracker.getComparisons());
        System.out.println("Доступы к массиву: " + tracker.getArrayAccesses());
        System.out.println("Время (нс): " + (endTime - startTime));

        try (PrintWriter writer = new PrintWriter("metrics.csv")) {
            writer.println("size,type,comparisons,accesses,time_ns");
            writer.println(size + "," + type + "," + tracker.getComparisons() + "," + tracker.getArrayAccesses() + "," + (endTime - startTime));
        } catch (IOException e) {
            System.out.println("Ошибка записи CSV: " + e.getMessage());
        }
    }

    private static int[] generateArray(int size, String type) {
        Random rand = new Random();
        int[] nums = new int[size];

        int majority = rand.nextInt(100);
        int majorityCount = size / 2 + 1;
        for (int i = 0; i < majorityCount; i++) {
            nums[i] = majority;
        }
        for (int i = majorityCount; i < size; i++) {
            nums[i] = rand.nextInt(100) + 100; // Другие
        }

        switch (type) {
            case "sorted":
                java.util.Arrays.sort(nums);
                break;
            case "reverse":
                java.util.Arrays.sort(nums);
                reverse(nums);
                break;
            case "nearly":
                java.util.Arrays.sort(nums);
                if (size > 1) {
                    int temp = nums[0];
                    nums[0] = nums[1];
                    nums[1] = temp;
                }
                break;
            default:
                shuffle(nums, rand);
        }
        return nums;
    }

    private static void reverse(int[] nums) {
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
    }

    private static void shuffle(int[] nums, Random rand) {
        for (int i = nums.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}