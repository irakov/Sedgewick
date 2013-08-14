//2.3.17
//page 305

public class QuicksortNoSentinels
{
	public static <T extends Comparable<T>> void sort(T[] items)
	{
		StdRandom.shuffle(items);
		
		int maxPos=0;
		for(int i=0;i<=items.length-1;i++)
			if(items[i].compareTo(items[maxPos])>0)
				maxPos=i;
		
		T temp=items[maxPos];
		items[maxPos]=items[items.length-1];
		items[items.length-1]=temp;
		
		sort(items,0,items.length-1);
	}
	
	private static <T extends Comparable<T>> void sort(T[] items,int left,int right)
	{
		if(right<=left) return;
		int i=partition(items,left,right);
		sort(items,left,i-1);
		sort(items,i+1,right);
	}
	
	private static <T extends Comparable<T>> int partition(T[] items,int left,int right)
	{
		int i=left;
		int j=right+1;
		T pivot=items[left];
		while(true)
		{
			while(items[++i].compareTo(pivot)<0);
			while(items[--j].compareTo(pivot)>0);
			
			if(i>=j) break;
			
			T temp=items[i];
			items[i]=items[j];
			items[j]=temp;
		}
		
		T temp=items[left];
		items[left]=items[j];
		items[j]=temp;
		
		return j;
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}