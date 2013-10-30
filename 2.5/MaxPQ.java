//algorithm 2.6 (+2.4.22+2.4.26)
//page 309+318

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

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
		Key temp=pq[position];
		int parentPosition=position/2;
		while(parentPosition>=1&&pq[parentPosition].compareTo(temp)<0)
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
		int greaterChild=leftChild;
		if(leftChild<=size&&rightChild<=size&&pq[rightChild].compareTo(pq[leftChild])>0)
			greaterChild=rightChild;
		while(greaterChild<=size&&pq[greaterChild].compareTo(temp)>0)
		{
			pq[position]=pq[greaterChild];
			position=greaterChild;
			leftChild=2*position;
			rightChild=2*position+1;
			greaterChild=leftChild;
			if(leftChild<=size&&rightChild<=size&&pq[rightChild].compareTo(pq[leftChild])>0)
				greaterChild=rightChild;
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
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
        MaxPQ<String> pq = new MaxPQ<String>();
        while (input.hasNext()) 
		{
            String item = input.next();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) output.print(pq.removeMax() + " ");
        }
        output.println("(" + pq.size() + " left on pq)");
    }
}