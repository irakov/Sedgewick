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
			if(s.equals("+")) operators.push(s);
			else if(s.equals("-")) operators.push(s);
			else if(s.equals("*")) operators.push(s);
			else if(s.equals("/")) operators.push(s);
			else if(s.equals(")")) StdOut.print(operators.pop()+" ");
			else if(s.equals("(")) StdOut.print("");
			else { StdOut.print(s+" ");	}
		}	
	}
}