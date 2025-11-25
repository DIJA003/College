package graphics_postLab4_assignments.trees_pyramdis;

import com.sun.opengl.util.GLUT;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TPlistener implements GLEventListener {

    private GLUT glut = new GLUT();
    private Random rand = new Random();

    // Character animation
    float legAngle = 0;
    boolean legForward = true;
    float armSwing = 0;
    boolean armForward = true;

    // Character positions
    float boyX = -350;
    float boyY = -50;
    float girlX = 250;
    float girlY = -50;
    float boySize = 40;

    // Final positions when they meet
    float finalBoyX = 50;
    float finalGirlX = 130;

    // Animation states
    String msg = "";
    boolean reached = false;
    int frameCount = 0;
    float heartScale = 1.0f;
    boolean heartGrowing = true;
    float celebrationHeartY = -100;
    boolean showCelebrationHearts = false;

    // Particle system for hearts
    private List<Heart> hearts = new ArrayList<>();

    // Sky elements
    private List<Star> stars = new ArrayList<>();
    private float moonGlow = 0.0f;
    private boolean moonGlowing = true;

    // Scene colors
    private static final float SKY_R = 0.05f;
    private static final float SKY_G = 0.05f;
    private static final float SKY_B = 0.15f;

    @Override
    public void init(GLAutoDrawable d) {
        GL gl = d.getGL();
        gl.glClearColor(SKY_R, SKY_G, SKY_B, 1);
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL.GL_LINE_SMOOTH);
        gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400, -300, 300, -1, 1);

        // Initialize stars
        for (int i = 0; i < 50; i++) {
            stars.add(new Star(
                    rand.nextFloat() * 800 - 400,
                    rand.nextFloat() * 400 - 100,
                    rand.nextFloat() * 2 + 1,
                    rand.nextFloat() * 0.5f + 0.5f
            ));
        }
    }

    @Override
    public void display(GLAutoDrawable d) {
        GL gl = d.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        update();
        drawScene(gl);
        frameCount++;
    }

    private void update() {
        // Animate walking legs
        if (!reached) {
            legAngle += (legForward ? 4 : -4);
            if (legAngle > 35) legForward = false;
            if (legAngle < -35) legForward = true;

            armSwing += (armForward ? 3 : -3);
            if (armSwing > 25) armForward = false;
            if (armSwing < -25) armForward = true;
        }

        // Move boy towards girl
        float dist = girlX - boyX;
        if (dist > 80 && !reached) {
            boyX += 1.5f;
        } else if (!reached) {
            reached = true;
            showCelebrationHearts = true;
            celebrationHeartY = boyY + 150;
            // Spawn celebration hearts around them
            for (int i = 0; i < 30; i++) {
                float angle = (float)(Math.random() * Math.PI * 2);
                float radius = (float)(Math.random() * 80 + 40);
                hearts.add(new Heart(
                        (boyX + girlX) / 2 + (float)Math.cos(angle) * radius,
                        boyY + 50 + (float)Math.sin(angle) * radius * 0.5f,
                        rand.nextFloat() * 1.5f + 1f,
                        rand.nextFloat() * 2 + 1f
                ));
            }
        }

        // Update messages based on distance
        if (!reached) {
            if (dist > 200) msg = "I... miss you...";
            else if (dist > 150) msg = "I'm coming to you...";
            else if (dist > 100) msg = "Please wait for me...";
            else msg = "Almost there... ❤";
        } else {
            msg = "";
        }

        // Update heart particles
        List<Heart> toRemove = new ArrayList<>();
        for (Heart h : hearts) {
            h.update();
            if (h.y > 400 || h.alpha <= 0) {
                toRemove.add(h);
            }
        }
        hearts.removeAll(toRemove);

        // Spawn new hearts when together - from above falling down
        if (reached && frameCount % 15 == 0) {
            hearts.add(new Heart(
                    (boyX + girlX) / 2 + rand.nextFloat() * 120 - 60,
                    250 + rand.nextFloat() * 50,
                    rand.nextFloat() * 1.2f + 0.8f,
                    rand.nextFloat() * 1.5f + 0.5f
            ));
        }

        // Animate celebration hearts rising
        if (showCelebrationHearts && celebrationHeartY < 200) {
            celebrationHeartY += 2f;
        }

        // Animate heart scale
        if (reached) {
            heartScale += (heartGrowing ? 0.015f : -0.015f);
            if (heartScale > 1.2f) heartGrowing = false;
            if (heartScale < 0.95f) heartGrowing = true;
        }

        // Animate moon glow
        moonGlow += (moonGlowing ? 0.01f : -0.01f);
        if (moonGlow > 0.3f) moonGlowing = false;
        if (moonGlow < 0.0f) moonGlowing = true;

        // Update stars
        for (Star s : stars) {
            s.update();
        }
    }

    private void drawScene(GL gl) {
        // Draw gradient sky
        drawGradientSky(gl);

        // Draw moon
        drawMoon(gl);

        // Draw stars
        for (Star s : stars) {
            s.draw(gl);
        }

        // Draw ground
        drawGround(gl);

        // Draw path
        drawPath(gl);

        // Draw characters
        drawBoy(gl, boyX, boyY, boySize);
        drawGirl(gl, girlX, girlY);

        // Draw floating hearts
        for (Heart h : hearts) {
            h.draw(gl);
        }

        // Draw message with shadow
        gl.glColor3f(0, 0, 0);
        gl.glRasterPos2f(-99, 199);
        for (char c : msg.toCharArray()) {
            glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, c);
        }

        gl.glColor3f(1, 0.9f, 0.7f);
        gl.glRasterPos2f(-100, 200);
        for (char c : msg.toCharArray()) {
            glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, c);
        }

        // Draw final message
        if (reached) {
            // Draw celebration hearts rising up
            drawCelebrationHearts(gl);

            gl.glColor3f(0.1f, 0, 0);
            gl.glRasterPos2f(-79, 239);
            for (char c : "Together Forever ❤".toCharArray()) {
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, c);
            }

            gl.glColor3f(1, 0.3f, 0.4f);
            gl.glRasterPos2f(-80, 240);
            for (char c : "Together Forever ❤".toCharArray()) {
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, c);
            }
        }
    }

    private void drawGradientSky(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.1f, 0.05f, 0.2f); // Top
        gl.glVertex2f(-400, 300);
        gl.glVertex2f(400, 300);
        gl.glColor3f(0.05f, 0.05f, 0.15f); // Bottom
        gl.glVertex2f(400, -100);
        gl.glVertex2f(-400, -100);
        gl.glEnd();
    }

    private void drawMoon(GL gl) {
        // Moon glow
        gl.glColor4f(1, 1, 0.8f, moonGlow);
        drawCircle(gl, 300, 220, 45);

        // Moon body
        gl.glColor3f(1, 1, 0.9f);
        drawCircle(gl, 300, 220, 35);

        // Craters
        gl.glColor3f(0.9f, 0.9f, 0.8f);
        drawCircle(gl, 310, 230, 8);
        drawCircle(gl, 295, 215, 6);
        drawCircle(gl, 305, 210, 5);
    }

    private void drawGround(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.15f, 0.25f, 0.15f); // Dark green
        gl.glVertex2f(-400, -100);
        gl.glVertex2f(400, -100);
        gl.glColor3f(0.1f, 0.2f, 0.1f); // Darker green
        gl.glVertex2f(400, -300);
        gl.glVertex2f(-400, -300);
        gl.glEnd();
    }

    private void drawPath(GL gl) {
        gl.glColor3f(0.3f, 0.25f, 0.2f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(-400, -80);
        gl.glVertex2f(400, -80);
        gl.glVertex2f(400, -110);
        gl.glVertex2f(-400, -110);
        gl.glEnd();
    }

    private void drawBoy(GL gl, float x, float y, float s) {
        gl.glPushMatrix();

        // Head (circle)
        gl.glColor3f(0.95f, 0.85f, 0.7f);
        drawCircle(gl, x + s/2, y + s + 20, s/2.2f);

        // Eyes
        gl.glColor3f(0, 0, 0);
        drawCircle(gl, x + s/2 - 6, y + s + 25, 2.5f);
        drawCircle(gl, x + s/2 + 6, y + s + 25, 2.5f);

        // Smile
        gl.glLineWidth(2);
        gl.glBegin(GL.GL_LINE_STRIP);
        for (int i = 0; i < 10; i++) {
            float angle = (float)Math.PI + i * 0.1f;
            gl.glVertex2f(x + s/2 + (float)Math.cos(angle) * 8,
                    y + s + 17 + (float)Math.sin(angle) * 4);
        }
        gl.glEnd();
        gl.glLineWidth(1);

        // Hair (brown)
        gl.glColor3f(0.35f, 0.2f, 0.1f);
        drawCircle(gl, x + s/2, y + s + 25, s/2.2f + 4);

        // Body (rectangle - blue shirt)
        gl.glColor3f(0.2f, 0.5f, 0.9f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(x + s/3, y + s);
        gl.glVertex2f(x + 2*s/3, y + s);
        gl.glVertex2f(x + 2*s/3, y - 5);
        gl.glVertex2f(x + s/3, y - 5);
        gl.glEnd();

        // Arms
        gl.glColor3f(0.95f, 0.85f, 0.7f);
        gl.glLineWidth(4);
        gl.glBegin(GL.GL_LINES);
        float leftArmAngle = reached ? 0 : armSwing;
        gl.glVertex2f(x + s/3, y + s*0.85f);
        gl.glVertex2f(x - 8, y + s*0.5f + leftArmAngle);
        float rightArmAngle = reached ? 0 : -armSwing;
        gl.glVertex2f(x + 2*s/3, y + s*0.85f);
        gl.glVertex2f(x + s + 8, y + s*0.5f + rightArmAngle);
        gl.glEnd();
        gl.glLineWidth(1);

        // Pants/Legs
        gl.glLineWidth(5);
        gl.glColor3f(0.2f, 0.2f, 0.3f);
        gl.glBegin(GL.GL_LINES);
        float leftLegAngle = reached ? 0 : legAngle;
        gl.glVertex2f(x + s/2 - 5, y - 5);
        gl.glVertex2f(x + s/2 - 10 + leftLegAngle/2, y - 30);

        float rightLegAngle = reached ? 0 : -legAngle;
        gl.glVertex2f(x + s/2 + 5, y - 5);
        gl.glVertex2f(x + s/2 + 10 + rightLegAngle/2, y - 30);
        gl.glEnd();
        gl.glLineWidth(1);

        gl.glPopMatrix();
    }

    private void drawGirl(GL gl, float x, float y) {
        float headSize = 30;

        // Hair (back layer - larger circle)
        gl.glColor3f(0.3f, 0.15f, 0.1f);
        drawCircle(gl, x, y + headSize + 15, headSize + 10);

        // Head
        gl.glColor3f(1f, 0.85f, 0.7f);
        drawCircle(gl, x, y + headSize + 12, headSize);

        // Eyes
        gl.glColor3f(0, 0, 0);
        drawCircle(gl, x - 9, y + headSize + 18, 2.5f);
        drawCircle(gl, x + 9, y + headSize + 18, 2.5f);

        // Blush
        gl.glColor4f(1, 0.5f, 0.5f, 0.4f);
        drawCircle(gl, x - 18, y + headSize + 10, 7);
        drawCircle(gl, x + 18, y + headSize + 10, 7);

        // Smile
        gl.glColor3f(0.8f, 0.2f, 0.3f);
        gl.glLineWidth(2);
        gl.glBegin(GL.GL_LINE_STRIP);
        for (int i = 0; i < 10; i++) {
            float angle = (float)Math.PI + i * 0.1f;
            gl.glVertex2f(x + (float)Math.cos(angle) * 10,
                    y + headSize + 7 + (float)Math.sin(angle) * 5);
        }
        gl.glEnd();
        gl.glLineWidth(1);

        // Dress (triangle shape like in image)
        gl.glColor3f(1f, 0.25f, 0.45f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2f(x - 35, y + headSize);
        gl.glVertex2f(x + 35, y + headSize);
        gl.glVertex2f(x, y - 40);
        gl.glEnd();

        // Dress dots decoration
        gl.glColor3f(1, 0.75f, 0.85f);
        for (int i = 0; i < 6; i++) {
            float yPos = y + headSize - 5 - i * 8;
            float xOffset = 20 - i * 3;
            drawCircle(gl, x - xOffset, yPos, 2.5f);
            drawCircle(gl, x + xOffset, yPos, 2.5f);
        }

        // Arms (skin colored lines)
        gl.glColor3f(1f, 0.85f, 0.7f);
        gl.glLineWidth(4);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(x - 30, y + headSize - 5);
        gl.glVertex2f(x - 50, y + headSize - 25);
        gl.glVertex2f(x + 30, y + headSize - 5);
        gl.glVertex2f(x + 50, y + headSize - 25);
        gl.glEnd();
        gl.glLineWidth(1);

        // Legs/Feet (pink to match dress)
        gl.glColor3f(0.9f, 0.2f, 0.35f);
        gl.glLineWidth(5);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(x - 8, y - 40);
        gl.glVertex2f(x - 12, y - 58);
        gl.glVertex2f(x + 8, y - 40);
        gl.glVertex2f(x + 12, y - 58);
        gl.glEnd();
        gl.glLineWidth(1);
    }

    private void drawBigHeart(GL gl, float cx, float cy, float size) {
        gl.glColor4f(1, 0.2f, 0.3f, 0.9f);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(cx, cy - size);
        for (int i = 0; i <= 180; i += 5) {
            float angle = (float)Math.toRadians(i + 180);
            float x = cx - size/2 + (float)Math.cos(angle) * size/2;
            float y = cy + (float)Math.sin(angle) * size/2;
            gl.glVertex2f(x, y);
        }
        for (int i = 0; i <= 180; i += 5) {
            float angle = (float)Math.toRadians(i + 180);
            float x = cx + size/2 + (float)Math.cos(angle) * size/2;
            float y = cy + (float)Math.sin(angle) * size/2;
            gl.glVertex2f(x, y);
        }
        gl.glEnd();

        // Heart shine
        gl.glColor4f(1, 0.8f, 0.8f, 0.6f);
        drawCircle(gl, cx - size/4, cy + size/4, size/6);
    }

    private void drawCelebrationHearts(GL gl) {
        if (!showCelebrationHearts) return;

        float centerX = (boyX + girlX) / 2;

        // Draw three rising hearts above the couple
        float[] sizes = {25, 30, 35};
        float[] yOffsets = {0, 40, 80};
        float[] scales = {0.9f, 1.0f, 1.1f};

        for (int i = 0; i < 3; i++) {
            float heartY = celebrationHeartY - yOffsets[i];
            if (heartY > boyY + 50 && heartY < 250) {
                float alpha = 1.0f - (heartY - (boyY + 50)) / 200f;
                gl.glColor4f(1, 0.2f + i * 0.1f, 0.3f + i * 0.05f, alpha * 0.8f);

                float s = sizes[i] * heartScale * scales[i];
                float offsetX = (float)Math.sin(heartY * 0.02f + i) * 15;

                // Draw heart
                gl.glBegin(GL.GL_TRIANGLE_FAN);
                gl.glVertex2f(centerX + offsetX, heartY - s);
                for (int j = 0; j <= 180; j += 5) {
                    float angle = (float)Math.toRadians(j + 180);
                    float x = centerX + offsetX - s/2 + (float)Math.cos(angle) * s/2;
                    float y = heartY + (float)Math.sin(angle) * s/2;
                    gl.glVertex2f(x, y);
                }
                for (int j = 0; j <= 180; j += 5) {
                    float angle = (float)Math.toRadians(j + 180);
                    float x = centerX + offsetX + s/2 + (float)Math.cos(angle) * s/2;
                    float y = heartY + (float)Math.sin(angle) * s/2;
                    gl.glVertex2f(x, y);
                }
                gl.glEnd();
            }
        }
    }

    @Override public void reshape(GLAutoDrawable d, int x, int y, int w, int h) {}
    @Override public void displayChanged(GLAutoDrawable d, boolean a, boolean b) {}

    private void drawCircle(GL gl, float cx, float cy, float r) {
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < 360; i += 5) {
            double rad = Math.toRadians(i);
            gl.glVertex2f(cx + (float)Math.cos(rad) * r,
                    cy + (float)Math.sin(rad) * r);
        }
        gl.glEnd();
    }

    // Particle classes
    class Heart {
        float x, y, size, speed, alpha;

        Heart(float x, float y, float size, float speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
            this.alpha = 1.0f;
        }

        void update() {
            y += speed;
            x += (float)Math.sin(y * 0.05f) * 0.5f;
            alpha -= 0.005f;
        }

        void draw(GL gl) {
            gl.glColor4f(1, 0.3f, 0.4f, alpha);
            drawBigHeart(gl, x, y, size * 5);
        }
    }

    class Star {
        float x, y, size, brightness, twinkleSpeed;
        boolean brightening;

        Star(float x, float y, float size, float brightness) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.brightness = brightness;
            this.twinkleSpeed = rand.nextFloat() * 0.01f + 0.005f;
            this.brightening = rand.nextBoolean();
        }

        void update() {
            brightness += brightening ? twinkleSpeed : -twinkleSpeed;
            if (brightness > 1.0f) brightening = false;
            if (brightness < 0.3f) brightening = true;
        }

        void draw(GL gl) {
            gl.glColor4f(1, 1, 0.9f, brightness);
            gl.glPointSize(size);
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2f(x, y);
            gl.glEnd();

            // Star twinkle effect
            gl.glLineWidth(1);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(x - size, y);
            gl.glVertex2f(x + size, y);
            gl.glVertex2f(x, y - size);
            gl.glVertex2f(x, y + size);
            gl.glEnd();
        }
    }
}