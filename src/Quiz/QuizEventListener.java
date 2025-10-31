package Quiz;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class QuizEventListener implements GLEventListener {
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0f, 0f, 0f, 0f);


        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();


        gl.glOrtho(0, 800, 0, 600, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        drawRect(gl,150,105,350,310,255,0,0);

        drawRect(gl,150,10,350,300,255,255,255);

        drawRect(gl,150,105,350,200,255,0,0);

        drawTri(gl,350,415,500,415,425,550,255,158,62);

        drawCircle(gl,30,425,365,37,243,254);

        drawCircle(gl,20,425,230,0,0,0);

        drawTri(gl,300,200,350,200,350, 275,37,243,254);

        drawTri(gl,500,200,500,200,550, 275,37,243,254);

        drawTri(gl,400,200,450,200,425, 120,255,158,62);

        drawCircle(gl,25,425,120,255,158,62);


    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    private static void drawRect(GL gl, int width, int height, int x, int y, int red, int green, int blue) {
        gl.glColor3f(red / 255f, green / 255f, blue / 255f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(x, y);
        gl.glVertex2i(x + width, y);
        gl.glVertex2i(x + width, y + height);
        gl.glVertex2i(x, y + height);
        gl.glEnd();
    }

    private static void drawTri(GL gl, int x1, int y1, int x2, int y2, int x3, int y3, int red, int green, int blue) {
        gl.glColor3f(red / 255f, green / 255f, blue / 255f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(x1, y1);
        gl.glVertex2i(x2, y2);
        gl.glVertex2i(x3, y3);
        gl.glEnd();
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
}
