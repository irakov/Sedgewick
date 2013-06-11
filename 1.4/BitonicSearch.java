//1.4.20
//page 210
public class BitonicSearch
{
	public boolean isInArray(int[] a,int term)
	{
		int maxPosition=findMax(a);
		int minPosition=0;
		if(a[minPosition]>a[a.length-1])
			minPosition=a.length-1;
		if(term>a[maxPosition])
			return false;
		if(term<a[minPosition])
			return false;
		if(minPosition!=0&&term<a[0])
			return binarySearch(a,maxPosition+1,a.length-1,term)!=-1;
		if(binarySearch(a,0,maxPosition,term)==-1)
			return binarySearch(a,maxPosition+1,a.length-1,term)!=-1;
		return true;
	}
	
	private int findMax(int[] a)
	{
		int left=0;
		int right=a.length-1;
		while(left!=right)
		{
			int middle=(left+right)/2;
			if(a[left]>=a[middle])//for sure max point is between left and middle as this is decreasing
			{
				right=middle;
			}
			else//either the max point is between left and middle of between middle and right
			{
				int posLeft,posRight;
				if(middle-1>=left)
					posLeft=middle-1;
				else
					posLeft=left;
				if(middle+1<=right)
					posRight=middle+1;
				else
					posRight=right;
				if(a[posLeft]<=a[middle])//increasing
					if(a[middle]>=a[posRight])//decreasing
						return middle;
					else//increasing
						left=middle+1;
				else
					right=middle;
			}
		}
		return -1;
	}
	
	private int binarySearch(int[] a,int left,int right,int term)
	{
		if(left<right)
		{
			int middle=(left+right)/2;
			if(a[middle]==term)
				return middle;
			if(term<a[middle])
				return binarySearch(a,left,middle,term);
			return binarySearch(a,middle+1,right,term);
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		BitonicSearch s=new BitonicSearch();
		int[] a={0,2,4,6,10,12,9,7,5,3,2,-1};
		StdOut.println(s.isInArray(a,8));
	}
}