/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalai;

import java.util.ArrayList;

/**
 *
 * @author Kathornhill
 */
public class NimGameState extends GameState<NimGameState, NimMove> {
    
    // Fields
    public int[] board;
    
    public NimGameState(int[] startBoard, boolean startTurn) {
        this.board = startBoard;
        this.turn = startTurn;
    }
    
    // Abstract Methods
    public NimGameState doMove(NimMove m) {
        int[] newBoard = new int[3];
        for (int i = 0; i <=2; i++) {
            newBoard[i] = this.board[i];
        }
        boolean newTurn = true;
        newTurn = newTurn && this.turn;
        NimGameState gs = new NimGameState(newBoard, !newTurn);
        gs.board[m.level - 1] -= m.numSticks;
        return gs;
    }
    
    public boolean gameOver() {
         return this.board[0] == 0 && this.board[1] == 0 && this.board[2] == 0;
    }
    
    public ArrayList<NimMove> legalMoves() {
        ArrayList moveList = new ArrayList(15);
        for (int i = 1; i <= this.board[0]; i++) {
            moveList.add(new NimMove(1,i));
        }
        for (int j = 1; j <= this.board[1]; j++) {
            moveList.add(new NimMove(2,j));
        }
        for (int k = 1; k <= this.board[2]; k++) {
            moveList.add(new NimMove(3,k));
        }
        return moveList;   
    }
}