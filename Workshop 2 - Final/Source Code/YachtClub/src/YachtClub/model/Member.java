package YachtClub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Member implements Serializable{

	private static final long serialVersionUID = 5751423326919203427L;
	private String name;
	private String pNumber;
	private String id;
	private ArrayList<Boat> boats;
	
	public Member(String n, String pn) {
		
		this.name = n;
		this.pNumber = pn;
		
		// create and set id
		Random rnd = new Random();
		this.id = n.substring(0,(n.length()/2)) + pNumber + (rnd.nextInt() * 5000);
		
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
		
		if (getBoat(i) != null) {
			
			getBoat(i).setType(t);
		}
	}
	
	public void setBoatLength(String i, int l) {
		if (getBoat(i) != null) {
			
			getBoat(i).setLength(l);
		}
	}
	
	public void setBoatId(String i, String ni) {
		if (getBoat(i) != null) {
			
			getBoat(i).setId(ni);;
		}
		
	}
	
	private Boat getBoat(String i) {
		
		ListIterator<Boat> it = boats.listIterator();
		
		// find and return boat based on string
		while (it.hasNext()) {
			
			Boat currentBoat = it.next();

			if (currentBoat.getId().toLowerCase().equals(i.toLowerCase())) {
				
				return currentBoat;
			}
		}
		return null;
	}
	
	public void removeBoat(String i) {
		
		if (getBoat(i) != null) {
			
			boats.remove(getBoat(i));
		}
	}
	
	public ArrayList<Boat> getBoats() {
		
		return boats;
	}
}