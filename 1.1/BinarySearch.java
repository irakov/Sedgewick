//page 9
//run with C:\java\Sedgewick\1.1>javac BinarySearch.java -Xlint:deprecation

import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BinarySearch
{
	public static int rank(int key, int[] a)
	{
		int left=0;
		int right=a.length-1;
		while(left<=right)
		{
			int middle=left+(right-left)/2;
			if(a[middle]<key)
				left=middle+1;
			else
				if(a[middle]>key)
					right=middle-1;
				else
					return middle;
		}
		
		return -1;
	}
	
	public static void main(String[] args)
	{
		int[] whitelist=readInts(args[0]);
		Arrays.sort(whitelist);
		
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		while(input.hasNext())
		{
			int key=input.nextInt();
			if(rank(key,whitelist)==-1)
				output.println(key);
		}
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