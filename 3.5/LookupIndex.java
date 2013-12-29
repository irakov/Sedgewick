//page 499
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class LookupIndex
{
	public static void main(String[] args)
	{	
		File file=new File(args[0]);
		String separator=args[1];
		if(!file.exists()) return;
		Scanner fileInput=null;
		
		try
		{
			fileInput=new Scanner(file);
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
		
		RedBlackBST<String,Queue<String>> index=new RedBlackBST<String,Queue<String>>();
		RedBlackBST<String,Queue<String>> invertedIndex=new RedBlackBST<String,Queue<String>>();
		
		while(fileInput.hasNextLine())
		{
			String[] terms=fileInput.nextLine().split(separator);
			String key=terms[0];
			for(int i=1;i<terms.length;i++)
			{
				String value=terms[i];
				if(!index.contains(key)) index.put(key,new Queue<String>());
				if(!invertedIndex.contains(value)) invertedIndex.put(value,new Queue<String>());
				index.get(key).enqueue(value);
				invertedIndex.get(value).enqueue(key);
			}
		}
		
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		while(input.hasNext())
		{
			String query=input.nextLine();
			if(index.contains(query))
				for(String s:index.get(query))
					output.println(s);
			if(invertedIndex.contains(query))
				for(String s:invertedIndex.get(query))
					output.println(s);
		}
	}
}