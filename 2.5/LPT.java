//2.5.13

import java.util.Arrays;

public class LPT
{
	public static void main(String[] args)
	{
		int cpuCount=StdIn.readInt();
	
		int size=StdIn.readInt();
		Job[] jobs=new Job[size];
		for(int i=0;i<size;i++)
		{
			String jobName=StdIn.readString();
			double jobDuration=StdIn.readDouble();
			jobs[i]=new Job(jobName,jobDuration);
		}
		
		Arrays.sort(jobs);
		
		MinPQ<Processor> procs=new MinPQ<Processor>(cpuCount);
		for(int i=0;i<cpuCount;i++)
			procs.insert(new Processor());
			
		for(int i=size-1;i>=0;i--)
		{
			Processor proc=procs.removeMin();
			proc.add(jobs[i]);
			procs.insert(proc);
		}
		
		for(int i=0;i<cpuCount;i++)
			StdOut.println(procs.removeMin());
	}
}