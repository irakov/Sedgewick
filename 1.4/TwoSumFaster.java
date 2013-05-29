//1.4.15
//page 210
import java.util.Arrays;

public class TwoSumFaster
{
	public int count(int[] a)
	{
		int result=0;
		Arrays.sort(a);	//n log n
		int left=0;
		int right=a.length-1;
		while(left!=right)//n
		{
			if(a[left]+a[right]>0)
				right--;
			else
				if(a[left]+a[right]<0)
					left++;
				else
				{
					result++;
					right--;
				}
		}
		
		return result;
	}

	public static void main(String[] args)
	{
		int[] a = In.readInts(args[0]);
		TwoSumFaster sum=new TwoSumFaster();
		StdOut.println(sum.count(a));
	}
}