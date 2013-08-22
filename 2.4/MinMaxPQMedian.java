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
	
	public MinMaxPQMedian()
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
				maxHeap=resize(2*maxHeap.length,maxHeapSize,maxHeap);
			maxHeap[++maxHeapSize]=item;
			maxSwim(maxHeapSize);
			rebalance();
		}
		else
		{
			if(minHeapSize>=minHeap.length-1)
				minHeap=resize(2*minHeap.length,minHeapSize,minHeap);
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
			maxHeap[maxHeapSize+1]=null;
			rebalance();
			
			if(maxHeapSize>0&&maxHeapSize<=(maxHeap.length-1)/4)
				maxHeap=resize(maxHeap.length/2,maxHeapSize,maxHeap);
		}
		else
		{
			minHeap[1]=minHeap[minHeapSize--];
			median=minHeap[1];
			minSink(1);
			minHeap[minHeapSize+1]=null;
			rebalance();
			
			if(minHeapSize>0&&minHeapSize<=(minHeap.length-1)/4)
				minHeap=resize(minHeap.length/2,minHeapSize,minHeap);
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
	private T[] resize(int newSize,int size,T[] heap)
	{
		T[] newHeap=(T[])new Comparable[newSize];
		for(int i=0;i<=size;i++)
			newHeap[i]=heap[i];
		return newHeap;
	}
	
	private void maxSwim(int index)
	{
		T key=maxHeap[index];
		int parent=index/2;
		while(parent>=1&&maxHeap[parent].compareTo(key)<0)
		{	
			maxHeap[index]=maxHeap[parent];
			index=parent;
			parent/=2;
		}
		maxHeap[index]=key;
		if(index==1&&isInMaxHeap)
			median=key;
	}
	
	private void minSwim(int index)
	{
		T key=minHeap[index];
		int parent=index/2;
		while(parent>=1&&minHeap[parent].compareTo(key)>0)
		{
			minHeap[index]=minHeap[parent];
			index=parent;
			parent/=2;
		}
		minHeap[index]=key;
		if(index==1&&!isInMaxHeap)
			median=key;
	}
	
	private void maxSink(int index)
	{
		T key=maxHeap[index];
		int child=2*index;
		if(child<=maxHeapSize&&child+1<=maxHeapSize&&maxHeap[child].compareTo(maxHeap[child+1])<0)
			child++;
		while(child<=maxHeapSize&&key.compareTo(maxHeap[child])<0)
		{
			if(index==1&&isInMaxHeap)
				median=maxHeap[child];
		
			maxHeap[index]=maxHeap[child];
			index=child;
			child=2*index;
			if(child<=maxHeapSize&&child+1<=maxHeapSize&&maxHeap[child].compareTo(maxHeap[child+1])<0)
				child++;
		}
		maxHeap[index]=key;
	}
	
	private void minSink(int index)
	{
		T key=minHeap[index];
		int child=2*index;
		if(child<=minHeapSize&&index+1<=minHeapSize&&minHeap[child].compareTo(minHeap[child+1])>0)
			child++;
		while(child<=minHeapSize&&key.compareTo(minHeap[child])>0)
		{
			if(index==1&&!isInMaxHeap)
				median=minHeap[child];
				
			minHeap[index]=minHeap[child];
			index=child;
			child=2*index;
			if(child<=minHeapSize&&index+1<=minHeapSize&&minHeap[child].compareTo(minHeap[child+1])>0)
				child++;
		}
		minHeap[index]=key;
	}
	
	private void rebalance()
	{
		if(maxHeapSize-minHeapSize>1)
		{
			minHeap[++minHeapSize]=maxHeap[1];
			minSwim(minHeapSize);
			maxHeap[1]=maxHeap[maxHeapSize--];
			maxSink(1);
			maxHeap[maxHeapSize+1]=null;
		}
		else if(maxHeapSize-minHeapSize<-1)
		{
			maxHeap[++maxHeapSize]=minHeap[1];
			maxSwim(maxHeapSize);
			minHeap[1]=minHeap[minHeapSize--];
			minSink(1);
			minHeap[minHeapSize+1]=null;
		}
		if(maxHeapSize>minHeapSize)
			isInMaxHeap=true;
		else
			if(maxHeapSize<minHeapSize)
				isInMaxHeap=false;
	}
	
	public static void main(String[] args)
	{
		MinMaxPQMedian<String> pq = new MinMaxPQMedian<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.deleteMedian() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
	}
}