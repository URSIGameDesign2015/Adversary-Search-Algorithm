package generalai;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegLangGameState extends GameState<RegLangGameState, RegLangMove> {
    
    public String[] compAlphabet;
    public String[] playerAlphabet;
    public Pattern goal;
    public String lettersPlayed;
    
    public RegLangGameState(String[] CompAlpha, String[] PlayerAlpha, Pattern p, String lp) {
        this.compAlphabet = CompAlpha;
        this.playerAlphabet = PlayerAlpha;
        this.goal = p;
        this.lettersPlayed = lp;
    }
    
    public RegLangGameState doMove(RegLangMove move) {
        String lp = this.lettersPlayed + move.nextLetter;
        RegLangGameState newGame = new RegLangGameState(this.compAlphabet, this.playerAlphabet, this.goal, lp);
        newGame.turn = !this.turn;
        return newGame;
    }
    
    public boolean gameOver() {
        Matcher m = this.goal.matcher(lettersPlayed);
        if (m.matches()) {
            return true;
        } else if (!m.hitEnd()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean findMatch() {
        Matcher m = this.goal.matcher(lettersPlayed);
        return m.matches();
    }
    
    public ArrayList<RegLangMove> legalMoves() {
        ArrayList moveList = new ArrayList(100);
        if (this.turn) {
            for (int i = 0; i < this.compAlphabet.length; i++) {
                moveList.add(new RegLangMove(this.compAlphabet[i]));
            }
        return moveList;
        }
        else {
            for (int i = 0; i < this.playerAlphabet.length; i++) {
                moveList.add(new RegLangMove(this.playerAlphabet[i]));
            }
            return moveList;
        }
    }
}