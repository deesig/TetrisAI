// Austin Trinh
// March 29, 2022
// ITCS
// Tetromino Object Class

// Link that helped me organize all the nightmarish rotation stuff:
// https://tetris.fandom.com/wiki/SRS

// Imports
import java.util.ArrayList;

public class Tetromino {

private int[][] grid;
private int r0, r1, r2, r3, c0, c1, c2, c3;
private int oldr0, oldr1, oldr2, oldr3, oldc0, oldc1, oldc2, oldc3;
private int block;
private boolean placeBlock = false;
private int nearBottom;
private int nearSide;
private int rPhase; // Rotation Phase
private Tetris tetris;
private boolean slamLock = false;
private ArrayList<int[]> moveList = new ArrayList<int[]>();
	
	// 2-Argument Constructor
	public Tetromino(Tetris tetris, int block){ // Needs the tetris grid that it goes into along with a corresponding int for the tetris block
	
		// Borrowing grid & tetris grid
		this.tetris = tetris;
		this.grid = tetris.getGrid();
		
		rPhase = 0;
		
		// Gathers the bottom of the grid as an int parameter
		nearBottom = tetris.getRows() -1;
		nearSide = tetris.getColumns() -1;
	
		// Block index:
		// 1 = T
		// 2 = Sq
		// 3 = I
		// 4 = L
		// 5 = Backwards L
		// 6 = Z
		// 7 = S
		
		// Borrowing block-int to decode into a block
		this.block = block;
		
		// T-Block
		if(block == 1) {
			r0 = 0; c0 = 4;
			r1 = 1; c1 = 3;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;

			// rPhase 0
			int[] move1 = {0,0,0};
			moveList.add(move1);
			int[] move2 = {0,0};   
			moveList.add(move2);
			int[] move3 = {0};   
			moveList.add(move3);
			int[] move4 = {};   
			moveList.add(move4);
			int[] move5 = {1};
			moveList.add(move5);
			int[] move6 = {1,1};
			moveList.add(move6);
			int[] move7 = {1,1,1};
			moveList.add(move7);
			int[] move8 = {1,1,1,1};
			moveList.add(move8);

			// rPhase 1
			int[] move9 = {3,0,0,0,0};
			moveList.add(move9);
			int[] move10 = {3,0,0,0};
			moveList.add(move10);
			int[] move11 = {3,0,0};
			moveList.add(move11);
			int[] move12 = {3,0};
			moveList.add(move12);
			int[] move13 = {3};
			moveList.add(move13);
			int[] move14 = {3,1};
			moveList.add(move14);
			int[] move15 = {3,1,1};
			moveList.add(move15);
			int[] move16 = {3,1,1,1};
			moveList.add(move16);
			int[] move17 = {3,1,1,1,1};
			moveList.add(move17);

			// rPhase 2
			int[] move18 = {3,3,0,0,0};
			moveList.add(move18);
			int[] move19 = {3,3,0,0};
			moveList.add(move19);
			int[] move20 = {3,3,0};
			moveList.add(move20);
			int[] move21 = {3,3};
			moveList.add(move21);
			int[] move22 = {3,3,1};
			moveList.add(move22);
			int[] move23 = {3,3,1,1};
			moveList.add(move23);
			int[] move24 = {3,3,1,1,1};
			moveList.add(move24);
			int[] move25 = {3,3,1,1,1,1};
			moveList.add(move25);

			// rPhase 3
			int[] move26 = {2,0,0,0};
			moveList.add(move26);
			int[] move27 = {2,0,0};
			moveList.add(move27);
			int[] move28 = {2,0};
			moveList.add(move28);
			int[] move29 = {2};
			moveList.add(move29);
			int[] move30 = {2,1};
			moveList.add(move30);
			int[] move31 = {2,1,1};
			moveList.add(move31);
			int[] move32 = {2,1,1,1};
			moveList.add(move32);
			int[] move33 = {2,1,1,1,1};
			moveList.add(move33);
			int[] move34 = {2,1,1,1,1,1};
			moveList.add(move34);
		}
		
		// Sq-Block
		else if(block == 2) {
			r0 = 0; c0 = 4;
			r1 = 0; c1 = 5;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;

			// rPhase 0
			int[] move1 = {0,0,0,0};
			moveList.add(move1);
			int[] move2 = {0,0,0};
			moveList.add(move2);
			int[] move3 = {0,0};
			moveList.add(move3);
			int[] move4 = {0};
			moveList.add(move4);
			int[] move5 = {};
			moveList.add(move5);
			int[] move6 = {1};
			moveList.add(move6);
			int[] move7 = {1,1};
			moveList.add(move7);
			int[] move8 = {1,1,1};
			moveList.add(move8);
			int[] move9 = {1,1,1,1};
			moveList.add(move9);
		}
		
		// I-Block
		else if(block == 3) {
			r0 = 1; c0 = 3;
			r1 = 1; c1 = 4;
			r2 = 1; c2 = 5;
			r3 = 1; c3 = 6;

			// rPhase 0
			int[] move1 = {0,0,0};
			moveList.add(move1);
			int[] move2 = {0,0};
			moveList.add(move2);
			int[] move3 = {0};
			moveList.add(move3);
			int[] move4 = {};
			moveList.add(move4);
			int[] move5 = {1};
			moveList.add(move5);
			int[] move6 = {1,1};
			moveList.add(move6);
			int[] move7 = {1,1,1};
			moveList.add(move7);

			// rPhase 1
			int[] move8 = {3,0,0,0,0,0};
			moveList.add(move8);
			int[] move9 = {3,0,0,0,0};
			moveList.add(move9);
			int[] move10 = {3,0,0,0};
			moveList.add(move10);
			int[] move11 = {3,0,0};
			moveList.add(move11);
			int[] move12 = {3,0};
			moveList.add(move12);
			int[] move13 = {3};
			moveList.add(move13);
			int[] move14 = {3,1};
			moveList.add(move14);
			int[] move15 = {3,1,1};
			moveList.add(move15);
			int[] move16 = {3,1,1,1};
			moveList.add(move16);
			int[] move17 = {3,1,1,1,1};
			moveList.add(move17);

			// rPhase 3
			int[] move18 = {2,0,0,0,0};
			moveList.add(move18);
			int[] move19 = {2,0,0,0};
			moveList.add(move19);
			int[] move20 = {2,0,0};
			moveList.add(move20);
			int[] move21 = {2,0};
			moveList.add(move21);
			int[] move22 = {2};
			moveList.add(move22);
			int[] move23 = {2,1};
			moveList.add(move23);
			int[] move24 = {2,1,1};
			moveList.add(move24);
			int[] move25 = {2,1,1,1};
			moveList.add(move25);
			int[] move26 = {2,1,1,1,1};
			moveList.add(move26);
			int[] move27 = {2,1,1,1,1,1};
			moveList.add(move27);
		}
		
		// L-Block
		else if(block == 4) {
			r0 = 0; c0 = 5;
			r1 = 1; c1 = 3;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;
			
			// rPhase 0
			int[] move1 = {0,0,0};
			moveList.add(move1);
			int[] move2 = {0,0};
			moveList.add(move2);
			int[] move3 = {0};
			moveList.add(move3);
			int[] move4 = {};
			moveList.add(move4);
			int[] move5 = {1};
			moveList.add(move5);
			int[] move6 = {1,1};
			moveList.add(move6);
			int[] move7 = {1,1,1};
			moveList.add(move7);
			int[] move8 = {1,1,1,1};
			moveList.add(move8);

			// rPhase 1
			int[] move9 = {3,0,0,0,0};
			moveList.add(move9);
			int[] move10 = {3,0,0,0};
			moveList.add(move10);
			int[] move11 = {3,0,0};
			moveList.add(move11);
			int[] move12 = {3,0};
			moveList.add(move12);
			int[] move13 = {3};
			moveList.add(move13);
			int[] move14 = {3,1};
			moveList.add(move14);
			int[] move15 = {3,1,1};
			moveList.add(move15);
			int[] move16 = {3,1,1,1};
			moveList.add(move16);
			int[] move17 = {3,1,1,1,1};
			moveList.add(move17);

			// rPhase 2
			int[] move18 = {3,3,0,0,0};
			moveList.add(move18);
			int[] move19 = {3,3,0,0};
			moveList.add(move19);
			int[] move20 = {3,3,0};
			moveList.add(move20);
			int[] move21 = {3,3};
			moveList.add(move21);
			int[] move22 = {3,3,1};
			moveList.add(move22);
			int[] move23 = {3,3,1,1};
			moveList.add(move23);
			int[] move24 = {3,3,1,1,1};
			moveList.add(move24);

			// rPhase 3
			int[] move25 = {2,0,0,0};
			moveList.add(move25);
			int[] move26 = {2,0,0};
			moveList.add(move26);
			int[] move27 = {2,0};
			moveList.add(move27);
			int[] move28 = {2};
			moveList.add(move28);
			int[] move29 = {2,1};
			moveList.add(move29);
			int[] move30 = {2,1,1};
			moveList.add(move30);
			int[] move31 = {2,1,1,1};
			moveList.add(move31);
			int[] move32 = {2,1,1,1,1};
			moveList.add(move32);
			int[] move33 = {2,1,1,1,1,1};
			moveList.add(move33);
		}

		// Backwards L-Block
		else if(block == 5) {
			r0 = 0; c0 = 3;
			r1 = 1; c1 = 3;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;

			// rPhase 0
			int[] move1 = {0, 0, 0};
			moveList.add(move1);
			int[] move2 = {0,0};
			moveList.add(move2);
			int[] move3 = {0};
			moveList.add(move3);
			int[] move4 = {};
			moveList.add(move4);
			int[] move5 = {1};
			moveList.add(move5);
			int[] move6 = {1,1};
			moveList.add(move6);
			int[] move7 = {1,1,1};
			moveList.add(move7);
			int[] move8 = {1,1,1,1};
			moveList.add(move8);

			// rPhase 1
			int[] move9 = {3,0,0,0,0};
			moveList.add(move9);
			int[] move10 = {3,0,0,0};
			moveList.add(move10);
			int[] move11 = {3,0,0};
			moveList.add(move11);
			int[] move12 = {3,0};
			moveList.add(move12);
			int[] move13 = {3};
			moveList.add(move13);
			int[] move14 = {3,1};
			moveList.add(move14);
			int[] move15 = {3,1,1};
			moveList.add(move15);
			int[] move16 = {3,1,1,1};
			moveList.add(move16);
			int[] move17 = {3,1,1,1,1};
			moveList.add(move17);

			// rPhase 2
			int[] move18 = {3,3,0,0,0};
			moveList.add(move18);
			int[] move19 = {3,3,0,0};
			moveList.add(move19);
			int[] move20 = {3,3,0};
			moveList.add(move20);
			int[] move21 = {3,3};
			moveList.add(move21);
			int[] move22 = {3,3,1};
			moveList.add(move22);
			int[] move23 = {3,3,1,1};
			moveList.add(move23);
			int[] move24 = {3,3,1,1,1};
			moveList.add(move24);
			int[] move25 = {3,3,1,1,1,1};
			moveList.add(move25);

			// rPhase 3
			int[] move26 = {2,0,0,0};
			moveList.add(move26);
			int[] move27 = {2,0,0};
			moveList.add(move27);
			int[] move28 = {2,0};
			moveList.add(move28);
			int[] move29 = {2};
			moveList.add(move29);
			int[] move30 = {2,1};
			moveList.add(move30);
			int[] move31 = {2,1,1};
			moveList.add(move31);
			int[] move32 = {2,1,1,1};
			moveList.add(move32);
			int[] move33 = {2,1,1,1,1};
			moveList.add(move33);
		}
		
		// Z-Block
		else if(block == 6) {
			r0 = 0; c0 = 3;
			r1 = 0; c1 = 4;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;

			// rPhase 0
			int[] move1 = {0,0,0};
			moveList.add(move1);
			int[] move2 = {0,0};
			moveList.add(move2);
			int[] move3 = {0};
			moveList.add(move3);
			int[] move4 = {};
			moveList.add(move4);
			int[] move5 = {1};
			moveList.add(move5);
			int[] move6 = {1,1};
			moveList.add(move6);
			int[] move7 = {1,1,1};
			moveList.add(move7);
			int[] move8 = {1,1,1,1};
			moveList.add(move8);

			// rPhase 1
			int[] move9 = {3,0,0,0,0};
			moveList.add(move9);
			int[] move10 = {3,0,0,0};
			moveList.add(move10);
			int[] move11 = {3,0,0};
			moveList.add(move11);
			int[] move12 = {3,0};
			moveList.add(move12);
			int[] move13 = {3};
			moveList.add(move13);
			int[] move14 = {3,1};
			moveList.add(move14);
			int[] move15 = {3,1,1};
			moveList.add(move15);
			int[] move16 = {3,1,1,1};
			moveList.add(move16);
			int[] move17 = {3,1,1,1,1};
			moveList.add(move17);

			// rPhase 3
			int[] move18 = {2,0,0,0};
			moveList.add(move18);
			int[] move19 = {2,0,0};
			moveList.add(move19);
			int[] move20 = {2,0};
			moveList.add(move20);
			int[] move21 = {2};
			moveList.add(move21);
			int[] move22 = {2,1};
			moveList.add(move22);
			int[] move23 = {2,1,1};
			moveList.add(move23);
			int[] move24 = {2,1,1,1};
			moveList.add(move24);
			int[] move25 = {2,1,1,1,1};
			moveList.add(move25);
			int[] move26 = {2,1,1,1,1,1};
			moveList.add(move26);
		}
		
		// S-Block
		else if(block == 7) {
			r0 = 0; c0 = 4;
			r1 = 0; c1 = 5;
			r2 = 1; c2 = 3;
			r3 = 1; c3 = 4;

			// rPhase 0
			int[] move1 = {0,0,0};
			moveList.add(move1);
			int[] move2 = {0,0};
			moveList.add(move2);
			int[] move3 = {0};
			moveList.add(move3);
			int[] move4 = {};
			moveList.add(move4);
			int[] move5 = {1};
			moveList.add(move5);
			int[] move6 = {1,1};
			moveList.add(move6);
			int[] move7 = {1,1,1};
			moveList.add(move7);
			int[] move8 = {1,1,1,1};
			moveList.add(move8);

			// rPhase 1
			int[] move9 = {3,0,0,0,0};
			moveList.add(move9);
			int[] move10 = {3,0,0,0};
			moveList.add(move10);
			int[] move11 = {3,0,0};
			moveList.add(move11);
			int[] move12 = {3,0};
			moveList.add(move12);
			int[] move13 = {3};
			moveList.add(move13);
			int[] move14 = {3,1};
			moveList.add(move14);
			int[] move15 = {3,1,1};
			moveList.add(move15);
			int[] move16 = {3,1,1,1};
			moveList.add(move16);
			int[] move17 = {3,1,1,1,1};
			moveList.add(move17);

			// rPhase 3
			int[] move18 = {2,0,0,0};
			moveList.add(move18);
			int[] move19 = {2,0,0};
			moveList.add(move19);
			int[] move20 = {2,0};
			moveList.add(move20);
			int[] move21 = {2};
			moveList.add(move21);
			int[] move22 = {2,1};
			moveList.add(move22);
			int[] move23 = {2,1,1};
			moveList.add(move23);
			int[] move24 = {2,1,1,1};
			moveList.add(move24);
			int[] move25 = {2,1,1,1,1};
			moveList.add(move25);
			int[] move26 = {2,1,1,1,1,1};
			moveList.add(move26);
		}
		
		// Setting each tetromino to their corresponding color
		colorInt(10);
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Sets all tetromino blocks into a temporary integer of 8 to be categorized as a moveable block
	public void tempInt() {
		grid[r0][c0] = 8;
		grid[r1][c1] = 8;
		grid[r2][c2] = 8;
		grid[r3][c3] = 8;
	}
	
	// Reverts all temporary ints back into their original color
	public void colorInt(int color) {
		grid[r0][c0] = color;
		grid[r1][c1] = color;
		grid[r2][c2] = color;
		grid[r3][c3] = color;
	}
	
	// Changes the tetromino's color to the color of the background as if it disappears
	public void deleteInt() {
		grid[r0][c0] = 0;
		grid[r1][c1] = 0;
		grid[r2][c2] = 0;
		grid[r3][c3] = 0;
	}
	
	// Changes the index of a tetromino back into their static counterparts once you have placed a block
	public void dynamicToStatic() {
		grid[r0][c0] = block;
		grid[r1][c1] = block;
		grid[r2][c2] = block;
		grid[r3][c3] = block;
	}
	
	// Sets the starting position of a new tetromino
	public void startPos() {
		for(int i=0; i<2; i++) {
			if(canMove(2)) {
				move(2);
			}
		}
	}
	
	// Move command for left, right, and down
	public void move(int x) {
		
		// 0 - Move left
		// 1 - Move right
		// 2 - Move down
		
		// Moving Left
		if(x == 0) {
			deleteInt();		
			// Move all indices to the left
			c0--; c1--;	c2--; c3--;
			colorInt(10);
		}
		
		// Moving Right
		else if(x == 1) {
			deleteInt();
			// Move all indices to the right
			c0++; c1++; c2++; c3++;
			colorInt(10);
		}
		
		// Moving Down
		else if(x == 2) {
			deleteInt();			
			// Move all indices down
			r0++; r1++; r2++; r3++;			
			colorInt(10);
		}
	}
	
	// Combining CanMove and Move methods for simplicity-sake
	public void moveIfAble(int x) {
		
		// 0 - Left
		// 1 - Right
		// 2 - Down
		
		// Left
		if(x == 0) {
			if(canMove(0))
				move(0);
		}
		
		// Right
		else if(x == 1) {
			if(canMove(1))
				move(1);
		}
		
		// Down
		else if(x == 2) {
			if(canMove(2))
				move(2);
		}
	}
	
	public void saveIndex() {
		oldr0 = r0; oldc0 = c0;
		oldr1 = r1; oldc1 = c1;
		oldr2 = r2; oldc2 = c2;
		oldr3 = r3; oldc3 = c3;
	}
	
	public void returnIndex() {
		r0 = oldr0; c0 = oldc0;
		r1 = oldr1; c1 = oldc1;
		r2 = oldr2; c2 = oldc2;
		r3 = oldr3; c3 = oldc3;
	}
	
	public void shiftX(int num) {
		c0 = c0+num;
		c1 = c1+num;
		c2 = c2+num;
		c3 = c3+num;
	}
	
	public void shiftY(int num) {
		r0 = r0-num;
		r1 = r1-num;
		r2 = r2-num;
		r3 = r3-num;
	}
	
	public void leftPhaseChange() { // 0 > 3 > 2 > 1 > 0
		if(rPhase > 0) {
			rPhase--;
		}
		else {
			rPhase = 3;
		}
	}
	
	public void rightPhaseChange() { // 0 > 1 > 2 > 3 > 0
		if(rPhase < 3) {
			rPhase++;
		}
		else {
			rPhase = 0;
		}
	}
	
	public void oldToNew() {
		grid[oldr0][oldc0] = 0;
		grid[oldr1][oldc1] = 0;
		grid[oldr2][oldc2] = 0;
		grid[oldr3][oldc3] = 0;
		
		grid[r0][c0] = 10;
		grid[r1][c1] = 10;
		grid[r2][c2] = 10;
		grid[r3][c3] = 10;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  COMPOSITE METHODS //  COMPOSITE METHODS //  COMPOSITE METHODS //  COMPOSITE METHODS //  COMPOSITE METHODS //  COMPOSITE METHODS //  COMPOSITE METHODS //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Move Left
	public void moveL() {
		//System.out.println("moveL");
		if(!slamLock)
			moveIfAble(0);
	}
	
	// Move Right
	public void moveR() {
		//System.out.println("moveR");
		if(!slamLock)
			moveIfAble(1);
	}
	
	// MoveDown Method
	public void moveD() {
		moveIfAble(2);
	}
	
	// Instantly moves the tetromino down until it can't anymore
	public void slam() {
		//System.out.println("slam");
		slamLock = true;
		while(canMove(2)) {
			moveD();
		}
		placingABlock();
		
		/*
		System.out.println("Holes: "+tetris.getHoles());
		System.out.println("Aggregate Height: "+tetris.getAggregateHeight());
		System.out.println("Bumpiness: "+tetris.getBumpiness());
		System.out.println("Score: "+tetris.getScore());
		*/
		
	}
	
	// Compiles the process of placing a block into one method
	public void placingABlock() {
		dynamicToStatic();
		placeBlock = true;
	}

	public void simulateMove(int move) {

		// MoveSet Reader
		for(int moveSet : moveList.get(move)) {
			
			// moveLeft
			if(moveSet == 0) {
				moveL();
			}	
			// moveRight
			else if(moveSet == 1) {
				moveR();
			}
			// rotateLeft
			else if(moveSet == 2) {
				rotateLeft();
			}
			// rotateRight
			else if(moveSet == 3) {
				rotateRight();
			}
		}
		//System.out.println("SIMULATED MOVE");
		slam(); // Final slam
	}

	public void playMove(int[] moves, Indicator indicator) {
		// MoveSet Reader
		for(int move : moves) {
			
			// moveLeft
			if(move == 0) {
				moveL();
				indicator.update();
			}	
			// moveRight
			else if(move == 1) {
				moveR();
				indicator.update();
			}
			// rotateLeft
			else if(move == 2) {
				rotateLeft();
				indicator.update();
			}
			// rotateRight
			else if(move == 3) {
				rotateRight();
				indicator.update();
			}
		}
		//System.out.println("PLAYED MOVE");
		slam(); // Final slam
	}
	
	// Method to rotate a block counterclockwise
	public void rotateL() {	
		// T-Block Rotation
		if(block == 1) {
			if(rPhase == 0) { // 0 to 3
				c0--; 	r0++;
				c1++; 	r1++;
				//		//
				c3--; 	r3--;
			}
			else if(rPhase == 3) { // 3 to 2
				c0++; 	r0++;
				c1++; 	r1--;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 2) { // 2 to 1
				c0++; 	r0--;
				c1--; 	r1--;
				//		//
				c3++; 	r3++;
			}
			else if(rPhase == 1) { // 1 to 0
				c0--; 	r0--;
				c1--; 	r1++;
				//		//
				c3++; 	r3--;
			}
		}	
		// Square Block Rotation
		else if(block == 2) {
			// THE JOKE IS THERE IS NONE :D
		}	
		// I-Block Rotation
		else if(block == 3) {
			if(rPhase == 0) { // 0 to 3
				c0++; 	r0+=2;
				/**/	r1++;
				c2--;	//
				c3-=2; 	r3--;
			}
			else if(rPhase == 3) { // 3 to 2
				c0+=2; 	r0--;
				c1++;	//
				/**/	r2++;
				c3--;	r3+=2;
			}
			else if(rPhase == 2) { // 2 to 1
				c0--;	r0-=2;
				r1--;	//
				c2++;	//
				c3+=2; 	r3++;
			}
			else if(rPhase == 1) { // 1 to 0
				c0-=2;	r0++;
				c1--;	//
				r2--;	//
				c3++; 	r3-=2;
			}
		}	
		// L-BLock Rotation
		else if(block == 4) {
			if(rPhase == 0) {
				c0-=2;	//
				c1++;	r1++;
				//		//
				c3--;	r3--;
			}
			else if(rPhase == 3) {
				/**/	r0+=2;
				c1++; 	r1--;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 2) {
				c0+=2;	//
				c1--;	r1--;
				//		//
				c3++;	r3++;
			}
			else if(rPhase == 1) {
				/**/	r0-=2;
				c1--; 	r1++;
				//		//
				c3++;	r3--;
			}
		}	
		// Backwards-L-Block Rotation
		else if(block == 5) {
			if(rPhase == 0) {
				/**/	r0+=2;
				c1++; 	r1++;
				//
				c3--; 	r3--;
			}
			else if(rPhase == 3) {
				c0+=2;	//
				c1++; 	r1--;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 2) {
				/**/	r0-=2;
				c1--; 	r1--;
				//		//
				c3++; 	r3++;
			}
			else if(rPhase == 1) {
				c0-=2;	//
				c1--; 	r1++;
				//		//
				c3++; 	r3--;
			}
		}
		// Z-Block Rotation
		else if(block == 6) {
			if(rPhase == 0) {
				/**/	r0+=2;
				c1--; 	r1++;
				//		//
				c3--; 	r3--;
			}
			else if(rPhase == 3) {
				c0+=2;	//
				c1++; 	r1++;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 2) {
				/**/	r0-=2;
				c1++; 	r1--;
				//		//
				c3++; 	r3++;
			}
			else if(rPhase == 1) {
				c0-=2;	//
				c1--; 	r1--;
				//		//
				c3++; 	r3--;
			}
		}
		
		// S-Block Rotation
		else if(block == 7) {
			if(rPhase == 0) {
				c0--; 	r0++;
				c1-=2;	//
				c2++; 	r2++;
				//		//
			}
			else if(rPhase == 3) {
				c0++; 	r0++;
				/**/	r1+=2;
				c2++; 	r2--;
				//		//
			}
			else if(rPhase == 2) {
				c0++; 	r0--;
				c1+=2;	//
				c2--; 	r2--;
				//		//
			}
			else if(rPhase == 1) {
				c0--; 	r0--;
				/**/	r1-=2;
				c2--; 	r2++;
				//		//
			}
		}
	}
	
	// Method to rotate a block clockwise
	public void rotateR() {	
		// T-Block Rotation
		if(block == 1) {
			if(rPhase == 0) { // 0 to 1
				c0++; 	r0++;
				c1++; 	r1--;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 1) { // 1 to 2
				c0--; 	r0++;
				c1++; 	r1++;
				//		//
				c3--; 	r3--;
			}		
			else if(rPhase == 2) { // 2 to 3
				c0--; 	r0--;
				c1--; 	r1++;
				//		//
				c3++; 	r3--;
			}			
			else if(rPhase == 3) { // 3 to 0
				c0++; 	r0--;
				c1--; 	r1--;
				//		//
				c3++; 	r3++;			
			}
		}
		// Square-Block Rotation
		else if(block == 2) {
			// None :D
		}	
		// I-Block Rotation
		else if(block == 3) {
			if(rPhase == 0) {
				c0+=2; 	r0--;
				c1++;	//
				/**/	r2++;
				c3--;	r3+=2;
			}
			else if(rPhase == 1) {
				c0++; 	r0+=2;
				/**/	r1++;
				c2--;	//
				c3-=2; 	r3--;
			}
			else if(rPhase == 2) {
				c0-=2; 	r0++;
				c1--;	//
				/**/	r2--;
				c3++; 	r3-=2;
			}
			else if(rPhase == 3) {
				c0--;	r0-=2;
				/**/	r1--;
				c2++;
				c3+=2; 	r3++;
			}
		}
		// L-BLock Rotation
		else if(block == 4) {
			if(rPhase == 0) {
				/**/	r0+=2;
				c1++; 	r1--;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 1) {
				c0-=2;	//
				c1++; 	r1++;
				//		//
				c3--; 	r3--;
			}
			else if(rPhase == 2) {
				/**/	r0-=2;
				c1--; 	r1++;
				//		//
				c3++; 	r3--;
			}
			else if(rPhase == 3) {
				c0+=2;	//
				c1--; 	r1--;
				//		//
				c3++; 	r3++;
			}
		}
		// Backwards-L-Block Rotation
		else if(block == 5) {
			if(rPhase == 0) {
				c0+=2;	//
				c1++; 	r1--;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 1) {
				/**/	r0+=2;
				c1++; 	r1++;
				//		//
				c3--; 	r3--;
			}
			else if(rPhase == 2) {
				c0-=2;	//
				c1--; 	r1++;
				//		//
				c3++; 	r3--;
			}
			else if(rPhase == 3) {
				/**/	r0-=2;
				c1--; 	r1--;
				//		//
				c3++; 	r3++;
			}
		}
		// Z-Block Rotation
		else if(block == 6) {
			if(rPhase == 0) {
				c0+=2;	//
				c1++; 	r1++;
				//		//
				c3--; 	r3++;
			}
			else if(rPhase == 1) {
				/**/	r0+=2;
				c1--; 	r1++;
				//		//
				c3--; 	r3--;
			}
			else if(rPhase == 2) {
				c0-=2;	//
				c1--; 	r1--;
				//		//
				c3++; 	r3--;
			}
			else if(rPhase == 3) {
				/**/	r0-=2;
				c1++; 	r1--;
				//		//
				c3++; 	r3++;
			}
		}
		// S-Block Rotation
		else if(block == 7) {
			if(rPhase == 0) {
				c0++; 	r0++;
				/**/	r1+=2;
				c2++; 	r2--;
				//		//
			}
			else if(rPhase == 1) {
				c0--; 	r0++;
				c1-=2;	//
				c2++; 	r2++;
				//		//
			}
			else if(rPhase == 2) {
				c0--; r0--;
				r1-=2;
				c2--; r2++;
				//		//
			}
			else if(rPhase == 3) {
				c0++; 	r0--;
				c1+=2;	//
				c2--; 	r2--;
				//		//
			}
		}
	}

	// Method for rotating right and wall-jumping to fit accordingly by testing 5 potential rotation shifts
	public void rRightAndTest() {
		
		saveIndex();
		deleteInt();
		rotateR();
		
		if(!(block == 2 || block == 3))  {
			
			// 0 to 1
			if(rPhase == 0) { 
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(-1,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(-1,1)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(0,-2)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(-1,-2)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			// 1 to 2
			else if(rPhase == 1) {
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(1,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(1,-1)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(0,2)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(1,2)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			// 2 to 3
			else if(rPhase == 2) {
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(1,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(1,1)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(0,-2)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(1,-2)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			// 3 to 0
			else if(rPhase == 3) { 
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(-1,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(-1,-1)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(0,2)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(-1,2)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
		}
		
		else if(block == 3) {
			
			// 0 to 1
			if(rPhase == 0) {
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(-2,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(1,0)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(1,2)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(-2,-1)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			
			// 1 to 2
			else if(rPhase == 1) {
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(-1,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(2,0)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(-1,2)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(2,-1)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			
			// 2 to 3
			else if(rPhase == 2) {
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(2,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(-1,0)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(2,1)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(-1,-2)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			
			// 3 to 0
			else if(rPhase == 3) {
				if(test(0,0)) { // Test 1
					oldToNew();
					rightPhaseChange();
				}
				else
					if(test(-2,0)) { // Test 2
						oldToNew();
						rightPhaseChange();
					}
					else
						if(test(1,0)) { // Test 3
							oldToNew();
							rightPhaseChange();
						}
						else
							if(test(-2,1)) { // Test 4
								oldToNew();
								rightPhaseChange();
							}
							else
								if(test(1,-2)) { // Test 5
									oldToNew();
									rightPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
		}
	}
	
	// Method for rotating left and checking for wall-jumps
	public void rLeftAndTest() {
		
		saveIndex();
		deleteInt();
		rotateL();
		
		if(!(block == 2 || block == 3))  {
			
			// 0 to 3
			if(rPhase == 0) {
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(1,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(1,1)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(0,-2)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(1,-2)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			// 3 to 2
			else if(rPhase == 3) { 
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(-1,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(-1,-1)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(0,2)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(-1,2)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			 // 2 to 1
			else if(rPhase == 2) {
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(-1,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(-1,1)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(0,-2)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(-1,-2)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			// 1 to 0
			else if(rPhase == 1) { 
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(1,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(1,-1)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(0,2)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(1,2)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
		}
		
		// Special Rotation Test for I-Blocks
		else if(block == 3){ 
			
			// 0 to 3
			if(rPhase == 0) {
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(2,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(-1,0)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(-1,2)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(2,-1)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			
			// 3 to 2
			else if(rPhase == 3) {
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(1,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(-2,0)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(1,2)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(-2,-1)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			
			// 2 to 1
			else if(rPhase == 2) {
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(-2,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(1,0)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(-2,1)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(1,-2)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
			
			// 1 to 0
			else if(rPhase == 1) {
				if(test(0,0)) { // Test 1
					oldToNew();
					leftPhaseChange();
				}
				else
					if(test(2,0)) { // Test 2
						oldToNew();
						leftPhaseChange();
					}
					else
						if(test(-1,0)) { // Test 3
							oldToNew();
							leftPhaseChange();
						}
						else
							if(test(2,1)) { // Test 4
								oldToNew();
								leftPhaseChange();
							}
							else
								if(test(-1,-2)) { // Test 5
									oldToNew();
									leftPhaseChange();
								}
								else {
									returnIndex();
									colorInt(10);
								}
			}
		}
	}
	
	public void rotateRight() {
		
		// Rotation for everything
		rRightAndTest();
	}
	
	public void rotateLeft() {
		
		// Rotation for everything
		rLeftAndTest();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean withinBounds() {
		if(	r0 >= 0 && r0 < 24 &&
			r1 >= 0 && r1 < 24 &&
			r2 >= 0 && r2 < 24 &&
			r3 >= 0 && r3 < 24 &&
			
			c0 >= 0 && c0 < 10 &&
			c1 >= 0 && c1 < 10 &&
			c2 >= 0 && c2 < 10 &&
			c3 >= 0 && c3 < 10)
			return true;
		else
			return false;
	}
	
	public boolean isClear() {
		if(grid[r0][c0] == 0 || grid[r0][c0] == 10 || grid[r0][c0] == 9) {
			if(grid[r1][c1] == 0 || grid[r1][c1] == 10 || grid[r1][c1] == 9) {
				if(grid[r2][c2] == 0 || grid[r2][c2] == 10 || grid[r2][c2] == 9) {
					if(grid[r3][c3] == 0 || grid[r3][c3] == 10 || grid[r3][c3] == 9) {
						return true;
					}
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean withinBoundsAndClear() {
		if(withinBounds() && isClear())
			return true;
		else {
			return false;
		}
	}
	
	// All testing conditions
	public boolean test(int x, int y) {
		
		// Shift and check if the new location is clear
		shiftX(x);
		shiftY(y);
		if(withinBoundsAndClear())
			return true;
		
		// Undo the shifting
		else {
			shiftX(-x);
			shiftY(-y);
			return false;
		}
	}
	
	// canMove Boolean is a cornerstone boolean that wraps up if a block can move left, right, or down
	public boolean canMove(int x) {
		
		// 0 - Checks to see if it can move left
		// 1 - Checks to see if it can move right
		// 2 - Checks to see if it can move down
		
		// Setting all current tetromino grid ints to a temporary integer
		tempInt();
		
		// Giant if-statements to only allow the tetromino to move into an empty space, or a space it already occupies
		
		// Checks to see if the block can move left
		if(x == 0) {
			
			// First sets an if-statement that it is within bounds
			if(c0 > 0 && c1 > 0 && c2 > 0 && c3 > 0) {
				if(grid[r0][c0-1] == 0 || grid[r0][c0-1] == 8 || grid[r0][c0-1] == 9) {
					if(grid[r1][c1-1] == 0 || grid[r1][c1-1] == 8 || grid[r1][c1-1] == 9) {
						if(grid[r2][c2-1] == 0 || grid[r2][c2-1] == 8 || grid[r2][c2-1] == 9) {
							if(grid[r3][c3-1] == 0 || grid[r3][c3-1] == 8 || grid[r3][c3-1] == 9) {
								colorInt(10);
								return true;
							}
							else {
								colorInt(10);
								return false;
							}
						}
						else {
							colorInt(10);
							return false;
						}
					}
					else {
						colorInt(10);
						return false;
					}
				}
				else {
					colorInt(10);
					return false;
				}
			}
			else {
				colorInt(10);
				return false;
			}
		}
		
		// Checks to see if the block can move right
		else if(x == 1) {
			
			// First sets an if-statement that it is within bounds
			if(c0 < nearSide && c1 < nearSide && c2 < nearSide && c3 < nearSide) {
				if(grid[r0][c0+1] == 0 || grid[r0][c0+1] == 8 || grid[r0][c0+1] == 9) {
					if(grid[r1][c1+1] == 0 || grid[r1][c1+1] == 8 || grid[r1][c1+1] == 9) {
						if(grid[r2][c2+1] == 0 || grid[r2][c2+1] == 8 || grid[r2][c2+1] == 9) {
							if(grid[r3][c3+1] == 0 || grid[r3][c3+1] == 8 || grid[r3][c3+1] == 9) {
								colorInt(block);
								return true;
							}
							else {
								colorInt(10);
								return false;
							}
						}
						else {
							colorInt(10);
							return false;
						}
					}
					else {
						colorInt(10);
						return false;
					}
				}
				else {
					colorInt(10);
					return false;
				}
			}
			else {
				colorInt(10);
				return false;
			}
		}
		
		// Checks to see if the block can move down
		else if(x == 2) {
			
			// First sets an if-statement that it is within bounds
			if(r0 < nearBottom && r1 < nearBottom && r2 < nearBottom && r3 < nearBottom) {
				if(grid[r0+1][c0] == 0 || grid[r0+1][c0] == 8 || grid[r0+1][c0] == 9) {
					if(grid[r1+1][c1] == 0 || grid[r1+1][c1] == 8 || grid[r1+1][c1] == 9) { 
						if(grid[r2+1][c2] == 0 || grid[r2+1][c2] == 8 || grid[r2+1][c2] == 9) { 
							if(grid[r3+1][c3] == 0 || grid[r3+1][c3] == 8 || grid[r3+1][c3] == 9) {
								colorInt(10);
								return true;
							}
							else {
								colorInt(10);
								//System.out.print("1"); // Troubleshooting purposes...
								return false;
							}
						}
						else {
							colorInt(10);
							//System.out.print("2"); // Troubleshooting purposes...
							return false;
						}
					}
					else {
						colorInt(10);
						//System.out.print("3"); // Troubleshooting purposes...
						return false;
					}
				}
				else {
					colorInt(10);
					//System.out.print("4"); // Troubleshooting purposes...
					return false;
	
				}
			}
			else {
				colorInt(10);
				//System.out.print("5"); // Troubleshooting purposes...
				return false;
			}
		}
		
		// Placeholder
		else
			return false;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  GETTERS & SETTERS //  GETTERS & SETTERS //  GETTERS & SETTERS //  GETTERS & SETTERS //  GETTERS & SETTERS //  GETTERS & SETTERS //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// didMove Boolean to place the block once the tetromino cannot move down
	public boolean getPlaceBlock() {
		return placeBlock;
	}
	// didMove Boolean to place the block once the tetromino cannot move down
	public void setPlaceBlock(boolean bool) {
		placeBlock = bool;
	}
	// gets the type of block
	public int getBlock() {
		return block;
	}
	// gets the grid
	public int[][] getGrid() {
		return grid;
	}
	// gets the tetris
	public Tetris getTetris() {
		return tetris;
	}
	public int getR0() {
		return r0;
	}
	public int getR1() {
		return r1;
	}
	public int getR2() {
		return r2;
	}
	public int getR3() {
		return r3;
	}
	public int getC0() {
		return c0;
	}
	public int getC1() {
		return c1;
	}
	public int getC2() {
		return c2;
	}
	public int getC3() {
		return c3;
	}
	public void setBlock(int x) {
		this.block = x;
	}
	public int getBottom() {
		return nearBottom;
	}
	public int getSide() {
		return nearSide;
	}
	public int getRPhase() {
		return rPhase;
	}
	public ArrayList<int[]> getMoveList() {
		return moveList;
	}
	public int getNumMoves() {
		return moveList.size();
	}
	public String toString() {
		return ""+block;
	}
}
