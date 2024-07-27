package tr.ltfkc.underterrain.game;

public interface GameListener {

    void create(Game game);

    void render(Game game);

    void resize(Game game, int width, int height);

    void dispose(Game game);
}
