//2.5.16

import java.util.Comparator;
import java.util.Arrays;

public class California
{
	public static final Comparator<String> CANDIDATES_ORDER=new CandidateComparator();
	
	private static class CandidateComparator implements Comparator<String>
	{
		private static String order="RWQOJMVAHBSGZXNTCIEKUPDYFL";
		
		public int compare(String a,String b)
		{
			if(a==b) return 0;
			int size=Math.min(a.length(),b.length());
			for(int i=0;i<size;i++)
			{
				int indexA=order.indexOf(a.charAt(i));
				int indexB=order.indexOf(b.charAt(i));
				if(indexA<indexB) return -1;
				if(indexB<indexA) return 1;
			}
			return a.length()-b.length();
		}
	}
	
	public static void main(String[] args)
	{
		String[] candidates=StdIn.readAll().toUpperCase().split("\\n+");
		int count=candidates.length;
		Arrays.sort(candidates,California.CANDIDATES_ORDER);
		for(int i=0;i<count;i++)
			StdOut.println(candidates[i]);
	}
}