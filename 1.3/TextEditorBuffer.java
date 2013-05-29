//1.3.44
//page 170

public class TextEditorBuffer
{
	private int size;
	private Stack<String> left, right;

	public TextEditorBuffer()
	{
		left=new Stack<String>();
		right=new Stack<String>();
	}
	
	public void insert(String c)
	{
		left.push(c);
		size++;
	}
	
	public String delete()
	{
		size--;
		return right.pop();
	}
	
	public void left(int k)
	{
		boolean nullFound=false;
		int i=0;
		while(i<k&&!nullFound)
		{
			String c=left.pop();
			if(c==null)
				nullFound=true;
			else
				right.push(c);
			i++;
		}
	}
	
	public void right(int k)
	{
		boolean nullFound=false;
		int i=0;
		while(i<k&&!nullFound)
		{
			String c=right.pop();
			if(c==null)
				nullFound=true;
			else
				left.push(c);
			i++;
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public static void main(String[] args)
	{
		TextEditorBuffer buffer=new TextEditorBuffer();
		buffer.insert("a");
		buffer.insert("b");
		buffer.insert("c");
		buffer.insert("d");
		buffer.insert("e");
		buffer.left(2);
		StdOut.println(buffer.delete());
		StdOut.println(buffer.size());
	}
}