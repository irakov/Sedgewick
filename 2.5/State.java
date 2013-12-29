//2.5.32(page 352)
//using http://www.cs.princeton.edu/courses/archive/spr08/cos226/assignments/8puzzle.html

public class State implements Comparable<State>
{
	private Board position;
	private int moves;
	private State previousState;
		
	public State(Board position, State previousState)
	{
		this.position=position;
		this.previousState=previousState;
		if(previousState!=null)
			moves=previousState.moves+1;
		else
			moves=0;
	}
	
	public Board getPosition() { return position;}
	public State getPrevious() {return previousState;}
	
	public boolean equals(Object obj)
	{
		if(obj==null) return false;
		if(obj==this) return true;
		if(!(obj instanceof State)) return false;
		State other=(State)obj;
		return this.position.equals(other.position);
	}
	
	public int hashCode()
	{
		return position.hashCode();
	}
	
	public int compareTo(State other)
	{
		if(position.manhattan()<other.position.manhattan())return -1;
		if(position.manhattan()>other.position.manhattan())return +1;
		return 0;
	}
	
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append(" Position: "+position.toString());
		sb.append(" Moves: "+moves);
		if(previousState!=null)
			sb.append(" Previous state: "+previousState);
		return sb.toString();
	}
}