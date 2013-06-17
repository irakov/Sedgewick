//algorithm 2.1.14
//page 265

public class DequeueSort<Item extends Comparable<Item>>
{
	public void sort(Deque<Item> deque)
	{
		int sorted=0;
		while(sorted!=deque.size())
		{
			for(int i=0;i<deque.size()-sorted-1;i++)
			{
				Item item1=deque.popLeft();
				Item item2=deque.popLeft();
				if(item1.compareTo(item2)>=0)
				{
					deque.pushRight(item1);
					deque.pushLeft(item2);
				}
				else
				{
					deque.pushRight(item2);
					deque.pushLeft(item1);
				}
			}
			Item item=deque.popLeft();
			for(int i=0;i<sorted;i++)
				deque.pushRight(deque.popLeft());
			deque.pushRight(item);
			sorted++;
		}
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		DequeueSort<String> ds=new DequeueSort<String>();
		Deque<String> deque=new Deque<String>();
		for(int i=0;i<a.length;i++)
			deque.pushRight(a[i]);
		ds.sort(deque);
		for(int i=0;i<a.length;i++)
			StdOut.print(deque.popLeft()+" ");
	}
}