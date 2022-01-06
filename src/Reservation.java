import java.util.*;

public class Reservation extends CommonTableRes{
	private Calendar cal = Calendar.getInstance();
	private Date reservationdate;
	private String name;
	private int contact;
	private int slotid;
	
	public Reservation(Calendar cal1,int pax,int hour, String name, int contact) {
		super(pax);
		this.name = name;
		this.contact = contact;
		this.setCalendar(cal1);
		this.setslotid(hour);
		reservationdate = this.getCalendar().getTime();
	}
	
	public Date getReservationDate() {
		return reservationdate;
	}
	
	public Calendar getCalendar() {
		return cal;
	}
	
	public int getslotid() {
		return slotid;
	}
	
	public void setslotid(int num) {
		slotid=num;
	}
	
	public void setCalendar(Calendar cal1) {
		cal =cal1;
	}

	public String getName() {
		return name;
	}
	
	public int getContact() {
		return contact;
	}
}
