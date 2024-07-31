package tr.ltfkc.underterrain.game.elements;

import tr.ltfkc.underterrain.engine.graphics.Renderer;

public class Element {

    protected Element parent;
    protected float x, y, angle;
    protected boolean enabled;

    public Element(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;

        enabled = true;
    }

    public Element() {
        this(0f, 0f, 0f);
    }

    public void update(float delta, Element[] family) {
        // Blank
    }

    public void render(Renderer renderer) {
        // Blank
    }

    public Element getParent() {
        return parent;
    }

    protected void setParent(Element parent) {
        this.parent = parent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
