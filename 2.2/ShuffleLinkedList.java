//2.2.18
//page 286
//see http://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time for uniform distribution twist

public class ShuffleLinkedList<Item extends Comparable<Item>>
{
	public void shuffle(LinkedList<Item> list,Item dummy)
	{	
		if(list.size()==1)
			return;
		
		LinkedList<Item> list1=new LinkedList<Item>();
		LinkedList<Item> list2=new LinkedList<Item>();
		
		while(list.size()!=0)
		{
			list1.insertLast(list.removeLast());
			if(list.size()!=0)
				list2.insertLast(list.removeLast());
		}
		
		shuffle(list1,dummy);
		shuffle(list2,dummy);
		
		if(list2.size()<list1.size())
		{
			int i=StdRandom.uniform(list2.size());
			list2.insert(dummy,i);
		}
		
		merge(list,list1,list2,dummy);
	}
	
	private void merge(LinkedList<Item> list,LinkedList<Item> list1,LinkedList<Item> list2,Item dummy)
	{
		while(list1.size()!=0&&list2.size()!=0)
		{
			int i=StdRandom.uniform(2);
			if(i==0)
				list.insertLast(list1.removeLast());
			else
				list.insertLast(list2.removeLast());	
		}
		
		while(list1.size()!=0)
			list.insertLast(list1.removeLast());
		while(list2.size()!=0)
			list.insertLast(list2.removeLast());
			
		list.removeAll(dummy);
	}

	public static void main(String[] args)
	{
		LinkedList<String> list=new LinkedList<String>();
		String[] a=In.readStrings();
		for(String s:a)
			list.insertLast(s);
		ShuffleLinkedList<String> shuffle=new ShuffleLinkedList<String>();
		shuffle.shuffle(list,new String("TEST"));
		while(list.size()!=0)
			StdOut.println(list.removeLast());
	}
}