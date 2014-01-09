package snake.src;

/**
 *
 * @author Joshua
 */
public class Score {
    int score;
    
    public Score() {
        score = 0;
    }
    
    public void reset() {
        score = 0;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
