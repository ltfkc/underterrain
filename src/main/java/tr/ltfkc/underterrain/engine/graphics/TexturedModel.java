package tr.ltfkc.underterrain.engine.graphics;

public class TexturedModel {

    private RawModel rawModel;
    private Texture texture;

    public TexturedModel(RawModel model, Texture texture){
        this.rawModel = model;
        this.texture = texture;
    }

    public TexturedModel(Loader loader, String texturePath){
        texture = loader.loadTexture(texturePath);
        float[] vertices = {-0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0};
        int[] indices = {0, 1, 3, 3, 1, 2};
        float[] textureCoords = {0, 0, 0, 1, 1, 1, 1, 0};
        rawModel = loader.loadToVAO(vertices, textureCoords, indices);
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public Texture getTexture() {
        return texture;
    }

}