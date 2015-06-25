package cz.simplycolour;

import cz.simplycolour.cuboids.Cuboid;
import cz.simplycolour.cuboids.CuboidBrick;
import cz.simplycolour.entities.Entity;
import cz.simplycolour.entities.EntityPlayer;

public class Registry {
    public static void init() {
        // register all models
        Cuboid.registerModelType(1, CuboidBrick.class);

        Entity.registerEntityType(1, EntityPlayer.class);
    }
}
