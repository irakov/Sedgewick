//page 311
//+2.4.26
import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>>
{
	private Key[] pq;
	private int size;
	
	public MinPQ()
	{
		this(1);
	}

	public MinPQ(int max)
	{
		pq=(Key[])new Comparable[max+1];
		size=0;
	}
	
	public MinPQ(Key[] keys)
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
	
	public Key min()
	{	
		if(isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}
	
	public Key removeMin()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		Key min=pq[1];
		pq[1]=pq[size--];
		sink(1);
		pq[size+1]=null;
		if(!isEmpty()&&size<=(pq.length-1)/4)
			resize(pq.length/2);
		return min;
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
		Key temp=pq[position];
		int parentPosition=position/2;
		while(parentPosition>=1&&pq[parentPosition].compareTo(temp)>0)
		{
			pq[position]=pq[parentPosition];
			position=parentPosition;
			parentPosition/=2;
		}
		pq[position]=temp;
	}
	
	private void sink(int position)
	{
		Key temp=pq[position];
		int leftChild=2*position;
		int rightChild=2*position+1;
		int lowerChild=leftChild;
		if(leftChild<=size&&rightChild<=size&&pq[rightChild].compareTo(pq[leftChild])<0)
			lowerChild=rightChild;
		while(lowerChild<=size&&pq[lowerChild].compareTo(temp)<0)
		{
			pq[position]=pq[lowerChild];
			position=lowerChild;
			leftChild=2*position;
			rightChild=2*position+1;
			lowerChild=leftChild;
			if(leftChild<=size&&rightChild<=size&&pq[rightChild].compareTo(pq[leftChild])<0)
				lowerChild=rightChild;
		}
		pq[position]=temp;
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
        MinPQ<String> pq = new MinPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.removeMin() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}