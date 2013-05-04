//page 9
//run with C:\java\Sedgewick\1.1>javac BinarySearch.java -Xlint:deprecation

import java.util.Arrays;

public class BinarySearch
{
	public static int rank(int key, int[] a)
	{
		int left=0;
		int right=a.length-1;
		while(left<=right)
		{
			int middle=(left+right)/2;
			if(a[middle]<key)
				left=middle+1;
			else
				if(a[middle]>key)
					right=middle-1;
				else
					return middle;
		}
		
		return -1;
	}
	
	public static void main(String[] args)
	{
		int[] whitelist=In.readInts(args[0]);
		
		Arrays.sort(whitelist);
		
		while(!StdIn.isEmpty())
		{
			int key=StdIn.readInt();
			if(rank(key,whitelist)==-1)
				StdOut.println(key);
		}
	}
}