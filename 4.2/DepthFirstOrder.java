//page 580

import java.io.*;

public class DepthFirstOrder
{
	private boolean[] marked;
	
	private Queue<Integer> preOrder;
	private Queue<Integer> postOrder;
	private Stack<Integer> reversePostOrder;

	public DepthFirstOrder(Digraph g)
	{
		preOrder=new Queue<Integer>();
		postOrder=new Queue<Integer>();
		reversePostOrder=new Stack<Integer>();
		marked=new boolean[g.V()];
		
		for(int i=0;i<g.V();i++)
			if(!marked[i]) dfs(g,i);
	}
	
	private void dfs(Digraph g,int s)
	{
		preOrder.enqueue(s);
		marked[s]=true;
		for(int i:g.adj(s))
			if(!marked[i])
				dfs(g,i);
		postOrder.enqueue(s);
		reversePostOrder.push(s);
	}
	
	public Iterable<Integer> preOrder()
	{
		return preOrder;
	}
	
	public Iterable<Integer> postOrder()
	{
		return postOrder;
	}
	
	public Iterable<Integer> reversePostOrder()
	{
		return reversePostOrder;
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		String fileName=args[0];
		Digraph g=new Digraph(fileName);
		DepthFirstOrder dfo=new DepthFirstOrder(g);
		
		output.println("preOrder:");
		for(int i:dfo.preOrder()) output.print(i+" ");
		output.println();
		
		output.println("postOrder:");
		for(int i:dfo.postOrder()) output.print(i+" ");
		output.println();
		
		output.println("reversePostOrder:");
		for(int i:dfo.reversePostOrder()) output.print(i+" ");
		output.println();
	}
	
}