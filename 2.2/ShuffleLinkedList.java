//2.2.18
//page 286
//see http://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time for uniform distribution twist

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class ShuffleLinkedList<Item extends Comparable<Item>>
{
	private static Random random;

	static
	{
		random=new Random(System.currentTimeMillis()); 
	}
	
	public void shuffle(LinkedList<Item> list,Item dummy)
	{	
		if(list.size()==1)
			return;
		
		LinkedList<Item> list1=new LinkedList<Item>();
		LinkedList<Item> list2=new LinkedList<Item>();
		
		while(list.size()!=0)
		{
			list1.insertLast(list.removeLast());
			if(list.size()!=0)
				list2.insertLast(list.removeLast());
		}
		
		shuffle(list1,dummy);
		shuffle(list2,dummy);
		
		if(list2.size()<list1.size())
		{
			int i=random.nextInt(list2.size());
			list2.insert(dummy,i);
		}
		
		merge(list,list1,list2,dummy);
	}
	
	private void merge(LinkedList<Item> list,LinkedList<Item> list1,LinkedList<Item> list2,Item dummy)
	{
		while(list1.size()!=0&&list2.size()!=0)
		{
			int i=random.nextInt(2);
			if(i==0)
				list.insertLast(list1.removeLast());
			else
				list.insertLast(list2.removeLast());	
		}
		
		while(list1.size()!=0)
			list.insertLast(list1.removeLast());
		while(list2.size()!=0)
			list.insertLast(list2.removeLast());
			
		list.removeAll(dummy);
	}

	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		LinkedList<String> list=new LinkedList<String>();
		String[] a=readStrings();
		for(String s:a)
			list.insertLast(s);
		ShuffleLinkedList<String> shuffle=new ShuffleLinkedList<String>();
		shuffle.shuffle(list,new String("TEST"));
		while(list.size()!=0)
			output.println(list.removeLast());
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