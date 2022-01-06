enum Type{
	mainCourse, drinks, dessert;
}

public class MenuItem extends OrderItem{
	//private String name;
	private String description;
	//private double price;
	private Type type;
	
	public MenuItem(String name, String description, double price, Type type){
		super(name);
		//this.name = name;
		this.orderitemtype = OrderItemType.alaCarte;
		this.description = description;
		this.setPrice(price);
		this.type = type;
	}
	    
	//Internal MenuItem viewList overrides viewList in OrderItem//
	public void viewList() {
		super.viewList();
		System.out.println("Description: " + description);
	}
	//Internal MenuItem viewList overrides viewList in OrderItem//
	
	/*public String getName(){
	    return this.name;
	}*/
	    
	public String getDesc() {
	    return this.description;
	}
	    
	/*public Type getType() {
	    return type;
	}*/
	    
	/*public void setName(String name) {
	    this.name = name;
	}*/
	    
	public void setDescription(String description) {
	    this.description = description;
	}
	    
	public void setType(Type type) {
	    this.type = type;
	}
}
