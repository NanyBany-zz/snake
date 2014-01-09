package snake.src;

import java.awt.GridLayout;
import javax.swing.JApplet;

/**
 *
 * @author Joshua
 */
public class SnakeGameApplet extends JApplet {

    private final AppletPanel PANEL = new AppletPanel();
    
    public SnakeGameApplet() {
        setLayout(new GridLayout(1, 0));
        
        init();
        
        add(PANEL);
    }
    
    @Override
    public void start() {
        PANEL.startGame();
    }
    
    public void resetGame() {
        PANEL.resetGame();
    }
}
