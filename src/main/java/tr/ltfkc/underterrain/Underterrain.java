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
    Renderable renderable;
    Renderable renderable2;
    Renderable renderable3;

    @Override
    public void create(Game game) {
        camera = new Camera(1200, 675);
        renderer = new Renderer();

        loader = new Loader();

        renderable = new Renderable(new TexturedModel(loader, "/tile.png"), new Vector2f(32, 32), new Vector2f(1f, 1f), 0);
        renderable3 = new Renderable(renderable.getTexturedModel(), new Vector2f(0, 0), new Vector2f(1f, 1f), 0);
        renderable2 = new Renderable(new TexturedModel(loader, "/test.png"), new Vector2f(120, 120), new Vector2f(1f, 1f), 0);
    }

    @Override
    public void render(Game game) {
        renderer.prepare(Color.LIGHT_GRAY);

        if (game.isKeyPressed(GLFW.GLFW_KEY_W))
            renderable.getPosition().add(0, 200f * game.getDelta());
        if (game.isKeyPressed(GLFW.GLFW_KEY_S))
            renderable.getPosition().add(0, -200f * game.getDelta());
        if (game.isKeyPressed(GLFW.GLFW_KEY_D))
            renderable.getPosition().add(200f * game.getDelta(), 0);
        if (game.isKeyPressed(GLFW.GLFW_KEY_A))
            renderable.getPosition().add(-200f * game.getDelta(), 0);

        renderer.put(renderable3);
        renderer.put(renderable);
        renderer.put(renderable2);

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
