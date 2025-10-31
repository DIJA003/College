package graphics_postLab4_assignments.trees_pyramdis;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class TPlistener implements GLEventListener {
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0);

        gl.glLineWidth(2.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();


        gl.glOrtho(-250, 250, -150, 150, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        drawGraph(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    private void drawGraph(GL gl) {
        float red;
        float green;
        float blue;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        red = 0.2f;
        green = 0.2f;
        blue = 0.2f;
        gl.glColor3f(red, green, blue);

        gl.glBegin(GL.GL_LINES);

        for (int x = -250; x <= 250; x += 10) {
            gl.glVertex2d(x, -150);
            gl.glVertex2d(x, 150);
        }
        for (int y = -150; y <= 150; y += 10) {
            gl.glVertex2d(-250, y);
            gl.glVertex2d(250, y);
        }
        gl.glEnd();

        red = 42/255f;
        green =67/255f;
        blue = 122/255f;
        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(0, 140);
        gl.glVertex2d(0, -140);
        gl.glVertex2d(240, 0);
        gl.glVertex2d(-240, 0);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(0, 150);
        gl.glVertex2d(-5, 140);
        gl.glVertex2d(5, 140);
        gl.glVertex2d(0, -150);
        gl.glVertex2d(-5, -140);
        gl.glVertex2d(5, -140);
        gl.glVertex2d(250, 0);
        gl.glVertex2d(240, -5);
        gl.glVertex2d(240, 5);
        gl.glVertex2d(-250, 0);
        gl.glVertex2d(-240, -5);
        gl.glVertex2d(-240, 5);
        gl.glEnd();
    }

    private void setHexColor(GL gl, String hex) {
        if (hex.length() < 7) {
            System.err.println("Hex String is less than 7 characters");
            return;
        }
        try {
            int r = Integer.parseInt(hex.substring(1, 3), 16);
            int g = Integer.parseInt(hex.substring(3, 5), 16);
            int b = Integer.parseInt(hex.substring(5, 7), 16);
            gl.glColor3f(r / 255f, g / 255f, b / 255f);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
