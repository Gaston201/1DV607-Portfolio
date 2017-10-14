package YachtClub.model;

import java.io.Serializable;

public class Boat implements Serializable{

	private static final long serialVersionUID = 2887806242090503495L;
	private String id;
	private BoatType type;
	private int length;
	
	public Boat(String i, String t, int l) {
		
		this.id = i;
		setType(t);
		this.length = l;
	}
	
	public int getLength() {
		
		return this.length;
	}
	
	public BoatType getType() {
		
		return this.type;
	}
	
	public String getId() {
		
		return this.id;
	}
	
	public void setLength(int l) {
		
		this.length = l;
	}
	
	public void setType(String s) {
		
		// convert string into legal boat type
		if (s.toLowerCase().equals("sailboat")) {
			
			this.type = BoatType.Sailboat;
		} else if (s.toLowerCase().equals("motorsailer")) {
			
			this.type = BoatType.Motorsailer;
		} else if (s.toLowerCase().equals("kayak")) {
			
			this.type = BoatType.Kayak;
		} else {
			
			this.type = BoatType.Other;
		}
	}
	
	public void setId(String i) {
		
		this.id = i;
	}
	
	enum BoatType {
		
		Sailboat,
		Motorsailer,
		Kayak,
		Other
		
	}
}
