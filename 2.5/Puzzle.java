//2.5.32
//using http://www.cs.princeton.edu/courses/archive/spr08/cos226/assignments/8puzzle.html

import java.util.Arrays;
import java.util.Collections;

public class Puzzle
{
	public class State
	{
		private Board position;
		private int moves;
		private State previousState;
				
		public State(Board position, State previousState)
		{
			this.position=position;
			this.previousState=previousState;
			if(previousState!=null)
				moves++;
			else
				moves=0;
		}
	}

	public class Board
	{
		private int[][] tiles;
		private int dimension;
	
		public Board(int[][] tiles)
		{
			this.tiles=tiles;
			dimension=tiles.length;
		}
		
		public int hamming()
		{
			int result=0;
			for(int i=0;i<dimension;i++)
				for(int j=0;j<dimension;j++)
					if(tiles[i][j]!=0&&tiles[i][j]!=(i*dimension+j+1))
						result++;
			return result;
		}
		
		public int manhattan()
		{
			int result=0;
			for(int i=0;i<dimension;i++)
				for(int j=0;j<dimension;j++)
					if(tiles[i][j]!=0&&tiles[i][j]!=(i*dimension+j+1))
					{
						int row=(tiles[i][j]-1)/dimension;
						int column=tiles[i][j]-row*dimension-1;
						result+=Math.abs(row-i)+Math.abs(column-j);
					}
			return result;
		}
		
		public boolean equals(Object obj)
		{
			if(obj==null) return false;
			if(obj==this) return true; 
			if(!(obj instanceof Board)) return false;
			Board other=(Board)obj;
			return Arrays.deepEquals(tiles,other.tiles);
		}
		
		public int hashCode()
		{
			return Arrays.deepHashCode(tiles);
		}
		
		public Iterable<Board> neighbors()
		{
			List<Board> neighbors=new ArrayList<Board>();
			for(int i=0;i<dimension;i++)
				for(int j=0;j<dimension;j++)
					if(tiles[i][j]==0)
					{
						Board neighbor;
						if(i>0)
						{
							neighbor=new Board(tiles);
							int temp=neighbor.tiles[i-1][j];
							neighbor.tiles[i-1][j]=0;
							neighbor[i][j]=temp;
							neighbors.add(neighbor);
						}
						if(i<dimension-1)
						{
							neighbor=new Board(tiles);
							int temp=neighbor.tiles[i+1][j];
							neighbor.tiles[i+1][j]=0;
							neighbor[i][j]=temp;
							neighbors.add(neighbor);
						}
						if(j>0)
						{
							int temp=neighbor.tiles[i][j-1];
							neighbor.tiles[i][j-1]=0;
							neighbor[i][j]=temp;
							neighbors.add(neighbor);
						}
						if(j<dimension-1)
						{
							int temp=neighbor.tiles[i][j+1];
							neighbor.tiles[i][j+1]=0;
							neighbor[i][j]=temp;
							neighbors.add(neighbor);
						}
					}
			return Collections.unmodifiableList(neighbors);
		}
		
		public String toString()
		{
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<dimension;i++)
			{
				sb.append("\\n");
				for(int j=0;j<dimension;j++)
				sb.append(tiles[i][j]+" ");
			}
			return sb.toString();
		}
	}
	
	public static String main(String[] args)
	{
	}
}