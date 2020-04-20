package Utilities;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {
	public static Integer BOARD_WIDE_INTEGER = 5; 
	public static Integer BOARD_SIZE = BOARD_WIDE_INTEGER*BOARD_WIDE_INTEGER; 
	public static Integer TREE_INTEGER = BOARD_WIDE_INTEGER-1; 
	
	public static ArrayList<String> initialBoard(){ 
		Integer BOARD_SIZE = BOARD_WIDE_INTEGER*BOARD_WIDE_INTEGER; 
		Integer TREE_INTEGER = BOARD_WIDE_INTEGER-1; 
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<Integer> integers = new ArrayList<Integer>();
		while(integers.size()<TREE_INTEGER) {
			Random random = new Random();
			int position = random.nextInt(BOARD_SIZE-2)+1;
			
			if(!integers.contains(position)) {
				integers.add(position);
			}
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			if(integers.contains(i)) {
				arr.add("T");
			}else {
				arr.add("R");
			}
		}
		arr.set(BOARD_SIZE-1, "G");
		return arr;
	}
	public static void printBoard(ArrayList<String> board, Integer carLocation){ 
		for (int i = 0; i < board.size(); i++) {
			if(i%BOARD_WIDE_INTEGER==0) {
				System.out.println("");
			}
			if(i==carLocation) {
				System.out.print("C ");
			}else {
				System.out.print(board.get(i) + " ");
			}
			
		}
		System.out.println("        C for Car; T For Tree; R For Road; G for Goal");
		
	}
	public static void printUtilities(){ 
		for (int i = 0; i < BOARD_SIZE; i++) {
			if(i%BOARD_WIDE_INTEGER==0) {
				System.out.println("");
			}
			System.out.printf("%3s",i);
			
		}
		
	}

}
