//2.2.14+2.2.15
//page 285

public class BottomUpQueueMergeSort<Item extends Comparable<Item>>
{
	public Queue<Item> sort(Item[] a)
	{
		Queue<Queue<Item>> megaQueue=new Queue<Queue<Item>>();
		for(int i=0;i<a.length;i++)
		{
			Queue<Item> queue=new Queue<Item>();
			queue.enqueue(a[i]);
			megaQueue.enqueue(queue);
		}
		sort(megaQueue);
		return megaQueue.dequeue();
	}
	
	private void sort(Queue<Queue<Item>> queue)
	{
		while(queue.size()>1)
		{
			queue.enqueue(merge(queue.dequeue(),queue.dequeue()));
		}
	}
	
	private Queue<Item> merge(Queue<Item> left,Queue<Item> right)
	{
		Queue<Item> result=new Queue<Item>();
		while(left.size()>0&&right.size()>0)
		{
			Item leftItem=left.peek();
			Item rightItem=right.peek();
			if(leftItem.compareTo(rightItem)<0)
				result.enqueue(left.dequeue());
			else
				result.enqueue(right.dequeue());
		}
		while(left.size()>0)
			result.enqueue(left.dequeue());
		while(right.size()>0)
			result.enqueue(right.dequeue());
			
		return result;
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		BottomUpQueueMergeSort<String> sort=new BottomUpQueueMergeSort<String>();
		Queue<String> result=sort.sort(a);
		for(String s:result)
			StdOut.print(s+" ");
	}
}