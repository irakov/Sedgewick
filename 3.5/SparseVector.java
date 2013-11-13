import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class SparseVector
{
	private LinearProbingHashST<Integer,Double> st;
	private int size;
	
	public SparseVector(int size)
	{
		this.size=size;
		st=new LinearProbingHashST<Integer,Double>();
	}
	
	public int size()
	{
		return size;
	}
	
	public void put(int i,double x)
	{
		if(i<0||i>=size) throw new IndexOutOfBoundsException();
		st.put(i,x);
	}
	
	public double get(int i)
	{
		if(i<0||i>=size) throw new IndexOutOfBoundsException();
		if(!st.contains(i)) return 0;
		else return st.get(i);
	}
	
	public double dot(double[] that)
	{
		double sum=0;
		for(int i:st.keys())
			sum+=that[i]*st.get(i);
		return sum;
	}
	
	public static void main(String[] args)
	{
		SparseVector a[]=new SparseVector[5];
		a[0]=new SparseVector(5);
		a[0].put(1,0.9);
		
		a[1]=new SparseVector(5);
		a[1].put(2,0.36);a[1].put(3,0.36);a[1].put(4,0.18);
		
		a[2]=new SparseVector(5);
		a[2].put(3,0.90);
		
		a[3]=new SparseVector(5);
		a[3].put(0,0.90);
		
		a[4]=new SparseVector(5);
		a[4].put(0,0.47);a[4].put(2,0.47);
		
		double x[]=new double[5];
		x[0]=0.05;x[1]=0.04;x[2]=0.36;x[3]=0.37;x[4]=0.19;
		
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		for(int i=0;i<5;i++)
			output.println(a[i].dot(x));
	}
}		