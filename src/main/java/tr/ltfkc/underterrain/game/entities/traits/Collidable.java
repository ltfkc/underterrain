package tr.ltfkc.underterrain.game.entities.traits;

import org.joml.Vector2i;

public interface Collidable {


    void resolveCollision(Vector2i direction);

    float getCollisionRectX();

    float getCollisionRectY();

    float getCollisionRectWidth();

    float getCollisionRectHeight();
}
