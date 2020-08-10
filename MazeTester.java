import  java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;

public class MazeTester {
	
	public static void main(String[] args) {
		testMazeRunner(args);
	}
	
	public static void testMazeRunner(String[] args) {
		if (args.length < 1) {
            System.err.println("usage: java MazeSolver <mazefile>");
            System.exit(1);
        }
		
		Maze maze = initialize(args[0]);
		MazeRunner mr = new MazeRunner(maze);
		if (mr.solve(maze.getStart(), maze.getFinish())) {
			System.out.println("Path from start to finish:");
			System.out.println(mr.getPathToSolution());
		} else {
			System.out.println("No solution found");
		}
	}
	
	/*
	 * Purpose: Creates a maze by reading the contents of an input file
	 * Parameters: String - name of the input file containing maze data
	 * Returns: Maze - the maze created from input file data
	 */
	public static Maze initialize(String data) {
        try {
            Scanner infileScanner = new Scanner(new File(data));
			
			int rows = infileScanner.nextInt();
			int columns = infileScanner.nextInt();
			int startRow = infileScanner.nextInt();
			int startColumn = infileScanner.nextInt();
			int finishRow = infileScanner.nextInt();
			int finishColumn = infileScanner.nextInt();
			infileScanner.nextLine();
		
			char[][] mazeData = new char[rows][columns];

			for (int row = 0; row < rows; row++) {
				String line = infileScanner.nextLine();
				for (int col = 0; col < columns; col++) {
					mazeData[row][col] = line.charAt(col);
				}
			}
			return new Maze(mazeData, new MazeLocation(startRow, startColumn), new MazeLocation(finishRow, finishColumn));
		} catch (FileNotFoundException ex) {
			System.out.println("Error scanning file "+data);
			System.exit(2);
		} catch(NoSuchElementException ex) {
			System.out.println("Maze data file is not formatted correctly, should be:");
			System.out.println("<num rows> <num columns>");
			System.out.println("<start row> <start column>");
			System.out.println("<finish row> <finish column>");
			System.out.println("<Maze data>");
			System.out.println("Example:");
			System.out.println("7 8");
			System.out.println("0 1");
			System.out.println("6 6");
			System.out.println("* ******");
			System.out.println("*    * *");
			System.out.println("**** * *");
			System.out.println("*      *");
			System.out.println("* ******");
			System.out.println("*      *");
			System.out.println("****** *");
			System.exit(3);
		}
		return null;
	}
	
}