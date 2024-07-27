package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;

    private float x, y, width, height, angle;
    private boolean needsUpdate;

    public Camera(float width, float height) {
        x = y = angle = 0;
        this.width = width;
        this.height = height;
        update(width, height);
    }

    public void update() {
        if (needsUpdate) {
            viewMatrix = new Matrix4f();
            viewMatrix.identity();
            viewMatrix.rotate(angle, new Vector3f(0, 0, 1));
            viewMatrix.translate(new Vector3f(-x, -y, 0));
            viewMatrix.translate(new Vector3f(-width / 2f, -height / 2f, 0));
            needsUpdate = false;
        }
    }

    public void update(float width, float height) {
        projectionMatrix = new Matrix4f().ortho2D(-width / 2f, width / 2f, -height / 2f, height / 2f);

        this.width = width;
        this.height = height;

        needsUpdate = true;
        update();
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        needsUpdate = true;
    }

    public void setX(float x) {
        this.x = x;
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