package cz.simplycolour.data;

import cz.simplycolour.entities.Entity;
import cz.simplycolour.cuboids.Cuboid;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public List<Entity> entities;
    public List<Cuboid> cuboids;

    public Level() {
        entities = new ArrayList<>();
        cuboids = new ArrayList<>();
    }
}
