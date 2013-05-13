//page 162
//1.3.9

public class LeftParentheses
{
	public static void main(String[] args)
	{
		Stack<String> reversedInputStack=new Stack<String>();
		Stack<String> correctedStack=new Stack<String>();
	
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			reversedInputStack.push(s);
		}
		
		boolean leftTermFollows=false;
		boolean leftGroupFollows=false;
		boolean valuesStarted=false;
		int parenToClose=0;
		
		while(!reversedInputStack.isEmpty())
		{
			String s=reversedInputStack.pop();
			if(s.equals(")"))
			{
				leftTermFollows=false;
				if(valuesStarted)
					leftGroupFollows=true;
				parenToClose++;
				correctedStack.push(s);
			}
			else if(s.equals("+"))
			{
				leftTermFollows=true;
				correctedStack.push(s);
			}
			else if(s.equals("-"))
			{
				leftTermFollows=true;
				correctedStack.push(s);
			}
			else if(s.equals("*"))
			{
				leftTermFollows=true;
				correctedStack.push(s);
			}
			else if(s.equals("/"))
			{
				leftTermFollows=true;
				correctedStack.push(s);
			}
			else
			{
				//values here
				correctedStack.push(s);
				if(leftTermFollows==true)
				{
					if(parenToClose>0)
					{
						correctedStack.push("(");
						parenToClose--;
						
						if(parenToClose>0)
							if(leftGroupFollows)
							{
								correctedStack.push("(");
								parenToClose--;
							}
					}
					
					leftTermFollows=false;
					valuesStarted=true;
				}
			}
		}
		
		while(!correctedStack.isEmpty())
		{
			String s=correctedStack.pop();
			StdOut.print(s+" ");
		}
	}
}