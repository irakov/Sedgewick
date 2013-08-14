//2.3.18
//page 305

public class QuickMedian3Part
{
	public static <T extends Comparable<T>> void sort(T[] items)
	{
		StdRandom.shuffle(items);
		sort(items,0,items.length-1);
	}
	
	private static <T extends Comparable<T>> void sort(T[] items,int left,int right)
	{
		if(left<right)
		{
			int size=right-left+1;
			int median=medianOf3(items,left,left+size/2,right);
		
			T item=items[left];
			items[left]=items[median];
			items[median]=item;
		
			int pivot=partition(items,left,right);
			sort(items,left,pivot-1);
			sort(items,pivot+1,right);
		}
	}
	
	private static <T extends Comparable<T>> int partition(T[] items,int left,int right)
	{
		int i=left;
		int j=right+1;
		T pivot=items[left];
		
		while(true)
		{
			while(items[++i].compareTo(pivot)<0)
				if(i==right)
					break;
			
			while(items[--j].compareTo(pivot)>0)
				if(j==left)
					break;
					
			if(i>=j) break;
			
			T item=items[i];
			items[i]=items[j];
			items[j]=item;
		}
		
		T item=items[j];
		items[j]=items[left];
		items[left]=item;
		
		return j;
	}
	
	private static <T extends Comparable<T>> int medianOf3(T[] items,int left,int middle,int right)
	{
		if(items[left].compareTo(items[middle])>0)
			if(items[middle].compareTo(items[right])>0)
				return middle;
			else
				return right;
		else
			if(items[left].compareTo(items[right])>0)
				return left;
		return right;
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}