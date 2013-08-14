//algorithm 2.1
//page 249

import java.util.List;

public class SelectionSort
{
	public static <T extends Comparable<T>> void sort(T[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			int min=i;
			for(int j=i+1;j<a.length;j++)
			{
				if(a[j].compareTo(a[min])<0)
					min=j;
			}
			T temp=a[i];
			a[i]=a[min];
			a[min]=temp;
		}
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}