package cz.simplycolour.entities;

import cz.simplycolour.Plane;
import org.lwjgl.util.vector.Vector2f;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    public static Map<Integer, Class<? extends Entity>> entityTypes;

    static {
        entityTypes = new HashMap<>();
    }

    protected int x, y;
    protected int width, height;
    protected Vector2f momentum;
    protected Plane    plane;

    public Entity(DataInputStream reader) throws IOException {
        x = reader.read();
        y = reader.read();
        width = reader.read();
        height = reader.read();
        momentum = new Vector2f();
        momentum.setX(Float.intBitsToFloat(reader.read()));
        momentum.setY(Float.intBitsToFloat(reader.read()));
        plane = Plane.fromOrdinal(reader.read());
    }

    public Entity(int x, int y, int width, int height, Plane plane) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.plane = plane;
        this.momentum = new Vector2f(0, 0);
    }

    public static void registerEntityType(int number, Class<? extends Entity> model) {
        entityTypes.put(number, model);
    }

    public static Entity load(DataInputStream reader) throws Exception {
        int type = reader.read();
        if (!entityTypes.containsKey(type)) {
            throw new Exception("Unknown model type " + type);
        }

        return entityTypes.get(type).getDeclaredConstructor(DataInputStream.class).newInstance(reader);
    }

    public void render() {
    }

    public void update() {
        y += momentum.getY();
        x += momentum.getX();
    }

    public Plane getPlane() {
        return plane;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2f getMomentum() {
        return momentum;
    }

    public boolean hasGravity() {
        return true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isOnGround() {
        return momentum.getY() == 0;
    }
}
