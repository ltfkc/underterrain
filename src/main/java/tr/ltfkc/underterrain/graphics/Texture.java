package tr.ltfkc.underterrain.graphics;

import org.joml.Vector2f;

public class Texture {

    private int texID;
    private int width, height;

    public Texture(int texID, int width, int height) {
        this.texID = texID;
        this.width = width;
        this.height = height;
    }

    public int getTextureID() {
        return texID;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Vector2f getDimensions() {
        return new Vector2f(width, height);
    }
}