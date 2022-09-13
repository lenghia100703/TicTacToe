package gameXO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
	static boolean run = true;
	static boolean whoseTurn = true;
	static String user = "X player";
	
	static ArrayList<String> xPos = new ArrayList<String>();
	static ArrayList<String> oPos = new ArrayList<String>();
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] gameBoard = {{"[ ]", "[ ]", "[ ]"},
				{"[ ]", "[ ]", "[ ]"},
				{"[ ]", "[ ]", "[ ]"}};
		runGame(gameBoard);
	}
	
	public static void runGame(String[][] gameBoard) {
		try (Scanner scan = new Scanner(System.in)) {
			printGameBoard(gameBoard);
			do {
				if (whoseTurn == true) {
					user = "X player";
					System.out.println("X turn:");
				}
				else {
					user = "O player";
					System.out.println("O turn:");
				}
				int x = scan.nextInt();
				int y = scan.nextInt();
				
				
				if (x <= 0 || x >= 4 || y <= 0 || y >= 4) {
					System.out.println("Please again, " + user);
					runGame(gameBoard);
				}
				else {
					boolean check = checkSame(gameBoard, whoseTurn, x, y);
					if (!check) {
						System.out.println("Please again, " + user);
						runGame(gameBoard);
					}
					else {
						placePiece(gameBoard, x, y, whoseTurn);
					}
				}
				checkWin();
				printGameBoard(gameBoard);
				whoseTurn = !whoseTurn;
			}
			while (run);
		}
	}
	
	public static void printGameBoard(String[][] gameBoard) {
		for (String[] row : gameBoard) {
			for (String c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(String[][] gameBoard, int x, int y, boolean whoseTurn) {
		String pos =  x + "." + y;
		if (whoseTurn == true) {
			xPos.add(pos);
			gameBoard[x - 1][y - 1] = "[X]";
		}
		else {
			oPos.add(pos);
			gameBoard[x - 1][y - 1] = "[O]";
		}
		
	}
	
	public static void checkWin() {
		List<String> topRow = Arrays.asList("1.1", "1.2", "1.3");
		List<String> midRow = Arrays.asList("2.1", "2.2", "2.3");
		List<String> botRow = Arrays.asList("3.1", "3.2", "3.3");
		List<String> leftCol = Arrays.asList("1.1", "2.1", "3.1");
		List<String> midCol = Arrays.asList("1.2", "2.2", "3.2");
		List<String> rightCol = Arrays.asList("1.3", "2.3", "3.3");
		List<String> cross1 = Arrays.asList("1.1", "2.2", "3.3");
		List<String> cross2 = Arrays.asList("1.3", "2.2", "3.1");
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for (List l : winning) {
			if (xPos.containsAll(l)) {
				System.out.println("Winner: X player");
				run = false;
				break;
			}
			else if (oPos.contains(l)) {
				System.out.println("Winner: O player");
				run = false;
				break;
			}
			else if (xPos.size() + oPos.size() == 9) {
				System.out.println("Draw Match");
				run = false;
				break;
			}
		}
	}
	
	public static boolean checkSame(String[][] gameBoard, boolean whoseTurn, int x, int y) {
		if (whoseTurn == true) {
			if (gameBoard[x - 1][y - 1].equals("[X]") || gameBoard[x - 1][y - 1].equals("[O]")) {
				return false;
			}
		}
		else {
			if (gameBoard[x - 1][y - 1].equals("[X]") || gameBoard[x - 1][y - 1].equals("[O]")) {
				return false;
			}
		}
		return true;
	}
	
}
