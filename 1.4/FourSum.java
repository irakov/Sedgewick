//1.4.14
//page 210

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.IOException;

public class FourSum
{
	public int count(int[] a)
	{
		int result=0;
		Arrays.sort(a);//n*log n
		Map<Integer, Set<TwoSumMembers>> twoSums=computeTwoElementSum(a); //n^2 log n
		Set<Integer> sums=twoSums.keySet();
		for(Integer sum:sums)	//the more sums, the less elements for each: sums*size=n
		{
			Set<TwoSumMembers> thisMembers=twoSums.get(sum);
			Set<TwoSumMembers> otherMembers=twoSums.get(-sum); //log n
			if(otherMembers!=null)
			{
				if(sum==0)
					thisMembers.removeAll(otherMembers);
				result+=thisMembers.size()*otherMembers.size();
			}
		}
		return result;
	}
	
	public Map<Integer, Set<TwoSumMembers>> computeTwoElementSum(int[] a)
	{
		Map<Integer, Set<TwoSumMembers>> result=new TreeMap<Integer,Set<TwoSumMembers>>();
		for(int i=0;i<a.length;i++)				//n
			for(int j=i+1;j<a.length;j++)				//n
			{
				Set<TwoSumMembers> set=result.get(a[i]+a[j]);//log n
				if(set==null)
					set=new TreeSet<TwoSumMembers>();
				set.add(new TwoSumMembers(i,j));
				result.put(a[i]+a[j],set);	//log n
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
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		int[] a = readInts(args[0]);
		FourSum sum=new FourSum();
		output.println(sum.count(a));
	}
	
	private static int[] readInts(String fileName)
	{
		Pattern whitespacePattern=Pattern.compile("\\p{javaWhitespace}+");
		Pattern everythingPattern=Pattern.compile("\\A");
	
		File file=new File(fileName);
		try
		{
			int[] result = null;
			Scanner scanner=new Scanner(file);
			while(scanner.hasNextLine())
			{
				String input=scanner.useDelimiter(everythingPattern).next();
				scanner.useDelimiter(whitespacePattern);
				
				ArrayList<String> tokens=new ArrayList<String>(Arrays.asList(whitespacePattern.split(input)));
				if(tokens.get(0).length()==0) tokens.remove(0);
				
				result=new int[tokens.size()];
				for(int i=0;i<result.length;i++) result[i]=Integer.parseInt(tokens.get(i));
			}
			
			return result;
		}
		catch(IOException ex)
		{
			System.err.println("Cannot open "+fileName);
			return null;
		}
	}
}