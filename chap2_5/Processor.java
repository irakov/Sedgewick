package chap2_5;//2.5.13(page 355)

public class Processor implements Comparable<Processor> {
    private double load = 0;
    private Queue<Job> jobs = new Queue<Job>();

    public void add(Job job) {
        jobs.enqueue(job);
        load += job.getProcessingTime();
    }

    public int compareTo(Processor that) {
        if (this.load < that.load) return -1;
        if (this.load > that.load) return +1;
        return 0;
    }

    public String toString() {
        String result = load + ": ";
        for (Job j : jobs)
            result += j + " ";
        return result;
    }
}