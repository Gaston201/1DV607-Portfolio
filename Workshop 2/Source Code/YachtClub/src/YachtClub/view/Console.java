package YachtClub.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import YachtClub.model.Boat;
import YachtClub.model.Member;

public class Console {
	
	private Scanner scan;
	
	public Console() {
		
		scan = new Scanner(System.in);
	}
	
	public void EndConsole() throws IOException {
		
		System.out.println("Shutting down...");
		scan.close();
	}
	
	public String getInput() throws IOException {
		
		String cmd = scan.nextLine();

		return cmd;
	}
	
	public void printWelcomeMessage() {
		
		System.out.println("Welcome, please follow the instructions on Instructions.txt");
	}
	
	public void printNewLine() {
		
		System.out.println("\n");
	}
	
	public void printCreateMemberName() {
		
		System.out.println("Enter the name of the new member:");
	}
	
	public void printCreatePN(String n) {
		
		System.out.println("Enter the personal number of " + n);
	}
	
	public void printComVer() {
		
		System.out.println("(c)ompact or (v)erbose listing?");
	}
	
	public void printShowMemberName() {
		
		System.out.println("Please enter name of member");
	}
	
	public void printMemberWasRemoved(String m) {
		
		System.out.println("The member " + m + " was removed");
	}
	
	public void printShowMemberInfo(Member m) {
		
		String boatList = ", Boats: ";
		ArrayList<Boat> boats = m.getBoats();
		
		for (Boat b : boats) {
			
			boatList += " " + b.getId() +" a "+ b.getLength() + "m long " + b.getType() + ",";
		}
		
		System.out.println("Name: " + m.getName() + ", Personal Number: " + m.getPN()
		+ ", Member ID: " + m.getId() + boatList);
	}
	
	public void printNonValidCommand() {
		
		System.out.println("Please enter a valid command..");
	}
	
	
	public void printEditName() {
		
		
		System.out.println("Edit name of member: y/n");
	}
	
	public void printNewName() {
		
		System.out.println("Please enter new name for member");
	}
	
	public void listMembersCompact(ArrayList<Member> a) {
		
		for (Member m : a) {
			
			System.out.println("Name: " + m.getName() + ", Member ID: " + m.getId() 
			+ ", Number of Boats: " + m.getBoatAmount());
		}
		
	}
	
	public void listMembersVerbose(ArrayList<Member> a) {
		
		for (Member m : a) {
			
			String boatList = ", Boats: ";
			ArrayList<Boat> boats = m.getBoats();
			
			for (Boat b : boats) {
				
				boatList += " " + b.getId() +" a "+ b.getLength() + "m long " + b.getType() + ",";
			}
			
			System.out.println("Name: " + m.getName() + ", Personal Number: " + m.getPN()
			+ ", Member ID: " + m.getId() + boatList);
		}
		
	}

	public void printEditPersonal() {
		System.out.println("Edit personal number of member: y/n");
	}

	public void printEnterPersonal() {
		System.out.println("Please enter personal number for member");
	}

	public void printEditComplete() {
		System.out.println("Member edit complete");
	}
	
	public void printBoatRemoved() {
		System.out.println("Boat was removed");
	}

	public void printEnterNewBoatID() {
		System.out.println("Please enter an id for the new boat");
	}

	public void printEnterNewBoatType() {
		System.out.println("Please enter the boats type");
	}

	public void printEnterNewBoatLength() {
		System.out.println("Please enter the boats length in meters");
	}

	public void printEnterBoatIDForRemoval() {
		System.out.println("Please enter the id for the boat you want to remove");
	}

	public void printEnterBoatID() {
		System.out.println("Please enter the id for the boat you want to edit");
	}

	public void printEnterEditBoatID() {
		System.out.println("Edit id of boat: y/n");
	}

	public void printEnterInputBoatID() {
		System.out.println("Please enter new id for the boat");
	}

	public void printEnterEditBoatType() {
		System.out.println("Edit type of the boat: y/n");
	}

	public void printEnterInputBoatType() {
		System.out.println("Please enter the new type for the boat");
	}

	public void printEnterEditBoatLength() {
		System.out.println("Edit length of the boat: y/n");
	}
	
	public void printEnterInputBoatLength() {
		System.out.println("Please enter the new length for the boat");
	}

	public void printBoatEditComplete() {
		System.out.println("Boat edit complete");
	}

	public void printMoveOut(String memberName) {
		System.out.println("Moving out of " + memberName);
	}

	public void printMemberNotFound(String cmd) {
		System.out.println("Member " + cmd + " not found");
	}
}