public class LookupCSV
{
	public static void main(String[] args)
	{
		String fileName=args[0];
		Integer keyField=Integer.parseInt(args[1]);
		Integer valueField=Integer.parseInt(args[2]);
		
		File file=new File(fileName);
		if(!file.exists()) return;
		
		Scanner fileInput=null;
		try
		{
			fileInput=new Scanner(file);
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
		
		RedBlackBST<String,String> st=new RedBlackBST<String,String>();
		while(fileInput.hasNextLine())
		{
			String line=fileInput.nextLine();
		}
	}
}