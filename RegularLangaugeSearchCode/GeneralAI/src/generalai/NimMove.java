/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalai;

/**
 *
 * @author Kathornhill
 */
public class NimMove implements Move {
    
    // fields
    public int level;
    public int numSticks;
    
    // methods
    public NimMove(int newLevel, int newNumSticks) {
        this.level = newLevel;
        this.numSticks = newNumSticks;
    }
}
