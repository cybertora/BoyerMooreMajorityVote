package bench;

import algorithms.BoyerMooreMajorityVote;
import metrics.MetricsTracker;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
public class BoyerMooreMajorityBench {
    @Param({"100", "1000"})
    private int size;

    @Param({"random", "sorted"})
    private String type;

    private int[] nums;

    @Setup
    public void setup() {
        Random rand = new Random();
        nums = new int[size];
        int majority = rand.nextInt(100);
        int majorityCount = size / 2 + 1;
        for (int i = 0; i < majorityCount; i++) {
            nums[i] = majority;
        }
        for (int i = majorityCount; i < size; i++) {
            nums[i] = rand.nextInt(100) + 100;
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
            case "random":
                shuffle(nums, rand);
                break;
        }
    }

    @Benchmark
    public void measurePerformance(Blackhole blackhole) {
        MetricsTracker tracker = new MetricsTracker();
        int result = BoyerMooreMajorityVote.findMajorityElement(nums, tracker);
        blackhole.consume(result);
    }

    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    private void shuffle(int[] arr, Random rand) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
