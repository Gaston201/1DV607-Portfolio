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

public class Secretary implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cmd;
	private ArrayList<Member> members;
	
	@SuppressWarnings("unchecked")
	public void initDatabase(Database db) {
		
		try {
			
			FileInputStream fis = new FileInputStream("members.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			members = (ArrayList<Member>) ois.readObject();
			ois.close();
		} catch(Exception e) {
			
			members = new ArrayList<Member>();
		}
		
		db.updateMembers(members);
	}
	
	public void saveList(ArrayList<Member> m) throws IOException {
		
		FileOutputStream fos= new FileOutputStream("members.ser");
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(m);
        oos.close();
	}
	
	public void initRegistry(Database database, Console console) throws IOException {
		
		initDatabase(database);
		
		cmd = "";
		
		console.printWelcomeMessage();
		
		while (!cmd.equals("quit")) {
			
			
			
			// get user input and trim it
			cmd = console.getInput();
			cmd = cmd.trim();
			
			// make new line if command is empty
			if (cmd.isEmpty()) {
				
				console.printNewLine();
			} else {
				
				// create a new member
				if (cmd.equals("mkmem")) {
					
					console.printCreateMemberName();
					// get the member name
					cmd = console.getInput();
					String argName = cmd.trim();
					
					console.printCreatePN(argName);
					// get the member personal number
					cmd = console.getInput();
					String argPN = cmd.trim();
					
					// add the new member to the database
					database.addMember(argName, argPN);
				} 
				
				// list members
				else if (cmd.equals("ls")) {
					
					console.printComVer();
					cmd = console.getInput();
					String arg = cmd.trim();
					
					if (arg.equals("c")) {
						
						console.listMembersCompact(database.listMembers());
					} else if (arg.equals("v")) {
						
						console.listMembersVerbose(database.listMembers());
					}
				}

				// get member info
				else if (cmd.equals("cat")) {
					
					console.printShowMemberName();
					cmd = console.getInput();
					Member lookupMember = database.showMember(cmd);
					
					if (!lookupMember.equals("-1")) {
						
						// show member info
						console.printShowMemberInfo(lookupMember);
						
						String memberName = cmd;
						cmd = console.getInput();
						
						// change member info
						if (cmd.equals("edit")) {
							
							console.printEditName();
							cmd = console.getInput();
							if (cmd.equals("y")) {
								
								console.printNewName();
								cmd = console.getInput();
								
								database.editName(memberName, cmd);
								memberName = cmd;
							}
							
							console.printEditPersonal();
							cmd = console.getInput();
							if (cmd.equals("y")) {
								
								console.printEnterPersonal();
								cmd = console.getInput();
								
								database.editPN(memberName, cmd);
							}
							
							console.printEditComplete();
						} 
						
						// delete member 
						else if (cmd.equals("rmmem")) {
							
							console.printMemberWasRemoved(memberName);
							database.removeMember(memberName);
						}
						
						// register boat
						else if (cmd.equals("mkboat")) {
							
							console.printEnterNewBoatID();
							cmd = console.getInput();
							String i = cmd;
							
							console.printEnterNewBoatType();
							cmd = console.getInput();
							String t = cmd;
							
							console.printEnterNewBoatLength();
							cmd = console.getInput();
							int l = Integer.parseInt(cmd);
							
							database.addBoat(memberName, i, t, l);
						}
						
						// delete a boat
						else if (cmd.equals("rmboat")) {
							
							console.printEnterBoatIDForRemoval();
							cmd = console.getInput();
							
							console.printBoatRemoved();
							database.removeBoat(memberName, cmd);
						}
						
						// edit boat
						else if (cmd.equals("editboat")) {
							
							console.printEnterBoatID();
							cmd = console.getInput();
							
							String editBoat = cmd;
							
							console.printEnterEditBoatID();
							cmd = console.getInput();
							if (cmd.equals("y")) {
								
								console.printEnterInputBoatID();
								cmd = console.getInput();
								
								database.setBoatId(memberName, editBoat, cmd);
								editBoat = cmd;
							}
							
							console.printEnterEditBoatType();
							cmd = console.getInput();
							if (cmd.equals("y")) {
								
								console.printEnterInputBoatType();
								cmd = console.getInput();
								
								database.setBoatType(memberName, editBoat, cmd);
							}
							
							console.printEnterEditBoatLength();
							cmd = console.getInput();
							if (cmd.equals("y")) {
								
								console.printEnterInputBoatLength();
								cmd = console.getInput();
								
								database.setBoatLength(memberName, editBoat, Integer.parseInt(cmd));
							}
							
							console.printBoatEditComplete();
						}
						
						else if (cmd.equals("x")) {
							
							console.printMoveOut(memberName);
							//continue;
						}
					} else {
						
						console.printMemberNotFound(cmd);
					}
				}
				
				else if (cmd.equals("quit")) {
					
				} else {
					
					console.printNonValidCommand();
				}
			}
		}
		
		saveList(database.listMembers());
		console.EndConsole();
	}
}
