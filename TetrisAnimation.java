// Austin Trinh
// March 31, 2022
// ITCS
// TetrisAnimation

// Import statements
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

@SuppressWarnings("serial")
public class TetrisAnimation extends JPanel {

// Set the initial width and height of your image
private static final int WIDTH = 525;
private static final int HEIGHT = 1000;

// Required global variables (initialize these in the constructor) 
private BufferedImage image;
//private Graphics g;
private Graphics gPlus;
public Timer timer, dropTimer, gameTimer, scoreTimer, botTimer;
private Tetris tetris; //change this to whatever object(s) you are animating
private Tetris holdingbay;
private ArrayList<Tetris> queuebay;
private boolean holdDisable = false;
private ArrayList<Tetromino> holdblock;
private ArrayList<Tetromino> tetromino;
private ArrayList<Tetromino> queueblock;
private ArrayList<Indicator> indicator;
private ArrayList<Integer> queue;
// private File TetrisTheme = new File("Tetris.wav");
private ImageIcon tetrisLogo;
private int seconds = 0;
private int maxseconds = 120;
private boolean timeGameOver = false;

private int highscore = 0;
private int testblock = 2;

private double dropTime = 1; // In seconds
private double botTime = 1; // In millisecondsdd

private boolean touchedGround = false;
private boolean perfectClear;
private int lockDelayResets = 0;
private int maxLockDelayResets = 15;

private int population = 10;
private GeneticAgent[] bots = new GeneticAgent[population];
private double[] botScores = new double[population];
private int generation = 1;
private int currentBotIndex;
private boolean toggleAI = true;

// For all TETRIS nerds out there... I have realized there is more to TETRIS than I could have ever preconceived...
// Nevertheless, fanservice is here with all its nerdy jargon to describe THIS particular version of TETRIS because there's a baijgiegjaerjiegjgillion of different variations
// with different rotation systems (SRS vs ARS vs SRS+ vs SRS-X ugghhgghg) with their own respective "wall-kick" mechanics
// In short: this TETRIS game involves the SRS+ to guide rotation as well as include symmetrical I-Block Rotation because this was closest to the settings I played with the most
// There is also no Infinity. Screw that mechanic. You cannot bide time forever by simply spamming and timing rotations endlessly and resetting the lockDelay.
// Holding blocks, soft-dropping and hard-dropping are also included
	
	public TetrisAnimation() {

		tetrisLogo = new ImageIcon(Tetris.class.getResource("Tetris.png"));
		//tetrisLogo = new ImageIcon("Tetris.png");
		
		// Starts the track
		PlaySound("Tetris.wav");
		
		/* 2
			INDEX:
			1 = T
			2 = Square
			3 = I
			4 = L
			5 = Backwards L
			6 = Z
			7 = S 
		*/

		// Builds Tetris Grid, and instantiates the ArrayLists of tetromino and indicator
		tetris = new Tetris(125, 100, 25);
		tetromino = new ArrayList<Tetromino>();
		indicator = new ArrayList<Indicator>();
		
		// Holding Bay
		holdingbay = new Tetris(-40, 200, 20, 4, 10);
		holdblock = new ArrayList<Tetromino>();
		
		// Queue System and sets each of the 5 individual integers a random block to be drawn
		queue = newBag(); // from 0 being first to 4 being last
		//System.out.println(queue);
		queuebay = new ArrayList<Tetris>();
		queueblock = new ArrayList<Tetromino>();
		for(int i=0; i<5; i++) {
			//queue.add(tetris.randomBlock());
			//queue.add(3);
			queuebay.add(new Tetris(340, 200+i*80, 20, 4, 10));
			queueblock.add(new Tetromino(queuebay.get(i), queue.get(i)));
			if(queueblock.get(i).canMove(2))
				queueblock.get(i).move(2);
			queueblock.get(i).dynamicToStatic();
//			queuebay.add(new Tetris(340, 200, 20, 4, 10)); // 1 (Top)
//			queuebay.add(new Tetris(340, 280, 20, 4, 10)); // 2
//			queuebay.add(new Tetris(340, 360, 20, 4, 10)); // 3
//			queuebay.add(new Tetris(340, 420, 20, 4, 10)); // 4
//			queuebay.add(new Tetris(340, 500, 20, 4, 10)); // 5 (Bottom)
		}
		
		// Adds the first tetris block
		//tetromino.add(new Tetromino(tetris, tetris.randomBlock()));
		tetromino.add(new Tetromino(tetris, queue.get(0)));
		updateQueue();
		tetromino.get(0).startPos();
		indicator.add(new Indicator(tetromino.get(0)));
		
		
		//indicator.get(0).softSlam();
		//tetromino.get(0).setBlock(block);
		//indicator.add(new BlockIndicator(tetris, tetromino.get(0).getTetromino()));

		//tetromino.add(new Tetromino(tetris, tetris.randomBlock()));

		// Timer
		timer = new Timer((int)1, new TimerListener());
		dropTimer = new Timer((int)(dropTime*1000), new dropTimerListener());
		gameTimer = new Timer(1000, new GameListener());
		scoreTimer = new Timer(1000, new ScoreListener());
		botTimer = new Timer((int)(botTime), new botListener());
		timer.start();
		dropTimer.start();
		gameTimer.start();
		botTimer.start();
		
		// Keyboard Listener
		addKeyListener(new Keyboard());
		setFocusable(true);

		if(toggleAI) {
			maxseconds = 30;
			// WHERE AI SHIT BEGINS
			holdBlock();

			// Instantiating all the bots for the first gen
			for(int i=0; i<bots.length; i++) {
				bots[i] = new GeneticAgent(tetris);
			}
			currentBotIndex = 0;
		}
	}
	
	// KeyboardListener class that is called when keys are pressed
	private class Keyboard implements KeyListener {
		
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_G) {
				if(timer.isRunning()) {
					timer.stop();
					dropTimer.stop();
					gameTimer.stop();
				}
				else {
					timer.start();
					dropTimer.start();
					gameTimer.start();
				}
			}
			
			// Ensure timer is running
			if(timer.isRunning()) {
				
				// Slam
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					dropTimer.stop();
					dropTimer.restart();
					tetromino.get(0).slam();
					dropTimer.start();

					// TEST COMMENTS
					// System.out.println("Holes: "+tetris.getHoles());
					// System.out.println("Aggregate Height: "+tetris.getAggregateHeight());
					// System.out.println("Bumpiness: "+tetris.getBumpiness());
					// System.out.println("Score: "+tetris.getScore());
				}
				// Move Left
				else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(dropTimer.isRunning()) {
						tetromino.get(0).moveL();
						indicator.get(0).update();
					}
				}
				// Move Right
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(dropTimer.isRunning()) {
						tetromino.get(0).moveR();
						indicator.get(0).update();
					}
				}						
				// Move Down
				else if(e.getKeyCode() == KeyEvent.VK_S) {
					tetromino.get(0).moveD();
					// Ensures there is no "double-dropping" - Timer restarts to give more control/stability
					if(tetromino.get(0).canMove(2))
						dropTimer.restart();
				}				
				// Rotate Right
				else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_UP) {
					tetromino.get(0).rotateRight();
					indicator.get(0).update();
					if(touchedGround)
						lockDelayResets++;
					//System.out.println("Test");
				}			
				// Rotate Left
				else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_DOWN) {
					tetromino.get(0).rotateLeft();
					indicator.get(0).update();
					if(touchedGround)
						lockDelayResets++;
					//System.out.println("Test");
				}

				// Method to hold block
				else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
					holdBlock();
				}

				// AI Test Key
				else if(e.getKeyCode() == KeyEvent.VK_T) {
					// Each bot goes one-by-one
					System.out.println();
					for(int num : bots[0].getBestMove(tetromino.get(0))) {
						System.out.print(num+" ");
					}
					System.out.println();
					tetromino.get(0).playMove(bots[0].getBestMove(tetromino.get(0)), indicator.get(0));
				}
				else if(e.getKeyCode() == KeyEvent.VK_P) {
					System.out.println("test");
					botTimer.start();
				}
				else if(e.getKeyCode() == KeyEvent.VK_L) {
					System.out.println("Weights for the current bot");
					System.out.println("wBump: "+bots[currentBotIndex].wBump);
					System.out.println("wHoles: "+bots[currentBotIndex].wHoles);
					System.out.println("wScore: "+bots[currentBotIndex].wScore);
					System.out.println("wAggregateHeight: "+bots[currentBotIndex].wAggregateHeight);
					System.out.println("wB2BT: "+bots[currentBotIndex].wB2BT);
					System.out.println("wTetris: "+bots[currentBotIndex].wTetris);
					System.out.println("wTime: "+bots[currentBotIndex].wTime);
					// wBump, wHoles, wScore, wAggregateHeight, wB2BT, wTetris, fitness, wTime, time;
				}
			}
			// Reset
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				reset();
			}
		}

		// Key Methods to move Tetris blocks
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	// TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
	
	// TimerListener class that is called repeatedly by the timer
	private class dropTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!tetromino.get(0).canMove(2)) {
				tetromino.get(0).placingABlock();
			}
			else
				tetromino.get(0).moveD();
			tetris.drawDynamicTetromino(tetromino.get(0), gPlus);
			tetris.draw(gPlus);
			repaint();
		}
	}
	
	// GameTimer
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			seconds++;
			if(seconds > maxseconds) {
				timeGameOver = true;
				seconds++;
			}
		}
	}
	
	// ScoreTimer
	private class ScoreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			perfectClear = false;
			scoreTimer.stop();
		}
	}

	// botListener
	private class botListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Game Over Conditions
			if(tetris.gameOver() || timeGameOver) {

				bots[currentBotIndex].time = seconds;

				// Rates the board
				bots[currentBotIndex].setFitness(bots[currentBotIndex].rateBoard());
				// System.out.println(bots[currentBotIndex].rateBoard());
				// System.out.println(bots[currentBotIndex].getFitness());

				// ... & saves the score
				botScores[currentBotIndex] = bots[currentBotIndex].getFitness();
				// & Reset
				reset();

				// Increment the next bot in the generation
				if(currentBotIndex < population-1)
					currentBotIndex++;
				else { // Once we reach the end of a generation
					GeneticAgent[] newBots = new GeneticAgent[bots.length];
					newBots[0] = GeneticAgent.getBestBot(bots);
					for(int i = 1; i < newBots.length; i++){
						if(i % 2 == 0){
							newBots[i] = new GeneticAgent(tetris);
						} else {
							newBots[i] = GeneticAgent.darwinism(bots);
						}
					}
					bots = newBots;
					generation++;
					currentBotIndex = 0;
					// & holds next block
					holdBlock();
				}
			}	
			// Tries the best move
			// System.out.println("Tetromino: "+tetromino);
			// System.out.println("Hold block: "+holdblock);
			if(bots[currentBotIndex].compareMove(tetromino.get(0), holdblock.get(0))) {
				tetromino.get(0).playMove(bots[currentBotIndex].getBestMove(tetromino.get(0)), indicator.get(0));
			}
			else {
				holdBlock();
				tetromino.get(0).playMove(bots[currentBotIndex].getBestMove(tetromino.get(0)), indicator.get(0));
			}
		}
	}
	
	// Gradient Background Method
	public static void gradientBackground(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		GradientPaint blackToBlue = new GradientPaint(0, 0, Color.BLACK, 0, 2500/*1600*/, Color.BLUE);
		g2D.setPaint(blackToBlue);
		g2D.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	// Updating Queue System Method
	public void updateQueue() {
		// Gets rid of the newly-pushed-out block
		for(int i=0; i<queueblock.size(); i++)
			queueblock.get(i).deleteInt();
		queue.remove(0);
		queueblock.remove(0);
		
		// Shift the remaining blocks up
		for(int i=0; i<4; i++) {
			queueblock.set(i, new Tetromino(queuebay.get(i), queue.get(i)));
			if(queueblock.get(i).canMove(2))
				queueblock.get(i).move(2);
			queueblock.get(i).dynamicToStatic();
		}
		
		// Adds another bag if the bag depletes
		if(queue.size() <= 14) {
			ArrayList<Integer> array = newBag();
			for(int i=0; i<array.size(); i++) {
				queue.add(array.get(i));
			}
		}
		//queue.add(tetris.randomBlock());
		//queue.add(3);
		queueblock.add(new Tetromino(queuebay.get(4), queue.get(4)));
		if(queueblock.get(4).canMove(2))
			queueblock.get(4).move(2);
		queueblock.get(4).dynamicToStatic();
	}
	
	public void refreshQueue() {
		queue.clear();
		for(int i=0; i<5; i++)
			queueblock.get(i).deleteInt();
		queueblock.clear();
		queue = newBag();
		for(int i=0; i<5; i++) {
			//queue.add(3);
			queueblock.add(new Tetromino(queuebay.get(i), queue.get(i)));
			if(queueblock.get(i).canMove(2))
				queueblock.get(i).move(2);
			queueblock.get(i).dynamicToStatic();
		}
		tetromino.add(new Tetromino(tetris, queue.get(0)));
		updateQueue();
	}
	
	// PaintComponent
	public void paintComponent(Graphics g) {
		gPlus = g;
		
		// Background
		gradientBackground(g);
		if(tetris.getScore() > highscore)
			highscore=tetris.getScore();
		
		//g.setColor(background);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// If a Tetris Block touches the ground, engage the lock delay
		if(!tetromino.get(0).canMove(2))
			touchedGround = true;
		if(lockDelayResets >= maxLockDelayResets)
			tetromino.get(0).slam();
		
		if(timeGameOver == true) {
			g.setColor(Color.white);
			g.setFont(new Font("Dialog", Font.BOLD, 25));
			g.drawString("GAME OVER", 200, 500);
			timer.stop();
			dropTimer.stop();
			if(tetris.getScore() > highscore)
				highscore=tetris.getScore();
		}
		
		// If Tetris Block can no longer move downwards...
		if((tetromino.get(0).getPlaceBlock() == true)) {
			
			// Resetting the holding mechanism
			holdDisable = false;
			
			// Changes the index of the tetromino into their static counterparts
			tetromino.get(0).dynamicToStatic();
			
			// Reset LockDelay mechanism
			touchedGround = false;
			lockDelayResets = 0;
			
			// Updates score
			tetris.LineClear();
			if(tetris.getRowsDeleted() > 0) {
				if(tetris.fullClear()) {
					tetris.setScore(tetris.getScore()+10000);
					perfectClear = true;
				}
				scoreTimer.start();
			}
			
			// Sets the block by removing it
			tetromino.remove(0);
			indicator.remove(0);
			
			// Game Over conditions
			if(tetris.gameOver()) {
				timer.stop();
				dropTimer.stop();
				if(tetris.getScore() > highscore)
					highscore=tetris.getScore();
				g.setColor(Color.yellow);
				g.setFont(new Font("Dialog", Font.BOLD, 25));
				g.drawString("GAME OVER", 175, 150);
			}
			
			// Creates another random blocks
			else {
				//tetromino.add(new Tetromino(tetris, tetris.randomBlock()));
				tetromino.add(new Tetromino(tetris, queue.get(0)));
				updateQueue();
				tetromino.get(0).startPos();
				indicator.add(new Indicator(tetromino.get(0)));
			}
		}
		
		//g.drawString(""+lockDelayResets, 25, 50);
		//g.drawString(tetris.getRowsDeleted()+" row(s) cleared", 25, 50);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", Font.BOLD, 25));
		g.drawString("Score", 25, 100);
		g.drawString("High Score", 25, 50);
		String scorePrefix = "";
		String highscorePrefix = "";
		for(int i=(int)(Math.log10(tetris.getScore()+1)); i<7; i++) {
			scorePrefix += "0";
		}
		for(int i=(int)(Math.log10(highscore+1)); i<7; i++) {
			highscorePrefix += "0";
		}
		g.drawString(scorePrefix+tetris.getScore(),192,100);
		g.drawString(highscorePrefix+highscore, 192, 50);
		scorePrefix = "";
		highscorePrefix = "";
		
		if(maxseconds-seconds >= 0)
			g.drawString(maxseconds-seconds+" s",230, 750);
		else
			g.drawString("Time's up!", 210, 750);
		
		// Constantly updates the tetris board
		if(tetromino.size() > 0)
			tetris.drawDynamicTetromino(tetromino.get(0), g);
		tetris.draw(g);
		holdingbay.draw(g);
		for(Tetris i: queuebay)
			i.draw(g);
		
		// Lazy Outlines
		g.setColor(Color.WHITE);
		g.fillRect(20, 160, 80, 40);
		g.drawRect(20, 160, 80, 120);
		g.fillRect(400, 160, 80, 40);
		g.drawRect(400, 160, 80, 440);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Roboto", Font.BOLD, 20));
		g.drawString("HOLD", 32, 192);
		g.drawString("NEXT", 412, 192);

		// Score Text!
		if(scoreTimer.isRunning() && perfectClear == true) {
			g.setColor(new Color(75, 75, 75));
			g.drawString("PERFECT CLEAR!", 169, 302+120);
			g.setColor(Color.ORANGE.brighter());
			g.drawString("PERFECT CLEAR!", 167, 300+120);
		}
		else if(scoreTimer.isRunning() && tetris.getRowsDeleted() == 4 && tetris.getDidBackToBackTetris()) {
			g.setColor(new Color(75, 75, 75));
			g.drawString("BACK-TO-BACK TETRIS!", 169-35, 302+120);
			g.setColor(Color.ORANGE.brighter());
			g.drawString("BACK-TO-BACK TETRIS!", 167-35, 300+120);
		}
		else if(scoreTimer.isRunning() && tetris.getRowsDeleted() == 2) {
			g.setColor(new Color(75, 75, 75));
			g.drawString("DOUBLE!", 212, 302+120);
			g.setColor(Color.ORANGE.brighter());
			g.drawString("DOUBLE!", 210, 300+120);
		}
		
		else if(scoreTimer.isRunning() && tetris.getRowsDeleted() == 3) {
			g.setColor(new Color(75, 75, 75));
			g.drawString("TRIPLE!", 212, 302+120);
			g.setColor(Color.ORANGE.brighter());
			g.drawString("TRIPLE!", 210, 300+120);
		}
		
		else if(scoreTimer.isRunning() && tetris.getRowsDeleted() == 4) {
			g.setColor(new Color(75, 75, 75));
			g.drawString("TETRIS!", 212, 302+120);
			g.setColor(Color.ORANGE.brighter());
			g.drawString("TETRIS!", 210, 300+120);
		}

		g.drawImage(tetrisLogo.getImage(), (int)(WIDTH/2-(1000*.2)/2),(int)(HEIGHT-(HEIGHT*.21)), (int)(1000*.20), (int)(694*.20), null);
		g.setColor(Color.WHITE);
		//g.fillRect(0,0,500,500);


		// AI TEXT
		if(toggleAI) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("Gen: "+generation, 15, 400);
			g.drawString("Bot: "+(currentBotIndex+1), 15, 435);
			g.drawString("Pop: "+population, 15, 470);
		}
	}
	
	// PlaySound Method
	static void PlaySound(String filename) {
		try (InputStream in = Tetris.class.getResourceAsStream(filename)) {
			InputStream bufferedIn = new BufferedInputStream(in);
			try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Bag randomizer
	public ArrayList<Integer> newBag() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<Integer> bag1 = new ArrayList<Integer>();
		ArrayList<Integer> bag2 = new ArrayList<Integer>();
		ArrayList<Integer> bag3 = new ArrayList<Integer>();
		for(int x=1; x<=7; x++) {
			bag1.add(x);
			bag2.add(x);
			bag3.add(x);

			// bag1.add(testblock);
			// bag2.add(testblock);
			// bag3.add(testblock);
		}
		Collections.shuffle(bag1);
		Collections.shuffle(bag2);
		Collections.shuffle(bag3);
		for(int i=0; i<bag1.size(); i++)
			array.add(bag1.get(i));
		for(int i=0; i<bag2.size(); i++)
			array.add(bag2.get(i));
		for(int i=0; i<bag3.size(); i++)
			array.add(bag3.get(i));
				
		return array;
	}

	public void reset() {
		if(tetromino.size() > 0)
			tetromino.remove(0);
		if(indicator.size() > 0)
			indicator.remove(0);
		tetris.clear();
		updateQueue();
		refreshQueue();
		if(holdblock.size() > 0) {
			holdblock.get(0).deleteInt();
			holdblock.remove(0);
		}
		if(tetromino.size() == 0)
			tetromino.add(new Tetromino(tetris, queue.get(0)));
			updateQueue();
			tetromino.get(0).startPos();
		if(indicator.size() == 0)
			indicator.add(new Indicator(tetromino.get(0)));
		tetris.setScore(0);
		if(!timer.isRunning())
			timer.start();
		if(!dropTimer.isRunning())
			dropTimer.start();
		seconds = 0;
		if(timeGameOver)
			timeGameOver = false;
		holdBlock();
	}

	public void holdBlock() {
		if(holdblock.size() == 0) {
			int block = tetromino.get(0).getBlock();
			//int rPhase = tetromino.get(0).getRPhase();
			
			holdblock.add(new Tetromino(holdingbay, block));
			/*while(!(holdblock.get(0).getRPhase() == rPhase)) {
				holdblock.get(0).rotateRight();
			}*/
			if(holdblock.get(0).canMove(2))
				holdblock.get(0).move(2);
			holdblock.get(0).dynamicToStatic();
			
			tetromino.get(0).deleteInt();
			indicator.get(0).deleteInt();
			
			tetromino.clear();
			indicator.clear();
			
			//tetromino.add(new Tetromino(tetris, tetris.randomBlock()));
			tetromino.add(new Tetromino(tetris, queue.get(0)));
			updateQueue();
			tetromino.get(0).startPos();
			indicator.add(new Indicator(tetromino.get(0)));
			
			holdDisable = true;
		}
		if(holdblock.size() > 0 && !holdDisable) {
			int block = tetromino.get(0).getBlock();
			//int rPhase = tetromino.get(0).getRPhase();
			int hBlock = holdblock.get(0).getBlock();
			//int rHPhase = holdblock.get(0).getRPhase();
			
			holdblock.get(0).deleteInt();
			holdblock.clear();
			holdblock.add(new Tetromino(holdingbay, block));
			/*while(!(holdblock.get(0).getRPhase() == rPhase))
				holdblock.get(0).rotateRight();*/
			if(holdblock.get(0).canMove(2))
				holdblock.get(0).move(2);
			holdblock.get(0).dynamicToStatic();
			
			tetromino.get(0).deleteInt();
			indicator.get(0).deleteInt();
			
			tetromino.clear();
			indicator.clear();
			
			tetromino.add(new Tetromino(tetris, hBlock));
			/*while(!(tetromino.get(0).getRPhase() == rHPhase))
				tetromino.get(0).rotateRight();*/
			tetromino.get(0).startPos();
			indicator.add(new Indicator(tetromino.get(0)));
			
			holdDisable = true;
		}
	}

	//main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("Animation Shell");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new TetrisAnimation());
		frame.setVisible(true);
	}
}