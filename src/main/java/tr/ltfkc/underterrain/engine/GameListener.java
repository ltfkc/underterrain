package tr.ltfkc.underterrain.engine;

public interface GameListener {

    void create(Game game);

    void render(Game game);

    void resize(Game game, int width, int height);

    void dispose(Game game);
}
