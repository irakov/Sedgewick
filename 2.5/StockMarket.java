//2.5.22(page 356)

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class StockMarket
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		int buyersCount=input.nextInt();
		MaxPQ<Order> buyers=new MaxPQ<Order>(buyersCount);
		
		for(int i=0;i<buyersCount;i++)
		{
			double price=input.nextDouble();
			int quantity=input.nextInt();
			String owner=input.next();
			
			buyers.insert(new Order(price,quantity,owner));
		}
		
		int sellersCount=input.nextInt();
		MinPQ<Order> sellers=new MinPQ<Order>(sellersCount);
		
		for(int i=0;i<sellersCount;i++)
		{
			double price=input.nextDouble();
			int quantity=input.nextInt();
			String owner=input.next();
			
			sellers.insert(new Order(price,quantity,owner));
		}
		
		while(true)
		{
			if(buyers.isEmpty()||sellers.isEmpty()) return;
			
			Order maxBuyer=buyers.max();
			Order minSeller=sellers.min();
			if(maxBuyer.getPrice()>=minSeller.getPrice())
			{
				maxBuyer=buyers.removeMax();
				minSeller=sellers.removeMin();
				int exchangedQuantity=Math.min(maxBuyer.getQuantity(),minSeller.getQuantity());
				output.println("###############");
				output.println(maxBuyer.getOwner()+" wants to buy "+maxBuyer.getQuantity()+" shares @ "+maxBuyer.getPrice()+" each");
				output.println(maxBuyer.getOwner()+" buys "+exchangedQuantity+" shares");
				output.println(minSeller.getOwner()+" wants to sell "+minSeller.getQuantity()+" shares @ "+minSeller.getPrice()+" each");
				output.println(minSeller.getOwner()+" sells "+exchangedQuantity+" shares");
				
				maxBuyer.complete(exchangedQuantity);
				minSeller.complete(exchangedQuantity);
				
				if(maxBuyer.getQuantity()>0)
					buyers.insert(maxBuyer);
				if(minSeller.getQuantity()>0)
					sellers.insert(minSeller);
			}
			else return;
		}
	}
}
