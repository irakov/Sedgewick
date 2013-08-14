public class FullStackException extends Exception
{
	private static final long serialVersionUID=42L;

	public FullStackException(){ super();}
	public FullStackException(String message){ super(message);}
	public FullStackException(String message, Throwable cause){ super(message,cause);}
	public FullStackException(Throwable cause){ super(cause);}
}