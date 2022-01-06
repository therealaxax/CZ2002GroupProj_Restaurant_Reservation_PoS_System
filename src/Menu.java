import java.util.*;
//import java.util.Scanner;

public class Menu implements ListOfItems {
	private Hashtable<String, MenuItem> menu_dict = new Hashtable<String, MenuItem>();
	
	//Internal Menu Methods//
	public void createMenuItem(String name, String desc, double price, Type type){
		//Scanner sc = new Scanner(System.in);
		//try {
			//if(name instanceof String==false){
				//throw new NotStringException("Please enter a string for the name!");
			//}
		//}catch (NotStringException e) {
			//System.out.println(e.getMessage());
			//name = sc.next();
		//}finally {
			menu_dict.put(name,new MenuItem(name, desc, price, type));
		//}
	}
	
	public MenuItem searchMenuItem(String name) {
		return menu_dict.get(name);
	}
	
	public void updateMenuItem(String name, String desc) { //overloaded
		menu_dict.get(name).setDescription(desc);
	}
	
	public void updateMenuItem(String name, double price) { //overloaded
		menu_dict.get(name).setPrice(price);
	}
	//Internal Menu Methods//
	
	//List Of Items interface//
	public void viewList() {
		for (Map.Entry<String, MenuItem> set : menu_dict.entrySet()) {
			set.getValue().viewList();
			//System.out.println("Item name: " + set.getValue().getName());
			//System.out.println("Item desc: " + set.getValue().getDesc());
			//System.out.println("Item price: " + set.getValue().getPrice());
			//System.out.println("Item type: " + set.getValue().getType());
		}
	}
	
	public void removeFromList(String name) {
		if(menu_dict.containsKey(name)) {
			menu_dict.remove(name);
		}
	}
	
	public Hashtable<String, MenuItem> getDict(){
		return menu_dict;
	}
	//List Of Items interface//
}
