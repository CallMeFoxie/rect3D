package cz.simplycolour.logic;

import cz.simplycolour.entities.Entity;
import cz.simplycolour.cuboids.Cuboid;

import java.util.List;

public class Physics {
    public static final double maxGravitySpeed = 1.0f;

    public static void processGravity(List<Entity> entities, List<Cuboid> cuboids) {
        for (Entity entity : entities) {
            processGravity(entity, cuboids);
        }
    }

    public static void processGravity(Entity entity, List<Cuboid> cuboids) {
        if (!entity.hasGravity()) // TODO check whether the entity is on screen possibly?
            return;

        int maxTop = 0;

        for (Cuboid cuboid : cuboids) {
            if (entity.getX() + entity.getWidth() / 2 >= cuboid.getX() && entity.getX() + entity.getWidth() / 2 <= cuboid.getX() + cuboid.getWidth())
                if (cuboid.getY() + cuboid.getHeight() > maxTop && entity.getY() >= cuboid.getHeight() + cuboid.getY())
                    maxTop = cuboid.getY() + cuboid.getHeight();
        }

        if (maxTop < entity.getY()) { // handles when falling through models, too :P
            if (entity.getY() - maxTop < entity.getMomentum().getY() * (-1)) { // make sure we do not jump through the block on edge cases
                entity.setY(maxTop);
                entity.getMomentum().setY(0);
            }
            if (entity.getMomentum().getY() == 0) // stationary, start falling down
                entity.getMomentum().setY(-1.2f);
            else if (entity.getMomentum().getY() > 0) // slow down jumping
                entity.getMomentum().setY(entity.getMomentum().getY() * 0.8f);
            else /*(entity.getMomentum().getY() < maxGravitySpeed)*/ // increase speed of falling
                entity.getMomentum().setY((float) Math.min(entity.getMomentum().getY() * 1.2f, maxGravitySpeed)); // cap it
        }


        if (maxTop == entity.getY())
            entity.getMomentum().setY(0);
    }
}
