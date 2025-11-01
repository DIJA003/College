package MyRandom.MyFirstGame;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongEventListener implements GLEventListener, KeyListener {

    float ballx = 0, bally = 0;
    float dx = 3, dy = 3;
    int ballSize = 15;

    float rackW = 15, rackH = 80;
    float leftRackY = 0, rightRackY = 0;
    float rackSpeed = 6;

    int leftScore = 0, rightScore = 0;

    boolean wPress = false, sPress = false;
    boolean arrUpPress = false, arrDownPress = false;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-400, 400, -250, 250, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        update();
        drawAll(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) wPress = true;
        if (e.getKeyCode() == KeyEvent.VK_S) sPress = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) arrUpPress = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) arrDownPress = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) wPress = false;
        if (e.getKeyCode() == KeyEvent.VK_S) sPress = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) arrUpPress = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) arrDownPress = false;
    }

    private void drawAll(GL gl) {
        gl.glColor3f(1, 1, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, -250);
        gl.glVertex2f(0, 250);
        gl.glEnd();

        drawRec(gl, -380, leftRackY, rackW, rackH);
        drawRec(gl, 380 - rackW, rightRackY, rackW, rackH);

        drawRec(gl, ballx, bally, ballSize, ballSize);
    }

    private void drawRec(GL gl, float x, float y, float w, float h) {
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x + w, y);
        gl.glVertex2f(x + w, y + h);
        gl.glVertex2f(x, y + h);
        gl.glEnd();
    }

    private void update() {

        if (ballx < -410) {
            rightScore++;
            resetBall();
            return;
        }
        if (bally > 410) {
            leftScore++;
            resetBall();
            return;
        }

        ballx += dx;
        bally += dy;

        if (bally > 250 - ballSize || bally < -250) dy *= -1;

        if (wPress) leftRackY += rackSpeed;
        if (sPress) leftRackY -= rackSpeed;
        if (arrUpPress) rightRackY += rackSpeed;
        if (arrDownPress) rightRackY -= rackSpeed;

        leftRackY = Math.max(-250, Math.min(250 - rackH, leftRackY));
        rightRackY = Math.max(-250, Math.min(250 - rackH, rightRackY));

        if (ballx <= -380 + rackW &&
                bally >= leftRackY &&
                bally <= leftRackY + rackH) {
            dx *= -1.1f;
            dy *= 1.05f;
        }
        if (ballx + ballSize >= 380 - rackW &&
                bally >= rightRackY &&
                bally <= rightRackY + rackH) {
            dx *= -1.1f;
            dy *= 1.05f;
        }

    }

    private void resetBall() {
        ballx = 0;
        bally = 0;
        dx = (dx > 0 ? -3 : 3);
        dy = 3;
    }
}
