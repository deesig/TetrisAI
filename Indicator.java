// Austin Trinh
// March 29, 2022
// ITCS
// Indicator Object Class

public class Indicator{

// Fields
private Tetris tetris;
private Tetromino tetromino;
private int[][] grid;
private int nearBottom;
private int nearSide;
private int r0, r1, r2, r3, c0, c1, c2, c3;
private int color;

//Indicator is structured with the Tetromino being already on the ground.
	
	// Call-To-Super Constructor
	public Indicator(Tetromino tetromino) {
		
		this.tetromino = tetromino;
		tetris = tetromino.getTetris();
		
		grid = tetris.getGrid();
		nearBottom = tetris.getRows() -1;
		nearSide = tetris.getColumns() -1;
		color = 9; // Color for indicators
		
		// Steals the indexes of the tetromino and creates a shadow out of it
		r0 = tetromino.getR0(); c0 = tetromino.getC0();
		r1 = tetromino.getR1(); c1 = tetromino.getC1();
		r2 = tetromino.getR2(); c2 = tetromino.getC2();
		r3 = tetromino.getR3(); c3 = tetromino.getC3();
		
		update();
	}
	
	// Move Down
	public void moveDown() {
		r0++;
		r1++;
		r2++;
		r3++;
	}
	
	// ColorInt to color only if the index it is replacing consists of the background
	public void colorInt(int color) {
		if(grid[r0][c0] == 0)
			grid[r0][c0] = color;
		if(grid[r1][c1] == 0)
			grid[r1][c1] = color;
		if(grid[r2][c2] == 0)
			grid[r2][c2] = color;
		if(grid[r3][c3] == 0)
			grid[r3][c3] = color;
	}
	
	// Deletes the indicator by only changing the index of indicators backto background
	public void deleteInt() {
		if(grid[r0][c0] == 9)
			grid[r0][c0] = 0;
		if(grid[r1][c1] == 9)
			grid[r1][c1] = 0;
		if(grid[r2][c2] == 9)
			grid[r2][c2] = 0;
		if(grid[r3][c3] == 9)
			grid[r3][c3] = 0;
	}
	
	// Modified canMoveDown method from tetromino
	public boolean canMoveDown() {
		if(r0 < nearBottom && r1 < nearBottom && r2 < nearBottom && r3 < nearBottom) {
			if(grid[r0+1][c0] == 0 || grid[r0+1][c0] == 8 || grid[r0+1][c0] == 10) {
				if(grid[r1+1][c1] == 0 || grid[r1+1][c1] == 8 || grid[r1+1][c1] == 10) { 
					if(grid[r2+1][c2] == 0 || grid[r2+1][c2] == 8 || grid[r2+1][c2] == 10) { 
						if(grid[r3+1][c3] == 0 || grid[r3+1][c3] == 8 || grid[r3+1][c3] == 10) {
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
		else
			return false;
	}
	
	// Update Method that deletes and slams down the indicator once the tetris block moves left or right
	public void update() {
		deleteInt();
		
		r0 = tetromino.getR0(); c0 = tetromino.getC0();
		r1 = tetromino.getR1(); c1 = tetromino.getC1();
		r2 = tetromino.getR2(); c2 = tetromino.getC2();
		r3 = tetromino.getR3(); c3 = tetromino.getC3();
		
		while(canMoveDown())
			moveDown();
		colorInt(color);
	}

	public String toString() {
		return ""+9;
	}
}