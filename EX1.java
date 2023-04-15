package fatma1;

import java.util.Scanner;

public class fatma1 {
	public static void main(String[] args) { // reset 2 boards 
		char[][] board = new char[5][4];
		char[][] memory = new char[5][4];
		startGame(board, memory);
	}

	public static void newBorad(char[][] board, char[][] memory) { 
		startboard(board);
		startboard(memory);
		startmemory(memory);
		printboard(board);
		
	}

	public static void startGame(char[][] board, char[][] memory) { // the main game function
		System.out.print("Welcome to Fatma Memory Game. ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine(); // getting the coordinates from the user
		newBorad(board, memory);
		boolean flag1 = true;
		while (notDone(board, memory, flag1)) { // while their are more than 2 # at the board
			int ij = flipcard1(board, memory, flag1); // flip the first card	
			int mk= flipcard2(board, memory, flag1);// flip the second card
			if (match(board,ij, mk )) {
				change(board, ij, mk); // change to *
			}
			else 
				change2(board, ij, mk); // change back to #
		}

	}

	public static boolean notDone(char[][] board1, char[][] memory1, boolean flag) {// checking if their is more than two #
		int count = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if ((board1[i][j] == '*')) {
					count++;
					if (count == 18) { // if its the last two cards 
						int k= fliplast(board1, memory1 ); 
						int x = k/100;
						int y = k%100;
						if (match(memory1, x, y)) {// checking if their match 
							printwin();
							finished(board1, memory1); 
						} else {
							printlose();
							finished(board1, memory1);

						}
						return false;
					}
				}
				
			}
			
		}
		return true;
	}

	public static void finished(char[][] board1, char[][] memory1) { //checking if the user want to play again
		System.out.print("Would you like to start a new game? ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		boolean done = true;
		if (str.charAt(0) == 'y' || str.charAt(0) == 'Y') {
			startGame(board1, memory1);
		}
		if (str.charAt(0) == 'n' || str.charAt(0) == 'N') {
			done = false;
			while (done == false) {
				break;
			}

		} else {
			printeror(); 
			finished(board1, memory1);
		}
		
	}

	public static void printwin() {
		System.out.println("\n"+"Game is over! All cards are matched");

	}

	public static void printlose() {
		System.out.print("\n"+"Game is over! No more possible matches.");

	}

	public static void startboard(char arr[][]) { // starting the # matrix
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = '#';
			}
		}
	}

	public static void startmemory(char arr[][]) { // starting the number matrix
		for (char i = '0'; i <= '9'; i++) { //  putting every number between 0-9 twice at the matrix 
			putnumber(i, arr); 
		}
		for (char i = '0'; i <= '9'; i++) {
			putnumber(i, arr);
		}

	}

	public static void putnumber(char i, char[][] arr) { //putting the numbers at random coordinate 
		int x = (int) (Math.random() * 5);
		int y = (int) (Math.random() * 4);
		if (nottaken(arr, x, y)) {   
			arr[x][y] = i;
		} else
			putnumber(i, arr);
	}

	public static boolean nottaken(char[][] arr, int x, int y) { // checking if the coordinate are already taken 
		if (arr[x][y] == '#') {
			return true;
		}
		return false;
	}

	public static void print1() { 
		System.out.println("\n"+"Please choose first card to flip");
		

	}

	public static int flipcard1(char[][] board1, char[][] memory1, boolean flag) { 
		if (flag) {
			print1();
		}
		Scanner sc = new Scanner(System.in);// getting the coordinate from the user
		String str = sc.nextLine();
		int i = (int) str.charAt(0) - 48; // remove the char into int 
		int j = (int) str.charAt(1) - 48;
		if (check(board1, i, j, flag)) {  // check if the place is legal
			board1[i][j] = memory1[i][j]; // remove the number from the numbers matrix to the board
			for (int k = 0; k < 5; k++) { // printing the new board
				if (k != 0) {
				System.out.println();
				}
				for (int l = 0; l < 4; l++) {
					System.out.print(board1[k][l] + " ");

				}

			}
			int ij = i*10 +j;
			return ij;
		} else {
			flag = false; // if the coordinate are wrong, try again
			 return flipcard1(board1, memory1, flag);
		}
	}

	public static boolean check(char[][] board2, int k, int g, boolean flag) {// help function - checking if the coordinate legal
		if ((k > 4) || (g > 3)) {
			printeror();
			flag = false;
			return false;
		}
		if ((board2[k][g] != '#')) {
			printeror();
			flag = false;
			return false;

		} else {
			flag = true;
			return true;

		}
	}

	public static void printeror() {
		System.out.println("Sorry, wrong input. Please try again.");
		

	}

	public static int flipcard2(char[][] board1, char[][] memory1, boolean flag) { // fliping the second card
		if (flag) {
			print2();
		}
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int m = (int) str.charAt(0) - 48;
		int g = (int) str.charAt(1) - 48;
		if (check(board1, m, g, flag)) {
			board1[m][g] = memory1[m][g];
			for (int k = 0; k < 5; k++) {
				if (k != 0) {
					System.out.println();
					}
				for (int l = 0; l < 4; l++) {
					System.out.print(board1[k][l] + " ");

				}

			}
			int mg = m*10 +g;
			return mg;
		} else {
			flag = false;
			return flipcard2(board1, memory1, flag);
		}
	}

	public static boolean match(char[][] board1, int x , int y) { // checking if the cards are match 
		char a = board1 [x/10][x%10];
		char b = board1 [y/10][y%10];
			
		if (a == b || a == '0' || b == '0') {
			System.out.println("\n"+"Cards match!");
			return true;
		} else {
			System.out.println("\n"+"Cards do not match!");
			return false;
		}
	}

	public static void print2() {
		System.out.println("\n"+"Please choose second card to flip ");
		

	}

	public static void change(char[][] board1, int i , int j ) { // change into * if cards match
		 board1 [i/10][i%10]= '*';
			board1 [j/10][j%10]= '*';
		printboard(board1);
	}

	public static void change2(char[][] board1, int i , int j) {// change back to # if cards not match
		 board1 [i/10][i%10]= '#';
		board1 [j/10][j%10]= '#';
		printboard(board1);
	}

	public static void printboard(char[][] boardA) {
		for (int i = 0; i < 5; i++) {
			if (i != 0) {
				System.out.println();
				}
			for (int j = 0; j < 4; j++) {
				System.out.print(boardA[i][j] + " ");
			}

		}
	}
	
	
	
	public static int fliplast(char[][] board1, char[][] memory1) { // checking if the last two cards are match
		int ij = 0;
		int mk = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board1[i][j]== '#') {
					 ij= i*10 + j ;
				}
			}
		}
		for (int m = 0; m < 5 && m!= ij /10 ; m++) {
			for (int k = 0; k < 4 && k !=ij %10 ; k++) {
				if (board1[m][k]== '#') {
					mk= m*10 + k ;
				}
			}
		}
		int mikom = ij*100 +mk;
		return mikom;
	

	}

}