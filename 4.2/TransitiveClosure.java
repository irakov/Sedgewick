//page 593

import java.io.*;

public class TransitiveClosure
{
	private DirectedDFS[] dfses;

	public TransitiveClosure(Digraph d)
	{
		dfses=new DirectedDFS[d.V()];
		for(int i=0;i<d.V();i++)
			dfses[i]=new DirectedDFS(d,i);
	}

	public boolean reachable(int v,int w)
	{
		return dfses[v].marked(w);
	}

	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);

		Digraph d=new Digraph(args[0]);
		TransitiveClosure tc=new TransitiveClosure(d);
		
		for(int i=0;i<d.V();i++)
		{
			for(int j=0;j<d.V();j++)
				if(tc.reachable(i,j))
					output.print("|Y|");
				else
					output.print("|N|");
			output.println();
		}
	}
}
