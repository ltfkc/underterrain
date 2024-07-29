package tr.ltfkc.underterrain.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private GameListener gameListener;

    private long glfwWindow;
    private int width, height;
    private String title;
    private boolean vsync = false;
    private double delta;
    private int fps;
    private int fpsCap;
    private boolean isRunning = false;

    public Game(int width, int height, String title, int fpsCap) {
        this.fpsCap = fpsCap;
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();
        
        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }
        this.title = title;
        this.width = width;
        this.height = height;

        glfwSetKeyCallback(glfwWindow, InputManager::keyCallback);
        glfwSetMouseButtonCallback(glfwWindow, InputManager::mouseButtonCallback);
        glfwSetCursorPosCallback(glfwWindow, InputManager::mousePosCallback);
        glfwSetScrollCallback(glfwWindow, InputManager::mouseScrollCallback);
        glfwSetWindowSizeCallback(glfwWindow, this::windowSizeCallback);

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(0);
        glfwShowWindow(glfwWindow);
    }

    private void windowSizeCallback(long window, int width, int height){
        if (width != 0 && height != 0) {
            this.width = width;
            this.height = height;
            gameListener.resize(this, width, height);
            GL11.glViewport(0, 0, width, height);
        }
    }

    public void run() {
        if(isRunning)
            throw new IllegalStateException("Cannot run the game already running.");
        if (gameListener == null)
            throw new IllegalStateException("Cannot run the game without GameListener.");

        isRunning = true;
        GL.createCapabilities();
        gameListener.create(this);
        double last = glfwGetTime();
        int frames = 0;
        double fpsTimer = 0;
        while (!glfwWindowShouldClose(glfwWindow)) {
            double time = glfwGetTime();
            delta += time - last;
            last = time;
            if (fpsCap == -1 || delta > 1d / fpsCap) {
                frames++;
                fpsTimer += delta;
                if(fpsTimer >= 1) {
                    fps = frames;
                    frames = 0;
                    fpsTimer = 0;
                }
                glfwPollEvents();
                gameListener.render(this);
                glfwSwapBuffers(glfwWindow);
                InputManager.update();
                delta = 0;
            }
        }
        terminate();
    }

    private void terminate() {
        gameListener.dispose(this);
        glfwDestroyWindow(glfwWindow);
        glfwTerminate();
    }

    // Utilities

    public void setWindowTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(glfwWindow, title);
    }

    public String getWindowTitle() {
        return title;
    }

    public void setVSync(boolean vsync) {
        this.vsync = vsync;
        glfwSwapInterval(vsync ? 1 : 0);
    }

    public boolean isVSync() {
        return vsync;
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
        this.gameListener.create(this);
    }

    public void close() {
        glfwSetWindowShouldClose(glfwWindow, true);
    }

    public float getDelta() {
        return (float) delta;
    }

    public int getFPS() {
        return fps;
    }

    public void setFPSCap(int fpsCap) {
        this.fpsCap = fpsCap;
    }

    public int getFPSCap() {
        return fpsCap;
    }

    public void setWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
        glfwSetWindowSize(glfwWindow, width, height);
    }

    public int getWindowWidth() {
        return width;
    }

    public int getWindowHeight() {
        return height;
    }
}
