package YachtClub.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class Database{
	
	private static ArrayList<Member> members;

	public Database() throws IOException, ClassNotFoundException {
		
		members = new ArrayList<Member>();
	}
	
	public void addMember(String n, String pn) {
		
		Member newMember = new Member(n, pn);
		members.add(newMember);
	}
	
	
	public Member showMember(String n) {
		
		ListIterator<Member> it = members.listIterator();
		
		while (it.hasNext()) {
			
			Member currentMember = it.next();
			
			if (currentMember.getName().toLowerCase().equals(n.toLowerCase())) {
				
				return currentMember;
			}
		}
		
		return null;
	}
	
	
	public ArrayList<Member> listMembers() {
		
		return members;
	}
	
	public void removeMember(String n) throws IOException {
		
		ListIterator<Member> it = members.listIterator();
		
		int index = 0;
		
		while (it.hasNext()) {
			
			Member currentMember = it.next();
			
			if (currentMember.getName().toLowerCase().equals(n.toLowerCase())) {
				
				members.remove(index);
				
				
				break;
			}
			
			index++;
		}
	}
	
	public void editName(String n, String nn) throws IOException {
		
		ListIterator<Member> it = members.listIterator();
		
		while (it.hasNext()) {
			
			Member currentMember = it.next();
			
			if (currentMember.getName().toLowerCase().equals(n.toLowerCase())) {
				
				currentMember.editName(nn);
				
				break;
			}
		}
	}
	
	public void editPN(String n, String pn) throws IOException {
		
		ListIterator<Member> it = members.listIterator();
		
		while (it.hasNext()) {
			
			Member currentMember = it.next();
			
			if (currentMember.getName().toLowerCase().equals(n.toLowerCase())) {
				
				currentMember.editPN(pn);
				
				break;
			}
		}
	}
	
	private Member getMember(String n) {
		
		ListIterator<Member> it = members.listIterator();
		
		while (it.hasNext()) {
			
			Member currentMember = it.next();
			
			if (currentMember.getName().toLowerCase().equals(n.toLowerCase())) {
				
				return currentMember;
			}
		}
		
		return null;
	}
	
	public void addBoat(String n, String i, String t, int l) throws IOException {
		
		getMember(n).addBoat(i, t, l);
	}
	
	public void removeBoat(String n, String i) throws IOException {
		
		getMember(n).removeBoat(i);
	}
	
	public void setBoatType(String n, String i, String t) {
		
		getMember(n).setBoatType(i, t);
	}
	
	public void setBoatLength(String n, String i, int l) {
		
		getMember(n).setBoatLength(i, l);
	}
	
	public void setBoatId(String n, String i, String ni) {
		
		getMember(n).setBoatId(i, ni);
	}
	
	public void updateMembers(ArrayList<Member> m) {
		
		members = m;
	}
}