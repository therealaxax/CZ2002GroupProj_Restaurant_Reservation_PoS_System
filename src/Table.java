import java.util.*;
import java.util.LinkedList;

public class Table extends CommonTableRes{
	//private int remainingreservations = 3;
	private Order order = null; //each table can only have one order
	private LinkedList<Reservation> reservation ; // reservation linked list
	//String in the above Hashtable is for the customer's name
	
	public Table(int pax) {
		super(pax); //capacity of table
        //LinkedList<Reservation> reservation = new LinkedList<Reservation>();
        this.reservation = new LinkedList<Reservation>();
	}
	
	public void setReservation(Reservation reservation) {
		int a = 0;
		if(this.reservation.size()==0)
		{this.reservation.add(reservation);}
		else if(this.reservation.size()>0){
			for(int i=0;i<this.reservation.size();i++) {
				if(this.reservation.get(i).getCalendar().after(reservation.getCalendar())) {
					this.reservation.add(i, reservation);
				    a=1;
				}
				if(a==0) 
				{this.reservation.add(reservation);}
			}
		}
	}
	
	/*public void addToReservation(Reservation reservation) {
		reservations.put(reservation.getName(), reservation);
		
	}*/
	public LinkedList<Reservation> getlist(){
		return this.reservation;
	}
	
	public void removeReservation(int id) {
		this.reservation.remove(id);
	}
	
	public Reservation getReservation(int id) {
		return this.reservation.get(id);
	}
	
	/*public Reservation getReservation(String name) {
		return reservations.get(name);
	}*/
	public boolean checktime(Calendar cal) {
		Calendar time= Calendar.getInstance();
		if(cal.after(time))
		{return true;}
		else
		{return false;}
	}
	
	public boolean checkcapcity(int num) {
		if(this.getPax()>=num) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkreservation(Calendar cal) {
		if(this.reservation.size()==0) {
			return true;
		}	
		else {
			for(int i=0;i<this.reservation.size();i++) {
				Reservation temp=this.reservation.get(i);
                if(temp.getCalendar()==cal) {
                	return false;
                }
                else if(temp.getCalendar().after(cal)) {
                	break;
                }
                else if(temp.getCalendar().before(cal)) {
                	continue;
                }
			}return true;
		}
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void checkReservationPeriodExpiry() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -15); //give 15 minutes leeway
		if(this.reservation.size()!=0) {
		while(cal.after(this.reservation.get(0).getCalendar()))
		{
		this.removeReservation(0);
		}}
	}
	
	public void showreservation() {
		if(this.reservation.size()==0) {
			System.out.println("there is no reservation for this table");
		}
		else if(this.reservation.size()>0)
		{
			System.out.println("there are " + this.reservation.size() + "reservation");
			for(int i=0;i<this.reservation.size(); i++) {
				Reservation temp=this.reservation.get(i);
				System.out.println("reservation"+(i+1)+" customer "+temp.getName()+" booked the table. Time: "+ temp.getReservationDate());
			}
		}
	}
	
}
