package YachtClub;

import java.io.IOException;

import YachtClub.controller.Secretary;
import YachtClub.model.Database;
import YachtClub.view.Console;

public class Program { 

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Database database = new Database();
		Console console = new Console();
		Secretary secretary = new Secretary();
		
		secretary.initRegistry(database, console);
	}
}