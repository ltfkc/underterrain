package tr.ltfkc.underterrain.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private GameListener gameListener;
    private long glfwWindow;

    public Game(int width, int height, String title) {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);
        glfwShowWindow(glfwWindow);
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public void changeGameListener(GameListener gameListener) {
        this.gameListener.dispose(this);
        this.gameListener = gameListener;
    }

    public void run() {
        if (gameListener == null) {
            throw new IllegalStateException("Cannot run game without GameListener.");
        }

        GL.createCapabilities();
        gameListener.create(this);
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();
            gameListener.render(this);
            glfwSwapBuffers(glfwWindow);
        }
        gameListener.dispose(this);
        glfwDestroyWindow(glfwWindow);
        glfwTerminate();
    }
}
