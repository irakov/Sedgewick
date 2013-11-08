import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class WhiteFilter
{
	public static void main(String[] args)
	{
		File file=new File(args[0]);
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
		
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		HashSET<String> whiteFilter=new HashSET<String>();
		
		while(fileInput.hasNext())
			whiteFilter.add(fileInput.next());
		
		while(input.hasNext())
		{
			String s=input.next();
			if(whiteFilter.contains(s))
				output.println(s);
		}
	}
}