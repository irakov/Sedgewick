//2.5.32
//using http://www.cs.princeton.edu/courses/archive/spr08/cos226/assignments/8puzzle.html

public class Puzzle
{
	private boolean isSolvable()
	{
		//count number of inversions - if it's odd - no solution, similar to 2.5.19
		return true;
	}

	public static void main(String[] args)
	{
		int dimension=StdIn.readInt();
		int[][] tiles=new int[dimension][dimension];
		for(int i=0;i<dimension;i++)
			for(int j=0;j<dimension;j++)
				tiles[i][j]=StdIn.readInt();
		
		State initialState=new State(new Board(tiles),null);
		
		int[][] goalTiles=new int[dimension][dimension];
		for(int i=0;i<dimension;i++)
			for(int j=0;j<dimension;j++)
				if(i!=dimension-1||j!=dimension-1)
					goalTiles[i][j]=i*dimension+j+1;
		goalTiles[dimension-1][dimension-1]=0;
		State goalState=new State(new Board(goalTiles),null);
	
		MinPQ<State> solution=new MinPQ<State>();
		solution.insert(initialState);
		
		while(!(solution.min().equals(goalState)))
		{
			State minState=solution.removeMin();
			Iterable<Board> neighbors=minState.getPosition().neighbors();
			for(Board neighbor:neighbors)
			{
				if(minState.getPrevious()==null||!(minState.getPrevious().getPosition().equals(neighbor)))
					solution.insert(new State(neighbor,minState));
			}
		}
		
		StdOut.println(solution.min().toString());
	}
}