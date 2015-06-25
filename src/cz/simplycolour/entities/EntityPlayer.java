package cz.simplycolour.entities;

import cz.simplycolour.Plane;
import org.lwjgl.opengl.GL11;

public class EntityPlayer extends Entity {
    private static final int width  = 32;
    private static final int height = 48;

    public EntityPlayer(int x, int y, Plane plane) {
        super(x, y, width, height, plane);
    }

    @Override
    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, 0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2d(0, 0);
        GL11.glColor3f(0.0f, 1f, 0f);
        GL11.glVertex2d(width, 0);
        GL11.glColor3f(1f, 0f, 0f);
        GL11.glVertex2d(width, height);
        GL11.glColor3f(0f, 0f, 1f);
        GL11.glVertex2d(0, height);

        GL11.glEnd();

        GL11.glPopMatrix();
    }
}
