package Graphics_Lab1;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

class SimpleGLEventListener implements GLEventListener {


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0.0, 800.0, 0.0, 800.0, -1.0, 1.0);

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glBegin(GL.GL_POINTS);
            gl.glColor3f(0,0,0);
            gl.glPointSize(250);
            gl.glVertex2i(300,300);
        gl.glEnd();

        gl.glBegin(GL.GL_POINTS);
            gl.glColor3f(0,0,0);
            gl.glPointSize(250);
            gl.glVertex2i(250,250);
        gl.glEnd();

        gl.glBegin(GL.GL_POINTS);
            gl.glColor3f(0,0,0);
            gl.glPointSize(250);
            gl.glVertex2i(350,350);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}

