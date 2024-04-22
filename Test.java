// Austin & Arjun

public class Test {
    public static void main(String[] args) {
        
        Tetris tetris = new Tetris();
        //tetris.printBoard();

        Tetromino test = new Tetromino(tetris, 1);
        //System.out.println();
        //tetris.printBoard();


        TetrisAI AI = new TetrisAI(tetris);
        AI.tryMove(test);
    }
}
