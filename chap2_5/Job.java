package chap2_5;//2.5.12(page 355)

public class Job implements Comparable<Job> {
    private final String jobName;
    private final double processingTime;

    public Job(String jobName, double processingTime) {
        if (processingTime < 0)
            throw new IllegalArgumentException();

        this.jobName = jobName;
        this.processingTime = processingTime;
    }

    public String getJobName() {
        return jobName;
    }

    public double getProcessingTime() {
        return processingTime;
    }

    public int compareTo(Job that) {
        if (this.processingTime < that.processingTime) return -1;
        if (this.processingTime > that.processingTime) return 1;
        return 0;
    }

    public String toString() {
        return String.format("%s %.1f", jobName, processingTime);
    }
}