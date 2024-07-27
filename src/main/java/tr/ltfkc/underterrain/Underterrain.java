package tr.ltfkc.underterrain;

import org.lwjgl.glfw.GLFW;

import tr.ltfkc.underterrain.game.Game;
import tr.ltfkc.underterrain.game.GameListener;
import tr.ltfkc.underterrain.graphics.*;

public class Underterrain implements GameListener {

    Loader loader;
    Camera camera;
    Renderer renderer;
    TexturedModel model;

    float x, y, speed;

    @Override
    public void create(Game game) {
        camera = new Camera(100, 100 * (float) game.getWindowHeight() / game.getWindowWidth());
        renderer = new Renderer();
        loader = new Loader();

        model = new TexturedModel(loader, "/grass.png");

        x = y = 0;
        speed = 100f;
    }

    @Override
    public void render(Game game) {
        renderer.prepare(Color.DARK_GRAY);
        camera.update();

        game.setWindowTitle("Underterrain | FPS " + game.getFPS());

        y += game.isKeyPressed(GLFW.GLFW_KEY_W) ? speed * game.getDelta() : 0;
        y -= game.isKeyPressed(GLFW.GLFW_KEY_S) ? speed * game.getDelta() : 0;
        x += game.isKeyPressed(GLFW.GLFW_KEY_D) ? speed * game.getDelta() : 0;
        x -= game.isKeyPressed(GLFW.GLFW_KEY_A) ? speed * game.getDelta() : 0;

        speed += game.isKeyPressed(GLFW.GLFW_KEY_UP) ? 100 * game.getDelta() : 0;
        speed -= game.isKeyPressed(GLFW.GLFW_KEY_DOWN) ? 100 * game.getDelta() : 0;

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
        Game game = new Game(16 * 50, 9 * 50, "Underterrain", -1);
        game.setGameListener(new Underterrain());
        game.run();
    }
}
