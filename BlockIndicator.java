// Austin Trinh
// March 29, 2022
// ITCS
// BlockIndicator Object Class

public class BlockIndicator {

private int[][] array;
private int r0, r1, r2, r3, c0, c1, c2, c3;
private int block;
private boolean placeBlock = false;
private boolean canMove;
private int nearBottom;
private int nearSide;
private Tetris tetris;
	
	// 2-Argument Constructor
	public BlockIndicator(Tetris tetris, int block){ // Needs the tetris grid that it goes into along with a corresponding int for the tetris block
	
		// Borrowing array & tetris grid
		this.tetris = tetris;
		this.array = tetris.getGrid();
		
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
		}
		
		// Sq-Block
		else if(block == 2) {
			r0 = 0; c0 = 4;
			r1 = 0; c1 = 5;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;
		}
		
		// I-Block
		else if(block == 3) {
			r0 = 0; c0 = 3;
			r1 = 0; c1 = 4;
			r2 = 0; c2 = 5;
			r3 = 0; c3 = 6;
		}
		
		// L-Block
		else if(block == 4) {
			r0 = 0; c0 = 5;
			r1 = 1; c1 = 3;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;	
		}
		
		// Backwards L-Block
		else if(block == 5) {
			r0 = 0; c0 = 3;
			r1 = 1; c1 = 3;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;
		}
		
		// Z-Block
		else if(block == 6) {
			r0 = 0; c0 = 3;
			r1 = 0; c1 = 4;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 5;
		}
		
		// S-Block
		else if(block == 7) {
			r0 = 0; c0 = 4;
			r1 = 0; c1 = 5;
			r2 = 1; c2 = 4;
			r3 = 1; c3 = 3;
		}
		
		// Setting each BlockIndicator to their corresponding color
		colorInt();
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //  PREREQUISITE METHODS //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Sets all BlockIndicator blocks into a temporary integer of 8 to be categorized as a moveable block
	public void tempInt() {
		array[r0][c0] = 8;
		array[r1][c1] = 8;
		array[r2][c2] = 8;
		array[r3][c3] = 8;
	}
	// Reverts all temporary ints back into their original color;
	public void colorInt() {
		array[r0][c0] = 9;
		array[r1][c1] = 9;
		array[r2][c2] = 9;
		array[r3][c3] = 9;
	}
	// Changes the BlockIndicator's color to the color of the background as if it disappears
	public void deleteInt() {
		array[r0][c0] = 0;
		array[r1][c1] = 0;
		array[r2][c2] = 0;
		array[r3][c3] = 0;
	}
	// Sets the starting position of a new BlockIndicator
	public void startPos() {
		for(int i=0; i<4; i++) {
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
			colorInt();
		}
		
		// Moving Right
		else if(x == 1) {
			deleteInt();
			// Move all indices to the right
			c0++; c1++; c2++; c3++;
			colorInt();
		}
		
		// Moving Down
		else if(x == 2) {
			deleteInt();			
			// Move all indices down
			r0++; r1++; r2++; r3++;			
			colorInt();
		}
	}
	
	// Combining CanMove and Move methods for simplicity-sake
	public void moveOrPlace(int x) {
		
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
			else
				placeBlock = true;
		}
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //  METHODS  //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Move Left
	public void moveL() {
		moveOrPlace(0);
	}
	
	// Move Right
	public void moveR() {
		moveOrPlace(1);
	}
	
	// MoveDown Method
	public void moveD() {
		moveOrPlace(2);
	}
	
	public void slam() {
		while(canMove(2)) {
			moveOrPlace(2);
		}
		placeBlock = true;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //  BOOLEANS  //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// canMove Boolean is a cornerstone boolean that wraps up if a block can move left, right, or down
	public boolean canMove(int x) {
		
		// 0 - Checks to see if it can move left
		// 1 - Checks to see if it can move right
		// 2 - Checks to see if it can move down
		
		// Setting all current BlockIndicator array ints to a temporary integer
		tempInt();
		
		// Giant if-statements to only allow the BlockIndicator to move into an empty space, or a space it already occupies
		
		// Checks to see if the block can move left
		if(x == 0) {
			// First sets an if-statement that it is within bounds
			if(c0 > 0 && c1 > 0 && c2 > 0 && c3 > 0) {
				if(array[r0][c0-1] == 0 || array[r0][c0-1] == 8 || array[r0][c0-1] == 9) {
					if(array[r1][c1-1] == 0 || array[r1][c1-1] == 8 || array[r1][c0-1] == 9) {
						if(array[r2][c2-1] == 0 || array[r2][c2-1] == 8 || array[r2][c0-1] == 9) {
							if(array[r3][c3-1] == 0 || array[r3][c3-1] == 8 || array[r3][c0-1] == 9) {
								colorInt();
								return true;
							}
							else {
								colorInt();
								return false;
							}
						}
						else {
							colorInt();
							return false;
						}
					}
					else {
						colorInt();
						return false;
					}
				}
				else {
					colorInt();
					return false;
				}
			}
			else {
				colorInt();
				return false;
			}
		}
		
		// Checks to see if the block can move right
		else if(x == 1) {
			// First sets an if-statement that it is within bounds
			if(c0 < nearSide && c1 < nearSide && c2 < nearSide && c3 < nearSide) {
				if(array[r0][c0+1] == 0 || array[r0][c0+1] == 8 || array[r0][c0+1] == 9) {
					if(array[r1][c1+1] == 0 || array[r1][c1+1] == 8 || array[r1][c0+1] == 9) {
						if(array[r2][c2+1] == 0 || array[r2][c2+1] == 8 || array[r1][c0+1] == 9) {
							if(array[r3][c3+1] == 0 || array[r3][c3+1] == 8 || array[r1][c0+1] == 9) {
								colorInt();
								return true;
							}
							else {
								colorInt();
								return false;
							}
						}
						else {
							colorInt();
							return false;
						}
					}
					else {
						colorInt();
						return false;
					}
				}
				else {
					colorInt();
					return false;
				}
			}
			else {
				colorInt();
				return false;
			}
		}
		
		// Checks to see if the block can move down
		else if(x == 2) {
			// First sets an if-statement that it is within bounds
			if(r0 < nearBottom && r1 < nearBottom && r2 < nearBottom && r3 < nearBottom) {
				if(array[r0+1][c0] == 0 || array[r0+1][c0] == 8 || array[r0+1][c0] == 9) {
					if(array[r1+1][c1] == 0 || array[r1+1][c1] == 8 || array[r1+1][c0] == 9) { 
						if(array[r2+1][c2] == 0 || array[r2+1][c2] == 8 || array[r2+1][c0] == 9) { 
							if(array[r3+1][c3] == 0 || array[r3+1][c3] == 8 || array[r3+1][c0] == 9) {
								colorInt();
								return true;
							}
							else {
								colorInt();
								return false;
							}
						}
						else {
							colorInt();
							return false;
						}
					}
					else {
						colorInt();
						return false;
					}
				}
				else {
					colorInt();
					return false;
				}
			}
			else {
				colorInt();
				return false;
			}
		}
		
		// Fail-Safe
		return canMove;
	}
	
	// didMove Boolean to place the block once the BlockIndicator cannot move down
	public boolean getPlaceBlock() {
		return placeBlock;
	}
	// didMove Boolean to place the block once the BlockIndicator cannot move down
	public void setPlaceBlock(boolean bool) {
		placeBlock = bool;
	}
}
