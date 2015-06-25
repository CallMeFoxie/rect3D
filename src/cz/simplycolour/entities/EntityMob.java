package cz.simplycolour.entities;

import java.io.DataInputStream;
import java.io.IOException;

public class EntityMob extends Entity {
    public EntityMob(DataInputStream reader) throws IOException {
        super(reader);
    }
}
