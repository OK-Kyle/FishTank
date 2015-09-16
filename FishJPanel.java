import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FishJPanel extends JPanel implements MouseListener, ActionListener {

    private Fish[] movingFish;
    private int numberOfFish;
    private Timer t;
	private Rectangle titleScreen;
	private boolean isShowingTitleScreen;
	public static final Font MEDIUM_FONT = new Font("ARIAL", Font.CENTER_BASELINE, 18);
	public static final Font LARGE_FONT = new Font("ARIAL", Font.BOLD, 30);
	public static final Font HUGE_FONT = new Font("SansSerif", Font.BOLD, 55);
    
    public FishJPanel() {
        setBackground(Color.CYAN);
        titleScreen = new Rectangle(0, 0 , 800, 800);
		isShowingTitleScreen = true;
        movingFish = new Fish[30];
        numberOfFish = 0;
        addMouseListener(this);
        t = new Timer(30, this);
        t.start();
    }
    
    public void mousePressed(MouseEvent e) {
		Point mousePressed = e.getPoint();
        if(titleScreen != null && titleScreen.contains(mousePressed)) {
            titleScreen = null;
            isShowingTitleScreen = false;
        }
        Fish newFish;
        if(numberOfFish < movingFish.length) {
            Point mouseClicked = e.getPoint();
            newFish = new Fish(mouseClicked);
            movingFish[numberOfFish] = newFish;
            numberOfFish++;
        }
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		drawTitleScreen(g);
        
        for(int i = 0; i < numberOfFish; i++) {
            if(movingFish[i] != null) {
                movingFish[i].draw(g);
            }
        }
        
    }
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < numberOfFish; i++) {
            if(movingFish[i] != null) {
                movingFish[i].move();
            }
        }
        repaint();
    }
    
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
	private void drawTitleScreen(Graphics g) {
        if(titleScreen != null && isShowingTitleScreen == true) {
            g.setColor(Color.CYAN);
            g.fillRect(titleScreen.x, titleScreen.y, titleScreen.width, titleScreen.height);
            g.setColor(Color.PINK);
            g.setFont(LARGE_FONT);
            g.drawString("Olivia Kyle's Fish Tank!", 0, 30);
            g.setColor(Color.BLUE);
            g.setFont(MEDIUM_FONT);
            g.drawString("Draws randomly generated fish at a click!", 30, 60);
			g.drawString("Click anywhere in the game area to start", 30, 80);
        }
    }
 
}
