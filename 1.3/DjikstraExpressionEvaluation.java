//page 129
//run as java DjikstraExpressionEvaluation
//( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
//Ctrl+z

import java.util.Stack;

public class DjikstraExpressionEvaluation
{
	public static void main(String[] args)
	{
		Stack<String> operators=new Stack<String>();
		Stack<Double> values=new Stack<Double>();
		
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			if(s.equals("("))
				;
			else if(s.equals("+"))
				operators.push(s);
			else if(s.equals("-"))
				operators.push(s);
			else if(s.equals("*"))
				operators.push(s);
			else if(s.equals("/"))
				operators.push(s);
			else if(s.equals("sqrt"))
				operators.push(s);
			else if(s.equals(")"))
			{
				String operator=operators.pop();
				double value=values.pop();
				if(operator.equals("+"))
					value=values.pop()+value;
				else if(operator.equals("-"))
					value=values.pop()-value;
				else if(operator.equals("*"))
					value=values.pop()*value;
				else if(operator.equals("/"))
					value=values.pop()/value;
				else if(operator.equals("sqrt"))
					value=Math.sqrt(value);
				values.push(value);
			}
			else values.push(Double.parseDouble(s));
		}
		
		StdOut.println(values.pop());
	}
}