//page 299

public class Quick3way
{
	public static void sort(Comparable[] items)
	{
		StdRandom.shuffle(items);
		sort(items,0,items.length-1);
	}
	
	private static void sort(Comparable[] items,int left,int right)
	{
		if(left>= right)return;
	
		int lt=left;
		int gt=right;
		int i=left+1;
		Comparable pivot=items[left];
		
		while(i<=gt)
		{
			int result=items[i].compareTo(pivot);
			if(result<0)
			{
				Comparable temp=items[i];
				items[i]=items[lt];
				items[lt]=temp;
				i++;
				lt++;
			}
			else
				if(result>0)
				{
					Comparable temp=items[i];
					items[i]=items[gt];
					items[gt]=temp;
					gt--;
				}
				else
					i++;
		}
		sort(items,left,lt-1);
		sort(items,gt+1,right);
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}