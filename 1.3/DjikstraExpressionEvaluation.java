//page 129
//run as java DjikstraExpressionEvaluation
//( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
//Ctrl+z

import java.util.Stack;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class DjikstraExpressionEvaluation
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		Stack<String> operators=new Stack<String>();
		Stack<Double> values=new Stack<Double>();
		
		while(input.hasNext())
		{
			String s=input.next();
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
		
		output.println(values.pop());
	}
}