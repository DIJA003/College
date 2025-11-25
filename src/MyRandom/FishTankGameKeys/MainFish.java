package MyRandom.FishTankGameKeys;

import javax.media.opengl.GL;

public class MainFish {
    public double x = 0, y = 0, size = 30;
    private int facing = 0;

    public double collRad = size/2;

    public void moveBy(double dx, double dy) {
        if (dx > 0) facing = 0;
        else if (dx < 0) facing = 1;
        x += dx;
        y += dy;
    }
    public void draw(GL gl){
        gl.glColor3f(0.0f, 0.9f, 0.0f);

        double half = size / 2;

        gl.glBegin(GL.GL_QUADS);

        if (facing == 0) {
            gl.glVertex2d(x - half, y - half);
            gl.glVertex2d(x + half, y - half);
            gl.glVertex2d(x + half, y + half);
            gl.glVertex2d(x - half, y + half);
        } else {
            gl.glVertex2d(x + half, y - half);
            gl.glVertex2d(x - half, y - half);
            gl.glVertex2d(x - half, y + half);
            gl.glVertex2d(x + half, y + half);
        }

        gl.glEnd();
    }
}
