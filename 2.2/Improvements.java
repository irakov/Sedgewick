//2.2.11
//page 285

public class Improvements
{
	public static void sort(Comparable[] a,int cutoff)
	{
		Comparable[] aux=new Comparable[a.length];
		for(int i=0;i<a.length;i++)
			aux[i]=a[i];
			
		sort(aux,a,0,a.length-1,cutoff);
	}	
	
	private static void sort(Comparable[] source,Comparable[] destination,int left,int right,int cutoff)
	{
		if(left<right)
		{
			if(right-left<=cutoff)
			{
				insertionSort(destination,left,right);
			}
			else
			{
				int middle=left+(right-left)/2;
				sort(destination,source,left,middle,cutoff);
				sort(destination,source,middle+1,right,cutoff);
				
				if(source[middle].compareTo(source[middle+1])<=0)
					for(int i=left;i<=right;i++)
						destination[i]=source[i];
				else
					merge(source,destination,left,middle,right);
			}
		}
	}
	
	private static void merge(Comparable[] source,Comparable[] destination,int left,int middle,int right)
	{
		int i=left;
		int j=middle+1;
		for(int k=left;k<=right;k++)
		{
			if(i>middle)
			{
				destination[k]=source[j];
				j++;
			}
			else
				if(j>right)
				{
					destination[k]=source[i];
					i++;
				}
				else
					if(source[i].compareTo(source[j])<0)
					{
						destination[k]=source[i];
						i++;
					}
					else
					{
						destination[k]=source[j];
						j++;
					}
		}
	}
	
	private static void insertionSort(Comparable[] a,int left,int right)
	{
		for(int i=left;i<=right;i++)
		{
			Comparable temp=a[i];
			int j=i-1;
			while(j>=left&&a[j].compareTo(temp)>0)
			{
				a[j+1]=a[j];
				j--;
			}
			a[++j]=temp;
		}
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		int x=3;
		sort(a,x);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}