//2.5.22
public class StockMarket
{
	public static void main(String[] args)
	{
		int buyersCount=StdIn.readInt();
		MaxPQ<Order> buyers=new MaxPQ<Order>(buyersCount);
		
		for(int i=0;i<buyersCount;i++)
		{
			double price=StdIn.readDouble();
			int quantity=StdIn.readInt();
			String owner=StdIn.readString();
			
			buyers.insert(new Order(price,quantity,owner));
		}
		
		int sellersCount=StdIn.readInt();
		MinPQ<Order> sellers=new MinPQ<Order>(sellersCount);
		
		for(int i=0;i<sellersCount;i++)
		{
			double price=StdIn.readDouble();
			int quantity=StdIn.readInt();
			String owner=StdIn.readString();
			
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
				StdOut.println("###############");
				StdOut.println(maxBuyer.getOwner()+" wants to buy "+maxBuyer.getQuantity()+" shares @ "+maxBuyer.getPrice()+" each");
				StdOut.println(maxBuyer.getOwner()+" buys "+exchangedQuantity+" shares");
				StdOut.println(minSeller.getOwner()+" wants to sell "+minSeller.getQuantity()+" shares @ "+minSeller.getPrice()+" each");
				StdOut.println(minSeller.getOwner()+" sells "+exchangedQuantity+" shares");
				
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
