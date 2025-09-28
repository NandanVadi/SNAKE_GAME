import java.awt.*;
import java.util.Random;
import java.awt.event.*;
public class SnakeGame extends Frame {
        String str = "S";
        Random rand = new Random();
        int ax = rand.nextInt(300);
        int ay = rand.nextInt(300);
        int sx = 300;
        int sy = 300;
        boolean GameOver = false;
    SnakeGame(){
        
        
        setSize(700,700);
        setVisible(true);
        repaint();

        addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                if(GameOver) return;
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_UP){sy = sy - 10;}
                else if(key == KeyEvent.VK_DOWN){sy = sy + 10;}
                else if(key == KeyEvent.VK_LEFT){sx = sx - 10;}
                else if(key == KeyEvent.VK_RIGHT){sx = sx + 10;}
                touch();
                repaint();
            }
            public void keyReleased(KeyEvent e){}
            public void keyTyped(KeyEvent e){}
        });
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            dispose();
        }
    });
}
       public void paint(Graphics g){
            
            if(!GameOver){
                g.drawString(str,sx,sy);
                g.drawString("A",ax,ay);
            }
            else{
                g.setFont(new Font("Arial",Font.BOLD,20));
                g.drawString("Game is Completed ",getWidth()/4,getHeight()/2);
                System.out.print("Game Over");
            }
       }
 
       public void touch(){
        if(Math.abs(sx-ax) < 10 && Math.abs(sy-ay) < 10){
            if(str.equals("S")) str = "SN";
            else if(str.equals("SN")) str = "SNA";
            else if(str.equals("SNA")) str = "SNAK";
            else if(str.equals("SNAK")) str = "SNAKE";
            else if(str.equals("SNAKE")) 
            {
                GameOver = true;
            }
            ax = rand.nextInt(getWidth() - 50) + 25;
            ay = rand.nextInt(getHeight() - 50) + 50;
        }
       }
public static void main(String[] args){
    SnakeGame snake = new SnakeGame();
}
}
