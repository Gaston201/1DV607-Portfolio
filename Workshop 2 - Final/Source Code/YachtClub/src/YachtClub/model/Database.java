package YachtClub.model;

import java.util.ArrayList;
import java.util.ListIterator;

public class Database{
	
	private ArrayList<Member> members;

	public Database() {
		
		members = new ArrayList<Member>();
	}
	
	public void addMember(String n, String pn) {
		
		Member newMember = new Member(n, pn);
		members.add(newMember);
	}
	
	public Member getMember(String n) {
		
		ListIterator<Member> it = members.listIterator();
		
		// find member and return it
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
	
	public void removeMember(String n) {
		
		members.remove(getMember(n));
	}
	
	public void editName(String n, String nn) {
		
		getMember(n).editName(nn);
	}
	
	public void editPN(String n, String pn) {
		
		getMember(n).editPN(pn);
	}
	
	public void updateMembers(ArrayList<Member> m) {
		
		members = m;
	}
}