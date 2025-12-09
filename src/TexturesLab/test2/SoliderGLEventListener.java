package TexturesLab.test2;

import TexturesLab.AnimListener;
import TexturesLab.Texture.TextureReader;

import java.awt.event.*;
import java.io.IOException;
import javax.media.opengl.*;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import javax.media.opengl.glu.GLU;

public class SoliderGLEventListener extends AnimListener {
    int countShot=0;
    boolean f=false;
    List<Integer> MonTexture = new ArrayList<>();
    List<Double> Monx=new ArrayList<>();
    List<Double> Mony=new ArrayList<>();
    int spawnDelay=5;
    int spawnCounter=spawnDelay;
    boolean shot=false;
    int indShot=-1;
    List<Double> shotx=new ArrayList<>();
    List<Double> shoty=new ArrayList<>();
    int animationIndex = 0;
    double maxWidth = 300;
    double maxHeight = 200;
    double x = 0, y = 0;
    int direction=0;
    List<Integer> direction1=new ArrayList<>();
    String[] textureNames = {"Man1.png","Man2.png","Man3.png","Man4.png","Balloon1.png","11.png","Back.png"};
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    int[] textures = new int[textureNames.length];
    boolean facingRight = true;

    /*
     5 means gun in array pos
     x and y coordinate for gun
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for(int i = 0; i < textureNames.length; i++){
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i] , true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch( IOException e ) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();
        countShot++;
        spawnCounter++;
        DrawBackground(gl);
        handleKeyPress();
        animationIndex = animationIndex % 4;
        if (spawnCounter >= spawnDelay && Monx.size() < 30) {
            double rand = -maxWidth + (Math.random() * (2 * maxWidth));

            Monx.add(rand);
            Mony.add((double) maxHeight);

            MonTexture.add(5);

            spawnCounter = 0;
        }
        if (shot) {
            for (int i = 0; i < indShot+1;i++) {
                DrawBullet(gl,i);
            }
        }
        for (int i = Mony.size() - 1; i >= 0; i--) {

            double objX = Monx.get(i);

            for (int j = 0; j < shotx.size(); j++) {
                boolean hit = Math.abs((shotx.get(j) - objX) / maxWidth) < 0.15;
                if (hit) {
                    Monx.remove(i);
                    MonTexture.remove(i);
                    f=true;
                    break;
                }
                else f=false;
            }
            if (f) continue;
            Monsters(gl, MonTexture.get(i), i);
        }

        DrawSprite(gl, x, y, animationIndex, 1,direction);

    }
    public void Monsters(GL gl,int index,int i){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);// Turn Blending On
        if (Mony.get(i) < -maxHeight) {
            Mony.remove(i);
            Monx.remove(i);
            MonTexture.remove(i);
            return;
        }

        Mony.set(i,Mony.get(i)-3);
        gl.glPushMatrix();
        gl.glTranslated( Monx.get(i)/maxWidth,Mony.get(i)/maxHeight  , 0);
        gl.glScaled(0.1,0.1,1);
        {
            gl.glBegin(GL.GL_QUADS);
            // Front Face
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, -1.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
            gl.glPopMatrix();

            gl.glDisable(GL.GL_BLEND);

        } // set sprite
    }

    public void DrawSprite(GL gl,double x, double y, int index, float scale,int dir){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);// Turn Blending On
        double X=x/maxWidth,Y=y/maxHeight;
        double angle=0;
        switch (dir){
            case 0: angle=0;break;
            case 1: angle=-90;break;
            case 2: angle=-180;break;
            case 3: angle=90;break;
            case 4: angle=-45;break;
            case 5: angle=45;break;
            case 6: angle=-135;break;
            case 7: angle=135;break;
        }
        gl.glPushMatrix();
        gl.glTranslated( X, Y , 0);
        gl.glScaled(0.1*scale, 0.1*scale, 1);
//        gl.glRotated(angle,0,0,1);
        {
            gl.glBegin(GL.GL_QUADS);
            // Front Face
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, -1.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
            gl.glPopMatrix();

            gl.glDisable(GL.GL_BLEND);
        } // set sprite
    }
    public void DrawBullet(GL gl,int i) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);// Turn Blending On

        switch (direction1.get(i)){
            case 0: shoty.set(i,shoty.get(i)+10);break;
            case 1: shotx.set(i,shotx.get(i)+10);break;
            case 2: shoty.set(i,shoty.get(i)-10);break;
            case 3: shotx.set(i,shotx.get(i)-10);break;
            case 4: shoty.set(i,shoty.get(i)+10);shotx.set(i,shotx.get(i)+10);break;
            case 5: shoty.set(i,shoty.get(i)+10);shotx.set(i,shotx.get(i)-10);break;
            case 6: shoty.set(i,shoty.get(i)-10);shotx.set(i,shotx.get(i)+10);break;
            case 7: shoty.set(i,shoty.get(i)-10);shotx.set(i,shotx.get(i)-10);break;
        }
        gl.glPushMatrix();
        gl.glTranslated( shotx.get(i)/maxWidth, shoty.get(i)/maxHeight , 0);
        gl.glScaled(0.03, 0.03, 1);

        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawBackground(GL gl){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length-1]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }



    public void handleKeyPress() {
//        if (isKeyPressed(KeyEvent.VK_LEFT) && isKeyPressed(KeyEvent.VK_UP)) {
//            if (x > 15-maxWidth) {
//                x-=5;
//            }
//            if (y < maxHeight-20) {
//                y+=5;
//            }
//            direction=5;
//            animationIndex++;
//
//        }else if (isKeyPressed(KeyEvent.VK_RIGHT) && isKeyPressed(KeyEvent.VK_UP)) {
//            if (x < maxWidth-15) {
//                x+=5;
//            }
//            if (y < maxHeight-20) {
//                y+=5;
//            }
//            direction=4;
//            animationIndex++;
//
//        }else if (isKeyPressed(KeyEvent.VK_LEFT) && isKeyPressed(KeyEvent.VK_DOWN)) {
//            if (x > 15-maxWidth) {
//                x-=5;
//            }
//            if (y > 15-maxHeight) {
//                y-=5;
//            }
//            direction=7;
//            animationIndex++;
//
//        } else if (isKeyPressed(KeyEvent.VK_RIGHT) && isKeyPressed(KeyEvent.VK_DOWN)) {
//            if (x < maxWidth-15) {
//                x+=5;
//            }
//            if (y > 15-maxHeight) {
//                y-=5;
//            }
//            direction=6;
//            animationIndex++;
//
//        }

//        else

            if (isKeyPressed(KeyEvent.VK_SPACE)) {
                if(facingRight){
                    if (x < maxWidth) {
                        x-=5;
                        direction=3;
                    }
                    animationIndex++;
                }
        }
//        else if (isKeyPressed(KeyEvent.VK_RIGHT)) {
//
//            if (x < maxWidth-15) {
//                x+=5;
//                direction=1;
//            }
//            animationIndex++;
//        }
//        else if (isKeyPressed(KeyEvent.VK_DOWN)) {
//
//            if (y > 15-maxHeight) {
//                y-=5;
//                direction=2;
//            }
//            animationIndex++;
//        }
//        else if (isKeyPressed(KeyEvent.VK_UP)) {
//            if (y < maxHeight-20) {
//                y+=5;
//                direction=0;
//            }
//            animationIndex++;
//        }if (isKeyPressed(KeyEvent.VK_SPACE)&&countShot>=5) {
//            indShot++;
//            direction1.add(direction);
//            shotx.add(x);
//            shoty.add(y);
//            shot=true;
//            countShot=0;
//        }

    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
    public void direction(GL gl){

    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

}