package cz.simplycolour.data;

import cz.simplycolour.cuboids.Cuboid;
import cz.simplycolour.entities.Entity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class DataLoader {
    public static final int TYPE_MODEL  = 1;
    public static final int TYPE_ENTITY = 2;

    public static Level loadLevelFromFile(String path) throws Exception {
        Level level = new Level();

        DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(path))));
        while (reader.available() > 0) {
            int number = reader.read();
            switch (number) {
                case TYPE_MODEL:
                    level.cuboids.add(Cuboid.load(reader));
                    break;
                case TYPE_ENTITY:
                    level.entities.add(Entity.load(reader));
                    break;
            }
        }

        return level;
    }
}
