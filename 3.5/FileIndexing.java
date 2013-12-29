//page 501

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class FileIndexing
{
	public static void main(String[] args)
	{
		RedBlackBST<String,SET<File>> bst=new RedBlackBST<String,SET<File>>();
		for(String fileName:args)
		{
			File file=new File(fileName);
			if(!file.exists()) continue;
			
			Scanner fileInput=null;
			try
			{
				fileInput=new Scanner(file);
			}
			catch(IOException ex)
			{
				System.err.println(ex);
				return;
			}
			
			while(fileInput.hasNext())
			{
				String s=fileInput.next();
				if(!bst.contains(s)) bst.put(s,new SET<File>());
				SET<File> set=bst.get(s);
				set.add(file);
			}
		}
		
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		while(input.hasNext())
		{
			String query=input.next();
			if(bst.contains(query))
				output.println(bst.get(query));
		}
	}
}