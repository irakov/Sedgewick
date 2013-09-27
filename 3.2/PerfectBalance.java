//3.2.25 (page 419)

import java.util.Arrays;

public class PerfectBalance
{
	private static void balance(BST<String,Integer> bst,String[] input,int lo,int hi)
	{
		if(hi<lo) return;
		int mid=lo+(hi-lo)/2;
		bst.put(input[mid],mid);
		StdOut.println(input[mid]+" "+mid);
		balance(bst,input,lo,mid-1);
		balance(bst,input,mid+1,hi);
	}
	
	public static void main(String[] args)
	{
		String[] input=StdIn.readAll().split("\\s+");
		Arrays.sort(input);
		BST<String,Integer> bst=new BST<String,Integer>();
		balance(bst,input,0,input.length-1);
	}
}