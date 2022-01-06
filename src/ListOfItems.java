import java.util.Hashtable;

public interface ListOfItems {
	public void viewList();
	public void removeFromList(String name);
	//public Hashtable<String, MenuItem> getDict();
	public Hashtable getDict();
}
