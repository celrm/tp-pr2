package p1;

import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner in;
	private boolean sigoAqui; // por favor, pon un nombre más decente. he recuperado este bool para el exit
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.in = sc;
		this.sigoAqui = true;
	}
	
	public void run() {
		boolean hasAnythingChanged = true;
		while(!this.game.playerWins() && !this.game.zombiesWin() && this.sigoAqui){
			
			if (hasAnythingChanged) {
				this.game.nextCycle();
				this.game.update();
				this.game.draw();
			}
			hasAnythingChanged = this.option();
			if (hasAnythingChanged) {//problema: después del reset se ejecuta el computer y entonces puede aparecer un zombie salvaje en el ciclo 0, lo cual está feo
				this.game.computer();
			}
		}

	    System.out.println("Game over");
		if (this.game.playerWins()) {
			this.game.draw();
		    System.out.println("Player wins!");
		}
		else if (this.game.zombiesWin()) {
			this.game.draw();
		    System.out.println("Zombies win :(");
		}
		else {
		    System.out.println("Chao");
		}
	}
	
	//sí, lo he buscado. si no, la excepción de parseInt es imposible de evitar.
	public static boolean isParsable(String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
	
	private boolean option(){
		boolean sol = false;
		System.out.print("Command > ");
		String[]words = this.in.nextLine().toLowerCase().trim().split(" ");

		switch(words[0]) {
		case "add":
		case "a": {
			if (words.length == 4 && isParsable(words[2]) && isParsable(words[3])) {
				int x = Integer.parseInt(words[2]);
				int y = Integer.parseInt(words[3]);
				sol = this.game.addPlant(words[1], x, y);
			}
			else System.out.println("Wrong parameters.");
		} break;
		case "reset":
		case "r": {
			this.game = new Game(this.game.getRand(), this.game.getLevel());
			sol = true;
		} break;
		case "list":
		case "l": this.list(); break;
		case "exit":
		case "e": this.sigoAqui = false; break;
		case "help":
		case "h": this.help(); break;
		case "": sol = true; break;
		default: System.out.println("Wrong command."); break;
		}
		return sol;
	}
	
	private void list() {
		System.out.println("[S]unflower: Cost: " + Integer.toString(Sunflower.COSTE) + " suncoins Harm: " + Integer.toString(Sunflower.HARM));
		System.out.println("[P]eashooter: Cost: " + Integer.toString(Peashooter.COSTE) + " suncoins Harm: " + Integer.toString(Peashooter.HARM));
	}
	 
	private void help() {
		System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
		System.out.println("List: Prints the list of available plants.");
		System.out.println("Reset: Starts a new game.");
		System.out.println("Help: Prints this help message.");
		System.out.println("Exit: Terminates the program.");
		System.out.println("[none]: Skips cycle.");
	}

}