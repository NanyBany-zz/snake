package snake.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Joshua
 */
public class AppletPanel extends JPanel implements ActionListener, KeyListener {
    Timer TIMER = new Timer(140, this);

    enum Direction {

        NORTH, EAST, SOUTH, WEST
    };
    Direction direction;
    
    Snake SNAKE = new Snake();
    Score SCORE = new Score();
    Apple APPLE = new Apple();
    
    boolean canMove = true;
    boolean gameOver = false;

    public AppletPanel() {
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);
        addKeyListener(this);
    }
    
    public void startGame() {
        SNAKE.setSize(3);
        TIMER.start();
        for (int i = 0; i < SNAKE.getSnakeBody().size(); i++) {
            SNAKE.getSnakeBody().get(i).setBounds(225, 225, 25, 25);
        }
        updateApple();
    }

    public void resetGame() {
        direction = null;
        gameOver = false;
        SNAKE.setSize(3);
        for (int i = 0; i < SNAKE.getSnakeBody().size(); i++) {
            SNAKE.getSnakeBody().get(i).setBounds(225, 225, 25, 25);
        }
        updateApple();
        TIMER.restart();
        TIMER.start();
    }
    
    private void updateSnake() {
        if (direction != null) {
            int x, y, tempX, tempY;
            
            switch (direction) {
                case NORTH:
                    x = (int)SNAKE.getSnakeBody().get(0).getX();
                    y = (int)SNAKE.getSnakeBody().get(0).getY();
                    
                    SNAKE.getSnakeBody().get(0).setLocation(
                            (int)SNAKE.getSnakeBody().get(0).getX(),
                            (int)SNAKE.getSnakeBody().get(0).getY() - 25);
                    for (int i = 1; i < SNAKE.getSnakeBody().size(); i++) {
                        tempX = (int)SNAKE.getSnakeBody().get(i).getX();
                        tempY = (int)SNAKE.getSnakeBody().get(i).getY();
                        
                        SNAKE.getSnakeBody().get(i).setLocation(x, y);
                        
                        x = tempX;
                        y = tempY;
                    }
                    break;
                case EAST:
                    x = (int)SNAKE.getSnakeBody().get(0).getX();
                    y = (int)SNAKE.getSnakeBody().get(0).getY();
                    
                    SNAKE.getSnakeBody().get(0).setLocation(
                            (int)SNAKE.getSnakeBody().get(0).getX() + 25,
                            (int)SNAKE.getSnakeBody().get(0).getY());
                    for (int i = 1; i < SNAKE.getSnakeBody().size(); i++) {
                        tempX = (int)SNAKE.getSnakeBody().get(i).getX();
                        tempY = (int)SNAKE.getSnakeBody().get(i).getY();
                        
                        SNAKE.getSnakeBody().get(i).setLocation(x, y);
                        
                        x = tempX;
                        y = tempY;
                    }
                    break;
                case SOUTH:
                    x = (int)SNAKE.getSnakeBody().get(0).getX();
                    y = (int)SNAKE.getSnakeBody().get(0).getY();
                    
                    SNAKE.getSnakeBody().get(0).setLocation(
                            (int)SNAKE.getSnakeBody().get(0).getX(),
                            (int)SNAKE.getSnakeBody().get(0).getY() + 25);
                    for (int i = 1; i < SNAKE.getSnakeBody().size(); i++) {
                        tempX = (int)SNAKE.getSnakeBody().get(i).getX();
                        tempY = (int)SNAKE.getSnakeBody().get(i).getY();
                        
                        SNAKE.getSnakeBody().get(i).setLocation(x, y);
                        
                        x = tempX;
                        y = tempY;
                    }
                    break;
                case WEST:
                    x = (int)SNAKE.getSnakeBody().get(0).getX();
                    y = (int)SNAKE.getSnakeBody().get(0).getY();
                    
                    SNAKE.getSnakeBody().get(0).setLocation(
                            (int)SNAKE.getSnakeBody().get(0).getX() - 25,
                            (int)SNAKE.getSnakeBody().get(0).getY());
                    for (int i = 1; i < SNAKE.getSnakeBody().size(); i++) {
                        tempX = (int)SNAKE.getSnakeBody().get(i).getX();
                        tempY = (int)SNAKE.getSnakeBody().get(i).getY();
                        
                        SNAKE.getSnakeBody().get(i).setLocation(x, y);
                        
                        x = tempX;
                        y = tempY;
                    }
                    break;
            }
            for (int i = 1; i < SNAKE.getSnakeBody().size(); i++) {
                if (SNAKE.getSnakeBody().get(0)
                        .intersects(SNAKE.getSnakeBody().get(i))) {
                    TIMER.stop();
                    gameOver = true;
                }
            }
            if (SNAKE.getSnakeBody().get(0).intersects(APPLE)) {
                updateApple();
                SNAKE.addSnakeBody();
                SNAKE.getSnakeBody().get(SNAKE.getSnakeBody().size() - 1)
                        .setSize(25, 25);
                SNAKE.getSnakeBody().get(SNAKE.getSnakeBody().size() - 1)
                        .setLocation(
                                (int)SNAKE.getSnakeBody().get(
                                        SNAKE.getSnakeBody().size() - 2).getX(),
                                (int)SNAKE.getSnakeBody().get(
                                        SNAKE.getSnakeBody().size() - 2).getY());
            }
            else if ((int)SNAKE.getSnakeBody().get(0).getX() < 0 ||
                    (int)SNAKE.getSnakeBody().get(0).getX() > 500 ||
                    (int) SNAKE.getSnakeBody().get(0).getY() < 0 ||
                    (int)SNAKE.getSnakeBody().get(0).getY() > 450) {
                TIMER.stop();
                gameOver = true;
            }
        }
    }
    
    private void updateScore() {
        SCORE.setScore(SCORE.getScore() + 25);
    }
    
    private void updateApple() {
        Random rand = new Random();
        int x = rand.nextInt(476);
        int y = rand.nextInt(426);
        while (x % 25 != 0 || y % 25 != 0) {
            if (x % 25 != 0) {
                x = rand.nextInt(476);
            }
            if (y % 25 != 0) {
                y = rand.nextInt(426);
            }
        }
        
        APPLE.setBounds(x, y, 25, 25);
        
        /**
         * Check to see if any of the coordinates for the snake are the same
         * as the apple coordinates, if so then generate new coordinates
         * for the apple, until an empty slot is found.
         */
        for (int i = 0; i < SNAKE.getSnakeBody().size(); i++) {
            if (SNAKE.getSnakeBody().get(i).intersects(APPLE)) {
                System.out.println("Debug: Same coordinates");
                updateApple();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < SNAKE.getSnakeBody().size(); i++) {
            if (i == 0) {
                g.setColor(Color.RED);
                g.fillRect((int)SNAKE.getSnakeBody().get(i).getX(),
                    (int)SNAKE.getSnakeBody().get(i).getY(),
                    SNAKE.getSnakeBody().get(i).width,
                    SNAKE.getSnakeBody().get(i).height);
            }
            else {
            g.setColor(Color.BLACK);
            g.fillRect((int)SNAKE.getSnakeBody().get(i).getX(),
                    (int)SNAKE.getSnakeBody().get(i).getY(),
                    SNAKE.getSnakeBody().get(i).width,
                    SNAKE.getSnakeBody().get(i).height);
            }
        }
        
        g.setColor(Color.GREEN);
        g.fillRect(APPLE.x, APPLE.y, APPLE.width, APPLE.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateSnake();
        repaint();
        canMove = true;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyChar() == 'w' &&
                direction != Direction.SOUTH &&
                canMove) {
            direction = Direction.NORTH;
            canMove = false;
        } else if (ke.getKeyChar() == 'd' &&
                direction != Direction.WEST &&
                canMove) {
            direction = Direction.EAST;
            canMove = false;
        } else if (ke.getKeyChar() == 's' &&
                direction != Direction.NORTH &&
                canMove) {
            direction = Direction.SOUTH;
            canMove = false;
        } else if (ke.getKeyChar() == 'a' &&
                direction != Direction.EAST &&
                canMove) {
            direction = Direction.WEST;
            canMove = false;
        }
        if (ke.getKeyChar() == 'p') {
            TIMER.stop();
        }
        else if (ke.getKeyChar() == 'u' &&
                !gameOver) {
            TIMER.start();
        }
        if (ke.isControlDown() && ke.getKeyChar() == 'n') {
            System.out.println("Debug: ctrl is down");
            resetGame();
        }
        else if (ke.isControlDown() && ke.getKeyChar() == 'e') {
            System.exit(0);
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
