//algorithm 2.3
//page 259

public class ShellSort
{
	public static void sort(Comparable[] a)
	{
		int n=a.length;
		int h=1;
		while(h<n/3)
			h=3*h+1;
		while(h>=1)
		{
			for(int i=h;i<n;i++)
				for(int j=i;j>=h&&a[j].compareTo(a[j-h])<0;j-=h)
				{
					Comparable temp=a[j-h];
					a[j-h]=a[j];
					a[j]=temp;
				}
			h/=3;
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