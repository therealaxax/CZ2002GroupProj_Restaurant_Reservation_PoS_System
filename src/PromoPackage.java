import java.util.Hashtable;
import java.util.Map;

public class PromoPackage extends OrderItem implements ListOfItems, OperationsOnMenu, PriceOperations{
	public ListOfItems menuchosen;
	private Hashtable<String, MenuItem>promo_dict = new Hashtable<String, MenuItem>();
	//private double price = 0;
	
	public PromoPackage(ListOfItems menuchosen, String name) {
		super(name);
		this.menuchosen = menuchosen;
		this.orderitemtype = OrderItemType.promoPackage;
	}
	
	//List Of Items interface//
	//Internal PromoPackage viewList() overrides viewList() in OrderItem//
	public void viewList() {
		System.out.println("Price of this promo package is: " + this.getPrice());
		for (Map.Entry<String, MenuItem> set : promo_dict.entrySet()) {
			set.getValue().viewList();
			//System.out.println("Item name: " + set.getValue().getName());
			//System.out.println("Item desc: " + set.getValue().getDesc());
			//System.out.println("Item price: " + set.getValue().getPrice());
			//System.out.println("Item type: " + set.getValue().getType());
			System.out.println("---------------------------");
		}
	}
	//Internal PromoPackage viewList() overrides viewList() in OrderItem//
	public void removeFromList(String name) {
		if(promo_dict.containsKey(name)) {
			promo_dict.remove(name);
		}
	}
	public Hashtable<String, MenuItem> getDict(){
		return promo_dict;
	}
	//List Of Items interface//
	
	//OperationsOnMenu interface//
	public void addFromMenu(String name) {
		if(promo_dict.containsKey(name)) {
			promo_dict.get(name).setQuantity(getQuantity()+1);
		}else {
			Hashtable<String, MenuItem>operateon = menuchosen.getDict();
			MenuItem menuitem = operateon.get(name);
			promo_dict.put(name, menuitem);
		}
	}
	//OperationsOnMenu interface//
	
	//PriceOperations interface//
	public double totalPrice(double multiplier) { //calculate the promotional price based on the total prices of the alacarte items in the promo package
		double promoprice = 0;
		for (Map.Entry<String, MenuItem> set : promo_dict.entrySet()) {
			//System.out.println("set.getvalue.getprice" + set.getValue().getPrice()); //for testing and debugging
			//System.out.println("this.getprice" + this.getPrice()); //for testing and debugging
			promoprice = promoprice + set.getValue().getPrice()*set.getValue().getQuantity();
			//System.out.println("this.getprice after setprice" + this.getPrice()); //for testing and debugging
		}
		this.setPrice(promoprice*multiplier);
		System.out.println("promo price: " + this.getPrice()); //for testing and debugging
		return this.getPrice();
	}
	//PriceOperations interface//
}
