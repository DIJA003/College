package MyRandom;

import MyRandom.FishTankGameKeys.BallFish;
import MyRandom.FishTankGameKeys.MainFish;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class BullshitListner implements GLEventListener, KeyListener {

    public double x, y, radius = 24.0;
    private int dir, stepsRemain;
    private final int maxSteps = 20;
    private double stepSize = 3.0;

    private final double xMin = -350.0;
    private final double xMax = 350.0;
    private final double yMin = -350.0;
    private final double yMax = 350.0;

    private final int maxBalls = 1;

    public boolean[] keys = new boolean[5]; //shift+,shift-,r

    private ArrayList<BallFish> balls = new ArrayList<>();

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0, 0.6f, 1, 1);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(xMin, xMax, yMin, yMax, -1, 1);

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

        updateBalls(gl);

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    public void draw(GL gl) {
        gl.glColor3f(1f, 0.2f, 0.2f);
        gl.glBegin(GL.GL_POLYGON);
        int steps = 36;
        for (int i = 0; i < steps; i++) {
//            double theta = 2 * Math.PI * i / steps;
//            double vx = 0;
//            double vy = 0;
            gl.glVertex2d(0, 0);
        }
        gl.glEnd();
    }

    public void moveByDir(double xMin, double xMax, double yMin, double yMax) {
        double nx = x, ny = y;
        switch (dir) {
            case 0:
                ny += stepSize;
                break;
            case 1:
                nx += stepSize;
                ny += stepSize;
                break;
            case 2:
                nx -= stepSize;
                ny += stepSize;
                break;
            case 3:
                ny -= stepSize;
                break;
            case 4:
                nx += stepSize;
                ny -= stepSize;
                break;
            case 5:
                nx -= stepSize;
                ny -= stepSize;
                break;
            case 6:
                nx += stepSize;
                break;
            case 7:
                nx -= stepSize;
                break;
        }

        boolean bounced = false;

        if (nx - radius < xMin || nx + radius > xMax) {
            if (dir == 6) dir = 7;
            else if (dir == 7) dir = 6;
            else if (dir == 1) dir = 2;
            else if (dir == 2) dir = 1;
            else if (dir == 4) dir = 5;
            else if (dir == 5) dir = 4;
            bounced = true;
        }

        if (ny - radius < yMin || ny + radius > yMax) {
            if (dir == 0) dir = 3;
            else if (dir == 3) dir = 0;
            else if (dir == 1) dir = 4;
            else if (dir == 4) dir = 1;
            else if (dir == 2) dir = 5;
            else if (dir == 5) dir = 2;
            bounced = true;
        }

        if (bounced) {
            stepsRemain = 5;
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean isAboutToHitBorder(double xMin, double xMax, double yMin, double yMax) {
        double nx = x, ny = y;
        switch (dir) {
            case 0:
                ny += stepSize;
                break;
            case 1:
                nx += stepSize;
                ny += stepSize;
                break;
            case 2:
                nx -= stepSize;
                ny += stepSize;
                break;
            case 3:
                ny -= stepSize;
                break;
            case 4:
                nx += stepSize;
                ny -= stepSize;
                break;
            case 5:
                nx -= stepSize;
                ny -= stepSize;
                break;
            case 6:
                nx += stepSize;
                break;
            case 7:
                nx -= stepSize;
                break;
        }
        return (nx - radius < xMin) || (nx + radius > xMax) || (ny - radius < yMin) || (ny + radius > yMax);
    }

    private void randomWander(double xMin, double xMax, double yMin, double yMax) {
        stepsRemain--;
        if (stepsRemain <= 0 || isAboutToHitBorder(xMin, xMax, yMin, yMax)) {
            dir = (int) (Math.random() * 8);
            stepsRemain = 10 + (int) (Math.random() * maxSteps);
        }
    }

    public void restartGame() {
        balls.clear();

        for (int i = 0; i < maxBalls; i++) {
            double x = 0;
            double y = 0;
            BallFish b = new BallFish(x, y);
            balls.add(b);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                keys[0] = true;
                break;
            case KeyEvent.VK_ADD:
                keys[1] = true;
                //stepSize += 0.3;
                break;
            case KeyEvent.VK_MINUS:
                keys[2] = true;
                //stepSize -= 0.3;
                break;
            case KeyEvent.VK_R:
                restartGame();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                keys[0] = false;
                break;
            case KeyEvent.VK_ADD:
                keys[1] = false;
                //stepSize += 0.3;
                break;
            case KeyEvent.VK_MINUS:
                keys[2] = false;
                //stepSize -= 0.3;
                break;
            case KeyEvent.VK_R:
                restartGame();
                break;
        }
    }

    private void updateBalls(GL gl) {

        Iterator<BallFish> it = balls.iterator();
        while (it.hasNext()) {

            BallFish b = it.next();
            if (keys[0] && keys[1]) b.increase(0.3);
            else if (keys[0] && keys[2]){
                b.decrease(0.3);
            }
            b.moveByDir(xMin, xMax, yMin, yMax);
            b.draw(gl);

        }
    }

    private double randNum(double a, double b) {
        return a + Math.random() * (b - a);
    }

}

