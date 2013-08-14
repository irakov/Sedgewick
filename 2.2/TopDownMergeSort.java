//algorithm 2.4
//page 271+273

public class TopDownMergeSort
{
	public static <T extends Comparable<T>> void sort(T[] a)
	{
		T[] aux=(T[])new Comparable[a.length];
		sort(a,aux,0,a.length-1);
	}
	
	private static <T extends Comparable<T>> void sort(T[] a,T[] aux,int left,int right)
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
		for(int i=left;i<=right;i++)
			aux[i]=a[i];
		int i=left;
		int j=middle+1;
		for(int k=left;k<=right;k++)
		{
			if(i>middle)
			{
				a[k]=aux[j];
				j++;
			}
			else
				if(j>right)
				{
					a[k]=aux[i];
					i++;
				}
				else
					if(aux[i].compareTo(aux[j])<0)
					{
						a[k]=aux[i];
						i++;
					}
					else
					{
						a[k]=aux[j];
						j++;
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