//page 162
//1.3.10
//usage java InfixToPostfix < operations.txt

public class InfixToPostfix
{	
	public static void main(String[] args)
	{
		Stack<String> operators=new Stack<String>();
		
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			if(s.equals("+"))
			{
				if(!operators.isEmpty())
				{
					String p=operators.peek();
					if(p.equals("("));
					else
						if(p.equals("+")||p.equals("-"))
							StdOut.print(operators.pop()+" ");
				}
				operators.push(s);
			}
			else if(s.equals("-"))
			{
				if(!operators.isEmpty())
				{
					String p=operators.peek();
					if(p.equals("("));
					else
						if(p.equals("+")||p.equals("-"))
							StdOut.print(operators.pop()+" ");
				}
				operators.push(s);
			}
			else if(s.equals("*"))
			{	
				if(!operators.isEmpty())
				{
					String p=operators.peek();
					if(p.equals("("));
					else
						if(p.equals("*")||p.equals("/"))
							StdOut.print(operators.pop()+" ");
				}
				operators.push(s);
			}
			else if(s.equals("/"))
			{
				if(!operators.isEmpty())
				{
					String p=operators.peek();
					if(p.equals("("));
					else
						if(p.equals("*")||p.equals("/"))
							StdOut.print(operators.pop()+" ");
				}
				operators.push(s);
			}
			else if(s.equals("("))
			{
				operators.push(s);
			}
			else if(s.equals(")"))
			{
				boolean shouldStop=false;
				do
				{
					String p=operators.pop();
					if(!p.equals("("))
						StdOut.print(p+" ");
					else shouldStop = true;
				}
				while(!shouldStop);
			}
			else
			{
				//we have values
				StdOut.print(s+" ");
			}
		}
		
		while(!operators.isEmpty())
			StdOut.print(operators.pop()+" ");
	}
}