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
public abstract class GameState<T, T1> {

    // Fields
    boolean turn;
    
    // Abstract Methods
    abstract T doMove(T1 m); 
    
    abstract boolean gameOver();
    
    abstract ArrayList<T1> legalMoves();
     
}
