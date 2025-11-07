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

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }






}

