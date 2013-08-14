//algorithm 2.2
//page 251

public class InsertionSort
{
	public static <T extends Comparable<T>> void sort(T[] a)
	{
		for(int i=1;i<a.length;i++)
		{
			for(int j=i;j>0&&a[j].compareTo(a[j-1])<0;j--)
			{
				T temp=a[j-1];
				a[j-1]=a[j];
				a[j]=temp;
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