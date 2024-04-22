// Austin Trinh & Arjun Rao
// Mr. Randall
// 5.20.23
// TetrisAI.java

// AI is used to simulate all possible moves for one tetromino and return the moveset that has the highest score
public class TetrisAI {

    // Fields
    private Tetris board;

    // Constructor
    public TetrisAI(Tetris tetris) {
        board = tetris;
    }

    public void tryMove(Tetromino tetromino) {
        int block = tetromino.getBlock();
        Tetris copy = board;
        copy.printBoard();
        Tetromino simTetromino = new Tetromino(copy, block);
        simTetromino.simulateMove(16);
        System.out.println();
        copy.printBoard();
        System.out.println();
        for(int i=0; i<simTetromino.getMoveList().get(0).length; i++) {
            System.out.print(simTetromino.getMoveList().get(0)[i]);
        }
    }
}
