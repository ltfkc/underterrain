package tr.ltfkc.underterrain;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import tr.ltfkc.underterrain.engine.Game;
import tr.ltfkc.underterrain.engine.GameListener;
import tr.ltfkc.underterrain.engine.graphics.*;
import tr.ltfkc.underterrain.engine.graphics.Loader;
import tr.ltfkc.underterrain.engine.graphics.Camera;

public class Underterrain implements GameListener {

    Loader loader;
    Camera camera;
    Renderer renderer;

    @Override
    public void create(Game game) {
        camera = new Camera(1200, 675);
        renderer = new Renderer();
        loader = new Loader();
    }

    @Override
    public void render(Game game) {
        renderer.prepare(Color.LIGHT_GRAY);
        camera.update();

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
