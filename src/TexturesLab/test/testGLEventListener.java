package TexturesLab.test;

import TexturesLab.AnimListener;
import TexturesLab.Texture.TextureReader;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class testGLEventListener extends AnimListener {

    Map<AnimationState, int[]> animationFrame = new HashMap<>();
    AnimationState currState = AnimationState.Idle;

    int animationIndex = 0;
    int frameCounter = 0;
    int animationSpeed = 2;

    int maxWidth = 200;
    int maxHeight = 200;
    int x = maxWidth / 2, y = maxHeight / 2;

    boolean facingRight = true;

    ArrayList<TextureReader.Texture> allTextures = new ArrayList<>();
    ArrayList<Integer> textureId = new ArrayList<>();

    @Override
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1,1,1,1);
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        loadAnimation(gl, AnimationState.Idle,    "src//Assets//knight//idle");
        loadAnimation(gl, AnimationState.Run,     "src//Assets//knight//run");
        loadAnimation(gl, AnimationState.Walk,    "src//Assets//knight//walk");
        loadAnimation(gl, AnimationState.Attack1, "src//Assets//knight//attack1");
        loadAnimation(gl, AnimationState.Attack2, "src//Assets//knight//attack2");
        loadAnimation(gl, AnimationState.Attack3, "src//Assets//knight//attack3");
    }

    public void loadAnimation(GL gl, AnimationState state, String folderPath) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles((d, n) -> n.endsWith(".png"));

        if (files == null || files.length == 0) {
            System.out.println("Error loading folder: " + folderPath);
            return;
        }

        Arrays.sort(files);

        int[] ids = new int[files.length];
        gl.glGenTextures(files.length, ids, 0);

        int index = 0;

        for (File file : files) {
            try {
                TextureReader.Texture t = TextureReader.readTexture(file.getPath(), true);

                allTextures.add(t);
                textureId.add(ids[index]);

                gl.glBindTexture(GL.GL_TEXTURE_2D, ids[index]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA,
                        t.getWidth(), t.getHeight(),
                        GL.GL_RGBA,
                        GL.GL_UNSIGNED_BYTE,
                        t.getPixels()
                );

                index++;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        animationFrame.put(state, ids);
    }

    @Override
    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        handleInput();

        frameCounter++;
        if (frameCounter % animationSpeed == 0) {
            animationIndex = (animationIndex + 1) % animationFrame.get(currState).length;
        }

        DrawSprite(gl);
    }

    public void DrawSprite(GL gl) {

        int[] frames = animationFrame.get(currState);
        int textureID = frames[animationIndex];
        TextureReader.Texture t = allTextures.get(textureId.indexOf(textureID));

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureID);

        float nx = (2f * x / maxWidth) - 1f;
        float ny = (2f * y / maxHeight) - 1f;
        float aspect = (float) t.getWidth() / t.getHeight();

        gl.glPushMatrix();
        gl.glTranslatef(nx, ny, 0);


        float baseScale = 0.1f; // adjust to make sprite bigger/smaller
        gl.glScalef(baseScale * aspect, baseScale, 1f);

        if (!facingRight) {
            gl.glScalef(-1, 1, 1);
        }

        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0, 0); gl.glVertex3f(-1, -1, 0);
        gl.glTexCoord2f(1, 0); gl.glVertex3f( 1, -1, 0);
        gl.glTexCoord2f(1, 1); gl.glVertex3f( 1,  1, 0);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-1,  1, 0);

        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    private void handleInput() {

        boolean moving = false;

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if(x > 0){
                x--;
                facingRight = false;
                currState = AnimationState.Run;
                moving = true;

            }

        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if(x < maxWidth-10){
                x++;
                facingRight = true;
                currState = AnimationState.Run;
                moving = true;

            }
        }
        if (isKeyPressed(KeyEvent.VK_A)) {
            currState = AnimationState.Attack1;
            moving = true;

        }
        if (isKeyPressed(KeyEvent.VK_S)) {
            currState = AnimationState.Attack2;
            moving = true;
        }
        if (isKeyPressed(KeyEvent.VK_D)) {
            currState = AnimationState.Attack3;
            moving = true;

        }

        if (!moving)
            currState = AnimationState.Idle;

        animationIndex %= animationFrame.get(currState).length;
    }

    // Key handling
    BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(KeyEvent event) {
        keyBits.set(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        keyBits.clear(event.getKeyCode());
    }

    @Override public void keyTyped(KeyEvent event) {}
    public boolean isKeyPressed(int keyCode) { return keyBits.get(keyCode); }

    @Override public void reshape(GLAutoDrawable d, int x, int y, int w, int h) {}
    @Override public void displayChanged(GLAutoDrawable d, boolean a, boolean b) {}
}
