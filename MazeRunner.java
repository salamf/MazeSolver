////Salam Fazil
//V00935894

public class MazeRunner {
	Maze mazeToSolve;
	A4Stack<MazeLocation> path;
	
	public MazeRunner(Maze aMaze) {
		mazeToSolve = aMaze;
		int size = mazeToSolve.getSize();
		path = new A4Stack<MazeLocation>(size);
	}
	
	/*
	 * Purpose: Determines whether there is a path from start to finish in this maze
	 * Parameters: MazeLocation start - starting coordinates of this maze
	 *			   MazeLocation finish - finish coordinates of this maze
	 * Returns: true if there is a path from start to finish
	 */
	public boolean solve(MazeLocation start, MazeLocation finish) {
		System.out.println("Searching maze from start: "+start+" to finish: "+finish);
		path.push(start);
		return findPath(start, finish);	
	}
	
	/*
	 * Purpose: Recursively determines if there is a path from cur to finish
	 * Parameters: MazeLocation cur - current cordinates in this maze
	 *			   MazeLocation finish - goal coordinates of this maze
	 * Returns: true if there is a path from cur to finish
	 *
	 * NOTE: This method updates the Maze's mazeData array when locations
	 *       are visited to an 'o', and further updates locations to an 'x'
	 *       if it is determined they lead to dead ends. If the finish 
	 *       location is found, the Stack named path should contain all of 
	 *       loations visited from the start location to the finish. 
	 */
	private boolean findPath(MazeLocation cur, MazeLocation finish) {
		int row = cur.row;
		int col = cur.col;

		mazeToSolve.setChar(row, col, 'o');

		System.out.println("\n" + mazeToSolve.toString());

		if (finish.row == cur.row && finish.col == cur.col) {
			return true;
		}

		if (cur.row + 1 < mazeToSolve.getRows() && mazeToSolve.getChar(row + 1, col) == ' ') {
			MazeLocation currentLocation = new MazeLocation(cur.row + 1, cur.col);

			System.out.println("Found a path DOWN, pushing position " + cur + " onto stack");
			path.push(currentLocation);
			return findPath(currentLocation, finish);

		} else if (cur.col + 1 < mazeToSolve.getCols() && mazeToSolve.getChar(row, col + 1) == ' ') {
			MazeLocation currentLocation = new MazeLocation(cur.row, cur.col + 1);

			System.out.println("Found a path RIGHT, pushing position " + cur + " onto stack");
			path.push(currentLocation);
			return findPath(currentLocation, finish);

		} else if (cur.col - 1 >= 0 && mazeToSolve.getChar(row, col - 1) == ' ') {
			MazeLocation currentLocation = new MazeLocation(cur.row, cur.col - 1);

			System.out.println("Found a path LEFT, pushing position " + cur + " onto stack");
			path.push(currentLocation);
			return findPath(currentLocation, finish);

		} else if (row - 1 >= 0 && mazeToSolve.getChar(row - 1, col) == ' ') {
			MazeLocation currentLocation = new MazeLocation(cur.row - 1, cur.col);

			System.out.println("Found a path UP, pushing position " + cur + " onto stack");
			path.push(currentLocation);
			return findPath(currentLocation, finish);

		} else if (row - 1 >= 0 && mazeToSolve.getChar(row - 1, col) == 'o') {
			mazeToSolve.setChar(row, col, 'x');

			System.out.println("No path found, need to backtrack. Popping: " + path.top());
			path.pop();
			cur.row--;
			MazeLocation currentLocation = new MazeLocation(cur.row, cur.col);

			return findPath(currentLocation, finish);

		} else if (col - 1 >= 0 && mazeToSolve.getChar(row, col - 1) == 'o') {
			mazeToSolve.setChar(row, col, 'x');

			System.out.println("No path found, need to backtrack. Popping: " + path.top());
			path.pop();
			cur.col--;

			MazeLocation currentLocation = new MazeLocation(cur.row, cur.col);
			return findPath(currentLocation, finish);

		} else if (col + 1 < mazeToSolve.getCols() && mazeToSolve.getChar(row, col + 1) == 'o') {
			mazeToSolve.setChar(row, col, 'x');

			System.out.println("No path found, need to backtrack. Popping: " + path.top());
			path.pop();
			cur.col++;

			MazeLocation currentLocation = new MazeLocation(cur.row, cur.col);
			return findPath(currentLocation, finish);

		} else if (row + 1 < mazeToSolve.getRows() && mazeToSolve.getChar(row + 1, col) == 'o') {
			mazeToSolve.setChar(row, col, 'x');

			System.out.println("No path found, need to backtrack. Popping: " + path.top());
			path.pop();
			cur.row++;

			MazeLocation currentLocation = new MazeLocation(cur.row, cur.col);
			return findPath(currentLocation, finish);

		} else {
			System.out.println("No Solution exists");
			return false;
		}
	}
	

	/*
	 * Purpose: Creates a string of maze locations found in the stack 
	 * Parameters: None
	 * Returns: A String representation of maze locations
	 */
	public String getPathToSolution() {
		String details = "";
		while(!path.isEmpty()) {
			details = path.pop() + details;
		}	
		return details;
	}
}