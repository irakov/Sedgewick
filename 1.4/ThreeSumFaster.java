//1.4.15
//page 210
import java.util.Arrays;

public class ThreeSumFaster
{
	public int count(int[] a)
	{
		int result=0;
		
		Arrays.sort(a); //n*log n
		
		for(int left=0;left<a.length-2;left++)	//n
		{
			int right=a.length-1;
			int sum=a[left]+a[right];
			int i=left+1;
			while(i<right)
			{
				StdOut.println(a[left]+" "+a[i]+" "+a[right]+" "+sum);
				if(sum+a[i]==0)
				{
					result++;
					i++;
				}
				else
					if(sum+a[i]<0)
						i++;
					else
						right--;
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