package metrics;

public class MetricsTracker {
    private int comparisons = 0;
    private int arrayAccesses = 0;
    private long lastExecutionTimeNs = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getArrayAccesses() {
        return arrayAccesses;
    }

    public long getLastExecutionTimeNs() {
        return lastExecutionTimeNs;
    }

    public void setLastExecutionTimeNs(long timeNs) {
        this.lastExecutionTimeNs = timeNs;
    }
}