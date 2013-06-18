//2.1.25
//page 267

public class InsertionSortWithoutExchanges
{
	public static void sort(Comparable[] a)
	{
		for(int i=1;i<a.length;i++)
		{
			if(a[i].compareTo(a[0])<0)
			{
				Comparable item=a[i];
				a[i]=a[0];
				a[0]=item;
			}
			Comparable temp=a[i];
			int j=i-1;
			while(j>=0&&a[j].compareTo(temp)>0)
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
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}