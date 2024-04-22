// Austin Trinh
// March 23, 2022
// ITCS
// Tetris Object Classs

// Import statements
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Parameters of Interest:
// Bumpiness
// Holes
// Lines Cleared

public class Tetris {

// Fields
private int[][] grid;
private int scale;
private int x;
private int y;
private Tetromino tetromino;
private int rows = 24; // Default is 24
private int columns = 10; // Default is 10
private int rowsDeleted = 0;
private int score;
private int linesCleared;
private Color background = Color.black;//Color.blue.darker().darker().darker();
private ArrayList<Integer> rowDeleted = new ArrayList<Integer>();
private boolean lineCleared;
private boolean holdingbay = false;
private Color outline = Color.WHITE.darker().darker().darker().darker().darker().darker();
private boolean didTetris = false;
private boolean backToBackTetris = false;
//private Color outline = Color.WHITE;

	//Tetris Default Constructor
	public Tetris() {
		
		// Instantiating fields
		x = 0;
		y = 0;
		scale = 25;
		
		// Creating the 2D grid and setting all integers to 0
		grid = new int[rows][columns];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				grid[r][c] = 0;
			}
		}
	}

	// Tetris 3-Argument Constructor
	public Tetris(int x, int y, int scale) {
		
		// Instantiating fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		
		// Creating the 2D grid and setting all integers to 0
		grid = new int[rows][columns];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				grid[r][c] = 0;
			}
		}
	}
	
	// Constructor for holdingbay specifically
	public Tetris(int x, int y, int scale, int rows, int columns) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.rows = rows;
		this.columns = columns;
		
		holdingbay = true;
		
		// Creating the 2D grid and setting all integers to 0
		grid = new int[rows][columns];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				grid[r][c] = 0;
			}
		}
	}

	public Tetris(Tetris tetris) {
		x = tetris.getX();
		y = tetris.getY();
		scale = tetris.getScale();
		rows = tetris.getRows();
		columns = tetris.getColumns();
		holdingbay = true;
		grid = tetris.getGrid();
	}
	
	// DrawArray Method that draws a 2D array -> 10 across, 22 down
	public void drawArray() {
		
		// Draws the array into the console
		for(int i=0; i<grid.length; i++) {
			for(int x=0; x<grid[0].length; x++)
				System.out.print(grid[i][x]);
			System.out.println("");
		}
	}
	
	// Draw Method that translates a grid into 2D graphics
	public void draw(Graphics g) {
		
		// In each row for each column...
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				
				// In this model of tetris, current player-controlled tetromino blocks have a unique index of 10
				// and will return to a static index of 1, 2, 3, 4, 5, 6, 7 once placed
				// This is to differentiate tetromino-controlled blocks from already-placed blocks
				// in terms of differentiating interactions and so forth
				
				// 0 Represents background blocks
				if(r>3 && !holdingbay) {
					if(grid[r][c] == 0) {
						g.setColor(background);
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}
				}
				if(holdingbay) {
					if(c>2 && c<7) {
						if(grid[r][c] == 0) {
							g.setColor(background);
							g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						}
					}
				}
				
				// 1 Represents T-Blocks
				if(grid[r][c] == 1) {
					g.setColor(Color.magenta.darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(Color.magenta);
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}	
				
				// 2 Represents Square Blocks
				else if(grid[r][c] == 2) {
					g.setColor(Color.orange.darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(Color.orange);
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
				
				// 3 Represents I blocks
				else if(grid[r][c] == 3) {
					g.setColor(Color.cyan.darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(Color.cyan);
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
				
				// 4 Represents L-Blocks
				else if(grid[r][c] == 4) {
					g.setColor(new Color(255, 120, 0).darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(new Color(255, 120, 0));
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}	
				
				// 5 Represents Backwards L-Blocks
				else if(grid[r][c] == 5) {
					g.setColor(Color.blue.darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(Color.blue);
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
				
				// 6 Represents Z-Blocks
				else if(grid[r][c] == 6) {
					g.setColor(Color.red.darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(Color.red);
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
				
				// 7 Represents S-Blocks
				else if(grid[r][c] == 7) {
					g.setColor(Color.green.darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
					g.setColor(Color.green);
					g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
					g.setColor(outline);
					g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
				
				// 8 Represents Error Blocks
				else if(grid[r][c] == 8) {
					g.setColor(Color.WHITE);
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
				
				// 9 Represent Block Indicators {
				else if(grid[r][c] == 9) {
					g.setColor(Color.WHITE.darker().darker().darker());
					//g.setColor(Color.WHITE.darker().darker());
					g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
				}
			}
		}
		if(!holdingbay) {
			
			// Gridlines
			g.setColor(outline);
			for(int r=5; r<rows; r++)
				g.drawLine(x, y+(scale*r), x+(scale*columns)-1, y+(scale*r));
			for(int c=1; c<columns; c++)
				g.drawLine(x+(scale*c), y+scale*4, x+(scale*c), y+(scale*rows)-1);
			
			// Borders of the Tetris Grid
			g.setColor(Color.WHITE);
			g.drawLine(x, y+scale*4, x, y+scale*rows); // Left
			g.drawLine(x+scale*columns, y+scale*4, x+scale*columns, y+scale*rows); // Right
			g.drawLine(x, y+scale*rows, x+scale*columns, y+scale*rows); // Bottom
		
			// Marks the threshold line that tetris blocks cannot exceed or game over
			//g.drawLine(x, y+(4*scale), x+(scale*columns), y+(4*scale));
		}
	}
	
	public void drawDynamicTetromino(Tetromino tetromino, Graphics g) {
		// In each row for each column...
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				
				// 10 Represents tetromino-controlled blocks with the corresponding numbers above
				if(grid[r][c] == 10) {	
					
					// 1 Represents T-Blocks
					if(tetromino.getBlock() == 1) {
						g.setColor(Color.magenta.darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(Color.magenta);
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}	
					
					// 2 Represents Square Blocks
					else if(tetromino.getBlock() == 2) {
						g.setColor(Color.orange.darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(Color.orange);
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}
					
					// 3 Represents I blocks
					else if(tetromino.getBlock() == 3) {
						g.setColor(Color.cyan.darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(Color.cyan);
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}
					
					// 4 Represents L-Blocks
					else if(tetromino.getBlock() == 4) {
						g.setColor(new Color(255, 120, 0).darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(new Color(255, 120, 0));
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}	
					
					// 5 Represents Backwards L-Blocks
					else if(tetromino.getBlock() == 5) {
						g.setColor(Color.blue.darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(Color.blue);
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}
					
					// 6 Represents Z-Blocks
					else if(tetromino.getBlock() == 6) {
						g.setColor(Color.red.darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(Color.red);
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}
					
					// 7 Represents S-Blocks
					else if(tetromino.getBlock() == 7) {
						g.setColor(Color.green.darker());
						g.fillRect(x+(scale*(c)), y+(scale*r), scale, scale);
						g.setColor(Color.green);
						g.fillRect(x+(scale*(c)+(scale/8)), y+(scale*r)+(scale/8), scale-((2*scale)/8), scale-((2*scale)/8));
						g.setColor(outline);
						g.drawRect(x+(scale*(c)), y+(scale*r), scale, scale);
					}
				}
			}
		}
	}
	
	// Returns a random int from 1-7 that represents a random Tetris block
	public int randomBlock() {
		return (int)(Math.random()*7+1);
	}
	
	// Returns a boolean that determines if the game is over or not
	public boolean gameOver() {
		for(int r=0; r<4; r++) {
			for(int c=0; c<columns; c++) {
				if(grid[r][c] != 0 && grid[r][c] != 10 && grid[r][c] != 9)
					return true;
			}
		}
		return false;
	}
	
	// Clears the whole board
	public void clear() {
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				grid[r][c] = 0;
			}
		}
	}
	
	// Method to clear an entire row
	public void clearRow(int row) {
		for(int c=0; c<columns; c++) {
			grid[row][c] = 0;
		}
	}
	
	// Method to clear a row if it fulfills the requirement of being full
	public void LineClear() {
		
		// First resets rowsDeleted to update the counter
		rowsDeleted = 0;
		
		// Set a for-loop to check every row
		for(int r=rows-1; r>=0; r--){
			
			// States a boolean that: if the row does not have a 0 in any column, it means it is full
			boolean isFull = true;
			
			// For-loop that checks through all columns
			for(int c=0; c<columns; c++) {
				if(grid[r][c] == 0) {
					isFull = false;
				}
			}
			
			// In the event that the row is full...
			if(isFull) {
				rowDeleted.add(r); // Saves the index where the row was cleared from bottom to top
				clearRow(r); // Clear the row
				lineCleared = true; // Allows shifting to begin only once rows have been cleared.
				rowsDeleted++; // Adds to the counter of how many rows you have cleared in one move. A Tetris is when you clear four lines with one move.
			}
		}

		if(rowsDeleted == 1) {
			score += 1000;
			didTetris = false;
			backToBackTetris = false;
		}
		else if(rowsDeleted == 2) {
			score += 3000;
			didTetris = false;
			backToBackTetris = false;
		}
		else if(rowsDeleted == 3) {
			score += 5000;
			didTetris = false;
			backToBackTetris = false;
		}
		else if(rowsDeleted == 4) {
			score += 8000;
			if(didTetris) {
				backToBackTetris = true;
				score +=4000;
			}
			didTetris = true;
		}
		
		// If lines are cleared... continue to the following
		if(lineCleared) {
			
			// For every row that is cleared... shift down once
			for(int i=0; i<rowDeleted.size(); i++) {
				linesCleared++;
				shiftDownFrom(rowDeleted.get(i));
			}
			
			// Resets lineCleared boolean
			lineCleared = false;
			rowDeleted.clear(); // Resets rowDeleted arrayList
		}
	}
	
	// Pre-req method to shift down the grid when given the row that has been cleared.
	public void shiftDownFrom(int row) {
		for(int r=row; r>0; r--) {
			for(int c=0; c<columns; c++) {
				grid[r][c] = grid[r-1][c];
			}
		}
		for(int i=0; i<rowDeleted.size(); i++)
			rowDeleted.set(i, rowDeleted.get(i)+1);
	}
	
	// Boolean for a full-clear
	public boolean fullClear() {
		boolean fullClear = true;
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				if(grid[r][c] != 0)
					fullClear = false;
			}
		}
		return fullClear;
	}

	// Print Method
	public void printBoard() {
		for(int r=0; r<rows; r++) {
			System.out.println();
			for(int c=0; c<columns; c++) {
				if(grid[r][c] == 0) {
					System.out.print("O ");
				}
				else {
					System.out.print("1 ");
				}
			}
		}
		System.out.println();
	}

	// Methods for Genetic Algorithm

	// getHoles
	public int getHoles() {
		int holes = 0;
		for(int c=0; c<columns; c++) {
			boolean firstBlock = false;
			for(int r=0; r<rows; r++) {
				if(grid[r][c] != 0 && grid[r][c] != 9)
					firstBlock = true;
				if((grid[r][c] == 0 || grid[r][c] == 9) && firstBlock)
					holes++;
			}
		}
		return holes;
	}
	// Total Score Cleared
	public int getScore() {
		return score;
	}
	// Bumpiness
	public int getBumpiness() {
		int bumpScore = 0;
		int[] bumps = new int[10];
		for(int c=0; c<columns; c++) {
			int row = rows;
			for(int r=0; r<rows; r++) {
				if(grid[r][c] != 0 && grid[r][c] != 9) {
					row = r;
					break;
				}
			}
			bumps[c] = rows-row;
		}
		for(int i=0; i<bumps.length-1; i++) {
			bumpScore += Math.abs(bumps[i]-bumps[i+1]);
		}
		return bumpScore;
	}
	// Height of stack
	public int getAggregateHeight() {
		// int tallest = getHeightHelper(0);
		// for(int c=1; c<columns; c++) {
		// 	if(tallest < getHeightHelper(c)) {
		// 		tallest = getHeightHelper(c);
		// 	}
		// }
		// return tallest;
		int aggregateHeight = 0;
		for(int c=0; c<columns; c++) {
			aggregateHeight += getHeightHelper(c);
		}
		return aggregateHeight;
	}
	// getHeight helper
	public int getHeightHelper(int col) {
		for(int r=0; r<rows; r++) {
			if(grid[r][col] != 0 && grid[r][col] != 9)
				return rows-r;
		}
		return 0;
	}
	public int didATetris() {
		if(didTetris)
			return 8000;
		else
			return 0;
	}
	public int didBackToBackTetris() {
		if(didTetris && backToBackTetris)
			return 4000;
		else
			return 0;
	}
	
	
	// Copy of Tetris
	public int[][] getGridCopy() {
		int[][] gridCopy = new int[rows][columns];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				if(grid[r][c] == 10)
					gridCopy[r][c] = 0;
				else
					gridCopy[r][c] = grid[r][c];
			}
		}
		return gridCopy;
	}



	// GETTER METHODS
	public int[][] getGrid() { 	// GetGrid method that returns the current grid of Tetris
		return grid;
	}
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getScale() {
		return scale;
	}
	public int getRows() { // Returns # of rows
		return rows;
	}
	public int getColumns() { // Returns # of rows
		return columns;
	}
	public Color getBackground() { // Returns the color of the background
		return background;
	}
	public int getRowsDeleted() {
		return rowsDeleted;
	}
	public boolean didLineClear() {
		return lineCleared;
	}
	public void lineClear(boolean bool) {
		this.lineCleared = bool;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getColor(Tetromino tetromino) {
		return tetromino.getBlock();
	}
	public boolean getDidBackToBackTetris() {
		return backToBackTetris;
	}
}