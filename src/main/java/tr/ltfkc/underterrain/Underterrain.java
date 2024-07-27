package tr.ltfkc.underterrain;

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
    TexturedModel model;

    float x, y;

    @Override
    public void create(Game game) {
        camera = new Camera(game.getWindowWidth(), game.getWindowHeight());
        renderer = new Renderer();
        loader = new Loader();

        model = new TexturedModel(loader, "/grass.png");

        x = y = 0;
    }

    @Override
    public void render(Game game) {
        renderer.prepare(Color.DARK_GRAY);
        camera.update();

        game.setWindowTitle("Underterrain | FPS " + game.getFPS());

        y += game.isKeyPressed(GLFW.GLFW_KEY_W) ? 1 : 0;
        y -= game.isKeyPressed(GLFW.GLFW_KEY_S) ? 1 : 0;
        x += game.isKeyPressed(GLFW.GLFW_KEY_D) ? 1 : 0;
        x -= game.isKeyPressed(GLFW.GLFW_KEY_A) ? 1 : 0;

        renderer.put(model, x, y, model.getTexture().getWidth(), model.getTexture().getHeight(), 0);
        renderer.render(camera);
    }

    @Override
    public void resize(Game game, int width, int height) {
        camera.update(100, 100 * (float) height / width);
    }

    @Override
    public void dispose(Game game) {
        loader.dispose();
        renderer.dispose();
    }

    public static void main(String[] args) {
        Game game = new Game(64 * 15, 9 * 15, "Underterrain", 144);
        game.setGameListener(new Underterrain());
        game.run();
    }
}
