//2.4.29
//2.4.29
import java.util.NoSuchElementException;

public class MinMaxPQ<T extends Comparable<T>>
{
	private T[] maxHeap;
	private T[] minHeap;
	private Integer[] maxToMin;//minHeap[maxToMin[i]]=maxHeap[i]; 
	private Integer[] minToMax;//maxHeap[minToMax[i]]=minHeap[i];
	private int size;
	
	public MinMaxPQ()
	{
		maxHeap=(T[])new Comparable[2];
		minHeap=(T[])new Comparable[2];
		maxToMin=new Integer[2];
		minToMax=new Integer[2];
		size=0;
	}
	
	//log n
	public void insert(T item)
	{
		if(size>=maxHeap.length-1)
			resize(2*maxHeap.length);
			
		maxHeap[++size]=item;
		minHeap[size]=item;
		
		maxToMin[size]=size;
		minToMax[size]=size;
		
		maxSwim(size);
		minSwim(size);
	}
	
	public T deleteMaximum()
	{
		if(size==0)
			throw new NoSuchElementException();
		
		T max=maxHeap[1];
		maxExchange(1,size);
		minExchange(minToMax[size],size);
		
		size--;
		
		maxSink(1);
		minSink(minToMax[size]);
		
		maxHeap[size+1]=null;
		minHeap[size+1]=null;
		
		if(size>0&&size<=(maxHeap.length-1)/4)
			resize(maxHeap.length/2);
		return max;
	}
	
	public T deleteMinimum()
	{
		if(size==0)
			throw new NoSuchElementException();
			
		T min=minHeap[1];
		minExchange(1,size);
		maxExchange(maxToMin[size],size);
		
		size--;
		
		minSink(1);
		minSink(maxToMin[size]);
		
		minHeap[size+1]=null;
		maxHeap[size+1]=null;
		
		if(size>0&&size<=(minHeap.length-1)/4)
			resize(minHeap.length/2);
		return min;
	}
	
	//O(1)
	public T findMaximum()
	{
		if(size==0)
			throw new NoSuchElementException();
		return maxHeap[1];
	}
	
	public T findMinimum()
	{
		if(size==0)
			throw new NoSuchElementException();
		return minHeap[1];
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int size()
	{
		return size;
	}
	
	//private methods
	private void maxSwim(int index)
	{
		T key=maxHeap[index];
		int parentIndex=index/2;
		while(parentIndex>=1&&maxHeap[parentIndex].compareTo(maxHeap[index])<0)
		{
			maxHeap[index]=maxHeap[parentIndex];
			index=parentIndex;
			parentIndex/=2;
		}
		maxHeap[index]=key;
	}
	
	private void minSwim(int index)
	{
		T key=minHeap[index];
		int parentIndex=index/2;
		while(parentIndex>=1&&minHeap[parentIndex].compareTo(minHeap[index])>0)
		{
			minHeap[index]=minHeap[parentIndex];
			index=parentIndex;
			parentIndex/=2;
		}
		minHeap[index]=key;
	}
	
	private void maxSink(int index)
	{
		T key=maxHeap[index];
		int leftChild=2*index;
		int rightChild=2*index+1;
		int greaterChild=leftChild;
		if(leftChild<=size&&rightChild<=size&&maxHeap[leftChild].compareTo(maxHeap[rightChild])<0)
			greaterChild=rightChild;
		while(greaterChild<=size&&maxHeap[greaterChild].compareTo(key)>0)
		{
			maxHeap[index]=maxHeap[greaterChild];
			index=greaterChild;
			leftChild=2*index;
			rightChild=2*index+1;
			greaterChild=leftChild;
			if(leftChild<=size&&rightChild<=size&&maxHeap[leftChild].compareTo(maxHeap[rightChild])<0)
				greaterChild=rightChild;
		}
		maxHeap[index]=key;
	}
	
	private void minSink(int index)
	{
		T key=minHeap[index];
		int leftChild=2*index;
		int rightChild=2*index+1;
		int smallerChild=leftChild;
		if(leftChild<=size&&rightChild<=size&&minHeap[leftChild].compareTo(minHeap[rightChild])>0)
			smallerChild=rightChild;
		while(smallerChild<=size&&minHeap[smallerChild].compareTo(key)<0)
		{
			minHeap[index]=minHeap[smallerChild];
			index=smallerChild;
			leftChild=2*index;
			rightChild=2*index+1;
			smallerChild=leftChild;
			if(leftChild<=size&&rightChild<=size&&minHeap[leftChild].compareTo(minHeap[rightChild])>0)
				smallerChild=rightChild;
		}
		minHeap[index]=key;
	}
	
	private void resize(int newSize)
	{
		T[] newMinHeap=(T[])new Comparable[newSize];
		for(int i=0;i<=size;i++)
			newMinHeap[i]=minHeap[i];
		minHeap=newMinHeap;
		
		T[] newMaxHeap=(T[])new Comparable[newSize];
		for(int i=0;i<=size;i++)
			newMaxHeap[i]=maxHeap[i];
		maxHeap=newMaxHeap;
		
		Integer[] newMaxToMin=new Integer[newSize];
		for(int i=0;i<=size;i++)
			newMaxToMin[i]=maxToMin[i];
		maxToMin=newMaxToMin;
		
		Integer[] newMinToMax=new Integer[newSize];
		for(int i=0;i<=size;i++)
			newMinToMax[i]=minToMax[i];
		minToMax=newMinToMax;
	}
	
	private void maxExchange(int pos1,int pos2)
	{
		T temp=maxHeap[pos1];
		maxHeap[pos1]=maxHeap[pos2];
		maxHeap[pos2]=temp;
		
		Integer tempPos=minToMax[pos1];
		minToMax[pos1]=minToMax[pos2];
		minToMax[pos2]=minToMax[tempPos];
	}
	
	private void minExchange(int pos1,int pos2)
	{
		T temp=minHeap[pos1];
		minHeap[pos1]=minHeap[pos2];
		minHeap[pos2]=temp;
	
		Integer tempPos=maxToMin[pos1];
		maxToMin[pos1]=maxToMin[pos2];
		maxToMin[pos2]=tempPos;
	}
	
	public static void main(String[] args) 
	{
        MinMaxPQ<String> pq = new MinMaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!(item.equals("-")||item.equals("+"))) pq.insert(item);
            else 
				if (!pq.isEmpty() && item.equals("-")) StdOut.print(pq.deleteMaximum() + " ");
				else
				if (!pq.isEmpty() && item.equals("+")) StdOut.print(pq.deleteMinimum() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}