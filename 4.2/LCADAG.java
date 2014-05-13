import java.io.*;

//based on http://www.topcoder.com/tc?d1=tutorials&d2=lowestCommonAncestor&module=Static


public class LCADAG
{	
	public LCADAG(Digraph d,int u,int v)
	{
		//1.find sources in DAG
		Iterable<Integer> sources=getSources(d);

		//2.check vertices are in the same component
		//and find the common source
		int source=hasCommonSource(d,sources,u,v);
		
		//3.reverse topological sort
		Iterable<Integer> rtop=reverseTopoSort(d);

		//4.compute the levels of the forest
		LinearProbingHashST<Integer,Integer> levels=computeLevels(rtop,d);

		//5.compute euler tour of given digraph that starts with common source
	}

	private Iterable<Integer> getSources(Digraph d)
	{
		Degrees deg=new Degrees(d);
		return deg.sources();
	}

	private int findCommonSource(Digraph d,Iterable<Integer> sources,int u,int v)
	{
		boolean sourceFound=false;
		for(int source:sources)
		{
			DepthFirstDirectedPaths paths=new DepthFirstDirectedPaths(d, source);
			if(paths.hasPathTo(u)&&paths.hasPathTo(v))
				return source;
		}

		throw new RuntimeException("No common source found");
	}

	private Iterable<Integer> reverseTopoSort(Digraph d)
	{
		Topological topoSort=new Topological(d);
		Iterable<Integer> topoOrder=topoSort.order();
		Stack<Integer> stack=new Stack<Integer>();
		for(Integer i:topoOrder)
			stack.push(i);
		return stack;
	}

	private LinearProbingHashST<Integer,Integer> computeLevels(Iterable<Integer> nodes,Digraph d)
	{
		LinearProbingHashST<Integer,Integer> result=new LinearProbingHashST<Integer,Integer>();
		Digraph r=d.reverse();
		for(Integer i:nodes)
		{
			int maxHeight=0;
			for(int j:r.adj(i))
				if(result.contains(j))
					if(maxHeight<result.get(j)+1)
						maxHeight=result.get(j)+1;
			result.put(i,maxHeight);
		}

		return result;
	}
		
	private Iterable<Integer> eulerTour(Digraph d,int startNode)
	{
		Stack<Integer> stack=new Stack<Integer>();
		LinkedList<Integer> result=new LinkedList<Integer>();
		stack.push(startNode);
		while(!stack.isEmpty())
		{
			Integer node=stack.pop();
			//check child not in result;
			result.add(node);
		}
	}

	public static void main(String[] args)
	{
		Digraph d=new Digraph(args[0]);
		int u=Integer.parse(args[1]);
		int v=Integer.parse(args[2]);
	}
}
	
