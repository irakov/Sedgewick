//2.5.32
//partially solves 2.5.19 (isSolvable)
//using http://www.cs.princeton.edu/courses/archive/spr08/cos226/assignments/8puzzle.html

public class Puzzle
{
	int inversionsCount=0;
	
	//an odd number of inversions => not solvable
	private boolean isSolvable(int[][] tiles)
	{
		int[] unsortedInput=new int[tiles.length*tiles.length-1];
		int counter=0;
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles.length;j++)
				if(tiles[i][j]!=0)
					unsortedInput[counter++]=tiles[i][j];
		int[] aux=new int[counter-1];
		mergeSort(unsortedInput,aux,0,counter-1);
		return inversionsCount%2==0;
	}
	
	private void mergeSort(int[] input,int[] aux,int left,int right)
	{
		if(left<right)
		{
			int middle=left+(right-left)/2;
			mergeSort(input,aux,left,middle);
			mergeSort(input,aux,middle+1,right);
			merge(input,aux,left,middle,right);
		}
	}
	
	private void merge(int[] input,int[] aux,int left,int middle,int right)
	{
		for(int i=left;i<=right;i++)
			aux[i]=input[i];
		int i=left;
		int j=middle+1;
		for(int k=left;k<=right;k++)
		{
			if(i>middle)
				input[k]=aux[j++];
			else
				if(j>right)
				{
					input[k]=aux[i++];
					inversionCount++;
				}
				else
					if(aux[i]<=aux[j])
						input[k]=aux[i++];
					else
					{
						input[k]=aux[j++];
						inversionsCount+=j-middle-1;
					}
		}
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