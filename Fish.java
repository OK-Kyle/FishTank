import java.awt.*;

public class Fish {

    public static final int BASIC_FISH_SIZE = 20;
    
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    
    private Rectangle fishArea;
    private int direction;
    private int speed;
    private Color color;
    private Color secondColor;
       
    public Fish(Point p) {
        int size = (int)(Math.random() * 80 + 20);
        fishArea = new Rectangle(p.x - size/2, p.y - size/6, size, size/3);
        direction = (int)(Math.random()*2);
        this.speed = (int)(Math.random() * 10 + 1);
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256); 
        int b = (int)(Math.random() * 256);
        color = new Color(r,g,b);
        r = (int)(Math.random() * 256);
        g = (int)(Math.random() * 256); 
        b = (int)(Math.random() * 256);
        secondColor = new Color(r,g,b);
        
    }
    
    public void move() {
        if (direction == LEFT) {
            fishArea.x = fishArea.x - speed;
            if (fishArea.x < 0) {
                fishArea.x = 0;
                direction = RIGHT;
            }
        } else {
            fishArea.x = fishArea.x + speed;
            if (fishArea.x + fishArea.width  > 800) {
                fishArea.x = 800 - fishArea.width;
                direction = LEFT;
            }
            
        }           
    }
    
    public void speedUp() {
        speed = speed + 2;
    }
    
    public void slowDown() {
        if (speed >= 4) {
            speed = speed - 2;
        }
    }
    
    public void draw(Graphics g) {
        drawTail(g);
        drawBody(g);
        drawCentre(g);
        drawMouth(g);
        drawEye(g);
    }
    
    private void drawBody(Graphics g) {
        g.setColor(color);
        g.fillOval(fishArea.x, fishArea.y, fishArea.width, fishArea.height);
        
        g.setColor(Color.BLACK);
        g.drawOval(fishArea.x, fishArea.y, fishArea.width, fishArea.height);
        g.drawOval(fishArea.x+1, fishArea.y+1, fishArea.width-2, fishArea.height-2);
    }
    
    private void drawEye(Graphics g) {
        int fishRight = fishArea.x + fishArea.width;
        int fishLeft = fishArea.x;
        int posX = fishArea.x + fishArea.width * 2 / 3;
        int posY = fishArea.y + fishArea.height / 5;
        int eyeSize = fishArea.width / 6;
        int rimSize = eyeSize / 3;
        Point eyePoint = new Point(posX, posY);
        
        if (direction == LEFT) {
            posX = fishRight - fishArea.width * 2 / 3 - eyeSize;
            eyePoint = new Point(posX, posY);
        }
        
        g.setColor(Color.WHITE);
        g.fillOval(eyePoint.x, eyePoint.y, eyeSize, eyeSize);
        
        g.setColor(Color.BLACK);
        g.fillOval(eyePoint.x + rimSize / 2, eyePoint.y + rimSize / 2, eyeSize - + rimSize, eyeSize - + rimSize);
    }
    
    private void drawTail(Graphics g) {
        int fishRight = fishArea.x + fishArea.width;
        int fishLeft = fishArea.x;
        int midY = fishArea.y + fishArea.height / 2;
        int size = fishArea.width;
        int tailLength = size / 2;
        Point tailPoint = new Point(fishRight - size / 6, midY);
        
        g.setColor(Color.BLACK);
        if (direction == LEFT) {
            tailPoint = new Point(fishLeft + size / 6, midY);
            g.drawLine(tailPoint.x, midY, tailPoint.x + tailLength * 2, midY);
            
            if (size > BASIC_FISH_SIZE * 2) {
                g.drawLine(tailPoint.x, midY, tailPoint.x + tailLength * 2, midY + tailLength / 5);
                g.drawLine(tailPoint.x, midY, tailPoint.x + tailLength * 2, midY - tailLength / 5);
            }
            
            g.drawLine(tailPoint.x, midY, tailPoint.x + tailLength * 2, midY + tailLength / 8);
            g.drawLine(tailPoint.x, midY, tailPoint.x + tailLength * 2, midY - tailLength / 8);
        } else {
            g.drawLine(tailPoint.x, midY, tailPoint.x - tailLength * 2, midY);
            if (size > BASIC_FISH_SIZE * 2) {
                g.drawLine(tailPoint.x, midY, tailPoint.x - tailLength * 2, midY + tailLength / 5);
                g.drawLine(tailPoint.x, midY, tailPoint.x - tailLength * 2, midY - tailLength / 5);
            }
            
            g.drawLine(tailPoint.x, midY, tailPoint.x - tailLength * 2, midY + tailLength / 8);
            g.drawLine(tailPoint.x, midY, tailPoint.x - tailLength * 2, midY - tailLength / 8);
        }
        
    }
    
    private void drawMouth(Graphics g) {
        int fishRight = fishArea.x + fishArea.width;
        int mouthY = fishArea.y + fishArea.height / 2;
        int mouthLeft = fishArea.x + 2;
        int mouthSize = fishArea.width / 8;
        int mouthRight;
        
        if (direction == RIGHT) {
            mouthLeft = fishRight - mouthSize - 2;
        }
        mouthRight = mouthLeft + mouthSize;
        
        g.setColor(Color.BLACK);
        g.drawLine(mouthLeft, mouthY, mouthRight, mouthY);
        g.drawLine(mouthLeft, mouthY+2, mouthRight, mouthY+2);
        g.setColor(Color.WHITE);
        g.drawLine(mouthLeft, mouthY+1, mouthRight, mouthY+1);
    }
    
    private void drawCentre(Graphics g) {
        Rectangle fishCentre = getFishCentreArea();
        
        g.setColor(secondColor);
        g.fillOval(fishCentre.x, fishCentre.y, fishCentre.width, fishCentre.height);
        g.setColor(Color.BLACK);
        g.drawOval(fishCentre.x, fishCentre.y, fishCentre.width, fishCentre.height);
    }
    
    public Rectangle getFishCentreArea() {
        Rectangle fishCentre = new Rectangle(fishArea);
        fishCentre.x = fishCentre.x + fishCentre.width  / 4;
        fishCentre.y = fishCentre.y + fishCentre.height  / 6;
        fishCentre.width = fishCentre.width / 2;
        fishCentre.height = fishCentre.height * 2 / 3;
        
        if (fishCentre.height < 4) {
            fishCentre = new Rectangle(fishArea);
        }
        
        return fishCentre;
    }
    
    public Rectangle getFishArea() {
        return fishArea;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public int getWidth() {
        return fishArea.width;
    }


}
