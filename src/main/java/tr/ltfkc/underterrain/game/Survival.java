package tr.ltfkc.underterrain.game;

import tr.ltfkc.underterrain.engine.Game;
import tr.ltfkc.underterrain.engine.GameListener;
import tr.ltfkc.underterrain.engine.graphics.*;

public class Survival implements GameListener {

    Renderer renderer;
    Loader loader;
    Camera camera;

    TexturedModel grass;
    TexturedModel stone;

    @Override
    public void create(Game game) {
        camera = new Camera(100, 100 * (float) game.getWindowHeight() / game.getWindowWidth());
        renderer = new Renderer();
        loader = new Loader();

        grass = loader.loadTexturedModel("/grass.png");
        stone = loader.loadTexturedModel("/stone.png");
    }

    @Override
    public void render(Game game) {
        camera.update();

        renderer.prepare(Color.DARK_GRAY);


        renderer.render(camera);
    }

    @Override
    public void resize(Game game, int width, int height) {
        camera.update(100, 100 * (float) game.getWindowHeight() / game.getWindowWidth());
    }

    @Override
    public void dispose(Game game) {
        loader.dispose();
        renderer.dispose();
    }
}
