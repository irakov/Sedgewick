//algorithm 2.6 (+2.4.22)
//page 309+318
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>>
{
	private Key[] pq;
	private int size;
	
	public MaxPQ()
	{
		this(1);
	}

	public MaxPQ(int max)
	{
		pq=(Key[])new Comparable[max+1];
		size=0;
	}
	
	public MaxPQ(Key[] keys)
	{
		size=keys.length;
		pq=(Key[])new Comparable[size+1];
		for(int i=1;i<=size;i++)
			pq[i]=keys[i-1];
		for(int i=size/2;i>=1;i--)
			sink(i);
	}
	
	public void insert(Key key)
	{
		if(size>=pq.length-1)
			resize(2*pq.length);
		pq[++size]=key;
		swim(size);
	}
	
	public Key max()
	{	
		if(isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}
	
	public Key removeMax()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		Key max=pq[1];
		pq[1]=pq[size--];
		sink(1);
		pq[size+1]=null;
		if(!isEmpty()&&size<=(pq.length-1)/4)
			resize(pq.length/2);
		return max;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int size()
	{
		return size;
	}
	
	private void swim(int position)
	{
		while(position>1&&pq[position/2].compareTo(pq[position])<0)
		{
			Key temp=pq[position];
			pq[position]=pq[position/2];
			pq[position/2]=temp;
			position=position/2;
		}
	}
	
	private void sink(int position)
	{
		while(position<=size/2)
		{
			Key thisKey=pq[position];
			Key leftChild=pq[2*position];
			Key rightChild=pq[2*position+1];
			if(leftChild.compareTo(rightChild)>0)
				if(leftChild.compareTo(thisKey)>0)
				{
					pq[position]=leftChild;
					pq[2*position]=thisKey;
					position=2*position;
				}
				else break;
			else
				if(rightChild.compareTo(thisKey)>0)
				{
					pq[position]=rightChild;
					pq[2*position+1]=thisKey;
					position=2*position+1;
				}
				else break;
		}
	}
	
	private void resize(int newSize)
	{
		Key[] newPq=(Key[])new Comparable[newSize];
		for(int i=1;i<=size;i++)
			newPq[i]=pq[i];
		pq=newPq;
	}
	
	public static void main(String[] args) 
	{
        MaxPQ<String> pq = new MaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.removeMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}