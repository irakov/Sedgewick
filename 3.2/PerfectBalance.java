//3.2.25 (page 419)

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class PerfectBalance
{
	private static void balance(BST<String,Integer> bst,String[] input,int lo,int hi)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		if(hi<lo) return;
		int mid=lo+(hi-lo)/2;
		bst.put(input[mid],mid);
		output.println(input[mid]+" "+mid);
		balance(bst,input,lo,mid-1);
		balance(bst,input,mid+1,hi);
	}
	
	public static void main(String[] args)
	{
		Pattern everythingPattern=Pattern.compile("\\A");
		Pattern whitespacePattern=Pattern.compile("\\p{javaWhitespace}+");
		Scanner scanner=new Scanner(new BufferedInputStream(System.in));
		String text="";
		if(scanner.hasNextLine())
		{
			text=scanner.useDelimiter(everythingPattern).next();
			scanner.useDelimiter(whitespacePattern);
		}
	
		String[] input=text.split("\\s+");
		Arrays.sort(input);
		BST<String,Integer> bst=new BST<String,Integer>();
		balance(bst,input,0,input.length-1);
	}
}