package MyRandom.FishTankGameMouse;

import com.sun.opengl.util.GLUT;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class EventListener implements GLEventListener, KeyListener {

    private final double xMin = -350.0;
    private final double xMax = 350.0;
    private final double yMin = -350.0;
    private final double yMax = 350.0;

    private final int maxBalls = 10;

    private MainFish player = new MainFish();
    private ArrayList<BallFish> balls = new ArrayList<>();
    private boolean youWin = false;

    private boolean[] keys = new boolean[4];

    private GLUT glut = new GLUT();

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0, 0.6f, 1, 1);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(xMin, xMax, yMin, yMax, -1, 1);

        player.x = 0;
        player.y = 0;
        player.size = 30;

        for (int i = 0; i < maxBalls; i++) {
            double x = randNum(xMin + 50, yMax - 50);
            double y = randNum(yMin + 50, yMax - 50);
            BallFish b = new BallFish(x, y);
            balls.add(b);
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        if (!youWin) {
            updatePlayer();
            updateBalls(gl);
        }

        player.draw(gl);
        checkWin();
        drawWinText(gl);

    }

    private void updatePlayer() {
        if (youWin) return;

        double speed = 4.0;
        if (keys[0]) player.moveBy(0, speed);
        if (keys[1]) player.moveBy(0, -speed);
        if (keys[2]) player.moveBy(-speed, 0);
        if (keys[3]) player.moveBy(speed, 0);

        double halfSize = player.size / 2;
        if (player.x > xMax - halfSize) player.x = xMax - halfSize;
        if (player.y > yMax - halfSize) player.y = yMax - halfSize;
        if (player.x < xMin + halfSize) player.x = xMin + halfSize;
        if (player.y < yMin + halfSize) player.y = yMin + halfSize;
        player.collRad = halfSize;
    }

    private void updateBalls(GL gl) {
        if (youWin) return;
        Iterator<BallFish> it = balls.iterator();
        while (it.hasNext()) {
            BallFish b = it.next();
            b.updateFleeDir(player, xMin, xMax, yMin, yMax);
            b.moveByDir(xMin, xMax, yMin, yMax);

            b.draw(gl);

            if (b.collide(player)) {
                it.remove();
                player.size += 10;
                player.collRad += 5;
            }

        }
    }

    private void checkWin() {
        if (balls.isEmpty()) {
            youWin = true;
        }
    }

    private void drawWinText(GL gl) {
        if (!youWin) return;

        gl.glColor3f(1f, 1f, 1f);
        gl.glRasterPos2d(-80, 0);

        for (char c : "YOU WIN!".toCharArray()) {
            glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, c);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keys[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                keys[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                keys[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keys[3] = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keys[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                keys[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                keys[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                keys[3] = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void reshape(GLAutoDrawable d, int x, int y, int w, int h) {
    }

    @Override
    public void displayChanged(GLAutoDrawable d, boolean modeChanged, boolean deviceChanged) {
    }

    private double randNum(double a, double b) {
        return a + Math.random() * (b - a);
    }

    public void restartGame() {
        youWin = false;
        balls.clear();

        player = new MainFish();
        player.x = 0;
        player.y = 0;
        player.size = 30;

        for (int i = 0; i < maxBalls; i++) {
            double x = randNum(xMin + 50, xMax - 50);
            double y = randNum(yMin + 50, yMax - 50);
            BallFish b = new BallFish(x, y);
            balls.add(b);
        }
    }
}
