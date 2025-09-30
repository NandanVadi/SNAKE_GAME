import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame extends Frame {
    String str = "S";
    Random rand = new Random();
    int ax = ThreadLocalRandom.current().nextInt(100, 400);
    int ay = ThreadLocalRandom.current().nextInt(100, 400);
    int sx = 300;
    int sy = 300;
    boolean GameOver = false;
    int dx = 0, dy = 0;
    int score = 0;

    SnakeGame() {

        setSize(700, 700);
        setVisible(true);
        repaint();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (!GameOver) {
                    sx += dx;
                    sy += dy;
                    touch();

                    //restart after 2 seconds

                    if (sx < 0 || sx > getWidth() - 20 || sy < 40 || sy > getHeight() - 40) {
                        GameOver = true;

                        new Timer().schedule(new TimerTask() {
                            public void run() {
                                restart();
                            }
                        }, 2000);
                    }

                    repaint();
                }
            }
        }, 0, 100);

        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (GameOver)
                    return;
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) {
                    dx = 0;
                    dy = -10;
                } else if (key == KeyEvent.VK_DOWN) {
                    dx = 0;
                    dy = 10;
                } else if (key == KeyEvent.VK_LEFT) {
                    dx = -10;
                    dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    dx = 10;
                    dy = 0;
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void restart() {
        sx = 300;
        sy = 300;
        dx = 0;
        dy = 0;
        str = "S";
        ax = ThreadLocalRandom.current().nextInt(100, 400);
        ay = ThreadLocalRandom.current().nextInt(100, 400);
        score = 0;
        GameOver = false;
        repaint();
    }

    public void paint(Graphics g) {

        if (!GameOver) {
            g.drawString(str, sx, sy);
            g.drawString("A", ax, ay);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + score, 20, 60);

        } else {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Game is Completed ", getWidth() / 4, getHeight() / 2);
            g.drawString("Final Score: " + score, getWidth() / 4, getHeight() / 2 + 30);
            System.out.print("Game Over");
        }
    }

    public void touch() {
        if (Math.abs(sx - ax) < 10 && Math.abs(sy - ay) < 10) {
            score++;
            if (str.equals("S"))
                str = "SN";
            else if (str.equals("SN"))
                str = "SNA";
            else if (str.equals("SNA"))
                str = "SNAK";
            else if (str.equals("SNAK"))
                str = "SNAKE";
            ax = ThreadLocalRandom.current().nextInt(100, 400);
            ay = ThreadLocalRandom.current().nextInt(100, 400);

            if (str.equals("SNAKE")) {
                ax = rand.nextInt(getWidth() - 50) + 25;
                ay = rand.nextInt(getHeight() - 50) + 50;
                GameOver = true;
            }

        }
    }

    public static void main(String[] args) {

        SnakeGame snake = new SnakeGame();
    }
}