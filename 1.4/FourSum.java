//1.4.14
//page 210
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

public class FourSum
{
	public int count(int[] a)
	{
		int result=0;
		Arrays.sort(a);//n*log n
		Map<Integer, Set<TwoSumMembers>> twoSums=computeTwoElementSum(a); //n^2 log n
		Set<Integer> sums=twoSums.keySet();
		for(Integer sum:sums)
		{
			StdOut.println(sum);
			Set<TwoSumMembers> otherMembers=twoSums.get(-sum);
			if(otherMembers!=null)
			{
				result+=twoSums.get(sum).size()*otherMembers.size();
				Set<TwoSumMembers> thisMembers=twoSums.get(sum);
				for(TwoSumMembers member:thisMembers)
				{
					StdOut.println("this "+member.firstElementIndex+" "+member.secondElementIndex);
				}
				for(TwoSumMembers member:otherMembers)
				{
					StdOut.println("other "+member.firstElementIndex+" "+member.secondElementIndex);
				}
			}
		}
		return result;
	}
	
	public Map<Integer, Set<TwoSumMembers>> computeTwoElementSum(int[] a)
	{
		Map<Integer, Set<TwoSumMembers>> result=new TreeMap<Integer,Set<TwoSumMembers>>();
		for(int i=0;i<a.length;i++)				//n
			for(int j=0;j<a.length;j++)				//n
			{
				if(i!=j)	
				{
					Set<TwoSumMembers> set=result.get(a[i]+a[j]);//log n
					if(set==null)
						set=new TreeSet<TwoSumMembers>();
				
					set.add(new TwoSumMembers(i,j));
					result.put(a[i]+a[j],set);	//log n
				}
			}
			
		return result;
	}
	
	public class TwoSumMembers implements Comparable<TwoSumMembers>
	{
		public Integer firstElementIndex;
		public Integer secondElementIndex;
		
		public TwoSumMembers(int firstElementIndex,int secondElementIndex)
		{
			this.firstElementIndex=firstElementIndex;
			this.secondElementIndex=secondElementIndex;
		}
		
		public int compareTo(TwoSumMembers other)
		{
			if(firstElementIndex<other.firstElementIndex)
				return -1;
			else if(firstElementIndex>other.firstElementIndex)
				return 1;
			else return secondElementIndex.compareTo(other.secondElementIndex);
		}
	}

	public static void main(String[] args)
	{
		int[] a = In.readInts(args[0]);
		FourSum sum=new FourSum();
		StdOut.println(sum.count(a));
	}
}