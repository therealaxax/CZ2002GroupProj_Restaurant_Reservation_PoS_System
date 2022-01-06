enum OrderItemType{
	alaCarte, promoPackage;
}

public class OrderItem {
	private String name;
	public OrderItemType orderitemtype;
	private double price = 0;
	private int quantity = 1;
	
	public void viewList() {
		System.out.println("Name:" + name);
		System.out.println("Original ala Carte Price:" + price);
		System.out.println("Qty:" + quantity);
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int qty) {
		this.quantity = qty;
	}
	
	public double getPrice() {
	    return this.price;
	}
	
	public void setPrice(double price) {
	    this.price = price;
	}
	
	public OrderItem(String name) {
		this.name = name;
	}
	
	public String getName(){
	    return this.name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public OrderItemType getType() {
		return orderitemtype;
	}
}
