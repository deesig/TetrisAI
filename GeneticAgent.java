import java.util.ArrayList;

public class GeneticAgent {
    double wBump, wHoles, wScore, wAggregateHeight, wB2BT, wTetris, fitness, wTime, time;
    private final double MUTATION_RATE = 0.2;
    private Tetris board;
    // private static int test = 0;

    public GeneticAgent(Tetris board){
        // Weights
        wBump = (-1)*Math.random();
        wHoles = (-1)*Math.random();
        wAggregateHeight = (-1)*Math.random();// - 10;
        wScore = Math.random();// + 100000000;
        wB2BT = Math.random();
        wTetris = Math.random();
        wTime = Math.random();

        // Tetris
        this.board = board;
        // Fitness
        fitness = Double.MIN_VALUE;
        time = 0;
    }

    public int[] getBestMove(Tetromino piece){
        int[] move = piece.getMoveList().get(getBestMoveIndex(piece));
        // System.out.println("Best Move Index: "+index+", Score: "+moveScores[index]);

        return move;
    }
    
    public int getBestMoveIndex(Tetromino piece){
        int size = piece.getNumMoves();
        double[] moveScores = new double[size];
        
        for(int i=0; i<size; i++) {
            moveScores[i] = tryMove(piece, i);
            // System.out.println("Move Index: "+i+", Score: "+moveScores[i]);
            // System.out.println();
        }

        int index = 0;
        double max = moveScores[0];
        for(int i = 0; i < size; i++){
            if(max < moveScores[i]){
                max = moveScores[i];
                index = i;
            }
        }

        return index;
    }

    public boolean compareMove(Tetromino piece1, Tetromino piece2){
        double score1 = tryMove(piece1, getBestMoveIndex(piece1));
        double score2 = tryMove(piece2, getBestMoveIndex(piece2));

        if(score1 >= score2){
            return true;
        } else {
            return false;
        }
    }

    public double tryMove(Tetromino piece, int move) {
        double score = 0;
        Tetris copy = new Tetris();
        copy.setGrid(board.getGridCopy());
        // if(test <= 3) {
        //     board.printBoard();
        //     copy.printBoard();
        //     test++;
        // }

        Tetromino simTetromino = new Tetromino(copy, piece.getBlock());

        simTetromino.simulateMove(move);

        score += wBump * copy.getBumpiness();

        score += wHoles * copy.getHoles();

        score += wScore * copy.getScore();

        score += wAggregateHeight * copy.getAggregateHeight();

        score += wB2BT * copy.didBackToBackTetris();

        score += wTetris * copy.didATetris();


        // System.out.println("Bumpiness: "+wBump * copy.getBumpiness());
        // System.out.println("Holes: "+wHoles * copy.getHoles());
        // System.out.println("Score: "+wScore * copy.getScore());
        // System.out.println("Height: "+wAggregateHeight * copy.getAggregateHeight());
        // System.out.println("wB2BT: "+wB2BT * copy.didBackToBackTetris());
        // System.out.println("Tetris: "+wTetris * copy.didATetris());

        return score;
    }

    public double rateBoard() {
        double score = 0;

        score += wBump * board.getBumpiness();
        score += wHoles * board.getHoles();
        score += wScore * board.getScore();
        score += wAggregateHeight * board.getAggregateHeight();
        score += wB2BT * board.didBackToBackTetris();
        score += wTetris * board.didATetris();
        score += wTime * time;

        return score;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public double getFitness() {
        return fitness;
    }
    /*
    public double getFitness(){
        double score = 0;

        score += wBump * board.getBumpiness();
        score += wHoles * board.getHoles();
        score += wScore * board.getScore();
        score += wAggregateHeight * board.getAggregateHeight();
        score += wB2BT * board.didBackToBackTetris();
        score += wTetris * board.didATetris();

        return score;
    }
    */

    public static GeneticAgent getBestBot(GeneticAgent[] arr) {
        ArrayList<GeneticAgent> list = new ArrayList<GeneticAgent>();
        for(int i = 0; i < arr.length; i++){
            list.add(arr[i]);
        }
        double max = list.get(0).getFitness();
        GeneticAgent best = list.get(0);
        for(int i = 0; i < list.size(); i++){
            if(max < list.get(i).getFitness()){
                max = list.get(i).getFitness();
                best = list.get(i);
            }
        }
        return best;
    }

    public static GeneticAgent darwinism(GeneticAgent[] arr) {
        ArrayList<GeneticAgent> list = new ArrayList<GeneticAgent>();
        for(int i = 0; i < arr.length; i++){
            list.add(arr[i]);
        }

        double max = list.get(0).getFitness();
        GeneticAgent best = list.get(0);
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(max < list.get(i).getFitness()){
                max = list.get(i).getFitness();
                best = list.get(i);
                index = i;
            }
        }

        //list.remove(index);
        GeneticAgent secondBest = list.get(0);
        max = list.get(0).getFitness();
        for(int i = 0; i < list.size(); i++){
            if(max < list.get(i).getFitness() && i != index){
                max = list.get(i).getFitness();
                secondBest = list.get(i);
            }
        }

        return best.cross(secondBest);
    }

    public GeneticAgent cross(GeneticAgent partner){
        GeneticAgent child = new GeneticAgent(board);
        
        // Randomizes which characteristics are inherited by which parent

        // Bumpiness
        int random = (int)(Math.random() * 2);
        if(random == 0){
            child.wBump = partner.wBump;
        } else {
            child.wBump = this.wBump;
        }

        // Holes
        random = (int)(Math.random() * 2);
        if(random == 0){
            child.wHoles = partner.wHoles;
        } else {
            child.wHoles = this.wHoles;
        }

        // Score
        random = (int)(Math.random() * 2);
        if(random == 0){
            child.wScore = partner.wScore;
        } else {
            child.wScore = this.wScore;
        }

        // Aggregate Height
        random = (int)(Math.random() * 2);
        if(random == 0){
            child.wAggregateHeight = partner.wAggregateHeight;
        } else {
            child.wAggregateHeight = this.wAggregateHeight;
        }

        // Back-To-Back Tetris
        random = (int)(Math.random() * 2);
        if(random == 0){
            child.wB2BT = partner.wB2BT;
        } else {
            child.wB2BT = this.wB2BT;
        }

        // Tetris
        random = (int)(Math.random() * 2);
        if(random == 0){
            child.wTetris = partner.wTetris;
        } else {
            child.wTetris = this.wTetris;
        }
        
        random = (int)(Math.random() * 2);
        if(random == 0){
            child.wTime = partner.wTime;
        } else {
            child.wTime = this.wTime;
        }

        
        // Adds randomness to certain characteristics based on mutation rate with a range from [-0.2, 0.2]
        double dRand = Math.random();
        if(dRand < MUTATION_RATE){
            child.wBump += (Math.random()*0.40)-0.2;
        }

        dRand = Math.random();
        if(dRand < MUTATION_RATE){
            child.wScore += (Math.random()*0.40)-0.2;
        }

        dRand = Math.random();
        if(dRand < MUTATION_RATE){
            child.wHoles += (Math.random()*0.40)-0.2;
        }

        dRand = Math.random();
        if(dRand < MUTATION_RATE){
            child.wAggregateHeight += (Math.random()*0.40)-0.2;
        }

        dRand = Math.random();
        if(dRand < MUTATION_RATE){
            child.wB2BT += (Math.random()*0.40)-0.2;
        }

        dRand = Math.random();
        if(dRand < MUTATION_RATE){
            child.wTetris += (Math.random()*0.40)-0.2;
        }

        if(dRand < MUTATION_RATE){
            child.wTime += (Math.random()*0.40)-0.2;
        }

/*
        System.out.println("\n----------------------------Parent 1 Scores: ----------------------------");
        System.out.println("wBump: " + wBump);
        System.out.println("wHoles: " + wHoles);
        System.out.println("wAggregateHeight: " + wAggregateHeight);
        System.out.println("wScore: " + wScore);
        System.out.println("wB2BT: " + wB2BT);
        System.out.println("wTetris: " + wTetris);
        System.out.println("wTime: " + wTime);

        System.out.println("\n----------------------------Parent 2 Scores: ----------------------------");
        System.out.println("wBump: " + partner.wBump);
        System.out.println("wHoles: " + partner.wHoles);
        System.out.println("wAggregateHeight: " + partner.wAggregateHeight);
        System.out.println("wScore: " + partner.wScore);
        System.out.println("wB2BT: " + partner.wB2BT);
        System.out.println("wTetris: " + partner.wTetris);
        System.out.println("wTime: " + partner.wTime);


        System.out.println("\n----------------------------Child Scores: ----------------------------");
        System.out.println("wBump: " + child.wBump);
        System.out.println("wHoles: " + child.wHoles);
        System.out.println("wAggregateHeight: " + child.wAggregateHeight);
        System.out.println("wScore: " + child.wScore);
        System.out.println("wB2BT: " + child.wB2BT);
        System.out.println("wTetris: " + child.wTetris);
        System.out.println("wTime: " + child.wTime);
*/
        return child;
    }

}
