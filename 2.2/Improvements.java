//2.2.11
//page 285

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class Improvements
{
	public static <T extends Comparable<T>> void sort(T[] a,int cutoff)
	{
		T[] aux=(T[])new Comparable[a.length];
		for(int i=0;i<a.length;i++)
			aux[i]=a[i];
			
		sort(aux,a,0,a.length-1,cutoff);
	}	
	
	private static <T extends Comparable<T>> void sort(T[] source,T[] destination,int left,int right,int cutoff)
	{
		if(left<right)
		{
			if(right-left<=cutoff)
			{
				insertionSort(destination,left,right);
			}
			else
			{
				int middle=left+(right-left)/2;
				sort(destination,source,left,middle,cutoff);
				sort(destination,source,middle+1,right,cutoff);
				
				if(source[middle].compareTo(source[middle+1])<=0)
					for(int i=left;i<=right;i++)
						destination[i]=source[i];
				else
					merge(source,destination,left,middle,right);
			}
		}
	}
	
	private static <T extends Comparable<T>> void merge(T[] source,T[] destination,int left,int middle,int right)
	{
		int i=left;
		int j=middle+1;
		for(int k=left;k<=right;k++)
		{
			if(i>middle)
			{
				destination[k]=source[j];
				j++;
			}
			else
				if(j>right)
				{
					destination[k]=source[i];
					i++;
				}
				else
					if(source[i].compareTo(source[j])<0)
					{
						destination[k]=source[i];
						i++;
					}
					else
					{
						destination[k]=source[j];
						j++;
					}
		}
	}
	
	private static <T extends Comparable<T>> void insertionSort(T[] a,int left,int right)
	{
		for(int i=left;i<=right;i++)
		{
			T temp=a[i];
			int j=i-1;
			while(j>=left&&a[j].compareTo(temp)>0)
			{
				a[j+1]=a[j];
				j--;
			}
			a[++j]=temp;
		}
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		String[] a=readStrings();
		int x=3;
		sort(a,x);
		for(int i=0;i<a.length;i++)
			output.print(a[i]+" ");
	
		output.println();
	}
	
	private static String[] readStrings()
	{
		Pattern everythingPattern=Pattern.compile("\\A");
		Pattern whitespacePattern=Pattern.compile("\\p{javaWhitespace}+");
		
		Scanner scanner=new Scanner(new BufferedInputStream(System.in));
		String input="";
		
		if(scanner.hasNextLine())
		{
			input=scanner.useDelimiter(everythingPattern).next();
			scanner.useDelimiter(whitespacePattern);
		}
		
		ArrayList<String> tokens=new ArrayList<String>(Arrays.asList(whitespacePattern.split(input)));
		if(tokens.get(0).length()==0) tokens.remove(0);
		
		return tokens.toArray(new String[tokens.size()]);
	}
	
	
}