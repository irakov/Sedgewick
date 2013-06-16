//algorithm 2.1
//page 249

public class SelectionSort
{
	public static void sort(Comparable[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			int min=i;
			for(int j=i+1;j<a.length;j++)
				if(a[j].compareTo(a[min])<0)
					min=j;
			Comparable temp=a[i];
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