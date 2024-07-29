package tr.ltfkc.underterrain.game.entities.traits;

import org.joml.Vector2i;

public abstract class Collidable {

    public float collisionRectX, collisionRectY, collisionRectWidth, collisionRectHeight;

    public abstract void resolveCollision(Vector2i direction);
}
