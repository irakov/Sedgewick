//page 271+278

public class BottomUpMergeSort
{
	private static Comparable[] aux;
	public static void sort(Comparable[] a)
	{
		aux=new Comparable[a.length];
		for(int size=1;size<a.length;size+=size)
		{
			for(int i=0;i+size<a.length;i+=2*size)
				merge(a,i,i+size-1,Math.min(i+2*size-1,a.length-1));
		}
	}
	
	private static void merge(Comparable[] a,int left,int middle,int right)
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