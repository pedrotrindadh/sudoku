package application;

import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;
import static util.BoardTemplate.BOARD_TEMPLATE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import model.entities.Board;
import model.entities.Space;

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

	private static void startGame(Map<String, String> positions) {
		if (nonNull(board)) {
			System.out.println("The game has already started");
			return;
		}
		
		List<List<Space>> spaces = new ArrayList<>();
		for (int i=0; i<BOARD_LIMIT; i++) {
			spaces.add(new ArrayList<>());
			for (int j=0; j<BOARD_LIMIT; j++) {
				var positionConfig = positions.get(String.format("%s","%s",i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(1).add(currentSpace);
			}
		}
		
		board = new Board(spaces);
		System.out.println("The game is ready to begin");
	}

	private static void inputNumber() {
		if (isNull(board)) {
			System.out.println("The game has not yet started");
			return;
		}
		
		System.out.println("Specify the column where the number will be inserted:");
		var col = runUntilGetValidNumber(0,8);
		System.out.println("Specify the row where the number will be inserted:");
		var row = runUntilGetValidNumber(0,8);
		System.out.printf("Enter the number that will go in position [%s,%s]\n", col, row);
		var value = runUntilGetValidNumber(1, 9);
		if (!board.changeValue(col, row, value)) {
			System.out.printf("The position [%s,%s] has a fixed value\n", col, row);
		}
		
		
	}

	private static void removeNumber() {
		if (isNull(board)) {
			System.out.println("The game has not yet started");
			return;
		}
		
		System.out.println("Specify the column where the number will be inserted:");
		var col = runUntilGetValidNumber(0,8);
		System.out.println("Specify the row where the number will be inserted:");
		var row = runUntilGetValidNumber(0,8);
		System.out.printf("Enter the number that will go in position [%s,%s]\n", col, row);
		if (!board.clearValue(col, row)){
            System.out.printf("The position [%s,%s] has a fixed value\n", col, row);
        }
	}

	private static void showCurrentGame() {
		if (isNull(board)) {
			System.out.println("The game has not yet started");
			return;
		}
		
		var args = new Object[81];
		var argPos = 0;
		for (int i=0 ; i<BOARD_LIMIT; i++) {
			for (var col: board.getSpaces()) {
				args[argPos ++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
			}
			System.out.println("Your current game:");
			System.out.printf((BOARD_TEMPLATE) + "\n", args);
		}	
	}

	private static void showGameStatus() {
		if (isNull(board)) {
			System.out.println("The game has not yet started");
			return;
		}
		
		System.out.println("Your current game status:  " + board.getStatus().getLabel());
		if(board.hasErrors()) {
			System.out.println("The game contains errors");
		}
		else {
			System.out.println("The game contains no errors.");
		}
	}

	private static void clearGame() {
		if (isNull(board)) {
			System.out.println("The game has not yet started");
			return;
		}
		
		System.out.println("Are you sure want to clear your game and lose all progress?");
		var confirm = sc.next();
		while (!confirm.equalsIgnoreCase("yes") || !confirm.equalsIgnoreCase("no")) {
			System.out.println("Please indicate 'yes' or 'no'");
			confirm = sc.next();
			
			if (confirm.equalsIgnoreCase("yes")) {
				board.reset();
			}
		}
	}

	private static Object finishGame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static int runUntilGetValidNumber(final int min, final int max) {
		var current = sc.nextInt();
		if (current < min || current > max) {
			System.out.printf("Enter a number between %s and %s\n", min, max);
			current = sc.nextInt();
		}
		return current;
	}
}
