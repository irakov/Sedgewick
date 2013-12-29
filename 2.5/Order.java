//2.5.22(page 356)

public class Order implements Comparable<Order>
{
	private double price;
	private int quantity;
	private String owner;
	
	public Order(double price,int quantity,String owner)
	{
		if(price<0) throw new IllegalArgumentException();
		if(quantity<0) throw new IllegalArgumentException();
	
		this.price=price;
		this.quantity=quantity;
		this.owner=owner;
	}	
	
	public double getPrice(){return price;}
	public int getQuantity(){return quantity;}
	public String getOwner(){return owner;}
	
	public void complete(int quantity)
	{
		if(this.quantity<quantity)
			throw new IllegalArgumentException();
			
		this.quantity-=quantity;
	}
	
	public int compareTo(Order that)
	{
		if(this.price<that.price) return -1;
		if(this.price>that.price) return +1;
		if(this.quantity<that.quantity) return -1;//we favor small orders over big ones
		if(this.quantity>that.quantity) return 1;
		return 0;
	}
}