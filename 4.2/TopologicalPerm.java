//4.2.9(597)

import java.io.*;
import java.util.*;

public class TopologicalPerm
{
	private Digraph d;
	private Iterable<Integer> perm;

	public  TopologicalPerm(Digraph d,Iterable<Integer> perm)
	{
		if(!isDAG(d)) throw new RuntimeException("Digraph is not DAG");
		this.d=d;
		this.perm=perm;
	}

	private boolean isDAG(Digraph d)
	{
		Topological topo=new Topological(d);
		return topo.isDAG();
	}

	public boolean isTopoPerm()
	{
		boolean[] marked=new boolean[d.V()];
		Stack<Integer> stack=new Stack<Integer>();
		for(int node:perm)
		{
			marked[node]=true;
			if(!stack.isEmpty())
			{
				int parent=stack.peek();
				boolean found=false;
				for(int i:d.adj(node))
					if(i==node)
					{
						found=true;
						break;
					}
				if(!found) return false;
			}

			boolean hasChildren=false;
			for(int i:d.adj(node))
				if(!marked[i])
				{
					stack.push(node);
					hasChildren=true;
					break;
				}
			while(!hasChildren)
			{
				if(stack.isEmpty()) break;
				int parent=stack.peek();
				for(int i:d.adj(parent))
					if(!marked[i])
					{
						hasChildren=true;
						break;
					}
				if(!hasChildren) stack.pop();
			}
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);

		Digraph d=new Digraph(args[0]);

		File file=new File(args[1]);
		if(!file.exists()) throw new IllegalArgumentException();
		Scanner input=null;
		try
		{
			input=new Scanner(file);
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}

		Bag<Integer> perm=new Bag<Integer>();
		while(input.hasNext()) perm.add(input.nextInt());

		TopologicalPerm topo=new TopologicalPerm(d,perm);
		output.println("is topological permutation:"+topo.isTopoPerm());
	}	
}
