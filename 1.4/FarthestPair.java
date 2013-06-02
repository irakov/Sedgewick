//1.4.17
//page 210

public class FarthestPair
{
	public PairResult find(int[] a)
	{
		if(a.length<2)
			throw new RuntimeException("Length must be >= 2");
		PairResult result=new PairResult(a[0],0,a[1],1);
		for(int i=2;i<a.length;i++)
		{
			int minDiff=0;
			int minPos=-1;
			if(Math.abs(a[i]-result.value1)>=minDiff)
			{
				minDiff=Math.abs(a[i]-result.value1);
				minPos=result.value1Index;
			}
			if(Math.abs(a[i]-result.value2)>=minDiff)
			{
				minDiff=Math.abs(a[i]-result.value2);
				minPos=result.value2Index;
			}
			if(minDiff>result.difference)
				result=new PairResult(a[i],i,minPos,a[minPos]);
		}
		
		return result;
	}
	
	public class PairResult
	{
		public int value1;
		public int value1Index;
		public int value2;
		public int value2Index;
		public int difference;
		
		public PairResult(int val1,int val1In,int val2,int val2In)
		{
			value1=val1;
			value1Index=val1In;
			value2=val2;
			value2Index=val2In;
			difference=Math.abs(value2-value1);
		}
	}
	
	public static void main(String[] args)
	{
		FarthestPair fp=new FarthestPair();
		int[] a={-11,12,113,0,29,7,2,22};
		PairResult result=fp.find(a);
		StdOut.println(result.value1+" "+result.value2+" "+result.difference);
	}
}