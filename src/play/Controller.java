package play;

import java.util.Scanner;

import printers.GamePrinter;
import printers.ReleasePrinter;
import logic.Game;
import commands.Command;
import commands.CommandParser;

public class Controller {
	private Game game;
	private Scanner scanner;
	private boolean exit;
	private GamePrinter gamePrinter;
	private final String unknownCommandMsg = "Unknown command";
	private final String prompt = "Command > ";
;	private boolean noPrint;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.scanner = sc;
		this.exit = false;
		this.noPrint = false;
		this.gamePrinter = new ReleasePrinter(j, Game.DIMX, Game.DIMY);
	} 
	
	public void run() {
		while (!game.isFinished() && !exit) {
			printGame();
			noPrint = true;
			
			System.out.print(prompt);
			String[] words = scanner.nextLine().toLowerCase().trim().split("\\s+");
			Command command = CommandParser.parseCommand(words, this);
			
			if (command != null) {
				command.execute(game, this);
			}
			
			else {
				System.err.println(unknownCommandMsg);
				setNoPrintGameState();
			}
		}
	}
	
	public void setNoPrintGameState() {
		this.noPrint = false;
	}
	
	public void setPrinter(GamePrinter print) {
		this.gamePrinter = print;
	}
	
	public void printGame() {
		if(noPrint) {
		System.out.println(game.cabezera());
		System.out.println(gamePrinter.printGame(game));
		}
	}
	
	public void exit() {
		this.exit = true;
	}
}