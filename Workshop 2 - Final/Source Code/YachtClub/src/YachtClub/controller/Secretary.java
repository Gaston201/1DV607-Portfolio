package YachtClub.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import YachtClub.model.Database;
import YachtClub.model.Member;
import YachtClub.view.Console;

// initializes the database and handles commands
public class Secretary implements Serializable{
	
	private static final long serialVersionUID = -6042569623144713360L;
	private ArrayList<Member> members;
	private String cmd;
	
	private Member controlledMember;
	private String memberName;
	
	private Database database;
	private Console console;
	
	@SuppressWarnings("unchecked")
	public Secretary(Database db, Console cons) {
		
		database = db;
		console = cons;
		
		cmd = "";
		
		// initialize database storage
		// convert the database file to an array if it exists
		try {
			
			FileInputStream fis = new FileInputStream("members.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			members = (ArrayList<Member>) ois.readObject();
			ois.close();
		} catch(Exception e) {
			
			members = new ArrayList<Member>();
		}
		
		// send the list of member to the database
		database.updateMembers(members);
	}
	
	public void saveList(ArrayList<Member> m) throws IOException {
		
		// Save the current database members list as a file
		FileOutputStream fos= new FileOutputStream("members.ser");
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(m);
        oos.close();
	}
	
	public void bootRegistry() throws IOException {
		
		console.printWelcomeMessage();
		
		// connect commands to second controllers functions
		while (!cmd.equals("quit")) {
			
			// get trimmed version of user input
			cmd = console.getInputTrimmed();
			
			// make new line if command is empty
			if (cmd.isEmpty()) {
				
				emptyLine();
			} else {
				
				// create a new member
				if (cmd.equals("mkmem")) {
					createMember();
				} 
				
				// list members
				else if (cmd.equals("ls")) {
					listMembers();
				}

				// get member info
				else if (cmd.equals("cat")) {
					
					// get the members name
					controlledMember = getMemberInfo();
					
					// if member exists
					if (controlledMember != null) {
						
						// show member info
						showMemberInfo();
						
						// get another command
						memberCommnad();
						
						// edit member info
						if (cmd.equals("edit")) {
							editMember();
						} 
						
						// delete a member 
						else if (cmd.equals("rmmem")) {
							deleteMember();
						}
						
						// register a boat
						else if (cmd.equals("mkboat")) {
							registerBoat();
						}
						
						// delete a boat
						else if (cmd.equals("rmboat")) {
							deleteBoat();
						}
						
						// edit a boat
						else if (cmd.equals("editboat")) {
							editBoat();
						}
						
						// exit out of member
						else if (cmd.equals("x")) {
							
							exitMember();
							//continue;
						}
					} else {
						memberNotFound();
					}
				}
				
				// if user wants to quit
				else if (cmd.equals("quit")) {
					quit();
				} 
				
				// if command isn't found
				else {
					nonValidCommand();
				}
			}
		}
		
		saveList(database.listMembers());
	}
	
	private void createMember() throws IOException {
		
		console.printCreateMemberName();
		// get the member name
		String name = console.getInputTrimmed();
		
		console.printCreatePN(name);
		// get the member personal number
		String peronalNumber = console.getInputTrimmed();
		
		// add the new member to the database
		database.addMember(name, peronalNumber);
	}
	
	private void listMembers() throws IOException {
		
		// ask user for type of list and get input
		console.printComVer();
		String arg = console.getInputTrimmed();
		
		// list as either compact or verbose based on input
		if (arg.equals("c")) {
			
			console.listMembersCompact(database.listMembers());
		} else if (arg.equals("v")) {
			
			console.listMembersVerbose(database.listMembers());
		}
	}
	
	private void editMember() throws IOException {
		
		console.printEditName();
		cmd = console.getInput();
		
		// if user wants to edit member name
		if (cmd.equals("y")) {
			
			console.printNewName();
			cmd = console.getInput();
			
			database.editName(memberName, cmd);
			memberName = cmd;
		}
		
		console.printEditPersonal();
		cmd = console.getInput();
		
		// if user wants to edit member personal number
		if (cmd.equals("y")) {
			
			console.printEnterPersonal();
			cmd = console.getInput();
			
			database.editPN(memberName, cmd);
		}
		
		console.printEditComplete();
	}
	
	private void deleteMember() {
		
		console.printMemberWasRemoved(memberName);
		database.removeMember(memberName);
	}
	
	private void registerBoat() throws IOException {
		
		// get boat id
		console.printEnterNewBoatID();
		cmd = console.getInput();
		String i = cmd;
		
		// get boat type
		console.printEnterNewBoatType();
		cmd = console.getInput();
		String t = cmd;
		
		// get boat length
		console.printEnterNewBoatLength();
		cmd = console.getInput();
		int l;
		
		// make sure the boat length input is an integer
		try {
			 
			l = Integer.parseInt(cmd);
		} catch(Exception e) {
			
			nonValidCommand();
			return;
		}
		
		database.getMember(memberName).addBoat(i, t, l);
	}
	
	private void deleteBoat() throws IOException {
		
		// boat to be deleted
		console.printEnterBoatIDForRemoval();
		cmd = console.getInput();
		
		console.printBoatRemoved();
		database.getMember(memberName).removeBoat(cmd);
	}
	
	private void editBoat() throws IOException {
		
		console.printEnterBoatID();
		cmd = console.getInput();
		
		String editBoat = cmd;
		
		console.printEnterEditBoatID();
		cmd = console.getInput();
		
		// users wants to edit boat id
		if (cmd.equals("y")) {
			
			console.printEnterInputBoatID();
			cmd = console.getInput();
			
			database.getMember(memberName).setBoatId(editBoat, cmd);
			editBoat = cmd;
		}
		
		console.printEnterEditBoatType();
		cmd = console.getInput();
		
		// users wants to edit boat type
		if (cmd.equals("y")) {
			
			console.printEnterInputBoatType();
			cmd = console.getInput();
			
			database.getMember(memberName).setBoatType(editBoat, cmd);
		}
		
		console.printEnterEditBoatLength();
		cmd = console.getInput();
		
		// users wants to edit boat length
		if (cmd.equals("y")) {
			
			console.printEnterInputBoatLength();
			cmd = console.getInput();
			
			// make sure boat length is an integer
			try {
				 
				Integer.parseInt(cmd);
			} catch(Exception e) {
				
				nonValidCommand();
				console.printBoatEditComplete();
				return;
			}
			database.getMember(memberName).setBoatLength(editBoat, Integer.parseInt(cmd));
		}
		
		console.printBoatEditComplete();
	}
	
	private void showMemberInfo(){
		
		console.printShowMemberInfo(controlledMember);
	}
	
	private void memberCommnad() throws IOException {
		
		memberName = cmd;
		cmd = console.getInput();
	}
	
	private Member getMemberInfo() throws IOException {
		
		console.printShowMemberName();
		cmd = console.getInput();
		return database.getMember(cmd);
	}
	
	private void emptyLine() {
		
		console.printNewLine();
	}

	private void memberNotFound() {
		
		console.printMemberNotFound(cmd);
	}

	private void nonValidCommand() {
	
		console.printNonValidCommand();
	}
	
	private void quit() throws IOException {
		
		console.endConsole();
	}
	
	private void exitMember() {
		
		console.printMoveOut(memberName);
	}
	
}
