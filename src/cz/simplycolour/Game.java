package cz.simplycolour;

import cz.simplycolour.cuboids.Cuboid;
import cz.simplycolour.data.DataLoader;
import cz.simplycolour.data.Level;
import cz.simplycolour.entities.Entity;
import cz.simplycolour.entities.EntityPlayer;
import cz.simplycolour.logic.Physics;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * You were born as a 2D entity. The world around you has got much shorter lifespan and has managed to evolve.
 * Evolve into 3 dimensions. You seem to have not adapted to it fully. But you can jump.
 * Jump really far and high. Into another plane of existence. Another plane of these 3 dimensions.
 * What was wide is now narrow. What was tall can be short. The difference is just a jump away.
 */
public class Game {
    private static Game INSTANCE;

    public  EntityPlayer player;
    private Level        currentLevel;

    public Game() {
        INSTANCE = this;
    }

    public static Game getGame() {
        return INSTANCE;
    }

    public void init() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
            Display.setTitle("rect3D");
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        player = new EntityPlayer(10, 60, Plane.FRONT);
        Registry.init();

        try {
            currentLevel = DataLoader.loadLevelFromFile("data\\levels.dat");
        } catch (Exception e) {
            // TODO some nice screen with info why it crashed and close the game
            e.printStackTrace();
        }
    }

    public void frameUpdate() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    public void run() {
        while (!Display.isCloseRequested()) {
            frameUpdate();

            // LOGIC LOOP
            for (Entity entity : currentLevel.entities)
                entity.update();
            player.update();

            Physics.processGravity(currentLevel.entities, currentLevel.cuboids);
            Physics.processGravity(player, currentLevel.cuboids);

            // RENDERING
            for (Entity entity : currentLevel.entities)
                if (entity.getPlane() == player.getPlane()) // TODO render only if in view
                    entity.render();

            for (Cuboid cuboid : currentLevel.cuboids)
                if (cuboid.getPlane() == player.getPlane()) // TODO render only if in view
                    cuboid.render();

            player.render();

            Display.update();
            Display.sync(60);
        }
    }

    public void close() {
        Display.destroy();
    }
}
