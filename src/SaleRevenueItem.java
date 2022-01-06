import java.util.*;

public class SaleRevenueItem{
	private Order order;
	private Calendar cal = Calendar.getInstance();
	private int year = cal.get(Calendar.YEAR);
    private int month = cal.get(Calendar.MONTH)+1;      // Month goes from 0 to 11
    private int day = cal.get(Calendar.DAY_OF_MONTH);
	//private int year;
	//private int month;
	//private int day;
    
    public SaleRevenueItem(Order order) {
    	this.order = order;
    }
    
    public int getYear() {
    	return year;
    }
    
    public int getMonth() {
    	return month;
    }
    
    public int getDay() {
    	return day;
    }
    
    public Order getOrder() {
    	return order;
    }
}
