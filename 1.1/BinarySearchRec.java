//page 25

import java.util.Arrays;

public class BinarySearchRec
{
	public static int rank(int key, int[] a)
	{
		return rank(key,a,0,a.length-1);
	}
	
	private static int rank(int key, int[] a, int left, int right)
	{
		if(left>right)
			return -1;
		int middle=left+(right-left)/2;
		if(a[middle]<key)
			return rank(key,a,middle+1,right);
		else
			if(a[middle]>key)
				return rank(key,a,left,middle-1);
			else
				return middle;
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