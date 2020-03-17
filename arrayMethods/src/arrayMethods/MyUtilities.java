package arrayMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyUtilities {
	Scanner sc = new Scanner(System.in);
	
	public int[] inputValues() {
		System.out.print("Input length of array >> ");
		int length = sc.nextInt();
		int myArray[] = new int[length];
		
		for(int i = 0; i < length; i++) {
			System.out.print("Enter value number " + (i+1) + " >>" );
			int value = sc.nextInt();
			myArray[i] = value;
		}
		
		return myArray;
	}
	
	// Number 1
	public void maxSplit() {
		System.out.println("Input length of array >> ");
		int length = sc.nextInt();
		int temp[] = new int[length];
		int secondMax = 0;
		int maxCounter = 0;
		int max = 0;
		
		for(int i = 0; i < length; i++) {
			System.out.print("Enter value number " + (i+1) + " >>" );
			int value = sc.nextInt();
			temp[i] = value;
		}	
		
		System.out.println("Input Array >> " + Arrays.toString(temp));
		
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] > max) max = temp[i];
			
		}
		
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] > secondMax & temp[i] != max) secondMax = temp[i];
		}
		
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] == max) maxCounter ++;
		}
		
		ArrayList<Integer> myArray = new ArrayList<Integer>(length + maxCounter);
		
		for(int i = 0;i <temp.length;i++)
		{
			if(temp[i] != max) myArray.add(temp[i]);
			
			else
			{
				myArray.add(secondMax);
				myArray.add(max-secondMax);
			}
			
		}
		System.out.println("Output Array >> " + myArray.toString());
	}
	
	// Number 2
	public void circularRight() {
		int myArray[] = inputValues();
		System.out.println("Input Array >> " + Arrays.toString(myArray));
		System.out.print("ROT >> ");
		int rot = sc.nextInt();
		int temp[] = new int[myArray.length];
		
		for(int i =0; i < myArray.length; i++) {
			temp[i] = myArray[i];
		}
		
		for(int i = 0; i < myArray.length; i++) {
			int amount = i+myArray.length-(rot%myArray.length);
			if(amount < myArray.length) myArray[i] = temp[amount];
			else myArray[i] = temp[amount-(myArray.length-1)-1];
		}
		System.out.println("Output Array >> " + Arrays.toString(myArray));
	}
	
	// Number 3
	public boolean symmetricCheck(int matrix[][]) {
		int temp[][] = new int[matrix.length][matrix[1].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[1].length; j++) {
				temp[j][i] = matrix[i][j];
			}
		}
		if(Arrays.deepEquals(matrix, temp)) return true;
		else return false;
	}
	
	// Number 4 (This solution is heavily influenced from online tutorials)
	/*
	 Magic square rules: 1) The very first number will always be inserted in the top row middle column
	 
	  					 2) The next number will be inserted in the row and column below the previous  
	  					 (if previous number is inserted on row 2 column 1, next number will be inserted on row 1 column 0)
	  					 
	  					 3) If the magic square's size is a factor of the current number, ignore rule number 2 and add 1 to the row instead
	  					 (if the size of the magic square is 3x3 and the number 3 is located on row 2 column 1, number 4 will be located on row 3 column 1)
	  					 
	  					 4) If at any point either row or column is equal to -1, add the size of the magic square to the value
	  					 (if the number 5 is located on row 0 column 1, the number 6 will be located on row 2 column 0)
	 */
	public void oddMagic(int n) {
		int square[][] = new int[n][n];	// The 2D array
		int r = 0;	// Represents row
		int c = n/2;	// Represents column
		
		for(int i = 1; i <= n*n; i++) {	// The variable i represents the actual numbers inside the magic square
			square[r][c] = i;	// Inserts the value to a certain position inside the magic square. This applies rule number 1 when i is equal to 1
			if(i % n == 0) r++;	// Checks whether rule 3 is applicable to current number
			else {
				r--;	// Applies rule number 2 if rule 3 is not applicable to current number
				c--;
			}
			if(r < 0) r += n;	// Checks whether rule 4 is applicable
			if(c < 0) c += n;
		}
		
		for(r = 0; r < n; r++) {	// Prints the magic square
			for(c = 0; c < n; c++) {
				System.out.print(square[r][c] + " | ");
			}
			System.out.println();
		}
	}
	
	// For doubly even I tried to do it myself thus there might be some inefficient segments
	public void doublyEvenMagic(int n) {
		int square[][] = new int[n][n];
		int half = n/2;
		int halfhalf = half/2;
		int x = 1;
		
		ArrayList<Integer> corners = new ArrayList<Integer>();
		ArrayList<Integer> center = new ArrayList<Integer>();
		for(int i = 0; i < halfhalf; i ++) {
			corners.add(i);
			corners.add(n-1-i);
		}
		for(int i = 0; i < n; i++) {
			if(corners.contains(i) == false) center.add(i);
		}
		
		for(int i = 0; i < n; i++) {		
			for(int j = 0; j < n; j++) {	
				int row = i;
				int column = j;
				if(corners.contains(row) && corners.contains(column)) {
					square[row][column] = x;
					x++;
				} 
				else if(center.contains(row) && center.contains(column)) {
					square[row][column] = x;
					x++;
				}
				else x++;
				
			}
		}
		
		x = 1;
		
		for(int i = n-1; i >= 0; i--) {
			for(int j = n-1; j >= 0; j--) {
				int row = i;
				int column = j;
				if(square[row][column] == 0){
					square[row][column] = x;
					x++;
				} else x++;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(square[i][j] + " | ");
			}
			System.out.println();
		}
		
	}
}
