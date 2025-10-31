package MyRandom;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class BullshitListner implements GLEventListener {

//    private int translateX = 0;
//    private int translateY = 0;
//    public void setTranslateX(int x){
//        translateX = x;
//    }
//    public void setTranslateY(int y){
//        translateY = y;
//    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0.5f, 0.5f, 0.5f, 0);

        gl.glLineWidth(2.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();


        gl.glOrtho(-250, 250, -150, 150, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        drawGraph(gl);
        drawPoly(gl, 0, 1, 0);

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }


    private static void drawCircle(GL gl, int radius, int xPos, int yPos, int red, int green, int blue) {
        gl.glColor3f(red / 255f, green / 255f, blue / 255f);
        double degree = Math.PI / 180;
        double threeSixty = 2 * Math.PI;
        gl.glBegin(GL.GL_POLYGON);
        for (double i = 0; i <= threeSixty; i += degree) {
            int x = (int) (radius * Math.cos(i)) + xPos;
            int y = (int) (radius * Math.sin(i)) + yPos;
            gl.glVertex2i(x, y);
        }
        gl.glEnd();
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

        red = 1f;
        green = 1f;
        blue = 0f;
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

    private static void drawPoly(GL gl, int red, int green, int blue) {
        gl.glColor3f((float) red, (float) green, (float) blue);

        gl.glPushMatrix();
        //martix multi concept or stack concept as translate is in first and scale in second when poping the scale start first as it is the top of the stack
        //operation order RTS

        gl.glRotated(45,0,0,1);

        gl.glScaled(2,2,1);
        gl.glTranslated(-25, -25, 0);

        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0 , 0);
        gl.glVertex2i(0 , 50);
        gl.glVertex2i(50 , 50);
        gl.glVertex2i(50 , 0);
        gl.glEnd();

        gl.glPopMatrix();

    }
}

