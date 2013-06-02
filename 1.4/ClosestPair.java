//1.4.16
//page 210

import java.util.Arrays;

public class ClosestPair
{
	public PairResult find(int[] a)
	{
		return divide(a, 0, a.length-1);
	}
	
	private PairResult divide(int[] a,int left,int right)
	{
		PairResult result=null;
		if(left<right)
		{
			int middle=(left+right)/2;
			PairResult leftResult=divide(a, left, middle);
			PairResult rightResult=divide(a, middle+1,right);
			result=conquer(a,left,middle,right,leftResult,rightResult);
		}
		return result;
	}
	
	private PairResult conquer(int[] a,int left,int middle,int right,PairResult leftResult,PairResult rightResult)
	{
		PairResult minimumResult=null;
		if(leftResult==null)
			minimumResult=rightResult;
		if(rightResult==null)
			minimumResult=leftResult;
		if(leftResult!=null&&rightResult!=null)
			if(leftResult.difference<rightResult.difference)
				minimumResult=leftResult;
			else
				minimumResult=rightResult;
		PairResult middleResult=new PairResult(a[middle],middle,a[middle+1],middle+1,Math.abs(a[middle+1]-a[middle]));
		if(minimumResult==null||minimumResult.difference>=middleResult.difference)
			minimumResult=middleResult;
		return minimumResult;
	}
	
	public class PairResult
	{
		public int value1;
		public int value1Index;
		public int value2;
		public int value2Index;
		public int difference;
		
		public PairResult(int val1,int val1In,int val2,int val2In,int diff)
		{
			value1=val1;
			value1Index=val1In;
			value2=val2;
			value2Index=val2In;
			difference=diff;
		}
	}
	
	public static void main(String[] args)
	{
	}
}