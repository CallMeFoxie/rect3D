package cz.simplycolour.cuboids;

import cz.simplycolour.Plane;
import org.lwjgl.util.Rectangle;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Cuboid {
    public static Map<Integer, Class<? extends Cuboid>> modelTypes;

    static {
        modelTypes = new HashMap<>();
    }

    protected int x, y, z, width, height, depth;
    Plane plane;

    public Cuboid(DataInputStream reader) throws IOException {
        x = reader.readInt();
        y = reader.readInt();
        z = reader.readInt();
        width = reader.readInt();
        height = reader.readInt();
        depth = reader.readInt();
        plane = Plane.fromOrdinal(reader.readInt());
    }


    public Cuboid(int x, int y, int z, int width, int height, int depth, Plane plane) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.plane = plane;
    }

    public static void registerModelType(int number, Class<? extends Cuboid> model) {
        modelTypes.put(number, model);
    }

    public static Cuboid load(DataInputStream reader) throws Exception {
        int type = reader.readInt();
        if (!modelTypes.containsKey(type)) {
            throw new Exception("Unknown model type " + type);
        }

        return modelTypes.get(type).getDeclaredConstructor(DataInputStream.class).newInstance(reader);
    }

    public void save(BufferedWriter writer) throws IOException {
        writer.write(x);
        writer.write(y);
        writer.write(z);
        writer.write(width);
        writer.write(height);
        writer.write(depth);
        writer.write(plane.toOrdinal());
    }

    public void render() {

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

    public int getZ() {
        return z;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public Rectangle getRectangleForPlane(Plane plane) {
        switch (plane) {
            case BACK:
            case FRONT:
                return new Rectangle(x, y, width, height);
            case BOTTOM:
            case TOP:
                return new Rectangle(x, z, width, depth);
            case LEFT:
            case RIGHT:
                return new Rectangle(z, y, depth, height);
        }

        return null;
    }
}
