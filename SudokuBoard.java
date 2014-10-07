package aima.gui.applications.search.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class SudokuBoard {
	
	public static Action ADD_ONE = new DynamicAction("one");

	public static Action ADD_TWO = new DynamicAction("two");

	public static Action ADD_THREE = new DynamicAction("three");

	public static Action ADD_FOUR = new DynamicAction("four");
	
	public static Action ADD_FIVE = new DynamicAction("five");

	public static Action ADD_SIX = new DynamicAction("six");

	public static Action ADD_SEVEN = new DynamicAction("seven");

	public static Action ADD_EIGHT = new DynamicAction("eight");
	
	public static Action ADD_NINE = new DynamicAction("nine");
	
	
	HashMap<Integer, int[]> columns, rows, square;
	//State maps the box number to a list of possible values the box can take
	HashMap<Integer, ArrayList<Integer>> state = new HashMap<Integer, ArrayList<Integer>>(81);
	
	HashSet<Integer> values = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		
	public SudokuBoard(String initConfig){
		
		columns = setColumns();
		rows = setRows();		
		square = setSquare();
		state = parseConfig(initConfig);
	}
	
	
	private HashMap<Integer, int[]> setColumns(){
		
		HashMap<Integer, int[]> cols = new HashMap<Integer, int[]>(81);
		
		int[] column = new int[9];
		
		for (int i = 0; i<81; i++){
			
			int elem = i % 9;
			
			for(int j = 0; j<column.length; j++){
				column[j] = elem;
				elem += 9;
			}
			
			cols.put(i, column.clone());
		}
		
		return cols;
	}

	private HashMap<Integer, int[]> setRows(){
		
		HashMap<Integer, int[]> rows = new HashMap<Integer, int[]>(81);
		
		int[] r = new int[9];
		
		for (int i = 0; i<9; i++){
			
			for (int counter = 9*i, j =0; counter <= (9*i + 8); counter++, j++){
				
				//Boxes of i th row
				r[j] = counter;
			}
			
			for (int j = 9*i; j <= (9*i+8); j++){
				
				rows.put(j, r.clone());
			}		
			
		}
		
		return rows;
	}

	private HashMap<Integer, int[]> setSquare(){
		
		HashMap<Integer, int[]> square = new HashMap<Integer, int[]>(81);
		
		int[][] regions = {{0,1,2,9,10,11,18,19,20},{3,4,5,12,13,14,21,22,23},{6,7,8,15,16,17,24,25,26},
				{27,28,29,36,37,38,45,46,47},{30,31,32,39,40,41,48,49,50},{33,34,35,42,43,44,51,52,53},
				{54,55,56,63,64,65,72,73,74},{57,58,59,66,67,68,75,76,77},{60,61,62,69,70,71,78,79,80}};
		
		for (int[] reg:regions){
			for(int elem:reg){
				square.put(elem, reg.clone());
			}
		}			
		
		return square;		
	}
	
	private HashMap<Integer, ArrayList<Integer>> parseConfig(String config){
		
		HashMap<Integer, ArrayList<Integer>> board = new HashMap<Integer, ArrayList<Integer>>(81);		
		char[] configChars = config.toCharArray();
		
		for (int i = 0; i<configChars.length; i++){
			int value = (int)configChars[i];
			
			if (value != 0){
				ArrayList<Integer> arr = new ArrayList<Integer>();
				arr.add(value);
				board.put(i, arr);
			}
			else if(value == 0)
				board.put(i, new ArrayList<Integer>());						
		}
		return board;
	}
}
