package snake.src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Joshua
 */
public class SnakeGameFrame extends JFrame {
    private SnakeGameApplet gameApplet = new SnakeGameApplet();
    
    private JPanel SCORE_PANEL = new JPanel();
    
    private final JMenuBar MENU_BAR = new JMenuBar();
    
    private final JMenu FILE_MENU = new JMenu("File");
    
    private final JMenuItem NEW_GAME_MENU_ITEM = new JMenuItem("New Game");
    private final JMenuItem EXIT_GAME_MENU_ITEM = new JMenuItem("Exit");
    
    public SnakeGameFrame() {
        setTitle("Snake");
        setSize(507, 513);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        
        initComponents();
        
        gameApplet.start();
        
        setVisible(true);
    }
    
    private void initComponents() {
        
        NEW_GAME_MENU_ITEM.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        EXIT_GAME_MENU_ITEM.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        
        
        NEW_GAME_MENU_ITEM.addActionListener(new MenuItemListener());
        EXIT_GAME_MENU_ITEM.addActionListener(new MenuItemListener());
        
        FILE_MENU.add(NEW_GAME_MENU_ITEM);
        FILE_MENU.addSeparator();
        FILE_MENU.add(EXIT_GAME_MENU_ITEM);
        
        MENU_BAR.add(FILE_MENU);
        
        setJMenuBar(MENU_BAR);
        
        add(SCORE_PANEL, BorderLayout.NORTH);
        add(gameApplet, BorderLayout.CENTER);
        
        gameApplet.init();
    }
    
    class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(NEW_GAME_MENU_ITEM)) {
                gameApplet.resetGame();
            }
            else if (e.getSource().equals(EXIT_GAME_MENU_ITEM)) {
                SnakeGameFrame.this.dispose();
                System.exit(0);
            }
        }
    }
    
    public static void main(String[] args) {
        new SnakeGameFrame();
    }
}
