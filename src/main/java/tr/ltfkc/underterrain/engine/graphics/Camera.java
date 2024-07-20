package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    private Vector2f position;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;

    private float angle;
    private boolean needsUpdate;

    public Camera(int width, int height) {
        position = new Vector2f(0, 0);
        angle = 0f;
        projectionMatrix = new Matrix4f().ortho2D((float) -width / 2, (float) width / 2, (float) -height / 2, (float) height / 2);
        needsUpdate = true;
        update();
    }

    public void update() {
        if (needsUpdate) {
            viewMatrix = new Matrix4f();
            viewMatrix.identity();
            viewMatrix.rotate(angle, new Vector3f(0, 0, 1));
            viewMatrix.translate(new Vector3f(-position.x, -position.y, 0));
            needsUpdate = false;
        }
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
        needsUpdate = true;
    }
    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        needsUpdate = true;
    }
}