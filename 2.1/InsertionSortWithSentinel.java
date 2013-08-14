//2.1.24
//page 267

public class InsertionSortWithSentinel
{
	public static <T extends Comparable<T>> void sort(T[] a)
	{
		for(int i=1;i<a.length;i++)
		{
			if(a[i].compareTo(a[0])<0)
			{
				T item=a[i];
				a[i]=a[0];
				a[0]=item;
			}
			for(int j=i;a[j].compareTo(a[j-1])<0;j--)
			{
				T item=a[j];
				a[j]=a[j-1];
				a[j-1]=item;
			}
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
