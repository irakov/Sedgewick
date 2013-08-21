//2.4.30
import java.util.NoSuchElementException;

public class MinMaxPQMedian<T extends Comparable<T>>
{
	private T median;
	private boolean isInMaxHeap;
	private T[] maxHeap;
	private T[] minHeap;
	private int maxHeapSize;
	private int minHeapSize;
	
	public MinMaxPQWithMedian()
	{
		maxHeap=(T[])new Comparable[2];
		minHeap=(T[])new Comparable[2];
		
		maxHeapSize=0;
		minHeapSize=0;
		isInMaxHeap=false;
	}
	
	//log N
	public void insert(T item)
	{
		if(isEmpty())
		{
			maxHeap[++maxHeapSize]=item;//initial case, empty pqs
			isInMaxHeap=true;
			median=item;
			return;
		}
		if(item.compareTo(median)<=0)
		{
			if(maxHeapSize>=maxHeap.length-1)
				maxHeap=resize(2*maxHeap.length,maxHeap);
			maxHeap[++maxHeapSize]=item;
			maxSwim(maxHeapSize);
			rebalance;
		}
		else
		{
			if(minHeapSize>=minHeap.length-1)
				minHeap=resize(2*minHeap.length,minHeap);
			minHeap[++minHeapSize]=item;
			minSwim(minHeapSize);
			rebalance();
		}
	}
	
	public T deleteMedian()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		
		T result=median;
			
		if(isInMaxHeap)
		{
			maxHeap[1]=maxHeap[maxHeapSize--];
			median=maxHeap[1];
			maxSink(1);
			maxHeap[size+1]=null;
			rebalance();
			
			if(size>0&&maxHeapSize<=(maxHeap.length-1)/4)
				maxHeap=resize(maxHeap.length/2,maxHeap);
		}
		else
		{
			minHeap[1]=minHeap[minHeapSize--];
		}
		
		return result;
	}
	
	//O(1)
	public T findMedian()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		return median;
	}
	
	public int size()
	{
		return maxHeapSize+minHeapSize;
	}
	
	public boolean isEmpty()
	{
		return (maxHeapSize+minHeapSize)==0;
	}
	
	//private methods
	private T[] resize(int newSize,T[] heap)
	{
		T[] newHeap=(T[])new Comparable[newSize];
		for(int i=0;i<=size;i++)
			newHeap[i]=heap[i];
		return newHeap;
	}
	
	private void maxSwim(int index)
	{
	}
	
	private int minSwim(int index)
	{
	}
	
	private void maxSink(int index)
	{
	}
	
	private void minSink(int index)
	{
	}
	
	private void rebalance()
	{
	}
}