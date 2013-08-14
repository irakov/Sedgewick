public class InvalidOperationException extends Exception
{
	private static final long serialVersionUID=42L;

	public InvalidOperationException(){ super();}		
	public InvalidOperationException(String message){ super(message);}
	public InvalidOperationException(String message, Throwable cause){ super(message,cause);}
	public InvalidOperationException(Throwable cause){ super(cause);}
}