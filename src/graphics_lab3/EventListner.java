package graphics_lab3;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class EventListner implements GLEventListener {

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0, 0, 0, 0);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0.0, 800, 0.0, 600, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(98 / 255f, 193 / 255f, 229 / 255f, 0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        drawRect(gl, 800, 200, 0, 0, 144, 238, 144);

        drawRoad(gl, 0, 100, 800, 80, 15, 0.005f);

        drawSun(gl, 50, 700, 520);


        drawHouse(gl, 100, 150, 222, 184, 135);
        drawHouse(gl, 350, 150, 210, 180, 140);
        drawHouse(gl, 600, 150, 200, 160, 120);


        drawTreeWithCircles(gl, 250, 180);
        drawTreeWithCircles(gl, 525, 180);
        drawTreeWithTriangles(gl, 750, 180);

        drawCharacter(gl, 350, 160, 51, 51, 230);
        drawCharacter(gl, 500, 160, 230, 51, 51);

        drawCircle(gl, 20, 200, 150, 34, 139, 34);
        drawCircle(gl, 20, 550, 150, 34, 139, 34);
        drawCircle(gl, 20, 700, 150, 34, 139, 34);
        drawCircle(gl, 20, 700, 175, 34, 139, 34);

        drawFence(gl, 80, 115, 200, 8, 50, 20);

        drawCloud(gl, 150, 470, 30);
        drawCloud(gl, 400, 500, 35);
        drawCloud(gl, 550, 460, 25);
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

    private static void drawSun(GL gl, int radius, int x, int y) {
        drawCircle(gl, radius, x, y, 255, 255, 0);
        gl.glColor3f(1f, 1f, 0f);
        gl.glLineWidth(4);

        double step = Math.PI / 4;
        double maxAngle = 2 * Math.PI;
        int inCircle = radius + 10;
        int outCircle = radius + 25;

        gl.glBegin(GL.GL_LINES);
        for (double angle = 0; angle < maxAngle; angle += step) {
            int x1 = (int) (inCircle * Math.cos(angle)) + x;
            int y1 = (int) (inCircle * Math.sin(angle)) + y;

            int x2 = (int) (outCircle * Math.cos(angle)) + x;
            int y2 = (int) (outCircle * Math.sin(angle)) + y;
            gl.glVertex2i(x1, y1);
            gl.glVertex2i(x2, y2);
        }
        gl.glEnd();

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

    private static void drawWindow(GL gl, int x, int y, int size) {
        drawRect(gl, size, size, x, y, 135, 206, 235);

        gl.glColor3f(139 / 255f, 69 / 255f, 19 / 255f);
        gl.glLineWidth(3);

        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(x + size / 2, y);
        gl.glVertex2i(x + size / 2, y + size);
        gl.glVertex2i(x, y + size / 2);
        gl.glVertex2i(x + size, y + size / 2);
        gl.glEnd();
    }

    private static void drawHouse(GL gl, int x, int y, int red, int green, int blue) {
        drawRect(gl, 100, 100, x, y, red, green, blue);

        drawTri(gl, x, y + 100, x + 50, y + 150, x + 100, y + 100, 139, 69, 19);

        int doorWidth = 30;
        int doorHeight = 50;
        int doorX = x + 20;
        int doorY = y;
        drawRect(gl, doorWidth, doorHeight, doorX, doorY, 101, 67, 33);

        int handleX = doorX + doorWidth - 5;
        int handleY = doorY + doorHeight / 2;
        drawCircle(gl, 3, handleX, handleY, 255, 215, 0);

        drawWindow(gl, x + 60, y + 50, 30);
    }


    private static void drawTreeWithCircles(GL gl, int x, int y) {
        drawRect(gl, 20, 60, x, y, 101, 67, 33);
        int r = 34, g = 139, b = 34;
        drawCircle(gl, 30, x + 10, y + 80, r, g, b);
        drawCircle(gl, 25, x - 15, y + 70, r, g, b);
        drawCircle(gl, 25, x + 35, y + 70, r, g, b);
        drawCircle(gl, 20, x - 5, y + 50, r, g, b);
        drawCircle(gl, 20, x + 25, y + 50, r, g, b);
    }

    private static void drawTreeWithTriangles(GL gl, int x, int y) {
        drawRect(gl, 20, 60, x, y, 101, 67, 33);
        int r = 34, g = 139, b = 34;
        drawTri(gl, x - 30, y + 60, x + 10, y + 120, x + 50, y + 60, r, g, b);
        drawTri(gl, x - 25, y + 90, x + 10, y + 150, x + 45, y + 90, r, g, b);
        drawTri(gl, x - 20, y + 120, x + 10, y + 170, x + 40, y + 120, r, g, b);
    }

    private static void drawCharacter(GL gl, int x, int y, int red, int green, int blue) {
        drawRect(gl, 16, 25, x - 8, y, red, green, blue);
        drawCircle(gl, 10, x, y + 35, 255, 224, 189);

        gl.glColor3f(red / 255f, green / 255f, blue / 255f);
        gl.glLineWidth(5);

        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(x - 8, y + 20);
        gl.glVertex2i(x - 25, y + 5);
        gl.glVertex2i(x + 8, y + 20);
        gl.glVertex2i(x + 25, y + 5);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(x - 4, y);
        gl.glVertex2i(x - 15, y - 20);
        gl.glVertex2i(x + 4, y);
        gl.glVertex2i(x + 15, y - 20);
        gl.glEnd();
    }

    private static void drawRoad(GL gl, int xPos, int yPos, int length, int width, float amp, float freq) {
        gl.glColor3f(255 / 255f, 214 / 255f, 172/ 255f);
        gl.glBegin(GL.GL_POLYGON);
        for (int x = 0; x <= length; x++) {
            float y = (float) (Math.sin(freq * x) * amp);
            gl.glVertex2f(x + xPos, y + yPos);
        }
        for (int x = length; x >= 0; x--) {
            float y = (float) (Math.sin(freq * x) * amp);
            gl.glVertex2f(x + xPos, y + yPos - width);
        }
        gl.glEnd();
    }

    private static void drawFence(GL gl, int x, int y, int length, int wWidth, int wHeight, int space) {
        int r = 139, g = 69, b = 19;
        drawRect(gl, length, 8, x, y + wHeight / 2, r, g, b);
        drawRect(gl, length, 8, x, y + wHeight - 10, r, g, b);
        int currX = x;
        while (currX <= x + length) {
            drawRect(gl, wWidth, wHeight, currX, y, r, g, b);
            currX += wWidth + space;
        }
    }

    private static void drawCloud(GL gl, int x, int y, int size) {
        int r = 255, g = 255, b = 255;
        drawCircle(gl, size, x, y, r, g, b);
        drawCircle(gl, size - 5, x - size, y, r, g, b);
        drawCircle(gl, size - 10, x - size / 2, y + size / 2, r, g, b);
    }
}

