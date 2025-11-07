package graphics_postLab4_assignments.solar_system;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class solarListener implements GLEventListener {
    private double angel = 0;
    private  double zoom = 1;
    private int speed = 1;
    private boolean isClockWise = true;

    public void increaseSpeed(){
        speed++;
    }
    public void decreaseSpeed(){
        if(speed > 1) speed--;
    }
    public void rotateCW(){
        isClockWise = true;
    }
    public void rotateCCW(){
        isClockWise = false;
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
        gl.glClearColor(0, 0, 0, 0);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glLineWidth(1.5f);

        gl.glOrtho(-400, 400, -225, 225, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        if(isClockWise){
            angel += speed;
        }
        if(!isClockWise){
            angel-=speed;
        }
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glScaled(zoom,zoom,1);


        //shams
        drawCircle(gl,45,0,0,"#FFD700");

        drawOrbit(gl, 60);
        //3tard
        gl.glPushMatrix();
        gl.glRotated(angel * 1.8, 0, 0, 1);
        gl.glTranslated(60, 0, 0);
        drawCircle(gl, 6, 0, 0, "#B8B8B8");
        gl.glPopMatrix();

        drawOrbit(gl, 90);
        //elzahra
        gl.glPushMatrix();
        gl.glRotated(angel * 1.4, 0, 0, 1);
        gl.glTranslated(90, 0, 0);
        drawCircle(gl, 9, 0, 0, "#E7C27C");
        gl.glPopMatrix();

        drawOrbit(gl, 120);
        //ard
        gl.glPushMatrix();
        gl.glRotated(angel, 0 ,0, 1);
        gl.glTranslated(120,0,0);
        drawCircle(gl,12,0,0,"#1E90FF");
        //el2mr
        gl.glPushMatrix();
        drawOrbit(gl, 15);
        gl.glRotated(angel * 3, 0, 0, 1);
        gl.glTranslated(15, 0, 0);
        drawCircle(gl, 4, 0, 0, "#D3D3D3");
        gl.glPopMatrix();

        gl.glPopMatrix();

        drawOrbit(gl, 145);
        //mareekh
        gl.glPushMatrix();
        gl.glRotated(angel * 0.8, 0, 0, 1);
        gl.glTranslated(145, 0, 0);
        drawCircle(gl, 8, 0, 0, "#FF4500");
        gl.glPopMatrix();

        drawOrbit(gl, 175);
        //elmoshtra
        gl.glPushMatrix();
        gl.glRotated(angel * 0.5, 0, 0, 1);
        gl.glTranslated(175, 0, 0);
        drawCircle(gl, 18, 0, 0, "#C1813A");
        gl.glPopMatrix();

        drawOrbit(gl, 205);
        //zo7al
        gl.glPushMatrix();
        gl.glRotated(angel * 0.3, 0, 0, 1);
        gl.glTranslated(205, 0, 0);

        drawCircle(gl, 16, 0, 0, "#D2B48C");

        setHexColor(gl, "#CFCFB1");
        gl.glBegin(GL.GL_LINE_LOOP);
        for (int i = 0; i < 360; i += 5) {
            double angle = Math.toRadians(i);
            int x = (int) (30 * Math.cos(angle));
            int y = (int) (12 * Math.sin(angle));
            gl.glVertex2i(x, y);
        }
        gl.glEnd();

        gl.glPopMatrix();

        drawOrbit(gl, 235);
        //uranus
        gl.glPushMatrix();
        gl.glRotated(angel * 0.2, 0, 0, 1);
        gl.glTranslated(235, 0, 0);
        drawCircle(gl, 10, 0, 0, "#78DCE5");
        gl.glPopMatrix();

        drawOrbit(gl, 260);
        //neptune
        gl.glPushMatrix();
        gl.glRotated(angel * 0.15, 0, 0, 1);
        gl.glTranslated(260, 0, 0);
        drawCircle(gl, 10, 0, 0, "#5B8FFF");
        gl.glPopMatrix();

//        if(isClockWise){
//            angel += speed;
//        }
//        if(!isClockWise){
//            angel-=speed;
//        }

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    private void drawCircle(GL gl, int radius, int cx, int cy, String hexColor) {
        setHexColor(gl, hexColor);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i <= 360; i += 1) {
            double angle = Math.toRadians(i);
            int x = (int) (radius * Math.cos(angle)) + cx;
            int y = (int) (radius * Math.sin(angle)) + cy;
            gl.glVertex2i(x, y);
        }
        gl.glEnd();
    }

    private void drawOrbit(GL gl, int radius) {
        setHexColor(gl, "#AAAAAA");
        gl.glBegin(GL.GL_POINTS);
        for (int i = 0; i < 360; i += 5) {
            double angle = Math.toRadians(i);
            int x = (int) (radius * Math.cos(angle)) ;
            int y = (int) (radius * Math.sin(angle));
            gl.glVertex2i(x, y);
        }
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
