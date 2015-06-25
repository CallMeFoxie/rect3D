package cz.simplycolour.cuboids;

import cz.simplycolour.Plane;
import org.lwjgl.opengl.GL11;

import java.io.DataInputStream;
import java.io.IOException;

public class CuboidBrick extends Cuboid {

    public CuboidBrick(DataInputStream reader) throws IOException {
        super(reader);
    }

    public CuboidBrick(int x, int y, int z, int width, int height, int depth, Plane plane) {
        super(x, y, z, width, height, depth, plane);
    }

    @Override
    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex2d(0, 0);
        GL11.glVertex2d(width, 0);
        GL11.glVertex2d(width, height);
        GL11.glVertex2d(0, height);

        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
