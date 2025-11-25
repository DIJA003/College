package MyRandom.FishTankGameMouse;

import javax.media.opengl.GL;

public class BallFish {
    public double x, y, radius = 24.0;
    private int dir, stepsRemain;
    private final int maxSteps = 20;
    private final double stepSize = 3.0, fleeRange = 120;


    public BallFish(double xStart, double yStart) {
        this.x = xStart;
        this.y = yStart;
        this.dir = (int) (Math.random() * 8);
        this.stepsRemain = 1 + (int) (Math.random() * maxSteps);
    }

    public void updateFleeDir(MainFish player, double xMin, double xMax, double yMin, double yMax) {
        double vx = x - player.x;
        double vy = y - player.y;
        double distance = Math.hypot(vx, vy);


        if (distance > fleeRange) {
            randomWander(xMin, xMax, yMin, yMax);
            return;
        }


        stepsRemain--;
        if (stepsRemain <= 0 || isAboutToHitBorder(xMin, xMax, yMin, yMax)) {
            double angle = Math.atan2(vy, vx);
            dir = angleToDir(angle);
            stepsRemain = 5 + (int)(Math.random() * maxSteps / 2);
        }
    }
    private void randomWander(double xMin, double xMax, double yMin, double yMax) {
        stepsRemain--;
        if (stepsRemain <= 0 || isAboutToHitBorder(xMin, xMax, yMin, yMax)) {
            dir = (int)(Math.random() * 8);
            stepsRemain = 10 + (int)(Math.random() * maxSteps);
        }
    }
    private int angleToDir(double angle) {
        double deg = Math.toDegrees(angle);
        if (deg > -22.5 && deg <= 22.5) return 6;
        if (deg > 22.5 && deg <= 67.5) return 1;
        if (deg > 67.5 && deg <= 112.5) return 0;
        if (deg > 112.5 && deg <= 157.5) return 2;
        if (deg > 157.5 || deg <= -157.5) return 7;
        if (deg > -157.5 && deg <= -112.5) return 5;
        if (deg > -112.5 && deg <= -67.5) return 3;
        return 4;
    }

    public void moveByDir(double xMin, double xMax, double yMin, double yMax) {
        double nx = x, ny = y;
        switch (dir) {
            case 0: ny += stepSize; break;
            case 1: nx += stepSize; ny += stepSize; break;
            case 2: nx -= stepSize; ny += stepSize; break;
            case 3: ny -= stepSize; break;
            case 4: nx += stepSize; ny -= stepSize; break;
            case 5: nx -= stepSize; ny -= stepSize; break;
            case 6: nx += stepSize; break;
            case 7: nx -= stepSize; break;
        }

        boolean bounced = false;

        if (nx - radius < xMin || nx + radius > xMax) {
            if (dir == 6) dir = 7;
            else if (dir == 7) dir = 6;
            else if (dir == 1) dir = 2;
            else if (dir == 2) dir = 1;
            else if (dir == 4) dir = 5;
            else if (dir == 5) dir = 4;
            bounced = true;
        }

        if (ny - radius < yMin || ny + radius > yMax) {
            if (dir == 0) dir = 3;
            else if (dir == 3) dir = 0;
            else if (dir == 1) dir = 4;
            else if (dir == 4) dir = 1;
            else if (dir == 2) dir = 5;
            else if (dir == 5) dir = 2;
            bounced = true;
        }

        if (bounced) {
            stepsRemain = 5;
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean isAboutToHitBorder(double xMin, double xMax, double yMin, double yMax) {
        double nx = x, ny = y;
        switch (dir) {
            case 0: ny += stepSize; break;
            case 1: nx += stepSize; ny += stepSize; break;
            case 2: nx -= stepSize; ny += stepSize; break;
            case 3: ny -= stepSize; break;
            case 4: nx += stepSize; ny -= stepSize; break;
            case 5: nx -= stepSize; ny -= stepSize; break;
            case 6: nx += stepSize; break;
            case 7: nx -= stepSize; break;
        }
        return (nx - radius < xMin) || (nx + radius > xMax) || (ny - radius < yMin) || (ny + radius > yMax);
    }

    public void draw(GL gl) {
        gl.glColor3f(1f, 0.2f, 0.2f);
        gl.glBegin(GL.GL_POLYGON);
        int steps = 36;
        for (int i = 0; i < steps; i++) {
            double theta = 2 * Math.PI * i / steps;
            double vx = x + radius * Math.cos(theta);
            double vy = y + radius * Math.sin(theta);
            gl.glVertex2d(vx, vy);
        }
        gl.glEnd();
    }

    public boolean collide(MainFish fish) {
        double dx = fish.x - x;
        double dy = fish.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist < (fish.collRad + radius);
    }

}
