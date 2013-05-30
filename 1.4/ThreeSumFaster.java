//1.4.15
//page 210
import java.util.Arrays;

public class ThreeSumFaster
{
	public int count(int[] a)
	{
		int result=0;
		int left=0;
		int right=a.length-1;
		
		Arrays.sort(a); //n*log n
		
		while(left<right-1)	//n
		{
			int sum=a[left]+a[right];
			for(int i=left+1;i<right;i++)	//n
			{
				if(sum+a[i]==0)
					result++;
				if(i==right-1)
					if(sum+a[i]<=0)
					{
						left++;
						right=a.length-1;
						break;
					}
					else
					{
						right--;
						break;
					}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args)
	{	
		int[] a = In.readInts(args[0]);
		ThreeSumFaster sum=new ThreeSumFaster();
		StdOut.println(sum.count(a));
	}
}