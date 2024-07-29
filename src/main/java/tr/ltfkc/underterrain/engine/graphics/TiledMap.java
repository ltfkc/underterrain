package tr.ltfkc.underterrain.engine.graphics;

public class TiledMap {

    private String[] tiles;
    private int width, height;

    public TiledMap(int width, int height) {
        tiles = new String[width * height];
        this.width = width;
        this.height = height;
    }

    public void fill(String tile) {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                setTile(x, y, tile);
            }
        }
    }

    public String getTile(int x, int y) {
        return x < width && x > -1 && y < height && y > -1 ? tiles[y * width + x] : null;
    }

    public void setTile(int x, int y, String tile) {
        if (x < width && x > -1 && y < height && y > -1)
            tiles[y * width + x] = tile;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
