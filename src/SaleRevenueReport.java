import java.util.*;

public class SaleRevenueReport{
	Vector<SaleRevenueItem> salerevenuereport = new Vector<SaleRevenueItem>();
	//private double price;
	
	/*//PriceOperations interface//
	public double totalPrice(double multiplier) {
		
	}
	//PriceOperations interface//*/
	
	//Internal Sale Revenue Report Operations//
	void addSaleRevenueItem(SaleRevenueItem salerevenueitem) {
		salerevenuereport.add(salerevenueitem);
	}
	
	void printSaleRevenueReport(int year) {
		System.out.println("Printing sale revenue report for year " + year);
		double price = 0;
		int i = 0;
		//Iterator<SaleRevenueItem> salerevenueitem = salerevenuereport.iterator();
		while(i<salerevenuereport.size()) {
			if(salerevenuereport.get(i).getYear()==year) {
				salerevenuereport.get(i).getOrder().viewList();
				price = salerevenuereport.get(i).getOrder().getPrice() + price;
			}
			i++;
		}
		System.out.println("Total sales revenue for year " + year + " is " + price);
	}
	
	void printSaleRevenueReport(int year, int month) { //overload
		System.out.println("Printing sale revenue report for year " + year + " month " + month);
		double price = 0;
		int i = 0;
		//Iterator<SaleRevenueItem> salerevenueitem = salerevenuereport.iterator();
		while(i<salerevenuereport.size()) {
			if(salerevenuereport.get(i).getYear()==year && salerevenuereport.get(i).getMonth()==month) {
				salerevenuereport.get(i).getOrder().viewList();
				price = salerevenuereport.get(i).getOrder().getPrice() + price;
			}
			i++;
		}
		System.out.println("Total sales revenue for year " + year + " month " + month + " is " + price);
	}

	void printSaleRevenueReport(int year, int month, int day) { //overload
		System.out.println("Printing sale revenue report for year " + year + " month " + month + " day " + day);
		double price = 0;
		int i = 0;
		//Iterator<SaleRevenueItem> salerevenueitem = salerevenuereport.iterator();
		while(i<salerevenuereport.size()) {
			if(salerevenuereport.get(i).getYear()==year && salerevenuereport.get(i).getMonth()==month && salerevenuereport.get(i).getDay()==day) {
				salerevenuereport.get(i).getOrder().viewList();
				price = salerevenuereport.get(i).getOrder().getPrice() + price;
			}
			i++;
		}
		System.out.println("Total sales revenue for year " + year + " month " + month + " day " + day + " is " + price);
	}
	//Internal Sale Revenue Report Operations//
}
