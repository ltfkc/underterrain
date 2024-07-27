package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Vector2f;

public class Renderable {

    private static int renderableCount = 0;

    private TexturedModel texturedModel;
    private String debugName;
    private float x, y, width, height, angle;

    public Renderable(TexturedModel texturedModel, float x, float y, float width, float height, float angle) {
        this.texturedModel = texturedModel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        debugName = "Renderable #" + renderableCount;
        renderableCount++;
    }

    public Renderable(Renderable renderable) {
        texturedModel = renderable.getTexturedModel();
        x = renderable.getX();
        y = renderable.getY();
        width = renderable.getWidth();
        height = renderable.getHeight();
        angle = renderable.getAngle();
        debugName = "Renderable #" + renderableCount;
        renderableCount++;
    }

    public void setDebugName(String debugName) {
        this.debugName = debugName;
    }

    public String getDebugName() {
        return debugName;
    }

    public TexturedModel getTexturedModel() {
        return texturedModel;
    }

    public void setTexturedModel(TexturedModel texturedModel) {
        this.texturedModel = texturedModel;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Vector2f getPosition() {
        return new Vector2f(x, y);
    }

    public Vector2f getDimensions() {
        return new Vector2f(width, height);
    }
}
