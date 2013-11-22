//3.5.23

import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class SparseMatrix
{
	private int size;
	private SparseVector[] rows;
	private SparseVector[] cols;

	public SparseMatrix(int size)
	{
		this.size=size;
		rows=new SparseVector[size];
		cols=new SparseVector[size];
		for(int i=0;i<size;i++) rows[i]=new SparseVector(size);
		for(int i=0;i<size;i++) cols[i]=new SparseVector(size);
	}
	
	public void put(int i,int j,double value)
	{
		if(i<0||i>=size) throw new IndexOutOfBoundsException();
		if(j<0||j>=size) throw new IndexOutOfBoundsException();
		rows[i].put(j,value);
		cols[j].put(i,value);
	}
	
	public double get(int i,int j)
	{
		if(i<0||i>=size) throw new IndexOutOfBoundsException();
		if(j<0||j>=size) throw new IndexOutOfBoundsException();
		return rows[i].get(j);//or return cols[j].get(i);
	}
	
	public SparseMatrix plus(SparseMatrix other)
	{
		if(other.size!=size) throw new IllegalArgumentException();
		SparseMatrix result=new SparseMatrix(size);
		for(int i=0;i<size;i++)
			result.rows[i]=rows[i].plus(other.rows[i]);
		for(int i=0;i<size;i++)
			result.cols[i]=cols[i].plus(other.cols[i]);
		return result;
	}
	
	public SparseVector dot(SparseVector other)
	{
		if(other.size()!=size) throw new IllegalArgumentException();
		SparseVector result=new SparseVector(size);
		for(int i=0;i<size;i++)
			result.put(i,rows[i].dot(other));
		return result;
	}
	
	public SparseMatrix dot(SparseMatrix other)
	{
		if(other.size!=size) throw new IllegalArgumentException();
		SparseMatrix result=new SparseMatrix(size);
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				result.put(i,j,rows[i].dot(other.cols[j]));
		return result;
	}
	
	public String toString()
	{
		String s="";
		for(int i=0;i<size;i++)
		{
			s+=i+": "+rows[i]+"\n";
		}
		return s;
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		SparseMatrix A = new SparseMatrix(5);
        SparseVector x = new SparseVector(5);
        A.put(0, 0, 1.0); A.put(1, 1, 1.0); A.put(2, 2, 1.0); A.put(3, 3, 1.0);
        A.put(4, 4, 1.0); A.put(2, 4, 0.3); x.put(0, 0.75); x.put(2, 0.11);
        output.println("x     : " + x);
        output.println("A     : " + A);
        output.println("Ax    : " + A.dot(x));
        output.println("A + A : " + A.plus(A));
	}
}