package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Vector2f;

public class Renderable {

    private static int renderableCount = 0;

    private TexturedModel texturedModel;
    private float x, y, width, height, angle;
    private boolean visible;

    public Renderable(TexturedModel texturedModel, float x, float y, float width, float height, float angle) {
        this.texturedModel = texturedModel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        visible = true;
    }

    public Renderable(Renderable renderable) {
        this(renderable.getTexturedModel(), renderable.getX(), renderable.getY(), renderable.getWidth(), renderable.getHeight(), renderable.getAngle());
    }

    public void render(Renderer renderer) {
        renderer.render(this);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
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
