package tr.ltfkc.underterrain.game;

import tr.ltfkc.underterrain.engine.Game;
import tr.ltfkc.underterrain.engine.GameListener;
import tr.ltfkc.underterrain.engine.graphics.*;
import tr.ltfkc.underterrain.game.elements.World;

public class Survival implements GameListener {

    Renderer renderer;
    Loader loader;
    Camera camera;

    TexturedModel grass;
    TexturedModel stone;

    World world;

    @Override
    public void create(Game game) {
        camera = new Camera(100, 100 * (float) game.getWindowHeight() / game.getWindowWidth());
        renderer = new Renderer();
        loader = new Loader();

        grass = loader.loadTexturedModel("/grass.png");
        stone = loader.loadTexturedModel("/stone.png");

        world = new World();
    }

    @Override
    public void render(Game game) {
        camera.update();
        world.update(game.getDelta(), null);

        renderer.start(Color.DARK_GRAY, camera);
        world.render(renderer);
        renderer.stop();
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
