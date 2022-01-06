import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		//Maybe allow user to define how many promo packages and menus he wants(?)
		System.out.println("Testing application: ");
		
		Menu menu = new Menu(); //initialise only one empty menu
		SaleRevenueReport salerevenuereport = new SaleRevenueReport(); //initialise only one empty salerevenuereport
		Hashtable<String, PromoPackage>promos = new Hashtable<String, PromoPackage>(); //store all the promo packages
		
		System.out.println("How many tables are there?");
	
		try {
			
		final int nooftables = sc.nextInt();
		
		if(nooftables<0) {
			throw new NegativeValueException("Error: Number of tables cannot be negative!");
		}
		
		
		Hashtable<Integer, Table>tables = new Hashtable<Integer, Table>(); //store all the tables, one order and one reservation associated with a table
		for(int i=1; i<=nooftables; i++) {
			System.out.println("Please enter number of pax that table " + i + " can hold: ");
			int pax = sc.nextInt();
			tables.put(i, new Table(pax));
		}
		
		System.out.println("Please choose from the options below: ");
		System.out.println("1: Create/Update/Remove menu item ");
		System.out.println("2: Create/Update/Remove promotion ");
		System.out.println("3: Create order ");
		System.out.println("4: View order ");
		System.out.println("5: Add/Remove order item/s to/from order ");
		System.out.println("6: Table availability and reservation bookings ");
		System.out.println("7: Print order invoice ");
		System.out.println("8: Print sale revenue report by period (eg day or month) ");
		System.out.println("9: Quit ");
		
		int choice = 0;
		while(choice<9) {
			System.out.println("Please enter your choice");
			choice = sc.nextInt();
			
			switch(choice){
			case 1: 
				System.out.println("Select 1 to create menu item, 2 to update menu item, 3 to remove menu item");
				int case1choice = sc.nextInt();
				
				if(case1choice==1) {
					System.out.println("Now creating menu item, please enter name, description, price, and menu item type in this order:");
					String name = sc.next();
					String desc = sc.next();
					double price = sc.nextDouble();
					System.out.println("Enter 1 for main course, 2 for drinks, 3 for dessert:");
					int type = sc.nextInt();
					if(type==1) {
						menu.createMenuItem(name, desc, price, Type.mainCourse);
					}else if(type==2) {
						menu.createMenuItem(name, desc, price, Type.drinks);
					}else {
						menu.createMenuItem(name, desc, price, Type.dessert);
					}
					System.out.println("Menu item created!");
				}else if(case1choice==2) {
					//update menu item
					System.out.println("Please enter name of menu item to update: ");
					String name = sc.next();
					System.out.println("Please enter 1 to update description or 2 to update price");
					int updatechoice = sc.nextInt();
					if(updatechoice==1) {
						System.out.println("Please enter a new description");
						String desc = sc.next();
						menu.updateMenuItem(name, desc);
					}else {
						System.out.println("Please enter a new price");
						double price = sc.nextDouble();
						menu.updateMenuItem(name, price);
					}
				}else {
					System.out.println("Please enter name of menu item to remove");
					String name = sc.next();
					menu.removeFromList(name);
					System.out.println("Menu item removed");
				}break;
			case 2: 
				System.out.println("Select 1 to create promotion, 2 to update promotion, 3 to remove promotion");
				int case2choice = sc.nextInt();
				
				if(case2choice==1) {
					System.out.println("Now creating promotion, please enter name of the promotion");
					String promoname = sc.next();
					PromoPackage promopackage = new PromoPackage(menu, promoname);
					promos.put(promoname, promopackage);
					System.out.println("Please add the first menu item to the promo package");
					String name = sc.next();
					promos.get(promoname).addFromMenu(name);
					System.out.println("First menu item added");
					System.out.println("How much discount would you like on the whole package in %?");
					double discount = sc.nextDouble();
					promopackage.totalPrice(1-(discount/100));
					//promopackage.viewList(); //for testing and debugging
					//promopackage.totalPrice(1); //setting the price of the promopackage, i put this here for testing and debugging purposes
				}else if(case2choice==2) {
					System.out.println("Please enter name of promotion to be updated");
					String promoname = sc.next();
					System.out.println("Please enter name of menu item to add to promo package");
					String name = sc.next();
					promos.get(promoname).addFromMenu(name);
					System.out.println("Menu item added");
					System.out.println("How much discount would you like on the whole package in %?");
					double discount = sc.nextDouble();
					promos.get(promoname).totalPrice(1-(discount/100));
				}else {
					System.out.println("Please enter name of promotion to remove");
					String promoname = sc.next();
					promos.remove(promoname);
				}break;
			case 3:
				System.out.println("Which table will you create an order for?");
				try {
					int tablenocase3 = sc.nextInt();
					if(!tables.containsKey(tablenocase3)){
						throw new TableOutOfBounds("Table does not exist!");
					}
					System.out.println("Please enter the name of the staff:");
					String staffname = sc.next();
					System.out.println("Please enter the gender of the staff:");
					String gender = sc.next();
					System.out.println("Please enter the employee ID of the staff:");
					int employeeid = sc.nextInt();
					System.out.println("Please enter the job title of the staff:");
					String jobtitle = sc.next();
					Order order = new Order(staffname, gender, employeeid, jobtitle);	
					tables.get(tablenocase3).setOrder(order);
					System.out.println("New order created!");
				}catch(TableOutOfBounds e) {
					System.out.println(e.getMessage());
				}break;
			case 4:
				System.out.println("Please enter the table number whose order you want to view");
				int tablenocase4 = sc.nextInt();
				try {
					if(!tables.containsKey(tablenocase4)) {
						throw new TableOutOfBounds("Table does not exist!");
					}
					tables.get(tablenocase4).getOrder().viewList();
				}catch(TableOutOfBounds e) {
					System.out.println(e.getMessage());
				}break;
			case 5:
				System.out.println("Please enter the table number");
				int tablenocase5 = sc.nextInt();
				try {
					if(!tables.containsKey(tablenocase5)) {
						throw new TableOutOfBounds("Table does not exist!");
					}
					System.out.println("Select 1 to add an alacarte item or 2 to add a promo package");
					int case5choice = sc.nextInt();
					if(case5choice==1) {
						System.out.println("Enter the name of the alacarte item to add");
						String name = sc.next();
						tables.get(tablenocase5).getOrder().addToOrderMenuItem(name, menu);
						System.out.println("alacarte item added!");
					}else {
						System.out.println("Enter the name of the promo package to add");
						String name = sc.next();
						tables.get(tablenocase5).getOrder().addToOrderPromoPackage(name, promos.get(name));
						System.out.println("Promo package added");
					}
				}catch(TableOutOfBounds e){
					System.out.println(e.getMessage());
				}
			break;
			case 6:
				System.out.println("enter 1 create a reservation");
				System.out.println("enter 2 for delete a reservation");
				System.out.println("enter 3 for check table availability for a time slot");
				//check expiry, print out all the tables with all their current reservations
				int case6choice = sc.nextInt();
				switch(case6choice) {
				case 1: 
					//try {
						System.out.println("enter the data of the reservation in yyyyMMDD format");
						String str = sc.next();
						int y=Integer.parseInt(str.substring(0,4));
						int m=Integer.parseInt(str.substring(4,6));
						int d=Integer.parseInt(str.substring(6));
						Calendar cal=Calendar.getInstance();
						cal.set(Calendar.YEAR, y);
						cal.set(Calendar.MONTH, m);
						cal.set(Calendar.DATE, d);
						System.out.println("select the time slot id(0-11)");
						int h=sc.nextInt();
						cal.set(Calendar.HOUR,h);
						Calendar time= Calendar.getInstance();
						if(!(cal.after(time)))
						{System.out.println("Error! time slot passed");
							break;}
					    System.out.println("enter the number of costomer");
						int num=sc.nextInt();
						int a=1;
						for (Map.Entry<Integer, Table> set : tables.entrySet()) {
							if(set.getValue().checkcapcity(num)&&set.getValue().checkreservation(cal)) {
								System.out.println("Table " + set.getKey() + " is available");
								a=0;
							}
							else {
								System.out.println("Table " + set.getKey() + " is not available");
			                   	}	
						}if(a==1) {
							System.out.println("no table available");
							break;
						}
						System.out.println("enter the table id");
						int tablechoicecase2 = sc.nextInt();
						System.out.println("Enter your name");
						String name = sc.next();
						System.out.println("Enter your contact");
						int contact = sc.nextInt();
						Reservation temp= new Reservation(cal,num,h,name,contact);
						tables.get(tablechoicecase2).setReservation(temp);
						System.out.println("booking sucessfully");
				//	}catch(TableOutOfBounds e) {
					//	System.out.println(e.getMessage());
					//}
						break;
				case 2:
					//try {
						System.out.println("enter the table id");
						int t = sc.nextInt();
						tables.get(t).showreservation();
						System.out.println("enter the reservation id you want to remove");
						int t1 = sc.nextInt();
						if(t1<=0||t1>tables.get(t).getlist().size()) {
							System.out.println("Error, wrong reservation id");
						}
	                    tables.get(t).removeReservation(t1);
	                    System.out.println("remove sucessfully");
				//	}catch(TableOutOfBounds e) {
					//	System.out.println(e.getMessage());
					//}
	                    break;
				case 3:
					System.out.println("enter the data of the reservation in yyyyMMDD format");
				//	try {
					String str1 = sc.next();
					int y1=Integer.parseInt(str1.substring(0,4));
					int m1=Integer.parseInt(str1.substring(4,6));
					int d1=Integer.parseInt(str1.substring(6));
					Calendar cal1=Calendar.getInstance();
					cal1.set(Calendar.YEAR, y1);
					cal1.set(Calendar.MONTH, m1);
					cal1.set(Calendar.DATE, d1);
					System.out.println("select the time slot id(0-11)");
					int h1=sc.nextInt();
					cal1.set(Calendar.HOUR,h1);
					Calendar time1= Calendar.getInstance();
					if(!(cal1.after(time1)))
					{System.out.println("Error! time slot passed");
						break;}
					int a1=0;
					for (Map.Entry<Integer, Table> set : tables.entrySet()) {
						if(set.getValue().checkreservation(cal1)) {
							System.out.println("Table " + set.getKey() + " is available with capacity"+set.getValue().getPax());
							a1++;
					}
						else {
							System.out.println("Table " + set.getKey() + " is not available" );
						}}
					if(a1==0) {
                    System.out.println("no table available");}
					else if(a1>0)
					{
						System.out.println("there are" + a1 +"table(s) available.");
					}
					//}catch(TableOutOfBounds e){
						//System.out.println(e.getMessage());
					//}
						break;
				}break;
			case 7:
				System.out.println("Please select table number to print order invoice for: ");
				int case7tablechoice = sc.nextInt();
				try {
					if(!tables.containsKey(case7tablechoice)) {
						throw new TableOutOfBounds("Table does not exist!");
					}
					System.out.println("Member? 1 if member and 0 if not");
					int membership = sc.nextInt();
					if(membership==1) {
						System.out.println("Please enter amount of member's discount in %");
						double discount = sc.nextDouble();
						discount = discount/100;
						tables.get(case7tablechoice).getOrder().printInvoice();
						System.out.println("Your total amount including GST is " + 1.07*tables.get(case7tablechoice).getOrder().totalPrice(1-discount)); //1.07 because 7% GST
						salerevenuereport.addSaleRevenueItem(new SaleRevenueItem(tables.get(case7tablechoice).getOrder())); //if using the computer time
						tables.get(case7tablechoice).setOrder(null); // customer vacated table
						tables.get(case7tablechoice).removeReservation(0); // customer vacated table
					}else {
						tables.get(case7tablechoice).getOrder().printInvoice();
						System.out.println("Your total amount including GST is " + 1.07*tables.get(case7tablechoice).getOrder().totalPrice(1)); //1.07 because 7% GST
						salerevenuereport.addSaleRevenueItem(new SaleRevenueItem(tables.get(case7tablechoice).getOrder()));
						tables.get(case7tablechoice).setOrder(null); // customer vacated table
						tables.get(case7tablechoice).removeReservation(0); // customer vacated table
					}
				}catch(TableOutOfBounds e) {
					System.out.println(e.getMessage());
				}break;
			case 8:
				System.out.println("Print sale revenue report by 1 - year, 2 - month, 3 - day");
				int case8choice = sc.nextInt();
				if(case8choice==1) {
					System.out.println("Please enter year to print report");
					int year = sc.nextInt();
					salerevenuereport.printSaleRevenueReport(year);
				}else if(case8choice==2) {
					System.out.println("Please enter year to print report");
					int year = sc.nextInt();
					System.out.println("Please enter month to print report");
					int month = sc.nextInt();
					salerevenuereport.printSaleRevenueReport(year, month);
				}else {
					System.out.println("Please enter year to print report");
					int year = sc.nextInt();
					System.out.println("Please enter month to print report");
					int month = sc.nextInt();
					System.out.println("Please enter day to print report");
					int day = sc.nextInt();
					salerevenuereport.printSaleRevenueReport(year, month, day);
				}break;
			default:
				System.out.println("Now terminating...");
			}
		}
	}
		catch(NegativeValueException e) {
			System.out.println(e.getMessage());
		}
	}
}
