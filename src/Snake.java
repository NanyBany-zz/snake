package snake.src;

import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public class Snake {

    private ArrayList<SnakeBody> snakeBody;

    public Snake() {
        
    }
    
    public void addSnakeBody() {
        snakeBody.add(new SnakeBody());
    }
    
    public void reset() {
        
    }
    
    public ArrayList<SnakeBody> getSnakeBody() {
        return snakeBody;
    }
    
    public void setSize(int size) {
        snakeBody = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            snakeBody.add(new SnakeBody());
        }
    }
}
