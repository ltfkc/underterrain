package tr.ltfkc.underterrain;

import org.joml.Vector2f;
import tr.ltfkc.underterrain.engine.Game;
import tr.ltfkc.underterrain.engine.GameListener;
import tr.ltfkc.underterrain.engine.graphics.*;
import tr.ltfkc.underterrain.engine.graphics.Loader;
import tr.ltfkc.underterrain.engine.graphics.Camera;

public class Underterrain implements GameListener {

    Loader loader;
    Camera camera;
    Renderer renderer;
    Renderable renderable;
    Renderable renderable2;

    @Override
    public void create(Game game) {
        camera = new Camera(1200, 675);
        renderer = new Renderer();

        loader = new Loader();

        renderable = new Renderable(new TexturedModel(loader, "/tile.png"), new Vector2f(32, 32), new Vector2f(1f, 1f), 0);
        renderable2 = new Renderable(new TexturedModel(loader, "/test.png"), new Vector2f(0, 0), new Vector2f(1f, 1f), 0);
    }

    @Override
    public void render(Game game) {
        game.clearScreen(Color.BLACK);

        renderer.processRenderable(renderable);
        renderer.processRenderable(renderable2);
        renderer.render(camera);
    }

    @Override
    public void dispose(Game game) {
        loader.dispose();
        renderer.dispose();
    }

    public static void main(String[] args) {
        Game game = new Game(1200, 675, "Underterrain", 60);
        game.setGameListener(new Underterrain());
        game.run();
    }
}
