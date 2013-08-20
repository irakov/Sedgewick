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
		if(isEmpty())
			throw new NoSuchElementException();
			
		T max=maxHeap[1];
		maxExchange(1,size);
		minExchange(minToMax[size],size);
		
		size--;
		
		if(size>0)
		{
			maxSink(1);
			minSink(minToMax[size]);
		
			maxHeap[size+1]=null;
			minHeap[size+1]=null;
		
			if(size<=(maxHeap.length-1)/4)
				resize(maxHeap.length/2);
		}
		return max;
	}
	
	public T deleteMinimum()
	{
		if(isEmpty())
			throw new NoSuchElementException();
			
		T min=minHeap[1];
		minExchange(1,size);
		maxExchange(maxToMin[size],size);
		
		size--;
		
		if(size>0)
		{
			minSink(1);
			maxSink(maxToMin[size]);
		
			minHeap[size+1]=null;
			maxHeap[size+1]=null;
		
			if(size<=(minHeap.length-1)/4)
				resize(minHeap.length/2);
		}
		return min;
	}
	
	//O(1)
	public T findMaximum()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		return maxHeap[1];
	}
	
	public T findMinimum()
	{
		if(isEmpty())
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
		while(index>1&&maxHeap[index/2].compareTo(maxHeap[index])<0)
		{
			maxExchange(index/2,index);
			index/=2;
		}
	}
	
	private void minSwim(int index)
	{
		while(index>1&&minHeap[index/2].compareTo(minHeap[index])>0)
		{
			minExchange(index/2,index);
			index/=2;
		}
	}
	
	private void maxSink(int index)
	{
		while(index<=size/2)
		{
			int child=2*index;
			if(child<size&&maxHeap[child].compareTo(maxHeap[child+1])<0)
				child++;
			if(maxHeap[index].compareTo(maxHeap[child])>0)
				break;
			maxExchange(index,child);
			index=child;
		}
	}
	
	private void minSink(int index)
	{
		while(index<=size/2)
		{
			int child=2*index;
			if(child<size&&minHeap[child].compareTo(minHeap[child+1])>0)
				child++;
			if(minHeap[index].compareTo(minHeap[child])<0)
				break;
			minExchange(index,child);
			index=child;
		}
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
		minToMax[pos2]=tempPos;
		
		tempPos=maxToMin[minToMax[pos1]];
		maxToMin[minToMax[pos1]]=maxToMin[minToMax[pos2]];
		maxToMin[minToMax[pos2]]=tempPos;
	}
	
	private void minExchange(int pos1,int pos2)
	{
		T temp=minHeap[pos1];
		minHeap[pos1]=minHeap[pos2];
		minHeap[pos2]=temp;
	
		Integer tempPos=maxToMin[pos1];
		maxToMin[pos1]=maxToMin[pos2];
		maxToMin[pos2]=tempPos;
		
		tempPos=minToMax[maxToMin[pos1]];
		minToMax[maxToMin[pos1]]=minToMax[maxToMin[pos2]];
		minToMax[maxToMin[pos2]]=tempPos;
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