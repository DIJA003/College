package graphics_lab2;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class SimpleGLEventListener implements GLEventListener {
    private boolean drawTriangle = false;
    private boolean drawQuad = false;
    protected void DrawTriangle(){
        drawTriangle = true;
    }
    protected void DrawQuad(){
        drawQuad = true;
    }
    private float i = 50;
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(30.0f/255, 182.0f/255, 225.0f/255, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0.0, 800.0, 0.0, 600.0, -1.0, 1.0);

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        if(drawTriangle){
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f,0.0f,0.0f);
            gl.glVertex2i(200,450);
            gl.glVertex2f(400.0f,337.5f);
            gl.glVertex2i(200,225);
            gl.glEnd();
            //drawTriangle = false;
        }
        if(drawQuad){
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f,1.0f,0.0f);
            gl.glVertex2i(200,225);
            gl.glVertex2i(200,300);
            gl.glVertex2i(600,300);
            gl.glVertex2i(600,225);
            gl.glEnd();
            //drawQuad = false;
        }
        gl.glColor3f(0,1,1);
        drawCircle(gl,50,400,300);

    }
    private static void drawCircle(GL gl, int radius, int xPos, int yPos) {
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

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}
