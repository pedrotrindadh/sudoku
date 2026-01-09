package application;

import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

import model.entities.Board;

public class Program {
	
	private final static Scanner sc = new Scanner(System.in);
	
	private static Board board;
	
	private final static int BOARD_LIMIT = 9;
	
	public static void main(String[] args) {
		
		final var positions = Stream.of(args)
				.collect(toMap(
						k -> k.split(";")[0],
						v -> v.split(";")[1]));
		var option = -1;
		
		while (true) {
			System.out.println("Select one of the following options:");
            System.out.println("1 - Start the game");
            System.out.println("2 - Enter a new number");
            System.out.println("3 - Remove a number");
            System.out.println("4 - View current game");
            System.out.println("5 - Check game status");
            System.out.println("6 - Clear game");
            System.out.println("7 - Finish the game");
            System.out.println("8 - Exit");

            option = sc.nextInt();
            
            switch (option) {
            	case 1:
            		startGame(positions); 
            		break;
            	case 2: 
            		inputNumber();
            		break;
            	case 3: 
            		removeNumber();
            		break;
            	case 4: 
            		showCurrentGame();
            		break;
            	case 5: 
            		showGameStatus();
            		break;
            	case 6: 
            		clearGame();
            		break;
            	case 7: 
            		finishGame();
            		break;
            	case 8: 
            		System.exit(0);
            		break;
            	default: 
            		System.out.println("Option invalid");
            		break;
            }
		}
	}

	private static Object startGame(Map<String, String> positions) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object inputNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object removeNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object showCurrentGame() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object showGameStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object clearGame() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object finishGame() {
		// TODO Auto-generated method stub
		return null;
	}
}
