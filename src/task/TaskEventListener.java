package task;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class TaskEventListener implements GLEventListener {
    private double angel = 1;
    private  double zoom = 1;
    private int speed = 1;

    public void increaseSpeed(){
        speed++;
    }
    public void decreaseSpeed(){
        speed--;
    }
    public void rotateCW(){
        angel += 1;
    }
    public void rotateCCW(){
        angel -= 1;
    }
    public void zoomIN(){
        zoom += 0.1;
    }
    public void zoomOut(){
        if(zoom > 0.2) zoom -= 0.1;
    }
    public int getSpeed() {
        return speed;
    }

    public double getZoom() {
        return zoom;
    }

    public double getAngel() {
        return angel;
    }


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(132/255f, 218/255f, 255/255f, 0);


        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glLineWidth(1.5f);

        gl.glOrtho(-400, 400, -225, 225, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glScaled(zoom,zoom,1);


        gl.glColor3f(0f,126/255f,181/255f);
        drawPoly(gl,-400,-225,400,-225,400,0,-400,0);

        gl.glPushMatrix();

        gl.glTranslated(speed,0,0);
        gl.glColor3f(157/255f,98/255f,66/255f);
        drawPoly(gl,-150,-110, 150,-110,200,10,-200,10);

        gl.glColor3f(184/255f,112/255f,112/255f);
        drawPoly(gl,-10,10,10,10,10,100,-10,100);

        gl.glPushMatrix();
        gl.glRotated(angel, 0, 1, 0);
        gl.glColor3f(0,0,0);
        drawTri(gl,10,50,10,100,100,75);
        gl.glPopMatrix();

        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    private static void drawPoly(GL gl, int x1, int y1, int x2, int y2, int x3,int y3, int x4,int y4) {
//        gl.glColor3f((float) red, (float) green, (float) blue);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(x1 , y1);
        gl.glVertex2i(x2, y2);
        gl.glVertex2i(x3 , y3);
        gl.glVertex2i(x4 , y4);
        gl.glEnd();
    }

    private static void drawTri(GL gl, int x1, int y1, int x2, int y2, int x3, int y3) {
//        gl.glColor3f(red / 255f, green / 255f, blue / 255f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(x1, y1);
        gl.glVertex2i(x2, y2);
        gl.glVertex2i(x3, y3);
        gl.glEnd();
    }
}
