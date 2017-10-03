package YachtClub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

public class Member implements Serializable{

	private static final long serialVersionUID = 5751423326919203427L;
	private String name;
	private String pNumber;
	private String id;
	private ArrayList<Boat> boats;
	
	public Member(String n, String pn) {
		
		this.name = n;
		
		this.pNumber = pn;
		this.id = n.substring(0,(n.length()/2)) + pNumber + n.substring((n.length()/2), n.length());
		
		this.boats = new ArrayList<Boat>();
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public String getPN() {
		
		return this.pNumber;
	}

	public String getId() {
	
	return this.id;
	}
	
	public int getBoatAmount() {
		
		return this.boats.size();
	}
	
	public void editName(String n) {
		
		this.name = n;
	}
	
	public void editPN(String pn) {
	
		this.pNumber = pn;
	}
	
	public void addBoat(String i, String t, int l) {
		
		Boat newBoat = new Boat(i, t, l);
		
		this.boats.add(newBoat);
	}

	public void setBoatType(String i, String t) {
		
		getBoat(i).setType(t);
	}
	
	public void setBoatLength(String i, int l) {
		
		getBoat(i).setLength(l);
	}
	
	public void setBoatId(String i, String ni) {
		
		getBoat(i).setId(ni);;
	}
	
	private Boat getBoat(String i) {
		
		ListIterator<Boat> it = boats.listIterator();
		
		while (it.hasNext()) {
			
			Boat currentBoat = it.next();

			if (currentBoat.getId().toLowerCase().equals(i.toLowerCase())) {
				
				return currentBoat;
			}
		}
		return null;
	}
	
	public void removeBoat(String i) {
		
		ListIterator<Boat> it = boats.listIterator();
		
		int index = 0;
		
		while (it.hasNext()) {
			
			Boat currentBoat = it.next();
			
			if (currentBoat.getId().toLowerCase().equals(i.toLowerCase())) {
				
				boats.remove(index);
				
				break;
			}
			
			index++;
		}
	}
	
	public ArrayList<Boat> getBoats() {
		
		return boats;
	}
}