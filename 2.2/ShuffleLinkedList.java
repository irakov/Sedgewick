//2.2.18
//page 286
//see http://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time for uniform distribution twist

public class ShuffleLinkedList
{
	public void shuffle<Item extends Comparable<Item>>(LinkedList<Item> list)
	{	
		if(list.size()==1)
			return list;
		
		LinkedList<Item> linkedList1=new LinkedList<Item>();
		LinkedList<Item> linkedList2=new LinkedList<Item>();
		
		while(list.size()!=0)
		{
			linkedList1.insertLast(list.removeLast());
			if(list.size()!=0)
				linkedList2.insertLast(list.removeLast());
		}
		
		shuffle(linkedList1);
		shuffle(linkedList2);
		
		if(linkedList2.size()<linkedList1.size())
		{
			int i=StdRandom.uniform(linkedList2.size());
			linkedList2.insert(Integer.MAX_VALUE,i);
		}
	}

	public static void main(String[] args)
	{
		LinkedList<String> list=new LinkedList<String>();
		String[] a=In.readStrings();
		for(String s:a)
			list.insertLast(s);
	}
}