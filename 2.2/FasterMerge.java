//2.2.10
//page 285

public class FasterMerge
{
	public static <T extends Comparable<T>> void sort(T[] a)
	{
		T[] aux=(T[])new Comparable[a.length];
		sort(a,aux,0,a.length-1);
	}
	
	private static <T extends Comparable<T>> void sort(T[] a,T[] aux, int left,int right)
	{
		if(left<right)
		{
			int middle=left+(right-left)/2;
			sort(a,aux,left,middle);
			sort(a,aux,middle+1,right);
			merge(a,aux,left,middle,right);
		}
	}
	
	private static <T extends Comparable<T>> void merge(T[] a,T[] aux,int left,int middle,int right)
	{
		for(int i=left;i<=middle;i++)
			aux[i]=a[i];
		for(int i=middle+1;i<=right;i++)
			aux[i]=a[right-i+middle+1];
		int i=left;
		int j=right;
		for(int k=left;k<=right;k++)
		{
			if(aux[i].compareTo(aux[j])<0)
			{
				a[k]=aux[i];
				i++;
			}
			else
			{
				a[k]=aux[j];
				j--;
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