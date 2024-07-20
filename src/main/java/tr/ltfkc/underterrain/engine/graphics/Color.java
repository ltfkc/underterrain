package tr.ltfkc.underterrain.engine.graphics;

public class Color {

    public static final Color
            WHITE = new Color(1f, 1f, 1f, 1f),
            GRAY = new Color(0.5f, 0.5f, 0.5f, 1f),
            LIGHT_GRAY = new Color(0.8f, 0.8f, 0.8f, 1f),
            BLACK = new Color(0f, 0f, 0f, 1f);

    private float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getRed() {
        return r;
    }

    public void setRed(float r) {
        this.r = r;
    }

    public float getGreen() {
        return g;
    }

    public void setGreen(float g) {
        this.g = g;
    }

    public float getBlue() {
        return b;
    }

    public void setBlue(float b) {
        this.b = b;
    }

    public float getAlpha() {
        return a;
    }

    public void setAlpha(float a) {
        this.a = a;
    }
}
