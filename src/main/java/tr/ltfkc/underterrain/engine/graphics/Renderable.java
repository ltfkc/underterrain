package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Vector2f;

public class Renderable {

    private static int renderableCount = 0;

    private TexturedModel texturedModel;
    private Vector2f position;
    private Vector2f scale;
    private String debugName;
    private float angle;

    public Renderable(TexturedModel texturedModel, Vector2f position, Vector2f scale, float angle) {
        this.texturedModel = texturedModel;
        this.position = position;
        this.angle = angle;
        this.scale = scale;
        debugName = "Renderable #" + renderableCount;
        renderableCount++;
    }

    public Renderable(Renderable renderable) {
        texturedModel = renderable.getTexturedModel();
        position = new Vector2f(renderable.getPosition());
        angle = renderable.getAngle();
        scale = new Vector2f(renderable.getScale());
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

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
