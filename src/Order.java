import java.util.Hashtable;
import java.util.Map;

//OrderItem will extend Item class, and Order will be composed of OrderItems.
public class Order extends OrderItem implements ListOfItems, PriceOperations{
	private Hashtable<String, OrderItem>order_dict = new Hashtable<String, OrderItem>();
	private String gender;
	private int employeeid;
	private String jobtitle;
	public Order(String staffname, String gender, int employeeid, String jobtitle) {
		super(staffname);
		this.gender = gender;
		this.employeeid = employeeid;
		this.jobtitle = jobtitle;
	}
	
	//Internal Order operations//
	//Constructors overloaded to cater to alacarte or promopackage//
	public void addToOrderMenuItem(String name, Menu menu) {
		if(order_dict.containsKey(name)) {
			order_dict.get(name).setQuantity(this.getQuantity()+1);
		}else {
			OrderItem menuitem = menu.searchMenuItem(name); //upcasting
			order_dict.put(name,menuitem);
		}
	}
	public void addToOrderPromoPackage(String name, PromoPackage promo) {
		if(order_dict.containsKey(name)) {
			order_dict.get(name).setQuantity(this.getQuantity()+1);
		}else {
			OrderItem promopackage = promo; //upcasting
			order_dict.put(name,promopackage);
		}
	}
	//Constructors overloaded to cater to alacarte or promopackage//
	//Print Order Invoice//
	public void printInvoice() {
		System.out.println("Staff member is " + this.getName());
		System.out.println("Gender is " + this.gender);
		System.out.println("Employee id is " + this.employeeid);
		System.out.println("Job title is " + this.jobtitle); 
		this.viewList();
	}
	//Print Order Invoice//
	//Internal Order operations//
	
	//ListOfItems interface//
	public void viewList() {
		for (Map.Entry<String, OrderItem> set : order_dict.entrySet()) {
			set.getValue().viewList();
			//System.out.println("Item name: " + set.getValue().getName());
			/*if(set.getValue().getType() == OrderItemType.alaCarte) {
				MenuItem menuitem = (MenuItem) set.getValue();
				System.out.println("---Alacarte item---");
				System.out.println("Item name: " + menuitem.getName());
				System.out.println("Item desc: " + menuitem.getDesc());
				System.out.println("Item price: " + menuitem.getPrice());
				System.out.println("Item type: " + menuitem.getType());
				System.out.println("Item qty: " + menuitem.getQuantity());
			}
			else {
				System.out.println("---Promo Package---");
				PromoPackage promopackage = (PromoPackage) set.getValue();
				System.out.println("Promo qty: " + promopackage.getQuantity());
				promopackage.viewList();
			}*/
			System.out.println("----------------");
		}
	}
	public void removeFromList(String name) {
		if(order_dict.containsKey(name)) {
			order_dict.remove(name);
		}
	}
	public Hashtable<String, OrderItem> getDict() {
		return order_dict;
	}
	//ListOfItems interface//
	
	//PriceOperations interface//
	public double totalPrice(double multiplier) {
		double price = 0;
		for (Map.Entry<String, OrderItem> set : order_dict.entrySet()) {
			if(set.getValue().getType() == OrderItemType.alaCarte) {
				MenuItem menuitem = (MenuItem) set.getValue();
				price = price + menuitem.getPrice()*menuitem.getQuantity();
				//this.setPrice(this.getPrice()+(menuitem.getPrice()*menuitem.getQuantity()));
			}
			else {
				PromoPackage promopackage = (PromoPackage) set.getValue();
				price = price + promopackage.getPrice()*promopackage.getQuantity();
				//this.setPrice(this.getPrice()+(promopackage.getPrice()*promopackage.getQuantity()));
			}
		}
		this.setPrice(price*multiplier);
		//this.setPrice(this.getPrice()*multiplier);
		System.out.println("order price: " + this.getPrice()); //for testing and debugging
		return this.getPrice();
	}
	//PriceOperations interface//
}
