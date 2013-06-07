//1.4.22
//page 211
public class BinarySearchWithAdditionAndSubstraction
{
	public boolean isInArray(int[] a,int term)
	{
		Pair<Integer,Integer> fibonacci=computeFibonacci(a.length-3);
		return search(a,fibonacci.x,fibonacci.y,term,0,fibonacci.x)!=-1;
	}
	
	private int search(int[] a,int first,int second,int term,int left,int right)
	{
		if(left>a.length-1)
			left=a.length-1;
		if(right>a.length-1)
			right=a.length-1;
		if(left<right)
		{
			int third=first-second;
			if(third>a.length-1)
				third=a.length-1;
			if(a[left+third]==term)
				return left+third;
			else
				if(a[left+third]>term)
					return search(a,second,third,term,left,left+third);
				else
					return search(a,second,third,term,left+third+1,left+first);
		}
		return -1;
	}
	
	private Pair<Integer,Integer> computeFibonacci(int size)
	{
		int firstTerm=1;
		int secondTerm=1;
		while(firstTerm<size)
		{
			int temp=firstTerm;
			firstTerm+=secondTerm;
			secondTerm=temp;
		}
		return new Pair<Integer,Integer>(firstTerm,secondTerm);
	}
	
	private class Pair<X,Y>
	{
		public final X x;
		public final Y y;
		public Pair(X x,Y y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args)
	{
		BinarySearchWithAdditionAndSubstraction bs=new BinarySearchWithAdditionAndSubstraction();
		int a[]={1,2,4,5,6,7,8,9,10,11,14,15,16,18};
		StdOut.println(bs.isInArray(a,14));
	}
}